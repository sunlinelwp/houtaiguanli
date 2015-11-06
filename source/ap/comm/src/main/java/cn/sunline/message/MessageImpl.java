package cn.sunline.message;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.sunline.exception.SumpException;

@Service("message")
public class MessageImpl implements Message {

	private static Logger logger = LoggerFactory.getLogger(MessageImpl.class);

	@Resource
	private Parse parseJSON;

	@Resource
	private Parse parseFix;

	@Resource
	private Parse parseXML;

	@Resource
	private Parse parseSplit;

	/**
	 * 根据报文类型返回解析实体
	 * 
	 * @param msgDefine
	 *            报文类型
	 * @return 返回解析类型
	 */
	private Parse getParse(String msgDefine) {
		Parse p = null;
		switch (msgDefine) {
		case "S":
			// 分割符报文
			p = parseSplit;
			break;
		case "D":
			// 定长报文
			p = parseFix;
			break;
		case "X":
			// xml报文
			p = parseXML;
			break;
		case "J":
			// json报文
			logger.debug("JSON报文实例==============" + parseJSON);
			p = parseJSON;
			break;
		default:
			throw new SumpException("1130", "不支持报文格式" + msgDefine);
		}

		return p;
	}

	/**
	 * 将数据转换成字符串
	 * 
	 * @param msgDefine
	 *            报文格式
	 * @param msgCd
	 *            报文号
	 * @param data
	 *            数据映射
	 * @return 字符串数据
	 */
	@Override
	public String encap(String msgDefine, String msgCd, Map<String, Object> data) {
		return getParse(msgDefine).encap(msgCd, data);
	}

	/**
	 * 将字符串转换成数据
	 * 
	 * @param msgCd
	 *            报文号
	 * @param msg
	 *            字符串数据
	 * @return 数据映射
	 */
	@Override
	public Map<String, Object> unencap(String msgDefine, String msgCd,
			String msg) {
		return getParse(msgDefine).unencap(msgCd, msg);
	}
}
