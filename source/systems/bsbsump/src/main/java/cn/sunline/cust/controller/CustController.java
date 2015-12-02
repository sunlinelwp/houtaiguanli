package cn.sunline.cust.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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
import cn.sunline.exception.SumpException;

@Controller("CustController")
@RequestMapping(value = "/rest/cust")
@ResponseBody
@SessionAttributes("User")
public class CustController {
	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory.getLogger(CustController.class);
	
	/*
	 * 客户信息查询
	 */
	@RequestMapping(value ="/custinfo")
	public Map<String,Object> getCust(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String , Object> map = new HashMap<String, Object>();
		Date date = new Date();
		map.put("startt", reqmap.get("start"));
		map.put("record", reqmap.get("length"));
		map.put("ecctno", reqmap.get("ecctno"));
		map.put("custna", reqmap.get("custna"));
		map.put("idtftp", reqmap.get("idtftp"));
		map.put("idtfno", reqmap.get("idtfno"));
		map.put("teleno", reqmap.get("teleno"));
		map.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		Map<String,Object> remap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("eaccts", map);
		} catch (SumpException e) {
			remap.put("retCode", e.getErrCode());
			remap.put("retMsg", e.getErrMsg());
		}
		remap.put("sEcho", date.getTime());
		remap.put("iTotalRecords", rspmap.get("recdsm"));
		remap.put("iTotalDisplayRecords", rspmap.get("recdsm"));
		remap.put("data", rspmap.get("ecctou"));
		
