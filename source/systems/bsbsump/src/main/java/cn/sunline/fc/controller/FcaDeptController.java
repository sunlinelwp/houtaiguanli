package cn.sunline.fc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

@Controller("FcaDeptController")
@RequestMapping(value = "/rest/fcadept")
@ResponseBody
@SessionAttributes("User")
public class FcaDeptController {
	@Autowired
	ClientImpl client;
	private static Logger logger = LoggerFactory.getLogger(FcaDeptController.class);
	
	/**
	 * 列表查询融资产品转让规则
	 */
	@RequestMapping(value ="/qralld")
	public Map<String,Object> qralld(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("列表查询融资产品转让规则开始---------------------------"+reqmap);	
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		}
		map.put("userid", user.getUserid()); 

		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("pagesz", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qralld", map);
		resmap.put(
				"data",
				resmap.get("deptInfos") == null ? new ArrayList<Object>() : resmap
						.get("deptInfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count") == null ? "0"
				: resmap.get("count"));
		resmap.put("iTotalRecords",
				resmap.get("count") == null ? "0" : resmap.get("count"));
		logger.info("列表查询融资产品转让规则结束---------------------------"+reqmap);
		return resmap;
	}
	

	@RequestMapping(value ="/qryrbm")
	public Map<String,Object> qryrbm(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		logger.info("qryrbm开始---------------------------"+reqmap);	
		try {
			rspmap = client.callClient("qryrbm", reqmap);
			List<Map<String,Object>> list =(List<Map<String,Object>>)rspmap.get("prinfo");
			List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
			for (Map<String,Object> map : list) {
				Map<String,Object> map1 = new HashMap<String,Object>();
				map1.put("id", map.get("prodcd"));
				map1.put("text", map.get("prodna"));
				list1.add(map1);
			}
			rspmap.put("data", list1);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("qryrbm结束---------------------------"+reqmap);
		return rspmap;
	}
	
	@RequestMapping(value ="/indebt")
	public Map<String,Object> indebt(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("indebt开始---------------------------"+reqmap);	
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = client.callClient("indebt", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("indebt结束---------------------------"+reqmap);
		return rspmap;
	}
	
	@RequestMapping(value ="/qrdepr")
	public Map<String,Object> qrdepr(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		logger.info("qrdepr开始---------------------------"+reqmap);	
		try {
			rspmap = client.callClient("qrdepr", reqmap);
			List<Map<String,Object>> list =(List<Map<String,Object>>)rspmap.get("prinfo");
			List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
			for (Map<String,Object> map : list) {
				Map<String,Object> map1 = new HashMap<String,Object>();
				map1.put("id", map.get("prodcd"));
				map1.put("text", map.get("prodna"));
				list1.add(map1);
			}
			rspmap.put("data", list1);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("qrdepr结束---------------------------"+reqmap);
		return rspmap;
	}
	
	@RequestMapping(value ="/inlimt")
	public Map<String,Object> inlimt(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("inlimt开始---------------------------"+reqmap);	
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = client.callClient("inlimt", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("inlimt结束---------------------------"+reqmap);
		return rspmap;
	}
	
	@RequestMapping(value ="/qrlimt")
	public Map<String,Object> qrlimt(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		logger.info("qrlimt开始---------------------------"+reqmap);	
		try {
			rspmap = client.callClient("qrlimt", reqmap);
			List<Map<String,Object>> list =(List<Map<String,Object>>)rspmap.get("limtInfos");
			List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
			for (Map<String,Object> map : list) {
				Map<String,Object> map1 = new HashMap<String,Object>();
				map1.put("id", map.get("limtsq"));
				map1.put("text", map.get("minbal")+"-"+map.get("maxbal"));
				list1.add(map1);
			}
			rspmap.put("data", list1);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("qrlimt结束---------------------------"+reqmap);
		return rspmap;
	}
	
	@RequestMapping(value ="/inrate")
	public Map<String,Object> inrate(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("inrate开始---------------------------"+reqmap);	
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = client.callClient("inrate", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("inrate结束---------------------------"+reqmap);
		return rspmap;
	}
	
	@RequestMapping(value ="/qonede")
	public Map<String,Object> qonede(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("qonede开始---------------------------"+reqmap);	
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = client.callClient("qonede", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("qonede结束---------------------------"+reqmap);
		return rspmap;
	}
	
	@RequestMapping(value ="/updept")
	public Map<String,Object> updept(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("updept开始---------------------------"+reqmap);	
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = client.callClient("updept", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("updept结束---------------------------"+reqmap);
		return rspmap;
	}
	
	@RequestMapping(value ="/qonelm")
	public Map<String,Object> qonelm(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("qonelm开始---------------------------"+reqmap);	
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = client.callClient("qonelm", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("qonelm结束---------------------------"+reqmap);
		return rspmap;
	}
	
	@RequestMapping(value ="/uplmit")
	public Map<String,Object> uplmit(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("uplmit开始---------------------------"+reqmap);	
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = client.callClient("uplmit", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("uplmit结束---------------------------"+reqmap);
		return rspmap;
	}
	
	@RequestMapping(value ="/dedebt")
	public Map<String,Object> dedebt(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("dedebt开始---------------------------"+reqmap);	
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = client.callClient("dedebt", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("dedebt结束---------------------------"+reqmap);
		return rspmap;
	}
	
	@RequestMapping(value ="/dedeli")
	public Map<String,Object> dedeli(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("dedeli开始---------------------------"+reqmap);	
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = client.callClient("dedeli", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("dedeli结束---------------------------"+reqmap);
		return rspmap;
	}
	@RequestMapping(value ="/qonera")
	public Map<String,Object> qonera(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		logger.info("qonera开始---------------------------"+reqmap);	
		try {
			rspmap = client.callClient("qonera", reqmap);
			List<Map<String,Object>> list =(List<Map<String,Object>>)rspmap.get("rateInfo");
			List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
			for (Map<String,Object> map : list) {
				Map<String,Object> map1 = new HashMap<String,Object>();
				map1.put("id", map.get("ratevl"));
				map1.put("text", map.get("ratevl"));
				list1.add(map1);
			}
			rspmap.put("data", list1);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("qonera结束---------------------------"+reqmap);
		return rspmap;
	}
	
	@RequestMapping(value ="/derate")
	public Map<String,Object> derate(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("derate开始---------------------------"+reqmap);	
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = client.callClient("derate", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("derate结束---------------------------"+reqmap);
		return rspmap;
	}
}
