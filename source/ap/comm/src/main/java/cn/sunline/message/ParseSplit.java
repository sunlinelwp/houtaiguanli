package cn.sunline.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.sunline.message.dao.ApSysColumn;
import cn.sunline.message.dao.ApSysColumnServiceImpl;
import cn.sunline.message.dao.ApSysMsg;
import cn.sunline.message.dao.ApSysMsgRepository;

@Service("parseSplit")
public class ParseSplit implements Parse {

	private String split;

	private String[] colArray;

	@Resource
	private ApSysColumnServiceImpl apSysColumnRepository;

	@Resource
	private ApSysMsgRepository apSysMsgRepository;

	@SuppressWarnings("unchecked")
	@Override
	public String encap(String msgCd, Map<String, Object> data) {
		StringBuffer reqBuff = new StringBuffer();

		List<ApSysColumn> columns = apSysColumnRepository
				.findByMsgCdRoot(msgCd);

		ApSysMsg msg = apSysMsgRepository.findOne(msgCd);
		this.split = msg.getSplitChar();

		for (ApSysColumn obj : columns) {
			/* 循环要做处理 */
			if ("Y".equals(obj.getCyclingFlag())) {
				List<Map<String, Object>> list = (List<Map<String, Object>>) data
						.get(obj.getColumnMapping());
				reqBuff.append(splitCyclingColumn(list, obj.getColumnCd(),
						msgCd));
			} else {
				/* 分隔符 */
				String val = ColumnUtil.getValue(data, obj);
				if (StringUtils.isEmpty(val)) {
					// 为空时，返回值是null,对于定长格式，转化为空
					val = "";
				}
				reqBuff.append(obj.getColumnCd()).append("=").append(val)
						.append(this.split);
			}
		}

		return reqBuff.toString();
	}

	@Override
	public Map<String, Object> unencap(String msgCd, String msg) {
		Map<String, Object> rspStr = new ConcurrentHashMap<String, Object>();

		List<ApSysColumn> columns = apSysColumnRepository
				.findByMsgCdRoot(msgCd);

		for (ApSysColumn obj : columns) {
			String colValue = "";
			// 循环节点
			if ("Y".equals(obj.getCyclingFlag())) {
				if (colArray == null) {
					colArray = msg.split("1:|2:");
				}
				Map<String, Object> colMap = splitCyclingColumns(
						obj.getColumnCd(), msgCd);
				rspStr.putAll(colMap);
			} else {
				String colCd = obj.getColumnCd();
				colValue = splitColumn(msg, colCd);
				rspStr.put(obj.getColumnMapping(),
						ColumnUtil.putValue(colValue, obj));
			}
		}

		return rspStr;
	}

	@SuppressWarnings("unchecked")
	private String splitCyclingColumn(List<Map<String, Object>> reqList,
			String columnCd, String msgCd) {
		StringBuffer reqBuff = new StringBuffer();

		List<ApSysColumn> cols = apSysColumnRepository
				.findChildByMsgCdColumnCd(columnCd, msgCd);

		for (Map<String, Object> req : reqList) {
			reqBuff.append("1:=|2:");
			for (ApSysColumn obj : cols) {

				/* 循环要做处理 */
				if ("Y".equals(obj.getCyclingFlag())) {
					List<Map<String, Object>> list = (List<Map<String, Object>>) req
							.get(obj.getColumnMapping());
					reqBuff.append(splitCyclingColumn(list, obj.getColumnCd(),
							msgCd));
				} else {
					/* 分隔符 */
					String val = ColumnUtil.getValue(req, obj);
					if (!StringUtils.isEmpty(val)) {
						reqBuff.append(obj.getColumnCd()).append("=")
								.append(val).append(this.split);
					}
				}
			}
		}

		return reqBuff.toString();
	}

	private Map<String, Object> splitCyclingColumns(String columnCd,
			String msgCd) {
		Map<String, Object> rspStr = new ConcurrentHashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<ApSysColumn> cols = apSysColumnRepository
				.findChildByMsgCdColumnCd(columnCd, msgCd);
		for (String tmpStr : colArray) {
			Map<String, Object> rspMap = new HashMap<String, Object>();
			for (ApSysColumn obj : cols) {
				String val = splitColumn(tmpStr, obj.getColumnCd());
				rspMap.put(obj.getColumnMapping(),
						ColumnUtil.putValue(val, obj));
			}
			list.add(rspMap);
		}
		return rspStr;
	}

	/**
	 * 解析分隔符报文单个字段
	 * 
	 * @param rsp
	 * @param match
	 * @return
	 */
	private String splitColumn(String rsp, String match) {
		int idxBegin = rsp.indexOf(match + "=") + match.length() + 1;
		String tmpStr = rsp.substring(idxBegin, rsp.length() - 1);
		int idxEnd = tmpStr.indexOf(this.split);
		return tmpStr.substring(0, idxEnd);
	}
}
