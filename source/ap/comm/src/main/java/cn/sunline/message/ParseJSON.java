package cn.sunline.message;

import java.io.IOException;
import java.util.ArrayList;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON封装解析类
 * 
 * @author Cuijia
 *
 */
@Service("parseJSON")
public class ParseJSON implements Parse {

	private static Logger logger = LoggerFactory.getLogger(ParseJSON.class);

	private final static String SPLIT = ",";

	@Resource
	private ApSysColumnServiceImpl apSysColumnRepository;

	/**
	 * 开始组装JSon报文 {obj:[{obj:val},{obj:val}],obj:val}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String encap(String msgCd, Map<String, Object> data) {
		StringBuilder reqStr = new StringBuilder();

		logger.debug("JSON封装报文======msgCd======" + msgCd);

		logger.debug("JSON封装报文======apSysColumnRepository======"
				+ apSysColumnRepository);

		List<ApSysColumn> columns = apSysColumnRepository
				.findByMsgCdRoot(msgCd);

		reqStr.append("{");

		for (ApSysColumn column : columns) {

			logger.debug("====cyclingFlag===" + column.getCyclingFlag());

			if ("Y".equals(column.getCyclingFlag())) {
				// 处理循环
				List<Map<String, Object>> list = (List<Map<String, Object>>) data
						.get(column.getColumnMapping());
				String childStr = encapCyclingColumn(list,
						column.getColumnCd(), msgCd);
				reqStr.append("\"").append(column.getColumnCd()).append("\"")
						.append(":[").append(childStr).append("]");
			} else if ("O".equals(column.getCyclingFlag())) {
				// 处理子对象
				String childStr = encapChildColumn(data, column.getColumnCd(),
						msgCd);
				reqStr.append("\"").append(column.getColumnCd()).append("\"")
						.append(":").append(childStr);

			} else {
				String val = ColumnUtil.getValue(data, column);
				if (StringUtils.isEmpty(val)) {
					// 为空时，返回值是null
					val = "";
				}
				logger.debug("字段值========"+val);
				reqStr.append("\"").append(column.getColumnCd()).append("\"")
						.append(":").append("\"").append(val).append("\"");
			}
			if (columns.indexOf(column) != columns.size() - 1) {
				reqStr.append(SPLIT);
			}
		}
		reqStr.append("}");
		return reqStr.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> unencap(String msgCd, String msg) {
		Map<String, Object> rspData = new ConcurrentHashMap<String, Object>();
		try {

			Map<String, Object> map = new ObjectMapper().readValue(msg,
					Map.class);
			logger.debug("=======转换后数据========" + map);
			logger.debug("=======msgCd========" + msgCd);
			List<ApSysColumn> columns = apSysColumnRepository
					.findByMsgCdRoot(msgCd);

			for (ApSysColumn column : columns) {
				String colCd = column.getColumnCd();

				logger.debug("=======处理字段========" + colCd);

				if ("Y".equals(column.getCyclingFlag())) {
					// 处理循环节点
					List<Map<String, Object>> list = (List<Map<String, Object>>) map
							.get(colCd);
					List<Map<String, Object>> ls = unencapCyclingColumn(list,
							colCd, msgCd);
					rspData.put(column.getColumnMapping(), ls);
				} else if ("O".equals(column.getCyclingFlag())) {
					// 处理子节点
					String childMsg = map.get(colCd).toString();
					logger.debug("子节点=============" + childMsg);
					Map<String, Object> m = new ObjectMapper().readValue(
							childMsg, Map.class);
                    
					Map<String, Object> data = unencapChildColumn(
							(Map<String, Object>) m, colCd, msgCd);
					rspData.putAll(data);
				} else {
					Object val = map.get(colCd);
					if (val == null) {
						rspData.put(column.getColumnMapping(), "");
					} else {
						Object valMap = ColumnUtil.putValue(val.toString(),
								column);
						if (valMap == null) {
							rspData.put(column.getColumnMapping(), "");
						} else {
							rspData.put(column.getColumnMapping(), valMap);
						}
					}
				}
			}

		} catch (JsonParseException e) {
			throw new SumpException("1203", "JSON格式解析错误");
		} catch (JsonMappingException e) {
			throw new SumpException("1204", "JSON数据映射错误");
		} catch (IOException e) {
			throw new SumpException("1205", "JSON数据读取错误");
		}
		return rspData;
	}

	/**
	 * 处理封装循环节点
	 * 
	 * @param childData
	 * @param columnCd
	 * @param msgCd
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String encapCyclingColumn(List<Map<String, Object>> childData,
			String columnCd, String msgCd) {
		StringBuilder reqBuff = new StringBuilder();
		List<ApSysColumn> cols = apSysColumnRepository
				.findChildByMsgCdColumnCd(columnCd, msgCd);
               
		for (int i = 0; i < childData.size(); i++) {
			Map<String, Object> req = childData.get(i);
			reqBuff.append("{");
			for (int j = 0; j < cols.size(); j++) {
				ApSysColumn obj = cols.get(j);
				/*
				 * 循环处理obj 格式 obj:val
				 */
				if ("Y".equals(obj.getCyclingFlag())) {
					/*
					 * 对象可循环 obj:[{obj:val},{obj:val}]
					 */
					List<Map<String, Object>> list = (List<Map<String, Object>>) req
							.get(obj.getColumnMapping());
					String childStr = encapCyclingColumn(list,
							obj.getColumnCd(), msgCd);

					reqBuff.append("\"").append(obj.getColumnCd()).append("\"")
							.append(":[").append(childStr).append("]");
				} else if ("O".equals(obj.getCyclingFlag())) {
					// 处理子对象
					String childStr = encapChildColumn(req, obj.getColumnCd(),
							msgCd);
					reqBuff.append("\"").append(obj.getColumnCd()).append("\"")
							.append(":").append(childStr);

				} else {
					String val = ColumnUtil.getValue(req, obj);
					if (StringUtils.isEmpty(val)) {
						val = "";
					}
					reqBuff.append("\"").append(obj.getColumnCd()).append("\"")
							.append(":").append("\"").append(val).append("\"");
				}
				if (j < cols.size() - 1) {
					/*
					 * 最后一个obj 不加逗号
					 */
					reqBuff.append(SPLIT);
				}
			}
			reqBuff.append("}");
			if (i < childData.size() - 1) {
				/*
				 * 最后一个obj 不加逗号
				 */
				reqBuff.append(SPLIT);
			}
		}
		return reqBuff.toString();
	}

	/**
	 * 处理封装循环节点
	 * 
	 * @param childData
	 * @param columnCd
	 * @param msgCd
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String encapChildColumn(Map<String, Object> childData,
			String columnCd, String msgCd) {
		StringBuilder reqBuff = new StringBuilder();
		List<ApSysColumn> cols = apSysColumnRepository
				.findChildByMsgCdColumnCd(columnCd, msgCd);
		reqBuff.append("{");
		for (int i = 0; i < cols.size(); i++) {
			ApSysColumn obj = cols.get(i);
			/*
			 * 循环处理obj 格式 obj:val
			 */
			if ("Y".equals(obj.getCyclingFlag())) {
				/*
				 * 对象可循环 obj:[{obj:val},{obj:val}]
				 */
				List<Map<String, Object>> list = (List<Map<String, Object>>) childData
						.get(obj.getColumnMapping());
				String childStr = encapCyclingColumn(list, obj.getColumnCd(),
						msgCd);

				reqBuff.append("\"").append(obj.getColumnCd()).append("\"")
						.append(":[").append(childStr).append("]");
			} else if ("O".equals(obj.getCyclingFlag())) {
				// 处理子对象
				String childStr = encapChildColumn(childData,
						obj.getColumnCd(), msgCd);
				reqBuff.append("\"").append(obj.getColumnCd()).append("\"")
						.append(":").append(childStr);
			} else {
				String val = ColumnUtil.getValue(childData, obj);
				if (StringUtils.isEmpty(val)) {
					val = "";
				}
				reqBuff.append("\"").append(obj.getColumnCd()).append("\"")
						.append(":").append("\"").append(val).append("\"");
			}
			logger.debug("======key====" + obj.getColumnCd());
			if (i < cols.size() - 1) {
				logger.debug("======子节点计数=====" + i + "=====size====="
						+ cols.size());
				/*
				 * 最后一个obj 不加逗号
				 */
				reqBuff.append(SPLIT);
			}
		}
		reqBuff.append("}");

		return reqBuff.toString();
	}

	/**
	 * 处理解析循环节点
	 * 
	 * @param childData
	 * @param columnCd
	 * @param msgCd
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> unencapCyclingColumn(
			List<Map<String, Object>> childData, String columnCd, String msgCd)
			throws JsonParseException, JsonMappingException, IOException {
		List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
		List<ApSysColumn> cols = apSysColumnRepository
				.findChildByMsgCdColumnCd(columnCd, msgCd);
        if(childData==null){
        	return ls;
        }
		for (Map<String, Object> mp : childData) {
			Map<String, Object> rspData = new ConcurrentHashMap<String, Object>();

			for (ApSysColumn obj : cols) {
				String colCd = obj.getColumnCd();

				if ("Y".equals(obj.getCyclingFlag())) {
					// 处理循环点
					List<Map<String, Object>> list = (List<Map<String, Object>>) rspData
							.get(colCd);
					List<Map<String, Object>> lls = unencapCyclingColumn(list,
							colCd, msgCd);
					rspData.put(obj.getColumnMapping(), lls);
				} else if ("O".equals(obj.getCyclingFlag())) {
					// 处理子节点
					// String childMsg = mp.get(colCd).toString();

					// Map<String, Object> childMap = new
					// ObjectMapper().readValue(childMsg,Map.class);

					logger.debug("子节点=============" + mp.get(colCd));
					if(mp.get(colCd) == null){
						continue;
					}
					Map<String, Object> m = unencapChildColumn(
							(Map<String, Object>) mp.get(colCd), colCd, msgCd);
					rspData.putAll(m);
				} else {
					Object val = mp.get(colCd);
					if (val == null) {
						rspData.put(obj.getColumnMapping(), "");
					} else {
						Object valMap = ColumnUtil
								.putValue(val.toString(), obj);
						if (valMap == null) {
							rspData.put(obj.getColumnMapping(), "");
						} else {
							rspData.put(obj.getColumnMapping(), valMap);
						}
					}
				}
			}

			ls.add(rspData);
		}

		return ls;

	}

	/**
	 * 处理解析子节点节点
	 * 
	 * @param childData
	 * @param columnCd
	 * @param msgCd
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> unencapChildColumn(
			Map<String, Object> childData, String columnCd, String msgCd)
			throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> data = new ConcurrentHashMap<String, Object>();
		List<ApSysColumn> cols = apSysColumnRepository
				.findChildByMsgCdColumnCd(columnCd, msgCd);
		logger.debug("=======处理数据========" + childData);
		for (ApSysColumn obj : cols) {
			String colCd = obj.getColumnCd();
			logger.debug("=======处理字段========" + colCd);
			if ("Y".equals(obj.getCyclingFlag())) {				
				// 处理循环节点
				List<Map<String, Object>> list = (List<Map<String, Object>>) childData
						.get(colCd);
				List<Map<String, Object>> ls = unencapCyclingColumn(list,
						colCd, msgCd);
				data.put(obj.getColumnMapping(), ls);
			} else if ("O".equals(obj.getCyclingFlag())) {
				// 处理子节点
				// String childMsg = childData.get(colCd).toString();
				logger.debug("=======处理子节点========" + childData.get(colCd));
				// Map<String, Object> mp = new
				// ObjectMapper().readValue(childMsg, Map.class);
				if(childData.get(colCd) == null){
					continue;
				}

				Map<String, Object> m = unencapChildColumn(
						(Map<String, Object>) childData.get(colCd), colCd,
						msgCd);
				data.putAll(m);
			} else {
				Object val = childData.get(colCd);
				if (val == null) {
					data.put(obj.getColumnMapping(), "");
				} else {
					Object valMap = ColumnUtil.putValue(val.toString(), obj);
					if (valMap == null) {
						data.put(obj.getColumnMapping(), "");
					} else {
						data.put(obj.getColumnMapping(), valMap);
					}
				}
			}
		}

		return data;

	}

}
