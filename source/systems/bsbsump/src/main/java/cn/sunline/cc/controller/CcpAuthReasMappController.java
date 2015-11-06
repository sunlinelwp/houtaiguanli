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

@Controller("CcpAuthReasMappController")
@RequestMapping(value = "/rest/cc")
@ResponseBody
@SessionAttributes("User")
public class CcpAuthReasMappController {
	
	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory.getLogger(CcpAuthReasMappController.class);
	
	/**
	 * 分页查询原因码定义映射参数
	 */
	@RequestMapping(value="/authreasmapps")
	public Map<String, Object> getlockcodes(@RequestParam Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		logger.info("------------------分页查询原因码定义映射参数开始-----------------");	
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_reason") != null && reqmap.get("q_reason") != "") {
			map.put("reason", reqmap.get("q_reason"));
		}
		if (reqmap.get("q_redesc") != null && reqmap.get("q_redesc") != "") {
			map.put("redesc", reqmap.get("q_redesc"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt((String) reqmap.get("length"), 10);
		int start = Integer.parseInt((String) reqmap.get("start"), 10);
		map.put("pageno", start / length+1);
		map.put("pagect", length);
		Map<String, Object> rspmap =new HashMap<String, Object>();
		rspmap = clict.callClient("qrremp", map);
		logger.info("------------------分页查询原因码定义映射参数结束-----------------");				
		rspmap.put("data", rspmap.get("infos")==null?"[]":rspmap.get("infos"));
		rspmap.put("iTotalDisplayRecords", rspmap.get("count"));
		rspmap.put("iTotalRecords", rspmap.get("count"));	
		return rspmap;
	}
	
	/**
	 * 单个查询原因码定义映射参数
	 */
	@RequestMapping(value="/authreasmapp")
	public Map<String, Object> getlockcode(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		//判断锁定码字段
		if (reqmap.get("reason") != null && reqmap.get("reason") != "") {
			map.put("reason", reqmap.get("reason"));
		}
		map.put("userid", user.getUserid());
		map.put("pageno", 1);
		map.put("pagect", 10);
		Map<String, Object> rspmap =new HashMap<String, Object>();
		rspmap = clict.callClient("qrremp", map);
		logger.debug("------------------单个查询原因码定义映射参数结束-----------------");				
		return rspmap;
	}
	
	/**
	 * 新增修改原因码定义映射参数
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edauthreasmapp")
	public Map<String, Object> edauthreasmapp(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------新增修改原因码定义映射参数------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = clict.callClient("edremp", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "对原因码定义映射参数操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------新增修改原因码定义映射参数------------------------");
		return resmap;
	}
	
	/**
	 *原因码定义映射参数删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/authreasmapps", method = { RequestMethod.DELETE })
	public Map<String, Object> deremp(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------原因码定义映射参数删除开始------------------------");
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = clict.callClient("deremp", req); 
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "原因码定义映射参数删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------原因码定义映射参数删除结束------------------------");
		return resmap;
	}

}
