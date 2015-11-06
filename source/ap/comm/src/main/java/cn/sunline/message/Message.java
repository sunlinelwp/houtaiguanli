package cn.sunline.message;

import java.util.Map;

/**
 * 数据格式转换
 * 
 * @author Cuijia
 *
 */
public interface Message {

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
	String encap(String msgDefine, String msgCd, Map<String, Object> data);

	/**
	 * 将字符串转换成数据
	 * 
	 * @param msgCd
	 *            报文号
	 * @param msg
	 *            字符串数据
	 * @return 数据映射
	 */
	Map<String, Object> unencap(String msgDefine, String msgCd, String msg);

}
