package cn.sunline.fund.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.sunline.client.ClientImpl;
import cn.sunline.domain.endauth.BSBUser;

@Controller("FundJDController")
@RequestMapping(value = "/rest/fund")
@ResponseBody
@SessionAttributes("User")
public class FundJDController {
	private static Logger logger = LoggerFactory.getLogger(FundJDController.class);
   
	@Autowired
	private ClientImpl client;
	
	/**
	 * 待解冻查询查询
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrfddj")
	public Map<String,Object> qrfddj(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String,Object> rspmap=new HashMap<String, Object>();
		reqmap.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		reqmap.put("pageno", start / length );
		reqmap.put("pagesize", length);
		rspmap = client.callClient("qrfddj", reqmap);
		rspmap.put("data",rspmap.get("infos") == null ? "[]" : rspmap.get("infos"));
		rspmap.put("iTotalDisplayRecords", rspmap.get("pcounts") == null ?  0 : rspmap.get("pcount"));
		rspmap.put("iTotalRecords", rspmap.get("pcount") == null ?  0 : rspmap.get("pcount"));
	    logger.debug(rspmap.toString());
		return rspmap;
	}
	
	/**
	 * 赎回解冻
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/rdopfd")
	public Map<String,Object> rdopfd(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String,Object> rspmap=new HashMap<String, Object>();
		reqmap.put("userid", user.getUserid());
		logger.debug("dddddd"+reqmap.toString());
		rspmap = client.callClient("rdopfd", reqmap);
	    logger.debug(rspmap.toString());
		return rspmap;
	}
}
