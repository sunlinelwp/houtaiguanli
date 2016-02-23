package cn.sunline.cust.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.sunline.client.ClientImpl;
import cn.sunline.domain.endauth.BSBUser;

@Controller("CardController")
@RequestMapping(value = "/rest/cust")
@ResponseBody
@SessionAttributes("User")
public class CardController {
	
	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory.getLogger(CorpController.class);
	
	/**
	 * 换绑卡
	 */
	@RequestMapping(value ="/qzcard")
	public Map<String,Object> qzcard(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		logger.info("------------------"+reqmap.toString());
		Map<String,Object> rspmap = clict.callClient("qzcard", reqmap);
		return rspmap;
	}
	
	/**
	 * 正常解绑卡
	 */
	@RequestMapping(value ="/hfcard")
	public Map<String,Object> hfcard(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		logger.info("------------------"+reqmap.toString());
		Map<String,Object> rspmap = clict.callClient("hfcard", reqmap);
		return rspmap;
	}
	

}
