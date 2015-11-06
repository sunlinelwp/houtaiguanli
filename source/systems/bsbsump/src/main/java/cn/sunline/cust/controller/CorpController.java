package cn.sunline.cust.controller;

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

/**
 * 对公客户相关处理
 * @author L H
 * @since 2015年9月25日11:55:52
 */
@Controller("CorpController")
@RequestMapping(value = "/rest/cust")
@ResponseBody
@SessionAttributes("User")
public class CorpController {
	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory.getLogger(CorpController.class);
	
	/**
	 * 新增对公客户信息（开立电子账户）
	 */
	@RequestMapping(value ="/opcpac")
	public Map<String,Object> opcpac(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		logger.info("------------------"+reqmap.toString());
		Map<String,Object> rspmap = clict.callClient("opcpac", reqmap);
		return rspmap;
	}
	
	@RequestMapping(value = "/cpacct")
	public Map<String , Object> acct(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		logger.info("请求参数================="+reqmap);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try{
			rspmap =  clict.callClient("gecpac", reqmap);
		} catch(SumpException e){
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("返回参数================="+rspmap);
		return rspmap;
	}
	
}
