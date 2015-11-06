package cn.sunline.utils;

import java.math.BigDecimal;

/**
 * 数字工具类
 * @author Cuijia
 *
 */
public class NumberTools {

	/**
	 * 将千分位金额字符类型转成数字类型
	 * @param amount 金额
	 * @return 金额
	 */
	public static BigDecimal string2BigDecimalMill(String amount){
		return new BigDecimal(amount).divide(BigDecimal.valueOf(100));
	}
}
