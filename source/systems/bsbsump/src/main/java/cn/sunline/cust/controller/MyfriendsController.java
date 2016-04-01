package cn.sunline.cust.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.sunline.client.ClientImpl;
import cn.sunline.domain.endauth.BSBUser;

@Controller("MyfriendsController")
@RequestMapping(value = "/rest/myfriends")
@ResponseBody
@SessionAttributes("User")
public class MyfriendsController {
	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory.getLogger(CustController.class);
	
	/*
	 * 客户信息查询
	 */
	@RequestMapping(value ="/myfriend")
	public Map<String,Object> getCust(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid()); //设置操作柜员	
		if (reqmap.get("custac") != null && !"".equals(reqmap.get("custac"))) {
			map.put("custac", reqmap.get("custac"));
		}
		if (reqmap.get("custpt") != null && !"".equals(reqmap.get("custpt"))) {
			map.put("custpt", reqmap.get("custpt"));
		}
		if (reqmap.get("idtfno") != null && !"".equals(reqmap.get("idtfno"))) {
			map.put("idtfno", reqmap.get("idtfno"));
		}
		if (reqmap.get("teleno") != null && !"".equals(reqmap.get("teleno"))) {
			map.put("teleno", reqmap.get("teleno"));
		}
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);//当前页数
		map.put("record", length); //每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = clict.callClient("qrmyfd", map);
		System.out.println("----------------------------"+resmap.get("myfdms"));
		resmap.put("data",resmap.get("myfdms") == null ? new ArrayList<Object>() : resmap.get("myfdms"));
		resmap.put("iTotalDisplayRecords",resmap.get("counts") == null ? "0" : resmap.get("counts"));
		resmap.put("iTotalRecords",resmap.get("counts") == null ? "0" : resmap.get("counts"));
		return resmap;
	}

}
