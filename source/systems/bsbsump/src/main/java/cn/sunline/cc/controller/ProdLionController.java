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

@Controller("ProdLionController")
@RequestMapping(value = "/rest/cc")
@ResponseBody
@SessionAttributes("User")
public class ProdLionController {
	
	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory.getLogger(ProdLionController.class);
	
	/**
	 * 分页查询产品参数
	 */
	@RequestMapping(value="/prodlions")
	public Map<String, Object> getprodlions(@RequestParam Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		logger.info("------------------分页查询产品参数开始-----------------");	
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		}
		if (reqmap.get("q_descrp") != null && reqmap.get("q_descrp") != "") {
			map.put("descrp", reqmap.get("q_descrp"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt((String) reqmap.get("length"), 10);
		int start = Integer.parseInt((String) reqmap.get("start"), 10);
		map.put("pageno", start / length+1);
		map.put("pagect", length);
		Map<String, Object> rspmap =new HashMap<String, Object>();
		rspmap = clict.callClient("qrprot", map);
		logger.info("------------------分页查询产品参数结束-----------------");				
		rspmap.put("data", rspmap.get("infos")==null?"[]":rspmap.get("infos"));
		rspmap.put("iTotalDisplayRecords", rspmap.get("count"));
		rspmap.put("iTotalRecords", rspmap.get("count"));	
		return rspmap;
	}
	
	/**
	 *查询信用模板类型分类
	 */
	@RequestMapping(value="/qrtpid")
	public Map<String, Object> getqrtpid(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		Map<String, Object> rspmap =new HashMap<String, Object>();
		rspmap = clict.callClient("qrtpid", map);
		logger.info("------------------查询信用模板类型分类结束-----------------");				
		return rspmap;
	}
	
	/**
	 * 单个查询产品参数
	 */
	@RequestMapping(value="/prodlion")
	public Map<String, Object> getprodlion(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("prodcd") != null && reqmap.get("prodcd") != "") {
			map.put("prodcd", reqmap.get("prodcd"));
		}
		map.put("userid", user.getUserid());
		map.put("pageno", 1);
		map.put("pagect", 10);
		Map<String, Object> rspmap =new HashMap<String, Object>();
		rspmap = clict.callClient("qrprot", map);
		logger.info("------------------单个查询产品参数结束-----------------");				
		return rspmap;
	}
	
	/**
	 * 新增修改产品参数
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edprodlion")
	public Map<String, Object> edprodlion(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------新增修改产品参数开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = clict.callClient("edprot", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "对锁定码操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------新增修改产品参数结束------------------------");
		return resmap;
	}
	
	/**
	 *锁定码删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/prodlions", method = { RequestMethod.DELETE })
	public Map<String, Object> deprodlions(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------锁定码删除开始------------------------");
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = clict.callClient("deprot", req); 
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
