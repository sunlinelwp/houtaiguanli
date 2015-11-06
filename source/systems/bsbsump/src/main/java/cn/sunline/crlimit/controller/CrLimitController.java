package cn.sunline.crlimit.controller;

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

import cn.sunline.client.Client;
import cn.sunline.domain.endauth.BSBUser;
import cn.sunline.loan.controller.LoanController;

@Controller("CrLimitController")
@RequestMapping(value = "/rest/crlimit")
@ResponseBody
@SessionAttributes("User")
public class CrLimitController {

	@Autowired
	Client client;

	private static Logger logger = LoggerFactory
			.getLogger(LoanController.class);

	/**
	 * 查询额度产品属性
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrclpr")
	public Map<String, Object> qrclpr(@RequestBody Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询额度产品属性开始-----------------" + reqmap);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("prodcd", reqmap.get("prodcd"));
		map.put("userid", user.getUserid()); // 设置操作柜员
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagect", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrclpr", map);
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? "0"
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询额度产品属性结束-----------------");
		return resmap;
	}

	/**
	 * 新增修改额度产品属性
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edclpr")
	public Map<String, Object> edclpr(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改额度产品属性开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("edclpr", req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品还款属性表操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增修改额度产品属性结束------------------------");
		return resmap;
	}

	/**
	 * 额度产品属性删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/declpr")
	public Map<String, Object> declpr(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("declpr", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷额度产品属性表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}


	/**
	 * 查询额度结构配置
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrconf")
	public Map<String, Object> qrconf(@RequestBody Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询额度结构配置开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
	
			map.put("prodcd", reqmap.get("prodcd"));
			map.put("cltpcd", reqmap.get("cltpcd"));
		map.put("userid", user.getUserid()); // 设置操作柜员
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagect", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrconf", map);
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? "0"
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询额度结构配置结束-----------------");
		return resmap;
	}

	/**
	 * 新增修改额度结构配置
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edconf")
	public Map<String, Object> edconf(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改额度结构配置开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("edconf", req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "额度结构配置表操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增修改额度结构配置结束------------------------");
		return resmap;
	}

	/**
	 * 额度结构配置删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/deconf")
	public Map<String, Object> deconf(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("deconf", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "额度结构配置表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	// -------------------------------------------------

	/**
	 * 查询额度类型配置
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrtype")
	public Map<String, Object> qrtype(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询额度类型配置开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid()); // 设置操作柜员
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagect", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty("q_prodcd")) {
			map.put("cltpcd", reqmap.get("q_prodcd"));
		}
		resmap = client.callClient("qrtype", map);
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? "0"
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询额度类型配置结束-----------------");
		return resmap;
	}

	/**
	 * 新增修改额度类型配置
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edtype")
	public Map<String, Object> edtype(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改额度类型配置开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("edtype", req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "额度类型配置表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增修改额度类型配置结束------------------------");
		return resmap;
	}

	/**
	 * 额度类型配置删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrtype", method = { RequestMethod.DELETE })
	public Map<String, Object> detype(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("detype", req); //
		if (resmap.get("retCode").toString().equals("0000")) {

			resmap.put("ret", "success");
			resmap.put("msg", "额度类型配置表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 查询额度产品树
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrcltr")
	public Object qrcltr(@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrcltr", map);
		logger.debug("------------------查询查询额度产品树对接结束-----------------");
		logger.debug("map" + resmap);
		return resmap;
	}

}
