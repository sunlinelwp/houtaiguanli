package cn.sunline.client;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sunline.flow.base.util.JsonUtils;

import cn.sunline.client.dao.ApSysEncap;
import cn.sunline.client.dao.ApSysEncapRepository;
import cn.sunline.client.dao.ApSysServs;
import cn.sunline.client.dao.ApSysServsRepository;
import cn.sunline.client.dao.ApSysTrans;
import cn.sunline.client.dao.ApSysTransRepository;
import cn.sunline.exception.SumpException;
import cn.sunline.file.DataWriter;
import cn.sunline.file.FileUtil;
import cn.sunline.message.Message;
import cn.sunline.message.MessageConstants;
import cn.sunline.message.dao.ApSysMsg;
import cn.sunline.message.dao.ApSysMsgRepository;
import cn.sunline.utils.DateTools;
import cn.sunline.utils.JsonPktUtil;

@Service("client")
public class ClientImpl implements Client {

	private static Logger logger = LoggerFactory.getLogger(ClientImpl.class);

	@Resource
	private ApSysTransRepository apSysTransRepository;

	@Resource
	private ApSysServsRepository apSysServsRepository;

	@Resource
	private ApSysEncapRepository apSysEncapRepository;

	@Resource
	private ApSysMsgRepository apSysMsgRepository;

	@Resource
	private Message message;

	@Resource
	private LttsConn tcpSocket;

