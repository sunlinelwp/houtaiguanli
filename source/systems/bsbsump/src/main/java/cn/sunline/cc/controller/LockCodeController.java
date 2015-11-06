package cn.sunline.cc.controller;

import java.util.HashMap;
import java.util.Map;

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

import cn.sunline.client.ClientImpl;
import cn.sunline.domain.endauth.BSBUser;

@Controller("LockCodeController")
@RequestMapping(value = "/rest/cc")
@ResponseBody
@SessionAttributes("User")
public class LockCodeController {
	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory.getLogger(LockCodeController.class);
	
	/**
	 * 分页查询锁定码
	 */
	@RequestMapping(value="/lockcodes")
	public Map<String, Object> getlockcodes(@RequestParam Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		logger.info("------------------查询分页查询锁定码开始-----------------");	
		Map<String, Object> map = new HashMap<String, Object>();
		//判断锁定码字段
		if (reqmap.get("q_lockcd") != null && reqmap.get("q_lockcd") != "") {
			map.put("lockcd", reqmap.get("q_lockcd"));
		}
		//描述字段
		if (reqmap.get("q_descrp") != null && reqmap.get("q_descrp") != "") {
			map.put("descrp", reqmap.get("q_descrp"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt((String) reqmap.get("length"), 10);
		int start = Integer.parseInt((String) reqmap.get("start"), 10);
		map.put("pageno", start / length+1);
		map.put("pagect", length);
		Map<String, Object> rspmap =new HashMap<String, Object>();
		rspmap = clict.callClient("qrlkcd", map);
		logger.info("------------------查询分页查询锁定码结束-----------------");				
		rspmap.put("data", rspmap.get("infos")==null?"[]":rspmap.get("infos"));
		rspmap.put("iTotalDisplayRecords", rspmap.get("count"));
		rspmap.put("iTotalRecords", rspmap.get("count"));	
		return rspmap;
	}
	
	/**
	 * 单个查询锁定码
	 */
	@RequestMapping(value="/lockcode")
	public Map<String, Object> getlockcode(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		//判断锁定码字段
		if (reqmap.get("lockcd") != null && reqmap.get("lockcd") != "") {
			map.put("lockcd", reqmap.get("lockcd"));
		}
		map.put("userid", user.getUserid());
		map.put("pageno", 1);
		map.put("pagect", 10);
		Map<String, Object> rspmap =new HashMap<String, Object>();
		rspmap = clict.callClient("qrlkcd", map);
		logger.debug("------------------单个查询锁定码结束-----------------");				
		return rspmap;
	}
	
	/**
	 * 新增修改锁定码
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edlockcode")
	public Map<String, Object> edprod(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------新增修改锁定码------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = clict.callClient("edlkcd", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "对锁定码操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------新增修改锁定码------------------------");
		return resmap;
	}
	
	/**
	 *锁定码删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/lockcodes", method = { RequestMethod.DELETE })
	public Map<String, Object> deprod(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------锁定码删除开始------------------------");
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = clict.callClient("delkcd", req); 
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "锁定码删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------锁定码删除结束------------------------");
		return resmap;
	}
	

}
