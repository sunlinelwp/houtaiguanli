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

@Controller("BnpShierController")
@RequestMapping(value = "/rest/cc")
@ResponseBody
@SessionAttributes("User")
public class BnpShierController {
	
	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory.getLogger(BnpShierController.class);
		
	/**
	 * 分页查询BNP优先级参数
	 */
	@RequestMapping(value="/bnpshier")
	public Map<String, Object> getbnpshiers(@RequestParam Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		logger.info("------------------分页查询BNP优先级参数开始-----------------");	
		Map<String, Object> map = new HashMap<String, Object>();
		//判断锁定码字段
		if (reqmap.get("q_hierid") != null && reqmap.get("q_hierid") != "") {
			map.put("hierid", reqmap.get("q_hierid"));
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
		rspmap = clict.callClient("qrbphi", map);
		logger.info("------------------分页查询BNP优先级参数结束-----------------");				
		rspmap.put("data", rspmap.get("infos")==null?"[]":rspmap.get("infos"));
		rspmap.put("iTotalDisplayRecords", rspmap.get("count"));
		rspmap.put("iTotalRecords", rspmap.get("count"));	
		return rspmap;
	}
	
	/**
	 * 新增修改BNP优先级参数
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edbnpshier")
	public Map<String, Object> edbphi(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------新增修改BNP优先级参数开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = clict.callClient("edbphi", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "对锁定码操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------新增修改BNP优先级参数结束------------------------");
		return resmap;
	}
	
	/**
	 *删除BNP优先级参数
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/bnpshier", method = { RequestMethod.DELETE })
	public Map<String, Object> deprod(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------删除BNP优先级参数开始------------------------");
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = clict.callClient("debphi", req); 
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "BNP优先级参数删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------删除BNP优先级参数结束------------------------");
		return resmap;
	}

}
