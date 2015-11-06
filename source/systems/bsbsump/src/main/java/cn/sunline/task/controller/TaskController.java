package cn.sunline.task.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import cn.sunline.client.Client;
import cn.sunline.domain.endauth.BSBUser;
import cn.sunline.service.endauth.SifSysParaService;

@Controller("TaskController")
@RequestMapping(value = "/rest/task")
@ResponseBody
@SessionAttributes("User")
public class TaskController {
	@Autowired
	Client client;
	
	private static Logger logger = LoggerFactory.getLogger(TaskController.class);
	/**
	 * 执行任务
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dotask")
	public Map<String,Object> dotask(@RequestBody Map<String,Object> reqmap,
			@ModelAttribute("User") BSBUser user){
		logger.debug("dotask------------------任务开始"+reqmap);
		reqmap.put("userid", user.getUserid());
		Map<String, Object> rspmap = new HashMap<String, Object>();
		rspmap = client.callClient("dotask", reqmap);
		logger.debug("dotask------------------任务结束"+rspmap);			
		return rspmap;		
	}
	/**
	 * 查询任务
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrtask")
	public Map<String,Object> qrtask(@RequestBody Map<String,Object> reqmap,
			@ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		Map<String, Object> rspmap = new HashMap<String, Object>();
		rspmap = client.callClient("qrtask", reqmap);		
		return rspmap;		
	}
	/**
	 * 查询批量任务
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrtkif")
	public Map<String,Object> qrtkif(@RequestBody Map<String,Object> reqmap,
			@ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		reqmap.put("brchcd", user.getBrchno());
		reqmap.put("corpno", user.getCorpno());
		Map<String, Object> rspmap = new HashMap<String, Object>();
		rspmap = client.callClient("qrtkif", reqmap);		
		return rspmap;		
	}
	
	/**
	 * 总账换日
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dogrop")
	public Map<String,Object> dogrop(@RequestBody Map<String,Object> reqmap,
			@ModelAttribute("User") BSBUser user){
		logger.debug("dogrop------------------任务开始"+reqmap);
		reqmap.put("userid", user.getUserid());
		Map<String, Object> rspmap = new HashMap<String, Object>();
		rspmap = client.callClient("dogrop", reqmap);
		logger.debug("dogrop------------------任务结束"+rspmap);			
		return rspmap;		
	}
}
