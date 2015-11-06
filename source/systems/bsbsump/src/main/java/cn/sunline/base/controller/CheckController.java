package cn.sunline.base.controller;

import java.util.HashMap;
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
import cn.sunline.exception.SumpException;
import cn.sunline.utils.Digests;

@Controller("CheckController")
@RequestMapping(value = "/rest/recheck")
@ResponseBody
@SessionAttributes("User")
public class CheckController {
	@Autowired
	private ClientImpl client;
	private static Logger logger = LoggerFactory.getLogger(CheckController.class);
	
	@RequestMapping(value="/personCheck")
	public Map<String, Object> personCheck(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		
		Map<String, Object> remap=new HashMap<String, Object>();
		
		remap.put("retCode", "0000");
		remap.put("retMsg", "12345");
		return remap;
	}
	
	@RequestMapping(value="/uschck")
	public Map<String, Object> uschck(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		
		Map<String, Object> rspmap=new HashMap<String, Object>();
		reqmap.put("userid", user.getUserid());
		if(reqmap.get("authpw")==null||reqmap.get("authus")==null){
			rspmap.put("retCode", "1002");
			rspmap.put("retMsg", "请求参数不全");
			return rspmap;
		}else{
			reqmap.put("authpw",Digests.GetMD5Code(reqmap.get("authpw").toString()));
		}
		try{
			rspmap =  client.callClient("uschck", reqmap);
		}catch(SumpException e){
			logger.debug("调用通讯模块出错", e);
			rspmap.put("retCode", "1001");
			rspmap.put("retMsg", "报文解析出错");
		}
		return rspmap;
	}
	
	@RequestMapping(value="/testCheck")
	public Map<String, Object> testCheck(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		
		Map<String, Object> remap=new HashMap<String, Object>();
		
		remap.put("retCode", "0000");
		remap.put("retMsg", "12345");
		return remap;
	}

	

}
