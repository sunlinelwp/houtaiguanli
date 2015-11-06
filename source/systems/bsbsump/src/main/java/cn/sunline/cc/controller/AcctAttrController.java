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

@Controller("AcctAttrController")
@RequestMapping(value = "/rest/cc")
@ResponseBody
@SessionAttributes("User")
public class AcctAttrController {
	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory.getLogger(AcctAttrController.class);
	
	/**
	 * 查询账户属性参数
	 */
	@RequestMapping(value="/acctattrs")
	public Map<String, Object> getacctattrs(@RequestParam Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		
		logger.info("------------------分页查询账户属性参数开始-----------------");	
		Map<String, Object> map = new HashMap<String, Object>();
		//判断账户属性ID字段
		if (reqmap.get("q_attrid") != null && reqmap.get("q_attrid") != "") {
			map.put("attrid", reqmap.get("q_attrid"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt((String) reqmap.get("length"), 10);
		int start = Integer.parseInt((String) reqmap.get("start"), 10);
		map.put("pageno", start / length+1);
		map.put("pagect", length);
		Map<String, Object> rspmap =new HashMap<String, Object>();
		rspmap = clict.callClient("qracat", map);
		logger.info("------------------分页查询账户属性参数结束-----------------");				
		rspmap.put("data", rspmap.get("infos")==null?"[]":rspmap.get("infos"));
		rspmap.put("iTotalDisplayRecords", rspmap.get("count"));
		rspmap.put("iTotalRecords", rspmap.get("count"));	
		return rspmap;
	}
	
	/**
	 * 单个账户属性参数
	 */
	@RequestMapping(value="/acctattr")
	public Map<String, Object> getacctattr(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		logger.info("------------------单个数据查询账户属性参数开始-----------------");		
		Map<String, Object> map = new HashMap<String, Object>();
		//判断锁定码字段
		if (reqmap.get("attrid") != null && reqmap.get("attrid") != "") {
			map.put("attrid", reqmap.get("attrid"));
		}
		map.put("userid", user.getUserid());
		map.put("pageno", 1);
		map.put("pagect", 10);
		Map<String, Object> rspmap =new HashMap<String, Object>();
		rspmap = clict.callClient("qracat", map);
		logger.info("------------------单个数据查询账户属性参数结束-----------------");				
		return rspmap;
	}
	
	/**
	 * 新增修改锁定码
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edacctattr")
	public Map<String, Object> edprod(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------新增修改账户属性参数------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = clict.callClient("edacat", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "对账户属性参数操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------新增修改账户属性参数结束------------------------");
		return resmap;
	}
	
	/**
	 *账户属性参数删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/acctattrs", method = { RequestMethod.DELETE })
	public Map<String, Object> deprod(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------账户属性参数删除开始------------------------");
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = clict.callClient("deacat", req); 
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "账户属性参数删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------账户属性参数删除结束------------------------");
		return resmap;
	}

}
