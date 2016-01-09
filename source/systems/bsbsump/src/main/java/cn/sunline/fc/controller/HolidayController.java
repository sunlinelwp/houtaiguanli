package cn.sunline.fc.controller;

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
import cn.sunline.exception.SumpException;

@Controller("HolidayController")
@RequestMapping(value = "/rest/holiday")
@ResponseBody
@SessionAttributes("User")
public class HolidayController {
	@Autowired
	ClientImpl client;
	private static Logger logger = LoggerFactory.getLogger(HolidayController.class);
	
	@RequestMapping(value ="/aphody")
	public Map<String,Object> aphody(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("aphody开始---------------------------"+reqmap);	
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = client.callClient("aphody", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("aphody结束---------------------------"+reqmap);
		return rspmap;
	}
	
	@RequestMapping(value ="/adwekd")
	public Map<String,Object> adwekd(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("adwekd开始---------------------------"+reqmap);	
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = client.callClient("adwekd", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("adwekd结束---------------------------"+reqmap);
		return rspmap;
	}
	
	@RequestMapping(value ="/dehody")
	public Map<String,Object> dehody(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("dehody开始---------------------------"+reqmap);	
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = client.callClient("dehody", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("dehody结束---------------------------"+reqmap);
		return rspmap;
	}
	
	/**
	 * 列表查询节假日
	 */
	@RequestMapping(value ="/qrhody")
	public Map<String,Object> qrhody(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("列表查询节假日开始---------------------------"+reqmap);	
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("inyear") != null && reqmap.get("inyear") != "") {
			map.put("inyear", reqmap.get("inyear"));
		}
		if (reqmap.get("indate") != null && reqmap.get("indate") != "") {
			map.put("indate", reqmap.get("indate"));
		}
		if (reqmap.get("inmoth") != null && reqmap.get("inmoth") != "") {
			map.put("inmoth", reqmap.get("inmoth"));
		}
		if (reqmap.get("remark") != null && reqmap.get("remark") != "") {
			map.put("remark", reqmap.get("remark"));
		}
		map.put("userid", user.getUserid()); 

		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("record", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrhody", map);
		resmap.put(
				"data",
				resmap.get("hodyif") == null ? new ArrayList<Object>() : resmap
						.get("hodyif"));
		resmap.put("iTotalDisplayRecords", resmap.get("counts") == null ? "0"
				: resmap.get("counts"));
		resmap.put("iTotalRecords",
				resmap.get("counts") == null ? "0" : resmap.get("counts"));
		logger.info("列表查询节假日结束---------------------------"+reqmap);
		return resmap;
	}
}
