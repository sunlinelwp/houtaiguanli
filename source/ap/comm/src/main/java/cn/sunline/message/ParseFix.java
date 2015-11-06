package cn.sunline.message;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.sunline.exception.SumpException;
import cn.sunline.message.dao.ApSysColumn;
import cn.sunline.message.dao.ApSysColumnServiceImpl;

/**
 * 定长报文解析
 * @author Cuijia
 *
 */
@Service("parseFix")
public class ParseFix implements Parse {

	private static Logger logger = LoggerFactory
			.getLogger(ParseFix.class);
	
	@Resource
	private ApSysColumnServiceImpl apSysColumnRepository;

	@Override
	public String encap(String msgCd, Map<String, Object> data) {
		StringBuilder reqStr = new StringBuilder();

		List<ApSysColumn> columns = apSysColumnRepository
				.findByMsgCdRoot(msgCd);

		for (ApSysColumn obj : columns) {
			/* 取值 */
			String val = ColumnUtil.getValue(data, obj);
			Long colLength = Long.valueOf(obj.getColumnLength());

			if(colLength <= 0)
				throw new SumpException("定长报文，字段长度必须大于零");
			
			Long valLength = 0l;
			if (StringUtils.isNotEmpty(val)) {
				valLength = Long.valueOf(val.length());
			} else {
				// 为空时，返回值是null,对于定长格式，转化为空
				val = "";
			}
			/* 确定是否要补齐字符:val为空或者小于定义的长度 */
			if (colLength > valLength) {
				String polish = obj.getPolishType().toString();
				String polisChar = obj.getPolishChar();
				if(StringUtils.isEmpty(polisChar))
					polisChar = " ";
				StringBuffer valBuff = new StringBuffer();
				for (int i = 0; i < colLength - valLength; i++) {
					valBuff.append(polisChar);
				}
				
				if ("1".equals(polish)) {
					// 左补齐 
					reqStr.append(valBuff).append(val);
					
				} else {
					// 默认右补齐 
					reqStr.append(val).append(valBuff);
				}
			} else {
				reqStr.append(val);
			}
		}

		return reqStr.toString();
	}

	@Override
	public Map<String, Object> unencap(String msgCd, String msg) {
		Map<String, Object> rspStr = new ConcurrentHashMap<String, Object>();
		List<ApSysColumn> columns = apSysColumnRepository
				.findByMsgCdRoot(msgCd);
		
		logger.debug("解析报文===========" + msg);
		
		String tmpStr = msg;
		for (ApSysColumn obj : columns) {
			/* 取值 */
			// 字段长度
			Long colLen = Long.valueOf(obj.getColumnLength());
			int len = colLen.intValue();
			
			logger.debug("字段长度===========" + len);
			
			String colValue = tmpStr.substring(0, len).trim();
			
			logger.debug("字段值===========" + colValue);
			
			rspStr.put(obj.getColumnMapping(), ColumnUtil.putValue(colValue, obj));
			
			if(len >= tmpStr.length())
				break;
			tmpStr = msg.substring(len, tmpStr.length());
		}

		return rspStr;
	}
}
