package cn.sunline.fund.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.sunline.client.Client;
import cn.sunline.domain.endauth.BSBUser;

@Controller("FundProdController")
@RequestMapping(value = "/rest/fund")
@ResponseBody
@SessionAttributes("User")
public class FundProdController {
	
	@Autowired
	Client client;

	private static Logger logger = LoggerFactory
			.getLogger(FundProdController.class);

	
	/**
	 * 查询基金产品
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrfdpd")
	public Map<String, Object> qrfdpd(@RequestParam  Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询金产品开始-----------------" + reqmap);
		reqmap.put("userid", user.getUserid()); // 设置操作柜员
		if (StringUtils.isNotEmpty((String)reqmap.get("q_prodcd"))) {
			reqmap.put("prodcd", reqmap.get("q_prodcd").toString());
		}
		if (StringUtils.isNotEmpty((String)reqmap.get("q_prodna"))) {
			reqmap.put("prodna", reqmap.get("q_prodna").toString());
		}
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		reqmap.put("pageno", start / length + 1);// 当前页数
		reqmap.put("pagesz", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrfdpd", reqmap);
		resmap.put(
				"data",
				resmap.get("fdprod") == null ? new ArrayList<Object>() : resmap
						.get("fdprod"));
		resmap.put("iTotalDisplayRecords", resmap.get("counts") == null ? "0"
				: resmap.get("counts"));
		resmap.put("iTotalRecords",
				resmap.get("counts") == null ? "0" : resmap.get("counts"));
		logger.debug("-----------------查询金产品结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增修改基金产品
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/upfdpd")
	public Map<String, Object> upfdpd(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改基金产品开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("upfdpd", req);
		logger.debug("--------------------新增修改基金产品结束------------------------");
		return resmap;
	}

	/**
	 * 基金产品删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrfdpd" , method = { RequestMethod.DELETE })
	public Map<String, Object> dechck(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("defdpd", req); 
		return resmap;
	}


}