		return remap;
	}
	
	/*
	 * 客户交易信息查询
	 */
	@RequestMapping(value = "/cutrif")
	public Map<String,Object> getCustTransInfo(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String , Object> map = new HashMap<String, Object>();
		Date date = new Date();
		int start = Integer.parseInt(reqmap.get("start").toString());
		int length  = Integer.parseInt(reqmap.get("length").toString());
		int pageno = start/length+1;
		map.put("pageno", pageno);
		map.put("recdct", length);
		map.put("ecctno", reqmap.get("ecctno").toString());
		map.put("bgindt", reqmap.get("from").toString());
		map.put("eenddt", reqmap.get("to").toString());
		map.put("eccttp", reqmap.get("eccttp").toString());
		map.put("crcycd", reqmap.get("crcycd").toString());
		map.put("userid", user.getUserid());
		logger.debug("请求map========"+map);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		Map<String,Object> remap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("qrtrdl", map);
		} catch (SumpException e) {
			remap.put("retCode", e.getErrCode());
			remap.put("retMsg", e.getErrMsg());
		}
		remap.put("sEcho", date.getTime());
		remap.put("iTotalRecords", rspmap.get("recdsm"));
		remap.put("iTotalDisplayRecords", rspmap.get("recdsm"));
		remap.put("data", rspmap.get("ecctlt"));
		return remap;
	}
	/*
	 * 客户信息维护
	 */
	@RequestMapping(value = "/update")
	public Map<String,Object> updateCif(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("upecct", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		return rspmap;
	}
	
	/*
	 * 大额审核登记信息查询
	 */
	@RequestMapping(value = "/qrhpay")
	public Map<String,Object> qrhpay(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String , Object> map = new HashMap<String, Object>();
		int start = Integer.parseInt(reqmap.get("start").toString());
		int length  = Integer.parseInt(reqmap.get("length").toString());
		int pageno = start/length+1;
		map.put("pageno", pageno);
		map.put("pagect", length);
		map.put("frondt", reqmap.get("q_frondt_sign") == null ? null : reqmap
				.get("q_frondt_sign"));
		map.put("status", reqmap.get("q_status") == null ? null : reqmap
				.get("q_status"));
		map.put("userid", user.getUserid());
		logger.debug("请求map========"+map);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		Map<String,Object> remap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("qrhpay", map);
		} catch (SumpException e) {
			remap.put("retCode", e.getErrCode());
			remap.put("retMsg", e.getErrMsg());
		}
		remap.put(
				"data",
				rspmap.get("bsInfs") == null ? new ArrayList<Object>() : rspmap
						.get("bsInfs"));
		remap.put("iTotalDisplayRecords", rspmap.get("counts") == null ? Integer.parseInt("1")
				: rspmap.get("counts"));
		remap.put("iTotalRecords",
				rspmap.get("counts") == null ? Integer.parseInt("1") : rspmap.get("counts"));
		
		return remap;
	}
	
	/*
	 * 大额审核登记信息修改
	 */
	@RequestMapping(value = "/ckrtsd")
	public Map<String,Object> ckrtsd(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		reqmap.put("chckur", user.getUserid());
		SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy",  Locale.UK);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			reqmap.put("chckdt", sdf.format(sdf1.parse(user.getSystdt())));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		reqmap.put("target", "1");//让前置拦截
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("ckrtsd", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		if (rspmap.get("retCode").toString().equals("0000")) {
			rspmap.put("ret", "success");
			rspmap.put("msg", "大额提现审核登记信息修改操作成功");
		} else {
			rspmap.put("msg", rspmap.get("retMsg").toString());
		}
		return rspmap;
	}
	
	/*
	 * 大额审核订单信息查询
	 */
	@RequestMapping(value = "/qrordr")
	public Map<String,Object> qrordr(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String , Object> map = new HashMap<String, Object>();
		int start = Integer.parseInt(reqmap.get("start").toString());
		int length  = Integer.parseInt(reqmap.get("length").toString());
		int pageno = start/length+1;
		map.put("pageno", pageno);
		map.put("pagect", length);
		map.put("fronsq", reqmap.get("q_fronsq") == null ? null : reqmap
				.get("q_fronsq"));
		map.put("frondt", reqmap.get("q_frondt") == null ? null : reqmap
				.get("q_frondt"));
		
		map.put("userid", user.getUserid());
		logger.debug("请求map========"+map);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		Map<String,Object> remap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("qrordr", map);
		} catch (SumpException e) {
			remap.put("retCode", e.getErrCode());
			remap.put("retMsg", e.getErrMsg());
		}
		remap.put(
				"data",
				rspmap.get("ordrinfo") == null ? new ArrayList<Object>() : rspmap
						.get("ordrinfo"));
		remap.put("iTotalDisplayRecords", rspmap.get("counts") == null ? Integer.parseInt("0")
				: rspmap.get("counts"));
		remap.put("iTotalRecords",
				rspmap.get("counts") == null ? Integer.parseInt("0") : rspmap.get("counts"));
		
		return remap;
	}
	
	/*
	 * 大额审核订单信息新增
	 */
	@RequestMapping(value = "/hrutsg")
	public Map<String,Object> hrutsg(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		reqmap.put("chckur", user.getUserid());
		reqmap.put("target", "1");//让前置拦截
		SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy",  Locale.UK);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			reqmap.put("chckdt", sdf.format(sdf1.parse(user.getSystdt())));
//			reqmap.put("ordrtm", sdf.format(sdf1.parse(user.getSystdt())));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("hrutsg", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		
		if (rspmap.get("retCode").toString().equals("0000")) {
			rspmap.put("ret", "success");
			rspmap.put("msg", "大额提现订单插入操作成功");
		} else {
			if (rspmap.get("retMsg").toString().startsWith("-1:[hfax0009]")) {
				rspmap.put("msg", rspmap.get("retMsg").toString().substring(13));
			} else {
				rspmap.put("msg", rspmap.get("retMsg").toString().substring(13));
			}
		}
		return rspmap;
	}
	
	
	/*
	 * 大额审核登记信息查询(对账之后修改信息)
	 */
	@RequestMapping(value = "/qrpyif")
	public Map<String,Object> qrpyif(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String , Object> map = new HashMap<String, Object>();
		int start = Integer.parseInt(reqmap.get("start").toString());
		int length  = Integer.parseInt(reqmap.get("length").toString());
		int pageno = start/length+1;
		map.put("pageno", pageno);
		map.put("pagect", length);
		map.put("frondt", reqmap.get("q_frondt_sign") == null ? null : reqmap
				.get("q_frondt_sign"));
		map.put("status", reqmap.get("q_status") == null ? null : reqmap
				.get("q_status"));
		map.put("userid", user.getUserid());
		logger.debug("请求map========"+map);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		Map<String,Object> remap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("qrpyif", map);
		} catch (SumpException e) {
			remap.put("retCode", e.getErrCode());
			remap.put("retMsg", e.getErrMsg());
		}
		
		remap.put(
				"data",
				rspmap.get("bsInfs") == null ? new ArrayList<Object>() : rspmap
						.get("bsInfs"));
		remap.put("iTotalDisplayRecords", rspmap.get("counts") == null ? Integer.parseInt("1")
				: rspmap.get("counts"));
		remap.put("iTotalRecords",
				rspmap.get("counts") == null ? Integer.parseInt("1") : rspmap.get("counts"));
		
		return remap;
	}
	
	/*
	 * 大额审核订单信息新增
	 */
	@RequestMapping(value = "/edhpay")
	public Map<String,Object> edhpay(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		reqmap.put("chckur", user.getUserid());
		reqmap.put("target", "1");//让前置拦截
		SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy",  Locale.UK);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			reqmap.put("chckdt", sdf.format(sdf1.parse(user.getSystdt())));
//			reqmap.put("ordrtm", sdf.format(sdf1.parse(user.getSystdt())));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("edhpay", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		
		if (rspmap.get("retCode").toString().equals("0000")) {
			rspmap.put("ret", "success");
			rspmap.put("msg", "大额提现订单插入操作成功");
		} else {
			if (rspmap.get("retMsg").toString().startsWith("-1:[hfax0009]")) {
				rspmap.put("msg", rspmap.get("retMsg").toString().substring(13));
			} else {
				rspmap.put("msg", rspmap.get("retMsg").toString().substring(13));
			}
		}
		return rspmap;
	}
	
}
