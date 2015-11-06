package cn.sunline.cust.controller;

import java.util.Date;
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
}