	@Override
	public Map<String, Object> callClient(String prcscd,
			Map<String, Object> reqData) {

		Map<String, Object> rspData = new ConcurrentHashMap<String, Object>();
		// 交易码不能为空
		if (StringUtils.isEmpty(prcscd))
			throw new SumpException("1100", "交易码不能为空");

		ApSysTrans trans = apSysTransRepository.findOne(prcscd);

		// 判断交易接口状态，如果无效就直接返回成功
		if ("N".equals(trans.getTransStatus())) {
			rspData.put("errMsg", "接口关闭!");
			rspData.put("errCode", "99");
			return rspData;
		}

		// 自动生成交易流水
		reqData.put(MessageConstants.TRANSQ, 1);
		// 自动生成交易日期
		reqData.put(MessageConstants.TRANDT,
				DateTools.getNow(DateTools.YYYYMMDD));
		// 自动生成交易时间
		reqData.put(MessageConstants.TRANTM, DateTools.getNow(DateTools.HHMMSS));
		// 交易处理码
		reqData.put(MessageConstants.PRCSCD, prcscd);
		
		//大额提现发送短信专用
		// 服务代码
		reqData.put(MessageConstants.SERVICECODE, prcscd);
		// 服务渠道
		reqData.put(MessageConstants.SERVICECHANNEL, "01");
		/**
		 * 转换请求报文
		 */

		ApSysEncap encap = apSysEncapRepository.findOne(trans.getEncapCd());

		ApSysMsg msg = apSysMsgRepository.findOne(encap.getReqMsg());

		// 封装发送报文体
		String sendBodyMsg = message.encap(msg.getMsgDefine(),
				encap.getReqMsg(), reqData);

		logger.debug("请求报文体==============" + sendBodyMsg);
		/**
		 * 调用通讯客户端
		 */
		ApSysServs serv = apSysServsRepository.findOne(trans.getServiceCd());
		try {
			// 请求数据字符集转换
			byte[] sendBody = sendBodyMsg.getBytes(serv.getEncode());
			// 增加报文头
			String sendHeadMsg = getHeadMsg(sendBody.length, msg, reqData);
			byte[] sendHead = sendHeadMsg.getBytes(serv.getEncode());
			// 将报文头和报文体拷贝到发送数组中
			byte[] sendMsg = new byte[sendBody.length + msg.getHeadLength()];
			System.arraycopy(sendHead, 0, sendMsg, 0, msg.getHeadLength());
			System.arraycopy(sendBody, 0, sendMsg, msg.getHeadLength(),
					sendBody.length);
			logger.info("请求报文==============" + sendHeadMsg + sendBodyMsg);
			// 调用服务
			LttsConn conn = null;
			switch (serv.getServiceType()) {
			case "1":
				// Socket通讯
				conn = tcpSocket;
				break;
			default:
				throw new SumpException("1130", "暂不支持的服务类型"
						+ serv.getServiceType());
			}

			rspData = conn.sendMsg(sendMsg, encap.getRspMsg(), serv);
			
			//统一处理错误码,0000或空转换成0000为正确码
			String errorcd = rspData.get(MessageConstants.ERRORCD).toString();
			if("0000".equals(errorcd) || StringUtils.isEmpty(errorcd)) {
				rspData.put(MessageConstants.ERRORCD, "0000");
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new SumpException("1130", "不支持的字符集" + serv.getEncode());
		}

		logger.info("反馈报文==============" + rspData);
		return rspData;
	}

	/**
	 * 封装报文头
	 * 
	 * @param bodyLenth
	 *            报文体长度
	 * @param msg
	 *            请求报文定义
	 * @param reqData
	 *            请求数据
	 * @return 返回报文头信息
	 */
	private String getHeadMsg(int bodyLenth, ApSysMsg msg,
			Map<String, Object> reqData) {
		// 报文头长度
		int headLength = msg.getHeadLength();
		int msgLength = 0;
		// 报文长度定义
		String msgLen = msg.getMsgLen();
		if ("1".equals(msgLen)) {
			// 报文体
			msgLength = bodyLenth;
		} else if ("2".equals(msgLen)) {
			// 报文体+报文头
			msgLength = headLength + bodyLenth;
		} else {
			throw new SumpException("1203", "报文长度定义参数未实现" + msgLen);
		}
		reqData.put(MessageConstants.HEADLN, msgLength);

		logger.debug("======headMsg======" + msg.getHeadMsg());

		ApSysMsg headMsg = apSysMsgRepository.findOne(msg.getHeadMsg());

		logger.debug("======msgDefine======" + headMsg.getMsgDefine());

		return message.encap(headMsg.getMsgDefine(), msg.getHeadMsg(), reqData);
	}

	@Override
	@Transactional
	public void readFile(String prcscd, DataWriter w,String inputDate) {
		// 交易码不能为空
		if (StringUtils.isEmpty(prcscd))
			throw new SumpException("1100", "交易码不能为空");

		ApSysTrans trans = apSysTransRepository.findOne(prcscd);

		// 判断交易接口状态，如果无效就直接返回成功
		if ("N".equals(trans.getTransStatus())) {
			return;
		}
		StringBuilder fileName = new StringBuilder()
				.append(specialCharater(trans.getFilePrefix(),inputDate)).append(prcscd)
				.append(specialCharater(trans.getFileSufix(),inputDate)).append(trans.getFileType());

		// 获取远程文件
		ApSysServs serv = apSysServsRepository.findOne(trans.getServiceCd());
		remoteFile(serv, "D", trans.getFilePath(), fileName.toString());

		// 处理文件数据
		if(trans.getBeginLine() == null){
			trans.setBeginLine(1);//默认从第二行开始
		}
		FileUtil file = new FileUtil(fileName.toString(), trans.getFilePath(),
				trans.getFileSplit(),serv.getEncode(),trans.getDealCnt(),trans.getCalFlag(),trans.getCalCharAt(),trans.getBeginLine());
		file.readFile(w,inputDate);

	}

	/**
	 * 处理远程文件
	 * @param serv 服务参数
	 * @param oper 操作码
	 * @param basePath 本地路径
	 * @param fileName 文件名称
	 */
	private void remoteFile(ApSysServs serv, String oper, String basePath,
			String fileName) {
		if ("2".equals(serv.getServiceType())) {
			// 本地服务，文件直接在本地不用去远程下载和上传
			return;
		} else if ("3".equals(serv.getServiceType())) {
			// ftp协议
		} else if ("4".equals(serv.getServiceType())) {
			// sftp协议

		}

		if ("U".equals(oper)) {
			// 上传文件

		} else if ("D".equals(oper)) {
			// 下载文件

		} else {
			logger.error("远程文件处理操作方法未知" + oper);
			throw new SumpException("1104", "远程文件处理操作方法未知" + oper);
		}
	}
	
	/**
	 * 处理特殊含义字符
	 * @param special 特殊字符
	 * @return 返回字符
	 * 
	 * 如果没有匹配到特殊字符就原值返回
	 */
	private String specialCharater(String special,String inputDate){
		
		String specialCharater = special;
		
		if(StringUtils.isEmpty(specialCharater))
			return "";
		
		if("date8bit".equals(special)){
			specialCharater = DateTools.getNow(DateTools.YYYYMMDD);
		}else if("dateinput".equals(special)){
			specialCharater = inputDate;
		}
			
		return specialCharater;
			 
	}

	@Override
	public Map<String, Object> callClientFlow(String prcscd,Map<String, Object> reqData) {
		// 交易码不能为空
				if (StringUtils.isEmpty(prcscd))
					throw new SumpException("1100", "交易码不能为空");

				ApSysTrans trans = apSysTransRepository.findOne(prcscd);
		Map<String, Object> rspData = new ConcurrentHashMap<String, Object>();
		
		ApSysServs serv = apSysServsRepository.findOne(trans.getServiceCd());
		// 调用服务
		LttsConn conn = null;
		switch (serv.getServiceType()) {
		case "1":
			// Socket通讯
			conn = tcpSocket;
			break;
		default:
			throw new SumpException("1130", "暂不支持的服务类型"
					+ serv.getServiceType());
		}
		rspData = conn.sendMsg(	JsonPktUtil.puck(reqData),  serv);
		
		//统一处理错误码,0000或空转换成0000为正确码
		String errorcd = rspData.get(MessageConstants.ERRORCD).toString();
		if("0000".equals(errorcd) || StringUtils.isEmpty(errorcd)) {
			rspData.put(MessageConstants.ERRORCD, "0000");
		}

		
		return rspData;
	}
}
