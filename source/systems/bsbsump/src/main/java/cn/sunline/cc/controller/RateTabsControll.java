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

@Controller("RateTabsControll")
@RequestMapping(value = "/rest/cc")
@ResponseBody
@SessionAttributes("User")
public class RateTabsControll {
	
	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory.getLogger(RateTabsControll.class);
	
	/**
	 * 分页查询利率参数表
	 */
	@RequestMapping(value="/ratetabs")
	public Map<String, Object> getratetabs(@RequestParam Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		logger.info("------------------分页查询利率参数表开始-----------------");	
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_intbid") != null && reqmap.get("q_intbid") != "") {
			map.put("intbid", reqmap.get("q_intbid"));
		}
		if (reqmap.get("q_intdes") != null && reqmap.get("q_intdes") != "") {
			map.put("intdes", reqmap.get("q_intdes"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt((String) reqmap.get("length"), 10);
		int start = Integer.parseInt((String) reqmap.get("start"), 10);
		map.put("pageno", start / length+1);
		map.put("pagect", length);
		Map<String, Object> rspmap =new HashMap<String, Object>();
		rspmap = clict.callClient("qrrttb", map);
		logger.info("------------------分页查询利率参数表结束-----------------");				
		rspmap.put("data", rspmap.get("infos")==null?"[]":rspmap.get("infos"));
		rspmap.put("iTotalDisplayRecords", rspmap.get("count"));
		rspmap.put("iTotalRecords", rspmap.get("count"));	
		return rspmap;
	}
	
	/**
	 * 新增利率参数
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edratetabs")
	public Map<String, Object> edratetabs(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------新增利率参数------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = clict.callClient("edrttb", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "对利率参数操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------新增利率参数------------------------");
		return resmap;
	}
	
	/**
	 *利率参数删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/ratetabs", method = { RequestMethod.DELETE })
	public Map<String, Object> deprod(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------利率参数删除开始------------------------");
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = clict.callClient("derttb", req); 
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "利率参数成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------利率参数删除结束------------------------");
		return resmap;
	}
	
	/**
	 * 分页查询比率参数表
	 */
	@RequestMapping(value="/ratedefi")
	public Map<String, Object> getratedefi(@RequestParam Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		logger.info("------------------分页查询比率参数表开始-----------------");	
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("intbid") != null && reqmap.get("intbid") != "") {
			map.put("intbid", reqmap.get("intbid"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt((String) reqmap.get("length"), 10);
		int start = Integer.parseInt((String) reqmap.get("start"), 10);
		map.put("pageno", start / length+1);
		map.put("pagect", length);
		Map<String, Object> rspmap =new HashMap<String, Object>();
		rspmap = clict.callClient("qrrtdf", map);
		logger.info("------------------分页查询比率参数表表结束-----------------");				
		rspmap.put("data", rspmap.get("infos")==null?"[]":rspmap.get("infos"));
		rspmap.put("iTotalDisplayRecords", rspmap.get("count"));
		rspmap.put("iTotalRecords", rspmap.get("count"));	
		return rspmap;
	}
	
	/**
	 * 新增/修改比率参数
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edratedefi")
	public Map<String, Object> edratedefi(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------新增/修改利率参数------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		req.put("intbid",req.get("bl_intbid"));
		Map<String, Object> resmap = clict.callClient("edrtdf", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "对比率参数操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------新增/修改利率参数------------------------");
		return resmap;
	}
	
	/**
	 *比率参数删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/ratedefi", method = { RequestMethod.DELETE })
	public Map<String, Object> deratedefi(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------比率参数开始------------------------");
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = clict.callClient("dertdf", req); 
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "利率参数成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------比率参数结束------------------------");
		return resmap;
	}

}
