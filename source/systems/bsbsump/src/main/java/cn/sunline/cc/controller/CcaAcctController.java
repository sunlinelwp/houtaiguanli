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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.sunline.client.ClientImpl;
import cn.sunline.domain.endauth.BSBUser;

@Controller("CcaAcctController")
@RequestMapping(value = "/rest/cc")
@ResponseBody
@SessionAttributes("User")
public class CcaAcctController {
	
	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory.getLogger(CcaAcctController.class);
	
	/**
	 * 分页查询账户表
	 */
	@RequestMapping(value="/ccaaccts")
	public Map<String, Object> getccaaccts(@RequestParam Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		logger.info("------------------分页查询账户表开始-----------------");	
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("elacct") != null && reqmap.get("elacct") != "") {
			map.put("elacct", reqmap.get("elacct"));
		}
		if (reqmap.get("prodcd") != null && reqmap.get("prodcd") != "") {
			map.put("prodcd", reqmap.get("prodcd"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt((String) reqmap.get("length"), 10);
		int start = Integer.parseInt((String) reqmap.get("start"), 10);
		map.put("pageno", start / length+1);
		map.put("pagect", length);
		Map<String, Object> rspmap =new HashMap<String, Object>();
		rspmap = clict.callClient("ccacls", map);
		logger.info("------------------分页查询账户表结束-----------------");				
		rspmap.put("data", rspmap.get("infos")==null?"[]":rspmap.get("infos"));
		rspmap.put("iTotalDisplayRecords", rspmap.get("count"));
		rspmap.put("iTotalRecords", rspmap.get("count"));	
		return rspmap;
	}
	
	/**
	 * 锁定/解锁账户
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edccfroz")
	public Map<String, Object> edccfroz(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------冻结/解冻账户开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = clict.callClient("ccfroz", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "对账户操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------冻结/解冻账户开始------------------------");
		return resmap;
	}

}
