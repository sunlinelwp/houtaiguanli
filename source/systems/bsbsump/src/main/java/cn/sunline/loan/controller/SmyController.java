package cn.sunline.loan.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.sunline.client.ClientImpl;
import cn.sunline.domain.endauth.BSBUser;

@Controller("SmyController")
@RequestMapping(value = "/rest/smy")
@ResponseBody
@SessionAttributes("User")
public class SmyController {
	@Autowired
	ClientImpl client;
	private static Logger logger = LoggerFactory
			.getLogger(SmyController.class);

	@RequestMapping(value = "/clearsmy")
	public Map<String, Object> clearSmy(
			@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------萨摩耶清算开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("smrpqs", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "清算成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.debug("--------------------萨摩耶清算结束------------------------");

		return resmap;
	}
	
	/**
	 * 贷款查询放款差异信息
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/smqrln")
	public Map<String, Object> smqrln(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------贷款查询放款差异信息开始------------------------"+ reqmap.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty((String)reqmap.get("q_trantp"))) {
			map.put("trantp", reqmap.get("q_trantp").toString());
		}
		map.put("userid", user.getUserid()); // 设置操作柜员
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagect", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("smqrln", map);
		resmap.put(
				"data",
				resmap.get("lncflist") == null ? new ArrayList<Object>() : resmap
						.get("lncflist"));
		resmap.put("iTotalDisplayRecords", resmap.get("count") == null ? "0"
				: resmap.get("count"));
		resmap.put("iTotalRecords",
				resmap.get("count") == null ? "0" : resmap.get("count"));
		logger.debug("--------------------贷款查询放款差异信息结束------------------------");
		return resmap;
	}
}
