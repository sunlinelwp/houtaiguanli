package cn.sunline.fc.controller;

import java.util.ArrayList;
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
@Controller("FcClearController")
@RequestMapping(value = "/rest/fc")
@ResponseBody
@SessionAttributes("User")
public class FcClearController {

	@Autowired
	private ClientImpl client;
	private static Logger logger = LoggerFactory.getLogger(FcClearController.class);
	
	@RequestMapping(value = "/findpay")
	public Map<String,Object> clearpay(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.debug("===========查询差错信息开始===========");
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
	 * 查询融资产品基础属性
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/fcqpro")
	public Map<String, Object> selfcpro(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询融资产品基础属性开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		}
		if (reqmap.get("q_prodna") != null && reqmap.get("q_prodna") != "") {
			map.put("prodna", reqmap.get("q_prodna"));
		}
		
		map.put("userid", user.getUserid()); // 设置操作柜员

		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagesz", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("fcqpro", map);
		resmap.put(
				"data",
				resmap.get("proInfos") == null ? new ArrayList<Object>() : resmap
						.get("proInfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count") == null ? "0"
				: resmap.get("count"));
		resmap.put("iTotalRecords",
				resmap.get("count") == null ? "0" : resmap.get("count"));
		logger.debug("-----------------查询融资产品基础属性结束-----------------");
		return resmap;
	}
	
	/**
	 *融资贷款产品基础删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/fcqpro", method = { RequestMethod.DELETE })
	public Map<String, Object> deprod(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("-----------------删除融资产品基础属性开始-----------------");
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("fcdpro", req); //
		logger.debug("-----------------删除融资产品基础属性结束-----------------");
		return resmap;
	}
	
	/**
	 * 查询贷款产品基础属性(单个)
	 * 
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/fcqrpr")
	public Map<String, Object> qrprsl(@RequestBody Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------单个查询产品基础属性接口开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resmap = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		} else {
			resmap.put("ret", "error");
			resmap.put("msg", "产品代码不能为空");
			return resmap;
		}
		map.put("userid", user.getUserid());
		map.put("pageno", 1);
		map.put("pagesz", 10);
		resmap = client.callClient("fcqpro", map);
		logger.debug("------------------单个查询产品基础属性接口结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增修改贷款产品基础属性
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/fcuipr")
	public Map<String, Object> fcuipr(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改融资产品基础属性开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("fcuipr", req);
		logger.debug("--------------------新增修改融资产品基础属性结束------------------------");
		return resmap;
	}
}
