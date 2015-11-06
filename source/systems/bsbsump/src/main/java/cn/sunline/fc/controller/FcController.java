package cn.sunline.fc.controller;

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
@Controller("FcController")
@RequestMapping(value = "/rest/fc")
@ResponseBody
@SessionAttributes("User")
public class FcController {

	@Autowired
	private ClientImpl client;
	private static Logger logger = LoggerFactory.getLogger(FcController.class);
	/**
	 * 清算查询
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/findclear")
	public Map<String,Object> findclear(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String,Object> rspmap=new HashMap<String, Object>();
		
		reqmap.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		reqmap.put("startx", start / length + 1);
		reqmap.put("pagect", length);
		rspmap = client.callClient("qrcler", reqmap);
		rspmap.put("data",rspmap.get("clerif") == null ? "[]" : rspmap.get("clerif"));
		rspmap.put("iTotalDisplayRecords", rspmap.get("counts") == null ?  0 : rspmap.get("counts"));
		rspmap.put("iTotalRecords", rspmap.get("counts") == null ?  0 : rspmap.get("counts"));
	    logger.debug(rspmap.toString());
		return rspmap;
	}
	/**
	 * 代付清算
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/payclear")
	public Map<String,Object> payclear(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String,Object> rspmap=new HashMap<String, Object>();
		reqmap.put("userid", user.getUserid());
		rspmap = client.callClient("fcpyst", reqmap);
//		if(rspmap.get("retCode").equals("0000")){
//			Map<String,Object>	flowMap=client.callClient("fcsmsg", reqmap);
//		}
		return rspmap;
	}
	/**
	 * 代扣清算
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/rcvclear")
	public Map<String,Object> rcvclear(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String,Object> rspmap=new HashMap<String, Object>();
		reqmap.put("userid", user.getUserid());
		rspmap = client.callClient("fcrest", reqmap);
		return rspmap;
	}
	/**
	 * 回款分配数据查询
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/findasgn")
	public Map<String,Object> findasgn(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String,Object> rspmap=new HashMap<String, Object>();
		
		reqmap.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		reqmap.put("pageno", start / length + 1);
		reqmap.put("pagesz", length);
		rspmap = client.callClient("qrfcas", reqmap);
		rspmap.put("data",rspmap.get("fcAsgnInfos") == null ? "[]" : rspmap.get("fcAsgnInfos"));
		rspmap.put("iTotalDisplayRecords", rspmap.get("count") == null ?  0 : rspmap.get("count"));
		rspmap.put("iTotalRecords", rspmap.get("count") == null ?  0 : rspmap.get("count"));
	    logger.debug(rspmap.toString());
		return rspmap;
	}
	/**
	 * 回款分配
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/fcasgn")
	public Map<String,Object> fcasgn(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String,Object> rspmap=new HashMap<String, Object>();
		reqmap.put("userid", user.getUserid());
		rspmap = client.callClient("fcasgn", reqmap);
		return rspmap;
	}
}
