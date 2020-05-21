package cn.sunline.message;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import cn.sunline.exception.SumpException;
import cn.sunline.message.dao.ApSysColumn;
import cn.sunline.message.dao.ApSysColumnServiceImpl;
import cn.sunline.message.dao.ApSysMsg;
import cn.sunline.message.dao.ApSysMsgRepository;

import javax.annotation.Resource;

@Service("parseXML")
public class ParseXML implements Parse {

	// private static Logger logger = LoggerFactory.getLogger(ParseXML.class);

	@Resource
	private ApSysColumnServiceImpl apSysColumnRepository;

	@Resource
	private ApSysMsgRepository apSysMsgRepository;

	@Override
	public String encap(String msgCd, Map<String, Object> data) {

		List<ApSysColumn> columns = apSysColumnRepository
				.findByMsgCdRoot(msgCd);

		ApSysMsg msg = apSysMsgRepository.findOne(msgCd);

		/* 创建xml头和根节点 */
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding(msg.getXmlCoding());

		if (columns.size() != 1) {
			throw new SumpException("1203", "XML报文格式配置错误，只能有一个根节点");
		} else {

			ApSysColumn rootObj = columns.get(0);
			Element root = DocumentHelper.createElement(rootObj.getColumnCd());
			document.setRootElement(root);

			/* 创建包体节点 */
			xmlReqColumns(data, root, rootObj.getColumnCd(), msgCd);
		}

		return document.asXML();
	}

	@Override
	public Map<String, Object> unencap(String msgCd, String msg) {
		Map<String, Object> rspStr = new ConcurrentHashMap<String, Object>();
		SAXReader saxReader = new SAXReader();
		Document doucment = null;
		try {
			msg = msg.trim();
			doucment = saxReader.read(new StringReader(msg));
		} catch (DocumentException e) {
			throw new SumpException("1204", "XML报文转换异常");
		}
		// 获取根节点
		Element root = doucment.getRootElement();

		List<ApSysColumn> columns = apSysColumnRepository
				.findByMsgCdRoot(msgCd);

		if (columns.size() != 1) {
			throw new SumpException("1204", "ML报文格式配置错误，只能有一个根节点");
		} else {
			ApSysColumn rootObj = columns.get(0);
			xmlRspColumns(rspStr, root, rootObj.getColumnCd(), msgCd);
		}

		return rspStr;
	}

	@SuppressWarnings("unchecked")
	private void xmlReqColumns(Map<String, Object> req, Element el,
			String columnCd, String msgCd) {
		List<ApSysColumn> cols = apSysColumnRepository
				.findChildByMsgCdColumnCd(columnCd, msgCd);

		for (ApSysColumn obj : cols) {
			String colCd = obj.getColumnCd();
			Long colLength = (long) obj.getColumnLength();
			StringBuffer valStr = new StringBuffer();
			Element elt = DocumentHelper.createElement(colCd);
			/* 可循环 */
			if ("Y".equals(obj.getCyclingFlag())) {
				List<Map<String, Object>> list = (List<Map<String, Object>>) req
						.get(obj.getColumnMapping());

				for (Map<String, Object> reqMap : list) {
					el.add(elt);
					xmlReqColumns(reqMap, elt, colCd, msgCd);
				}
			} else {
				el.add(elt);
				String val = ColumnUtil.getValue(req, obj);
				Integer valLength = 0;
				if (!StringUtils.isEmpty(val)) {
					valLength = val.length();
				} else {
					// 为空时，返回值是null,对于定长格式，转化为空
					val = "";
				}

				/* 确定是否要补齐字符:val为空或者小于定义的长度 */
				if (colLength > valLength) {
					String polish = obj.getPolishType().toString();
					String polisChar = obj.getPolishChar();
					StringBuffer valBuff = new StringBuffer();
					for (int i = 0; i < colLength - valLength; i++) {
						valBuff.append(polisChar);
					}
					/* 左补齐 */
					if ("1".equals(polish)) {
						valStr.append(valBuff).append(val);
						/* 默认右补齐 */
					} else {
						valStr.append(val).append(valBuff);
					}
				} else {
					valStr.append(val);
				}

				if (StringUtils.isEmpty(val)) {
					// 为空时，返回值是null,对于定长格式，转化为空
					val = "";
				} else {
					elt.setText(valStr.toString());
				}
				xmlReqColumns(req, elt, colCd, msgCd);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void xmlRspColumns(Map<String, Object> rsp, Element el,
			String columnCd, String msgCd) {
		List<ApSysColumn> cols = apSysColumnRepository
				.findChildByMsgCdColumnCd(columnCd, msgCd);

		for (ApSysColumn obj : cols) {
			String colCd = obj.getColumnCd();

			/* 可循环 */
			if ("Y".equals(obj.getCyclingFlag())) {
				List<Element> list = el.elements(colCd);
				List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> ret = new HashMap<String, Object>();
					Element elt = (Element) list.get(i);
					xmlRspColumns(ret, elt, colCd, msgCd);
					ls.add(ret);
				}
				rsp.put(obj.getColumnMapping(), ls);
			} else {
				Element elt = el.element(colCd);
				String colStr = el.elementTextTrim(colCd);
				rsp.put(obj.getColumnMapping(),
						ColumnUtil.putValue(colStr, obj));
				xmlRspColumns(rsp, elt, colCd, msgCd);
			}
		}
	}
}
