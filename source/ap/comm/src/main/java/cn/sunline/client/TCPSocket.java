package cn.sunline.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.sunline.client.dao.ApSysServs;
import cn.sunline.exception.SumpException;
import cn.sunline.message.Message;
import cn.sunline.message.MessageConstants;
import cn.sunline.message.dao.ApSysMsg;
import cn.sunline.message.dao.ApSysMsgRepository;
import cn.sunline.utils.JsonPktUtil;

/**
 * Socket通讯实现
 * 
 * @author Cuijia
 *
 */
@Service("tcpSocket")
public class TCPSocket implements LttsConn{
	private static Logger logger = LoggerFactory
			.getLogger(TCPSocket.class);

	@Resource
	private ApSysMsgRepository apSysMsgRepository;
	
	@Resource
	private Message message;

	@Override
	public Map<String, Object> sendMsg(byte[] sendBuffer, String rspMsgCd,
			ApSysServs serv) {

		Socket sock = null;
		OutputStream os = null;
		InputStream is = null;
		try {
			sock = new Socket();
			sock.connect(new InetSocketAddress(serv.getServiceIp(), serv.getServicePort()));
			// 设置InputStream上调用 read()阻塞超时时间5秒
			sock.setSoTimeout(ClientConstants.TIMEOUT);
			sock.setReceiveBufferSize(ClientConstants.SO_RCVBUF);
			// 关闭Nagle算法.立即发包
			sock.setTcpNoDelay(true);
			// 发送数据 
			os = sock.getOutputStream();
			os.write(sendBuffer);
			os.flush();
			// 接收数据 
			is = sock.getInputStream();
			
			//反馈报文
			logger.debug("======rspMsgCd=======" + rspMsgCd);
			ApSysMsg msg = apSysMsgRepository.findOne(rspMsgCd);
			logger.debug("======msg=======" + msg);
			//处理反馈报文头
			byte[] recvHeadBuffer = new byte[msg.getHeadLength()];
			is.read(recvHeadBuffer);

			String recvHeadMsg = new String(recvHeadBuffer, serv.getEncode());

			ApSysMsg headMsg = apSysMsgRepository.findOne(msg.getHeadMsg());
			Map<String, Object> rspData = message.unencap(
					headMsg.getMsgDefine(), msg.getHeadMsg(), recvHeadMsg);

			//处理反馈报文体
			int msgLen = Integer.valueOf(rspData.get(MessageConstants.HEADLN)
					.toString());
			int bodyLen = 0;
			//报文长度定义
			String msgBodyLen = msg.getMsgLen();
			if("1".equals(msgBodyLen)){
				//报文体
				bodyLen = msgLen;
			}else if("2".equals(msgBodyLen)){
				//报文体+报文头
				bodyLen = msgLen - msg.getHeadLength();
			}else{
				throw new SumpException("1203","报文长度定义参数未实现" + msgBodyLen);
			}

			byte[] recvBodyBuffer = new byte[bodyLen];

			is.read(recvBodyBuffer);
			
			logger.info("反馈报文长度=================" + recvBodyBuffer.length);
			String recvBodyMsg = new String(recvBodyBuffer, serv.getEncode());

			logger.info("反馈报文=================" + recvBodyMsg);
			Map<String, Object> rspBodyData = message.unencap(
					msg.getMsgDefine(), rspMsgCd, recvBodyMsg);

			//返回数据
			rspData.putAll(rspBodyData);

			return rspData;
		} catch (UnknownHostException e) {
			//e.printStackTrace();
			throw new SumpException("1110", "未知主机");
		} catch (IOException e) {
			//e.printStackTrace();
			throw new SumpException("1120", "通讯超时");
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			if (sock != null) {
				try {
					sock.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

	}

	@Override
	public Map<String, Object> sendMsg(byte[] sendBuffer, ApSysServs serv) {
		Socket sock = null;
		OutputStream os = null;
		InputStream is = null;
		sock = new Socket();
		Map<String, Object> resMap =new HashMap<String, Object>();	
		try {
		sock.connect(new InetSocketAddress(serv.getServiceIp(), serv.getServicePort()));
		// 设置InputStream上调用 read()阻塞超时时间5秒
		sock.setSoTimeout(ClientConstants.TIMEOUT);
		sock.setReceiveBufferSize(ClientConstants.SO_RCVBUF);
		// 关闭Nagle算法.立即发包
		sock.setTcpNoDelay(true);
		// 发送数据 
		os = sock.getOutputStream();
		os.write(sendBuffer);
		os.flush();
		// 接收数据 
		is = sock.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				"UTF-8"));
		String line = null;
		String content=null;
		while ((line = reader.readLine()) != null) {
			content += line;
		}
		
		resMap=JsonPktUtil.unpuck(content.getBytes("UTF-8"));
		} catch (UnknownHostException e) {
			//e.printStackTrace();
			throw new SumpException("1110", "未知主机");
		} catch (IOException e) {
			//e.printStackTrace();
			throw new SumpException("1120", "通讯超时");
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			if (sock != null) {
				try {
					sock.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return resMap;
	}
}
