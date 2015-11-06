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

@Controller("PlanTpltController")
@RequestMapping(value = "/rest/cc")
@ResponseBody
@SessionAttributes("User")
public class PlanTpltController {
	
	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory.getLogger(PlanTpltController.class);
	
	/**
	 * 分页查询信用计划模板参数表
	 */
	@RequestMapping(value="/plantplt")
	public Map<String, Object> getplantplts(@RequestParam Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		logger.info("------------------分页查询信用计划模板参数表开始-----------------");	
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_planbr") != null && reqmap.get("q_planbr") != "") {
			map.put("planbr", reqmap.get("q_planbr"));
		}
		if (reqmap.get("q_plande") != null && reqmap.get("q_plande") != "") {
			map.put("plande", reqmap.get("q_plande"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt((String) reqmap.get("length"), 10);
		int start = Integer.parseInt((String) reqmap.get("start"), 10);
		map.put("pageno", start / length+1);
		map.put("pagect", length);
		Map<String, Object> rspmap =new HashMap<String, Object>();
		rspmap = clict.callClient("qrtplt", map);
		logger.info("------------------分页查询信用计划模板参数表结束-----------------");				
		rspmap.put("data", rspmap.get("infos")==null?"[]":rspmap.get("infos"));
		rspmap.put("iTotalDisplayRecords", rspmap.get("count"));
		rspmap.put("iTotalRecords", rspmap.get("count"));	
		return rspmap;
	}
	
	/**
	 *查询利率表id
	 */
	@RequestMapping(value="/qrrtid")
	public Map<String, Object> getqrrtid(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		Map<String, Object> rspmap =new HashMap<String, Object>();
		rspmap = clict.callClient("qrrtid", map);
		logger.info("------------------查询查询利率表id结束-----------------");				
		return rspmap;
	}
	
	/**
	 * 查询单条信用计划模板参数
	 */
	@RequestMapping(value="/qruntp")
	public Map<String, Object> getplantplt(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		logger.info("------------------查询单条信用计划模板参数表开始-----------------");	
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("planbr") != null && reqmap.get("planbr") != "") {
			map.put("planbr", reqmap.get("planbr"));
		}
		map.put("userid", user.getUserid());
		Map<String, Object> rspmap =new HashMap<String, Object>();
		rspmap = clict.callClient("qruntp", map);
		return rspmap;
	}
	
	/**
	 * 新增修改信用计划模板参数
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edplantplt")
	public Map<String, Object> edplantplt(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------新增修改信用计划模板参数开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());						
		if (req.get("bnpidx1") != null && req.get("bnpidx1") != "") {
			req.put("pribnp",req.get("bnpidx1"));
			req.put("bnptpx1","PRI");
		}
		if (req.get("bnpidx2") != null && req.get("bnpidx2") != "") {
			req.put("intbnp",req.get("bnpidx2"));
			req.put("bnptpx2","INT");
		}
		if (req.get("bnpidx3") != null && req.get("bnpidx3") != "") {
			req.put("txfbnp",req.get("bnpidx3"));
			req.put("bnptpx3","TXF");
		}
		if (req.get("bnpidx4") != null && req.get("bnpidx4") != "") {
			req.put("ltfbnp",req.get("bnpidx4"));
			req.put("bnptpx4","LTF");
		}
		Map<String, Object> resmap = clict.callClient("eduntp", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "对锁定码操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------新增修改信用计划模板参数结束------------------------");
		return resmap;
	}
	
	/**
	 *信用计划模板删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/plantplt", method = { RequestMethod.DELETE })
	public Map<String, Object> deprodlions(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.info("--------------------信用计划模板删除开始------------------------");
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = clict.callClient("detplt", req); 
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "信用计划模板删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.info("--------------------信用计划模板删除结束------------------------");
		return resmap;
	}


}
