package cn.sunline.fuyou.controller;

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

@Controller("FuyouController")
@RequestMapping(value = "/rest/fuyou")
@ResponseBody
@SessionAttributes("User")
public class FuyouController {
	@Autowired
	ClientImpl client;
	private static Logger logger = LoggerFactory
			.getLogger(FuyouController.class);

	@RequestMapping(value = "/clearfy")
	public Map<String, Object> clearFuyou(
			@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------富友清算开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("infyqs", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "清算成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.debug("--------------------富友清算结束------------------------");

		return resmap;
	}
}
