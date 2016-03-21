package cn.sunline.cust.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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

import cn.sunline.client.ClientImpl;
import cn.sunline.domain.endauth.BSBUser;
import cn.sunline.exception.SumpException;

@Controller("CntxycController")
@RequestMapping(value = "/rest/cntxyc")
@ResponseBody
@SessionAttributes("User")
public class CntxycController {
	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory.getLogger(CustController.class);
	
	/*
	 * 客户信息查询
	 */
	@RequestMapping(value ="/cntxycs")
	public Map<String,Object> getCust(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid()); //设置操作柜员	
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);//当前页数
		map.put("record", length); //每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = clict.callClient("cntyin", map);
		resmap.put("data",resmap.get("cntxin") == null ? new ArrayList<Object>() : resmap.get("cntxin"));
		resmap.put("iTotalDisplayRecords",resmap.get("counts") == null ? "0" : resmap.get("counts"));
		resmap.put("iTotalRecords",resmap.get("counts") == null ? "0" : resmap.get("counts"));
		return resmap;
	}
	
	@RequestMapping(value ="/upfrsqs")
	public Map<String,Object> upcnsj(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try{
			rspmap=clict.callClient("upfrsq", reqmap);
		} catch(SumpException e){
		}
		return rspmap;
	}

}
