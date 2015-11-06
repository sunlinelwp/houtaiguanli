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

@Controller("TranCodeController")
@RequestMapping(value = "/rest/cc")
@ResponseBody
@SessionAttributes("User")
public class TranCodeController {
	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory.getLogger(TranCodeController.class);
	
	/**
	 * 分页查询交易码参数
	 */
	@RequestMapping(value="/trancode")
	public Map<String, Object> gettrancodes(@RequestParam Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		logger.info("------------------分页查询交易码参数开始-----------------");	
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_txncdx") != null && reqmap.get("q_txncdx") != "") {
			map.put("txncdx", reqmap.get("q_txncdx"));
		}
		if (reqmap.get("q_txndes") != null && reqmap.get("q_txndes") != "") {
			map.put("txndes", reqmap.get("q_txndes"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt((String) reqmap.get("length"), 10);
		int start = Integer.parseInt((String) reqmap.get("start"), 10);
		map.put("pageno", start / length+1);
		map.put("pagect", length);
		Map<String, Object> rspmap =new HashMap<String, Object>();
		rspmap = clict.callClient("qrtrcd", map);
		logger.info("------------------分页查询交易码参数结束-----------------");				
		rspmap.put("data", rspmap.get("infos")==null?"[]":rspmap.get("infos"));
		rspmap.put("iTotalDisplayRecords", rspmap.get("count"));
		rspmap.put("iTotalRecords", rspmap.get("count"));	
		return rspmap;
	}
	
	/**
	 * 
	 * 交易码参数新增/修改
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edtrancode")
	public Map<String, Object> edtrancode(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------新增修改交易码参数------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = clict.callClient("edtrcd", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "对交易码参数操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------新增修改交易码参数------------------------");
		return resmap;
	}
	
	/**
	 *交易码参数删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/trancode", method = { RequestMethod.DELETE })
	public Map<String, Object> detrancode(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------交易码参数删除开始------------------------");
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = clict.callClient("detrcd", req); 
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "交易码参数删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------交易码参数删除结束------------------------");
		return resmap;
	}

}
