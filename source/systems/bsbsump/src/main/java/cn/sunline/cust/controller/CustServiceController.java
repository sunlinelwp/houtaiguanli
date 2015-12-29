package cn.sunline.cust.controller;

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

@Controller("CustServiceController")
@RequestMapping(value = "/rest/custService")
@ResponseBody
@SessionAttributes("User")
public class CustServiceController {
	@Autowired
	ClientImpl client;
	private static Logger logger = LoggerFactory.getLogger(CustServiceController.class);
	
	/**
	 * 根据电子账号查询客户信息
	 */
	@RequestMapping(value ="/mypage")
	public Map<String,Object> getCust(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("客户信息查询开始---------------------------"+reqmap);	
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = client.callClient("mypage", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("客户信息查询结束---------------------------"+reqmap);
		return rspmap;
	}
	
	/**
	 * 根据电子账号查询投资信息
	 */
	@RequestMapping(value = "/fclnqr")
	public Map<String, Object> fclnqr(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------投资信息开始-----------------"+reqmap);
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_custac") != null && reqmap.get("q_custac") != "") {
			map.put("custac", reqmap.get("q_custac"));
		}
		
		if (reqmap.get("q_phoneNo") != null && reqmap.get("q_phoneNo") != "") {
			map.put("moblie", reqmap.get("q_phoneNo"));
		}
		
		map.put("userid", user.getUserid()); 

		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("record", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("fclnqr", map);
		resmap.put(
				"data",
				resmap.get("lninfo") == null ? new ArrayList<Object>() : resmap
						.get("lninfo"));
		resmap.put("iTotalDisplayRecords", resmap.get("totshu") == null ? "0"
				: resmap.get("totshu"));
		resmap.put("iTotalRecords",
				resmap.get("totshu") == null ? "0" : resmap.get("totshu"));
		logger.debug("-----------------投资信息结束-----------------"+resmap);
		return resmap;
	}
	
	
	/**
	 * 根据电子账号查询转让信息
	 */
	@RequestMapping(value = "/qrdbsb")
	public Map<String, Object> qrdbsb(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------转让信息开始-----------------"+reqmap);
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_custac") != null && reqmap.get("q_custac") != "") {
			map.put("custac", reqmap.get("q_custac"));
		}
		
		if (reqmap.get("q_phoneNo") != null && reqmap.get("q_phoneNo") != "") {
			map.put("mobile", reqmap.get("q_phoneNo"));
		}
		
		map.put("trantp", "3"); //已转出
		map.put("userid", user.getUserid()); 

		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("record", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrdbsb", map);
		resmap.put(
				"data",
				resmap.get("subjif") == null ? new ArrayList<Object>() : resmap
						.get("subjif"));
		resmap.put("iTotalDisplayRecords", resmap.get("counts") == null ? "0"
				: resmap.get("counts"));
		resmap.put("iTotalRecords",
				resmap.get("counts") == null ? "0" : resmap.get("counts"));
		logger.debug("-----------------转让信息结束-----------------"+resmap);
		return resmap;
	}
	
	
	
	/**
	 * 根据电子账号查询转让信息
	 */
	@RequestMapping(value = "/qrdbsbin")
	public Map<String, Object> qrdbsbin(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------转让信息开始-----------------"+reqmap);
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_custac") != null && reqmap.get("q_custac") != "") {
			map.put("custac", reqmap.get("q_custac"));
		}
		
		if (reqmap.get("q_phoneNo") != null && reqmap.get("q_phoneNo") != "") {
			map.put("mobile", reqmap.get("q_phoneNo"));
		}
		
		map.put("trantp", "4"); //已转出
		map.put("userid", user.getUserid()); 

		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("record", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrdbsb", map);
		resmap.put(
				"data",
				resmap.get("subjif") == null ? new ArrayList<Object>() : resmap
						.get("subjif"));
		resmap.put("iTotalDisplayRecords", resmap.get("counts") == null ? "0"
				: resmap.get("counts"));
		resmap.put("iTotalRecords",
				resmap.get("counts") == null ? "0" : resmap.get("counts"));
		logger.debug("-----------------转让信息结束-----------------"+resmap);
		return resmap;
	}
	
	
	/**
	 * 查询转让下的详细记录
	 * @author jianlei
	 */
	@RequestMapping(value = "/tranhistory")
	public Map<String, Object> tranferHistory(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		
		logger.debug("------------------转让的记录查询开始-----------------"+reqmap);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		map.put("custac", reqmap.get("custac")); //电子账号
		map.put("debtcd", reqmap.get("debtcd")); //转让标的编号
		
		map.put("userid", user.getUserid()); 

		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		
		map.put("pageno", start / length + 1);
		map.put("record", length);
		
		Map<String, Object> resmap = new HashMap<String, Object>();
		
		resmap = client.callClient("fczrdt", map);
		
		resmap.put(
				"data",
				resmap.get("tranif") == null ? new ArrayList<Object>() : resmap
						.get("tranif"));
		
		resmap.put("iTotalDisplayRecords", resmap.get("counts") == null ? "0"
				: resmap.get("counts"));
		
		resmap.put("iTotalRecords",
				resmap.get("counts") == null ? "0" : resmap.get("counts"));
		
		logger.debug("----------------转让的记录查询技术-----------------"+resmap);
		
		return resmap;
	}
	
}
