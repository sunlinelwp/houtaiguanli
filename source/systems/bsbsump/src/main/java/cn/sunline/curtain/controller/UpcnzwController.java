package cn.sunline.curtain.controller;

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

import cn.sunline.base.controller.CheckController;
import cn.sunline.client.ClientImpl;
import cn.sunline.domain.endauth.BSBUser;
import cn.sunline.exception.SumpException;
import cn.sunline.utils.Digests;

@Controller("UpcnzwController")
@RequestMapping(value = "/rest/upcntain")
@ResponseBody
@SessionAttributes("User")
public class UpcnzwController {
	@Autowired
	private ClientImpl client;
	private static Logger logger = LoggerFactory.getLogger(CheckController.class);
	@RequestMapping(value ="/upcnsj")
	public Map<String,Object> upcnsj(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		reqmap.put("servno", "104");
		if (reqmap.get("acctno") != null && !"".equals(reqmap.get("acctno"))) {
			reqmap.put("acctno", reqmap.get("acctno"));
		}
		if (reqmap.get("tranam") != null && !"".equals(reqmap.get("tranam"))) {
			reqmap.put("tranam", reqmap.get("tranam"));
		}
		if (reqmap.get("coresq") != null && !"".equals(reqmap.get("coresq"))) {
			reqmap.put("coresq", reqmap.get("coresq"));
		}
		System.out.println("----------------------"+reqmap);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try{
			rspmap=client.callClient("seabfo", reqmap);
		} catch(SumpException e){
		}
		return rspmap;
	}
}
