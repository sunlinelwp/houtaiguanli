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

@Controller("TranCodeMappController")
@RequestMapping(value = "/rest/cc")
@ResponseBody
@SessionAttributes("User")
public class TranCodeMappController {
	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory.getLogger(TranCodeMappController.class);
	
	/**
	 * 分页查询系统交易码映射表
	 */
	@RequestMapping(value="/trancodemapp")
	public Map<String, Object> getTranCodeMapp(@RequestParam Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		logger.info("------------------查询分页系统交易码映射表开始-----------------");	
		Map<String, Object> map = new HashMap<String, Object>();
		//判断交易代码
		if (reqmap.get("q_stxncd") != null && reqmap.get("q_stxncd") != "") {
			map.put("txncdx", reqmap.get("q_stxncd"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt((String) reqmap.get("length"), 10);
		int start = Integer.parseInt((String) reqmap.get("start"), 10);
		map.put("pageno", start / length+1);
		map.put("pagect", length);
		Map<String, Object> rspmap =new HashMap<String, Object>();
		rspmap = clict.callClient("qrtcmp", map);
		logger.info("------------------查询分页系统交易码映射表结束-----------------");				
		rspmap.put("data", rspmap.get("infos")==null?"[]":rspmap.get("infos"));
		rspmap.put("iTotalDisplayRecords", rspmap.get("count"));
		rspmap.put("iTotalRecords", rspmap.get("count"));	
		return rspmap;
	}
	
	/**
	 * 新增修改系统交易码映射表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edtcmp")
	public Map<String, Object> edprod(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------新增修改系统交易码映射表------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = clict.callClient("edtcmp", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "对锁定码操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------新增修改系统交易码映射表------------------------");
		return resmap;
	}
	
	/**
	 *系统交易码映射表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/trancodemapp", method = { RequestMethod.DELETE })
	public Map<String, Object> deprod(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------系统交易码映射表删除开始------------------------");
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = clict.callClient("detcmp", req); 
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "系统交易码映射表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------系统交易码映射表删除结束------------------------");
		return resmap;
	}

}
