package cn.sunline.prod.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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

import cn.sunline.client.Client;
import cn.sunline.domain.endauth.BSBUser;



@Controller("KcmController")
@RequestMapping(value = "/rest/kcm")
@ResponseBody
@SessionAttributes("User")
public class KcmController {

	@Autowired
	Client client;
	private static Logger logger = LoggerFactory.getLogger(KcmController.class);
	
	@RequestMapping(value = "/selKcmMerc")
	public Map<String, Object> selKcmMerc(
			@RequestParam Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------查询商户信息开始------------------------"+req.toString());
		req.put("userid", user.getUserid());
//		req.put("corpno", user.getCorpno());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcqrmc", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "查询商户信息成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------查询商户信息结束------------------------");
		Map<String ,Object> rmap = new HashMap<String ,Object>();
		rmap.put("data", resmap.get("mercls")==null?"[]":resmap.get("mercls"));
		
		return rmap;
	}
	
	/**
	 * 增加商户信息
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addKcmMerc")
	public Map<String, Object> addKcmMerc(
			@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增商户信息开始------------------------"+req.toString());
		req.put("userid", user.getUserid());
//		req.put("corpno", user.getCorpno());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcinmc", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "新增商户成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------新增商户信息结束------------------------");		
		return resmap;
	}
	@RequestMapping(value = "/selKcmInfo")
	public Map<String, Object> selKcmInfo(
			@RequestParam Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------查询合作定价开始------------------------"+req.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("startt", start / length + 1);
		map.put("record", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcqrif", map);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "查询合作定价信息成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------查询合作定价信息结束------------------------");
		Map<String ,Object> rmap = new HashMap<String ,Object>();
		rmap.put("data", resmap.get("ouinfo")==null?"[]":resmap.get("ouinfo"));
		rmap.put("iTotalDisplayRecords", resmap.get("recdsm"));
		rmap.put("iTotalRecords", resmap.get("recdsm"));	
		
		return rmap;
	}
	
	/**
	 * 增加修改合作定价信息
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addKcmInfo")
	public Map<String, Object> addKcmInfo(
			@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增合作定价信息开始------------------------"+req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcinif", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "新增合作定价成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------新增合作定价信息结束------------------------");		
		return resmap;
	}
	
	/**
	 * 删除合作定价信息
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/selKcmInfo", method = { RequestMethod.DELETE })
	public Map<String, Object> delKcmInfo(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------删除合作定价信息开始------------------------"+req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcdeif", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "合作定价删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.debug("--------------------删除合作定价信息结束------------------------");
		return resmap;
	}
	
	/**
	 * 查询合作商信息
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/selKcmDefn")
	public Map<String, Object> selKcmDefn(
			@RequestParam Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------查询合作商信息开始------------------------"+req.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		
		map.put("defncd", req.get("defncd"));
		map.put("merccd", req.get("merccd"));
		map.put("cmtype", req.get("cmtype"));
		map.put("startt", start / length + 1);
		map.put("record", length);
		
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcqrkc", map);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "查询合作商信息成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------查询合作商信息结束------------------------");
		Map<String ,Object> rmap = new HashMap<String ,Object>();
		rmap.put("data", resmap.get("defnfo")==null?"[]":resmap.get("defnfo"));
		rmap.put("iTotalDisplayRecords", resmap.get("recdsm"));
		rmap.put("iTotalRecords", resmap.get("recdsm"));	
		
		return rmap;
	}
	
	/**
	 * 增加修改合作商信息
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addKcmDefn")
	public Map<String, Object> addKcmDefn(
			@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增合商开始------------------------"+req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcinkc", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "新增合作商成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------新增合作商结束------------------------");		
		return resmap;
	}
	/**
	 * 增加修改合作商圈关系
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addKcmRelaRing")
	public Map<String, Object> addKcmRelaRing(
			@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增合商圈开始------------------------"+req.toString());
		Map<String, Object> reqmap = new HashMap<String, Object>();
		reqmap.put("userid", user.getUserid());
		reqmap.put("defncd", req.get("cycle_defncd"));
		reqmap.put("merccd", req.get("cycle_merccd"));
		reqmap.put("merctp", "1");
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcinkc", reqmap);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "新增合作关系圈商圈成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------新增合作商圈结束------------------------");		
		return resmap;
	}
	/**
	 * 增加修改合作商圈关系
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addKcmRelaTree")
	public Map<String, Object> addKcmRelaTree(
			@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增合商树开始------------------------"+req.toString());
		Map<String, Object> reqmap = new HashMap<String, Object>();
		reqmap.put("userid", user.getUserid());
		reqmap.put("defncd", req.get("tree_defncd"));
		reqmap.put("merccd", req.get("tree_merccd"));
		reqmap.put("pmercd", req.get("tree_pmercd"));
		reqmap.put("merctp", "3");
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcinkc", reqmap);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "新增合作关系树成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------新增合作商树关系结束------------------------");		
		return resmap;
	}
	/**
	 * 增加修改合作商链关系
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addKcmRelaLink")
	public Map<String, Object> addKcmRelaLink(
			@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增合商链开始------------------------"+req.toString());
		Map<String, Object> reqmap = new HashMap<String, Object>();
		reqmap.put("userid", user.getUserid());
		reqmap.put("defncd", req.get("link_defncd"));
		reqmap.put("merccd", req.get("link_merccd"));
		reqmap.put("corefg", req.get("link_corefg"));
		reqmap.put("relatp", req.get("link_relatp"));
		reqmap.put("merctp", "2");
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcinkc", reqmap);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "新增合作商关系链成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------新增合作商链结束------------------------");		
		return resmap;
	}
	
	/**
	 * 合作商-删除
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/selKcmDefn", method = {RequestMethod.DELETE})
	public Map<String, Object> delKcmDefn(@RequestBody Map<String, Object> req, @ModelAttribute("User") BSBUser user){
		logger.debug("------------合作商-删除--开始----------------");
		req.put("userid", user.getUserid());
		Map<String, Object> resMap = client.callClient("kcdekc", req);
		if(resMap.get("retCode").toString().equals("0000")){
			resMap.put("ret", "success");
			resMap.put("msg", "删除合作商成功");
		}else{
			resMap.put("msg", resMap.get("retMsg").toString());
		}
		logger.debug("------------合作商-删除--结束----------------");
		return resMap;
	}
	
	
	/**
	 * 增加定价属性信息
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addKcmtmplprop")
	public Map<String, Object> addKcmtmplprop(
			@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增定价属性信息开始------------------------"+req.toString());
		req.put("userid", user.getUserid());
//		req.put("corpno", user.getCorpno());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcintp", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "新增商户成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------新增定价属性信息结束------------------------");		
		return resmap;
	}
	
	/**
	 * 查询定价属性信息
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/selKcmtmplprop")
	public Map<String, Object> selKcmtmplprop(
			@RequestParam Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------查询定价属性信息开始------------------------"+req.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("startt", start / length + 1);
		map.put("record", length);
		map.put("propcd", req.get("propcd")==null?"":req.get("propcd"));
		map.put("proptp", req.get("proptp")==null?"":req.get("proptp"));
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcqrtp", map);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "查询定价属性信息成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------查询定价属性信息结束------------------------");
		resmap.put("data", resmap.get("tmprop")==null?"[]":resmap.get("tmprop"));
		resmap.put("iTotalDisplayRecords", resmap.get("recdsm"));
		resmap.put("iTotalRecords", resmap.get("recdsm"));
		
		return resmap;
	}
	
	/**
	 * 删除定价属性信息
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/selKcmtmplprop", method = { RequestMethod.DELETE })
	public Map<String, Object> delKcmtmplprop(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------删除定价属性信息开始------------------------"+req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcdetp", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "定价属性删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.debug("--------------------删除定价属性信息结束------------------------");
		return resmap;
	}

	/**
	 * 增加活动
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addKcmActv")
	public Map<String, Object> addKcmActv(
			@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增活动开始------------------------"+req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcinav", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "新增活动成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------新增活动结束------------------------");		
		return resmap;
	}
	

	
	
	/**
	 * 合作商定价模版-新增和修改
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addKcmTmpl")
	public Map<String, Object> addKcmTmpl(@RequestBody Map<String, Object> req, @ModelAttribute("User") BSBUser user){
		logger.debug("------------合作商定价模版-新增和修改--开始----------------");
		logger.debug("交易柜员========="+user.getUserid());
		req.put("userid", user.getUserid());
		Map<String, Object> resMap = client.callClient("kcintm", req);
		if(resMap.get("retCode").toString().equals("0000")){
			resMap.put("ret", "success");
			resMap.put("msg", "合作商定价模版-新增--成功");
		}else{
			resMap.put("msg", resMap.get("retMsg").toString());
		}
		logger.debug("------------合作商定价模版-新增和修改--结束----------------");
		return resMap;
	}
	
	/**
	 * 合作商定价模版-查询
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/selKcmTmpl")
	public Map<String, Object> selKcmTmpl(@RequestParam Map<String, Object> req, @ModelAttribute("User") BSBUser user){
		logger.debug("------------合作商定价模版-查询--开始----------------");
		Map<String, Object> map = new HashMap<>();
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String)req.get("length")) ? (String)req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String)req.get("start")) ? (String)req.get("start") : "1", 10);
		map.put("startt", start / length + 1);
		map.put("record", length);
		Map<String, Object> resMap = client.callClient("kcqrtm", map);
		resMap.put("data", resMap.get("tmpout") == null ? new ArrayList<Object>() : resMap.get("tmpout"));
		resMap.put("iTotalDisplayRecords", resMap.get("recdsm") == null ? "0" : resMap.get("recdsm"));
		resMap.put("iTotalRecords", resMap.get("recdsm") == null ? "0" : resMap.get("recdsm"));
		logger.debug("------------合作商定价模版-查询--结束----------------");
		return resMap;
	}
	
	/**
	 * 合作商定价模版-删除
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/selKcmTmpl", method = {RequestMethod.DELETE})
	public Map<String, Object> delKcmTmpl(@RequestBody Map<String, Object> req, @ModelAttribute("User") BSBUser user){
		logger.debug("------------合作商定价模版-删除--开始----------------");
		req.put("userid", user.getUserid());
		Map<String, Object> resMap = client.callClient("kcdetm", req);
		if(resMap.get("retCode").toString().equals("0000")){
			resMap.put("ret", "success");
			resMap.put("msg", "合作商定价模版-删除--成功");
		}else{
			resMap.put("msg", resMap.get("retMsg").toString());
		}
		logger.debug("------------合作商定价模版-删除--结束----------------");
		return resMap;
	}
	
	/**
	 * 合作商定价模版配置-新增和修改
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addKcmTmplConf")
	public Map<String, Object> addKcmTmplConf(@RequestBody Map<String, Object> req, @ModelAttribute("User") BSBUser user){
		logger.debug("------------合作商定价模版配置-新增和修改--开始----------------");
		req.put("userid", user.getUserid());
		Map<String, Object> resMap = client.callClient("kcintc", req);
		if(resMap.get("retCode").toString().equals("0000")){
			resMap.put("ret", "success");
			resMap.put("msg", "合作商定价模版-新增--成功");
		}else{
			resMap.put("msg", resMap.get("retMsg").toString());
		}
		logger.debug("------------合作商定价模版配置-新增和修改--结束----------------");
		return resMap;
	}
	
	/**
	 * 合作商定价模版配置-查询
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/selKcmTmplConf")
	public Map<String, Object> selKcmTmplConf(@RequestParam Map<String, Object> req, @ModelAttribute("User") BSBUser user){
		logger.debug("------------合作商定价模版配置-查询--开始----------------");
		Map<String, Object> map = new HashMap<>();
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String)req.get("length")) ? (String)req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String)req.get("start")) ? (String)req.get("start") : "1", 10);
		map.put("startt", start / length + 1);
		map.put("record", length);
		map.put("busicd", req.get("busicd")==null?"":req.get("busicd"));
		map.put("tmplcd", req.get("tmplcd")==null?"":req.get("tmplcd"));
		map.put("propcd", req.get("propcd")==null?"":req.get("propcd"));
		Map<String, Object> resMap = client.callClient("kcqrtc", map);
		if (resMap.get("retCode").toString().equals("0000")) {
			resMap.put("ret", "success");
			resMap.put("msg", "查询合作商定价模版配置成功");
		} else {
			resMap.put("msg", resMap.get("retMsg").toString());
		}
		resMap.put("data", resMap.get("cnfout") == null ? new ArrayList<>() : resMap.get("cnfout"));
		resMap.put("iTotalDisplayRecords", resMap.get("recdsm") == null ? "0" : resMap.get("recdsm"));
		resMap.put("iTotalRecords", resMap.get("recdsm") == null ? "0" : resMap.get("recdsm"));
		logger.debug("------------合作商定价模版配置-查询--结束----------------");
		return resMap;
	}
	
	/**
	 * 合作商定价模版配置-删除
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/selKcmTmplConf", method = {RequestMethod.DELETE})
	public Map<String, Object> delKcmTmplConf(@RequestBody Map<String, Object> req, @ModelAttribute("User") BSBUser user){
		logger.debug("------------合作商定价模版配置-删除--开始----------------");
		req.put("userid", user.getUserid());
		Map<String, Object> resMap = client.callClient("kcdetc", req);
		if(resMap.get("retCode").toString().equals("0000")){
			resMap.put("ret", "success");
			resMap.put("msg", "删除合作商定价模版配置成功");
		}else{
			resMap.put("msg", resMap.get("retMsg").toString());
		}
		logger.debug("------------合作商定价模版配置-删除--结束----------------");
		return resMap;
	}
	

	
	/**
	 * 查询活动信息
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/selKcmActv")
	public Map<String, Object> selKcmActv(
			@RequestParam Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------查询活动信息开始------------------------"+req.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		map.put("actvcd", "");
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("startt", start / length + 1);
		map.put("record", length);

		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcqrav", map);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "查询活动信息成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------查询活动信息结束------------------------");
		Map<String ,Object> rmap = new HashMap<String ,Object>();
		rmap.put("data", resmap.get("ouactv")==null?"[]":resmap.get("ouactv"));
		rmap.put("iTotalDisplayRecords", resmap.get("recdsm"));
		rmap.put("iTotalRecords", resmap.get("recdsm"));	
		
		return rmap;
	}
	
	/**
	 * 活动删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/selKcmActv", method = { RequestMethod.DELETE })
	public Map<String, Object> dbrdel(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcdeav", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "活动删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
	
	/**
	 * 增加活动适用范围
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addKcmSuitScop")
	public Map<String, Object> addKcmSuitScop(
			@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增活动适用范围开始------------------------"+req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcinss", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "新增活动适用范围成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------新增活动适用范围结束------------------------");		
		return resmap;
	}
	
	
	/**
	 * 查询活动适用范围信息
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/selKcmSuitScop")
	public Map<String, Object> selKcmSuitScop(
			@RequestParam Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------查询活动适用范围信息开始------------------------"+req.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		map.put("actvcd", "");
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("startt", start / length + 1);
		map.put("record", length);
		
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcqrss", map);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "查询活动适用范围信息成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------查询活动适用范围信息结束------------------------");
		Map<String ,Object> rmap = new HashMap<String ,Object>();
		rmap.put("data", resmap.get("ouactv")==null?"[]":resmap.get("ouactv"));
		rmap.put("iTotalDisplayRecords", resmap.get("recdsm"));
		rmap.put("iTotalRecords", resmap.get("recdsm"));	
		
		return rmap;
	}
	
	/**
	 * 活动适用范围删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/selKcmSuitScop", method = { RequestMethod.DELETE })
	public Map<String, Object> delKcmSuitScop(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("kcdess", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "活动适用范围删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
	/**
	 * 查询定价模板配置信息
	 * @param req
	 * @param user
	 * @return
	 */
//	@RequestMapping(value = "/selKcmSuitScop")
//	public Map<String, Object> selKcmTmplConf(
//			@RequestParam Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
//		logger.debug("--------------------查询定价模板配置信息开始------------------------"+req.toString());
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("userid", user.getUserid());
//		map.put("actvcd", "");
//		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
//				.get("length")) ? (String) req.get("length") : "10", 10);
//		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
//				.get("start")) ? (String) req.get("start") : "1", 10);
//		map.put("startt", start / length + 1);
//		map.put("record", length);
//		
//		Map<String, Object> resmap = new HashMap<String, Object>();
//		resmap = client.callClient("kcqrtc", map);
//		if (resmap.get("retCode").toString().equals("0000")) {
//			resmap.put("ret", "success");
//			resmap.put("msg", "查询定价模板配置信息成功");
//		} else {
//			resmap.put("msg", resmap.get("retMsg").toString());
//		}			
//		logger.debug("--------------------查询定价模板配置信息结束------------------------");
//		Map<String ,Object> rmap = new HashMap<String ,Object>();
//		rmap.put("data", resmap.get("ouactv")==null?"[]":resmap.get("ouactv"));
//		rmap.put("iTotalDisplayRecords", resmap.get("recdsm"));
//		rmap.put("iTotalRecords", resmap.get("recdsm"));	
//		
//		return rmap;
//	}
	
}
