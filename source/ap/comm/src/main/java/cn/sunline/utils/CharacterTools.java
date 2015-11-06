package cn.sunline.utils;

/**
 * 字符工具类
 * 
 * @author cuijia
 *
 */
public class CharacterTools {

	/**
	 * 特殊字符转义处理
	 * @param c
	 * @return
	 */
	public static String convertChar(String c) {
		String ret = null;
		switch (c) {
		case "|":
			ret = "\\|";
			break;
		default:
			ret = c;
		}

		return ret;
	}
}
