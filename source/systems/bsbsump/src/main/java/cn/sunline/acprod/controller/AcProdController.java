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

@Controller("AcProdController")
@RequestMapping(value = "/rest/acprod")
@ResponseBody
@SessionAttributes("User")
public class AcProdController {
	
	@Autowired
	ClientImpl client;
	
	private static Logger logger = LoggerFactory.getLogger(AcProdController.class);
	
	/**
	 * selAcprod 分页查询 红包积分产品定义表 数据集合信息
	 * @param reqmap 请求参数
	 * @param user 操作员
	 * @return 数据信息集合
	 * add by Helios.duan at 2015-07-08 @Beijing
	 */
	@RequestMapping(value = "/selAcprod")
	public Map<String, Object> selprd(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询红包积分产品基础属性接口对接开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		}
		if (reqmap.get("q_prodna") != null && reqmap.get("q_prodna") != "") {
			map.put("prodna", reqmap.get("q_prodna"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("start", start / length + 1);
		map.put("count", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("selprd", map);
		logger.debug("------------------查询红包积分产品基础属性接口对接结束-----------------");
		resmap.put("data",resmap.get("infos") == null ? "[]" : resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));
		return resmap;
	}
	/**
	 * selAcbsi 根据 产品编号 删除 红包积分产品定义表 数据
	 * @param reqmap 请求参数
	 * @param user 操作员
	 * @return 数据信息
	 * add by Helios.duan at 2015-07-08 @Beijing
	 */
	@RequestMapping(value = "/selAcprod", method = { RequestMethod.DELETE })
	public Map<String, Object> delprd(@RequestBody Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("-----------------红包积分产品基础属性接口对接开始删除接口开始-----------------"+ reqmap.toString());
		reqmap.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("delprd", reqmap);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "产品基础属性删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.debug("------------------红包积分产品基础属性接口对接开始删除接口结束-----------------");
		return resmap;
	}
	/**
	 * addAcprod 新增 红包积分产品定义表 数据
	 * @param reqmap 请求参数
	 * @param user 操作员
	 * @return 数据信息
	 * add by Helios.duan at 2015-07-08 @Beijing
	 */
	@RequestMapping(value = "/addAcprod")
	public Map<String, Object> addprd(@RequestBody Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------新增红包积分产品基础属性接口对接开始-----------------");
		reqmap.put("userid", user.getUserid());
		reqmap.put("corpno",user.getCorpno());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("insprd", reqmap);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "红包积分产品基础属性新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	/**
	 * uptAcprod 更新 红包积分产品定义表 数据
	 * @param reqmap 请求参数
	 * @param user 操作员
	 * @return 数据信息
	 * add by Helios.duan at 2015-07-08 @Beijing
	 */
	@RequestMapping(value = "/uptAcprod")
	public Map<String, Object> uptprd(@RequestBody Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------修改红包积分产品基础属性接口对接开始-----------------");
		reqmap.put("userid", user.getUserid());
		reqmap.put("corpno",user.getCorpno());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("updprd", reqmap);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "红包积分产品基础属性修改成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
}
