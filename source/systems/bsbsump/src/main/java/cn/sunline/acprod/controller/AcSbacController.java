package cn.sunline.acprod.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.sunline.client.ClientImpl;
import cn.sunline.domain.endauth.BSBUser;

@Controller("AcSbacController")
@RequestMapping(value = "/rest/acpsbc")
@ResponseBody
@SessionAttributes("User")
public class AcSbacController {
	
	@Autowired
	ClientImpl client;
	
	private static Logger logger = LoggerFactory.getLogger(AcSbacController.class);
	
	/**
	 * selAcsbc 分页查询 红包积分子户属性定义表 数据集合信息
	 * @param reqmap 请求参数
	 * @param user 操作员
	 * @return 数据信息集合
	 * add by Helios.duan at 2015-07-08 @Beijing
	 */
	@RequestMapping(value = "/selAcsbc")
	public Map<String, Object> selAcsbc(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询红包积分产品子账户属性接口对接开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("start", start / length + 1);
		map.put("count", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("selsbc", map);
		logger.debug("------------------查询红包积分产品子账户属性表接口对接结束-----------------");
		resmap.put("data",resmap.get("infos") == null ? "[]" : resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));
		return resmap;
	}
	/**
	 * selAcsbc 根据 产品编号 删除 红包积分子户属性定义表  数据
	 * @param reqmap 请求参数
	 * @param user 操作员
	 * @return 数据信息
	 * add by Helios.duan at 2015-07-08 @Beijing
	 */
	@RequestMapping(value = "/selAcsbc", method = { RequestMethod.DELETE })
	public Map<String, Object> delAcsbc(@RequestBody Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("-----------------红包积分产品子账户属性接口对接开始删除接口开始-----------------"+ reqmap.toString());
		reqmap.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("delsbc", reqmap);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "产品子账户属性删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.debug("------------------红包积分产品子账户属性接口对接开始删除接口结束-----------------");
		return resmap;
	}
	/**
	 * addAcsbc 新增 红包积分子户属性定义表 数据
	 * @param reqmap 请求参数
	 * @param user 操作员
	 * @return 数据信息
	 * add by Helios.duan at 2015-07-08 @Beijing
	 */
	@RequestMapping(value = "/addAcsbc")
	public Map<String, Object> addAcsbc(@RequestBody Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------新增红包积分产品子账户属性接口对接开始-----------------");
		reqmap.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("inssbc", reqmap);
		
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "红包积分产品子账户属性新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
	/**
	 * uptAcsbc 更新 红包积分子户属性定义表  数据
	 * @param reqmap 请求参数
	 * @param user 操作员
	 * @return 数据信息
	 * add by Helios.duan at 2015-07-08 @Beijing
	 */
	@RequestMapping(value = "/uptAcsbc")
	public Map<String, Object> uptAcsbc(@RequestBody Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------修改红包积分产品子账户接口对接开始-----------------");
		reqmap.put("userid", user.getUserid());
		reqmap.put("corpno",user.getCorpno());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("updsbc", reqmap);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "红包积分产品子账户属性修改成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
}
