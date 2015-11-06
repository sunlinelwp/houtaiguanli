package cn.sunline.message;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.sunline.exception.SumpException;
import cn.sunline.message.dao.ApSysColumn;
import cn.sunline.utils.DateTools;

/**
 * 处理字段数据
 * 
 * @author Cuijia
 *
 */
public class ColumnUtil {

	private static Logger logger = LoggerFactory.getLogger(ColumnUtil.class);

	/**
	 * 从Map数据中获取对应数据，并进行类型和格式处理
	 * 
	 * @param data
	 *            Map数据
	 * @param obj
	 *            字段属性
	 * @return 返回数值
	 */
	public static String getValue(Map<String, Object> data, ApSysColumn obj) {
		
		logger.debug("======getValue========" + data);
		
		String val = "";
		/* 取值 */
		String pro = obj.getColumnMapping();
		/* 如果没有匹配字段，则获取默认值 */
		if (StringUtils.isEmpty(pro)) {
			val = obj.getDefaultValue();
		} else {

			logger.debug("======map kep========" + pro);

			Object value = data.get(pro);
			// 如果有匹配字段，请求变量data未传值，则不作处理，默认空值到请求报文
			if (value != null) {
				String valType = obj.getColumnType().toString();
				if ("F".equals(valType)) {
					// 浮点型
					BigDecimal bval = (BigDecimal) value;
					logger.debug("======= 浮点型 =======" + bval);
					val = bval.toString();
				} else if ("Z".equals(valType)) {
					// 整型
					Long lval = Long.valueOf(value.toString());
					/* 转换成要求的进制数 */
					// TODO val = NumberUtils.transferIntegerNumber(obj
					// .getValueDigit(), lval);
					val = lval.toString();
				} else if ("S".equals(valType)) {
					// 字符
					logger.debug("map字段值=========="+value);
					val = value.toString();
				} else if ("D".equals(valType)) {
					// 日期
					Date tval = (Date) value;
					/* 转换日期格式 */
					if ("1".equals(obj.getDatePattern())) {
						// yyyyMMdd
						val = DateTools.Date2String(tval, DateTools.YYYYMMDD);
					} else if ("2".equals(obj.getDatePattern())) {
						// yyyy-MM-dd
						val = DateTools.Date2String(tval, DateTools.YYYY_MM_DD);
					} else {
						// 没有配置使用默认的yyyyMMdd
						val = DateTools.Date2String(tval, DateTools.YYYYMMDD);
					}
				} else {
					throw new SumpException("1400", "没有匹配的字段类型");
				}

			}
		}

		return val;
	}

	/**
	 * 赋值类型和格式转换
	 * 
	 * @param colStr
	 * @param obj
	 * @return
	 */
	public static Object putValue(String colStr, ApSysColumn obj) {

		Object val = "";
		/* 取值 */
		/* 如果没有匹配字段，则获取默认值 */
		if (StringUtils.isEmpty(colStr)) {
			val = obj.getDefaultValue();
		} else {
			String valType = obj.getColumnType().toString();
			if ("F".equals(valType)) {
				val = Double.valueOf(colStr);
			} else if ("Z".equals(valType)) {
				/* 转换成要求的进制数 */
				// TODO val = NumberUtils.(obj
				// .getValueDigit(), colStr);
				val = Long.valueOf(colStr);
			} else if ("S".equals(valType)) {
				val = colStr;
			} else if ("D".equals(valType)) {
				try {
					/* 转换日期格式 */
					if ("1".equals(obj.getDatePattern())) {
						// yyyyMMdd
						val = DateUtils.parseDate(colStr, DateTools.YYYYMMDD);
					} else if ("2".equals(obj.getDatePattern())) {
						// yyyy-MM-dd
						val = DateUtils.parseDate(colStr, DateTools.YYYY_MM_DD);
					} else {
						// 没有配置使用默认的yyyyMMdd
						val = DateUtils.parseDate(colStr, DateTools.YYYYMMDD);
					}
				} catch (ParseException e) {
					throw new SumpException("1201", "日期类型数值获取并转换失败");
				}
			} else {
				throw new SumpException("1202", "没有匹配的字段类型");
			}
		}

		return val;
	}
}
