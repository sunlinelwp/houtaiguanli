package cn.sunline.prod.controller;


import java.util.HashMap;
import java.util.List;
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

@Controller("ProdController")
@RequestMapping(value = "/rest/prod")
@ResponseBody
@SessionAttributes("User")
public class ProdController {
	@Autowired
	Client client;

	private static Logger logger = LoggerFactory
			.getLogger(ProdController.class);

	/**
	 * 查询产品基础属性(datatable)
	 * 
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/selDppb")
	public Map<String, Object> seldpp(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询产品基础属性接口对接开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		}
		if (reqmap.get("q_prodtx") != null && reqmap.get("q_prodtx") != "") {
			map.put("prodtx", reqmap.get("q_prodtx"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("pagesize", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dppsel", map);
		logger.debug("------------------查询产品基础属性接口对接结束-----------------");
		resmap.put("data",
				resmap.get("infos") == null ? "[]" : resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));
		return resmap;
	}

	/**
	 * 查询产品基础属性(单个)
	 * 
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/sesDpp")
	public Map<String, Object> sesdpp(@RequestBody Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询产品基础属性接口对接开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		}
		map.put("userid", user.getUserid());
		map.put("pageno", 1);
		map.put("pagesize", 10);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dppsel", map);
		logger.debug("------------------查询产品基础属性接口对接结束-----------------");
		return resmap;
	}

	/**
	 * 产品基础属性删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/selDppb", method = { RequestMethod.DELETE })
	public Map<String, Object> selDpp(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------产品基础属性删除接口开始-----------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dppdel", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "产品基础属性删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.debug("------------------产品基础属性删除接口结束-----------------");
		return resmap;

	}

	/**
	 * 新增产品基础属性
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addDppb")
	public Map<String, Object> addDppb(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增产品基础属性开始------------------------"
				+ req.toString());

		Map<String, Object> dppmap = new HashMap<String, Object>();

		dppmap.put("userid", user.getUserid());
		dppmap.put("corpno", user.getCorpno());
		dppmap.putAll((Map<String, Object>) req.get("dppbdata"));
		Map<String, Object> resmap = client.callClient("dppins", dppmap);
		StringBuffer errorMsg=new StringBuffer();
		errorMsg.append("");
		/*
		 * 主表成功后再插入从表数据
		 */
		if (resmap.get("retCode").toString().equals("0000")) {
			if (req.get("actpdata")!=null&&((List<Map<String, Object>>) req.get("actpdata")).size() > 0) {// 账户控制
				logger.debug("actpdata：" + req.get("actpdata"));
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dpactp", req.get("actpdata"));
				try {
					resmap.put("actpret", dapins(map, user));
				} catch (Exception e) {
					logger.debug("错误代码"+e.toString());
					errorMsg.append( "dapins产品账户类型控制失败");
				}
			}
			if (req.get("brchdata")!=null&&((List<Map<String, Object>>) req.get("brchdata")).size() > 0) {// 
				logger.debug("brchdata：" + req.get("brchdata"));
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dpbrch", req.get("brchdata"));
				try {
					resmap.put("brchret", dbrins(map, user));
				} catch (Exception e) {
					logger.debug("错误代码"+e.toString());
					errorMsg.append( "dbrins产品机构控制失败");
				}
			}
			if (req.get("custdata")!=null&&((List<Map<String, Object>>) req.get("custdata")).size() > 0) {// 
				logger.debug("custdata：" + req.get("custdata"));
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("kupdpt", req.get("custdata"));
				try {
					resmap.put("custret", dcuins(map, user));
				} catch (Exception e) {
					logger.debug("错误代码"+e.toString());
					errorMsg.append( "dcuins产品开户控制失败");
				}
			}
			if (req.get("termdata")!=null&&((List<Map<String, Object>>) req.get("termdata")).size() > 0) {// 
				logger.debug("termdata：" + req.get("termdata"));
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dpterm", req.get("termdata"));
				try {
					resmap.put("termdata", dteins(map, user));
				} catch (Exception e) {
					logger.debug("错误代码"+e.toString());
					errorMsg.append("dteins产品存期控制失败");
				}
			}
			if (req.get("postdata")!=null&&((List<Map<String, Object>>) req.get("postdata")).size() > 0) {// 
				logger.debug("postdata：" + req.get("postdata"));
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dppost", req.get("postdata"));
				try {
					resmap.put("postdata", dpoins(map, user));
				} catch (Exception e) {
					logger.debug("错误代码"+e.toString());
					errorMsg.append("dpoins产品存入控制失败");
				}
			}
			if (req.get("drawdata")!=null&&((List<Map<String, Object>>) req.get("drawdata")).size() > 0) {// 
				logger.debug("drawdata：" + req.get("drawdata"));
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dpdraw", req.get("drawdata"));
				try {
					resmap.put("drawdata", ddrins(map, user));
				} catch (Exception e) {
					logger.debug("错误代码"+e.toString());
					errorMsg.append( "ddrins产品支取控制失败");
				}
			}
			if (req.get("matudata")!=null&&((List<Map<String, Object>>) req.get("matudata")).size() > 0) {// 
				logger.debug("matudata：" + req.get("matudata"));
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dpmatu", req.get("matudata"));
				try {
					resmap.put("matudata", dmains(map, user));
				} catch (Exception e) {
					logger.debug("错误代码"+e.toString());
					errorMsg.append( "dmains产品到期失败");
				}
			}
			if (req.get("dfintrdata")!=null&&((List<Map<String, Object>>) req.get("dfintrdata")).size() > 0) {// 
				logger.debug("dfintrdata：" + req.get("dfintrdata"));
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dpdfit", req.get("dfintrdata"));
				try {
					resmap.put("dfintrdata", ddfins(map, user));
				} catch (Exception e) {
					logger.debug("错误代码"+e.toString());
					errorMsg.append("ddfins产品违约支取利息定义失败");
				}
			}
			if (req.get("postplandata")!=null&&((List<Map<String, Object>>) req.get("postplandata")).size() > 0) {//
				logger.debug("postplandata：" + req.get("postplandata"));
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dpptpl", req.get("postplandata"));
				try {
					resmap.put("postplandata", dplins(map, user));
				} catch (Exception e) {
					logger.debug("错误代码"+e.toString());
					errorMsg.append("dplins产品存入计划失败");
				}
			}
			if (req.get("drawplandata")!=null&&((List<Map<String, Object>>) req.get("drawplandata")).size() > 0) {// 
				logger.debug("drawplandata：" + req.get("drawplandata"));
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dpdwpl", req.get("drawplandata"));
				try {
					resmap.put("drawplandata", dwpins(map, user));
				} catch (Exception e) {
					logger.debug("错误代码"+e.toString());
					errorMsg.append( "dwpins产品支取计划失败");
				}
			}
			if (req.get("intrdata")!=null&&((List<Map<String, Object>>) req.get("intrdata")).size() > 0) {//
				logger.debug("intrdata：" + req.get("intrdata"));
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dpintr", req.get("intrdata"));
				try {
					resmap.put("intrdata", dinins(map, user));
				} catch (Exception e) {
					logger.debug("错误代码"+e.toString());
					errorMsg.append("dinins产品利息利率定义失败");
				}
			}
			if (req.get("acctdata")!=null&&((List<Map<String, Object>>) req.get("acctdata")).size() > 0) {// 机构控制
				logger.debug("acctdata：" + req.get("acctdata"));
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dpacct", req.get("acctdata"));
				try {
					resmap.put("acctdata", datins(map, user));
				} catch (Exception e) {
					logger.debug("错误代码"+e.toString());
					errorMsg.append( "datins核算定义失败");
				}
			}
		} else {
			
		}
		resmap.put("msg", resmap.get("retMsg").toString()+","+errorMsg);
		logger.debug("--------------------新增产品基础属性结束------------------------");
		return resmap;
	}

	/**
	 * 产品基础属性修改
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/upDppb")
	public Map<String, Object> upDppb(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------产品基础属性修改开始------------------------"
				+ req.toString());
		Map<String, Object> resmap = new HashMap<String, Object>();
		req.put("userid", user.getUserid());
		req.put("corpno", user.getCorpno());
		resmap = client.callClient("dppupd", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "产品基础属性修改成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.debug("--------------------产品基础属性修改结束------------------------");
		return resmap;
	}

	/**
	 * 异步检查产品号唯一性
	 * 
	 * @param reqmap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/chprod")
	public Boolean checkProdCd(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("检查prodCd是否重复:" + reqmap);
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("prodcd") != null && reqmap.get("prodcd") != "") {
			map.put("prodcd", reqmap.get("prodcd"));
		}
		map.put("userid", user.getUserid());
		map.put("pageno", 1);
		map.put("pagesize", 10);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dppsel", map);
		List<Map<String, Object>> list = (List<Map<String, Object>>) (resmap
				.get("infos") == null ? "[]" : resmap.get("infos"));
		if (list.size() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 产品机构查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dbrsel")
	public Map<String, Object> dbrsel(@RequestParam Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (req.get("q_prodcd") != null && req.get("q_prodcd") != "") {
			map.put("prodcd", req.get("q_prodcd"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("pagesize", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dbrsel", map);
		resmap.put("data",
				resmap.get("infos") == null ? "[]" : resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));
		return resmap;
	}

	/**
	 * 产品机构删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dbrsel", method = { RequestMethod.DELETE })
	public Map<String, Object> dbrdel(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dbrdel", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "产品机构删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 产品机构新增
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dbrins")
	public Map<String, Object> dbrins(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("产品机构新增" + req);
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("addpbh", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "产品机构新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 产品账户类型控制查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dapsel")
	public Map<String, Object> dapsel(@RequestParam Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (req.get("q_prodcd") != null && req.get("q_prodcd") != "") {
			map.put("prodcd", req.get("q_prodcd"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("pagesize", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dapsel", map);
		resmap.put("data",
				resmap.get("infos") == null ? "[]" : resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));
		return resmap;
	}

	/**
	 * 产品账户类型控制删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dapsel", method = { RequestMethod.DELETE })
	public Map<String, Object> dapdel(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dapdel", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "产品账户类型控制删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 产品账户类型控制新增
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dapins")
	public Map<String, Object> dapins(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("产品机构新增" + req);
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("addpap", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "产品账户类型控制新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 开户控制查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dcusel")
	public Map<String, Object> dcusel(@RequestParam Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (req.get("q_prodcd") != null && req.get("q_prodcd") != "") {
			map.put("prodcd", req.get("q_prodcd"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("pagesize", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dcusel", map);
		resmap.put("data",
				resmap.get("infos") == null ? "[]" : resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));
		return resmap;
	}

	/**
	 * 开户控制删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dcusel", method = { RequestMethod.DELETE })
	public Map<String, Object> dcudel(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dcudel", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "开户控制删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 开户控制新增
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dcuins")
	public Map<String, Object> dcuins(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("开户控制新增" + req);
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("addpct", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "开户控制新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 存期查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dtesel")
	public Map<String, Object> dtesel(@RequestParam Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (req.get("q_prodcd") != null && req.get("q_prodcd") != "") {
			map.put("prodcd", req.get("q_prodcd"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("pagesize", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dtesel", map);
		resmap.put("data",
				resmap.get("infos") == null ? "[]" : resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));
		return resmap;
	}

	/**
	 * 存期删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dtesel", method = { RequestMethod.DELETE })
	public Map<String, Object> dtedel(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dtedel", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "存期删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 存期新增
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dteins")
	public Map<String, Object> dteins(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("存期新增" + req);
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("addptm", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "存期新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 到期查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dmasel")
	public Map<String, Object> dmasel(@RequestParam Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (req.get("q_prodcd") != null && req.get("q_prodcd") != "") {
			map.put("prodcd", req.get("q_prodcd"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("pagesize", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dmasel", map);
		resmap.put("data",
				resmap.get("infos") == null ? "[]" : resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));
		return resmap;
	}

	/**
	 * 到期删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dmasel", method = { RequestMethod.DELETE })
	public Map<String, Object> dmadel(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dmadel", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "到期删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 到期新增
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dmains")
	public Map<String, Object> dmains(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("到期新增" + req);
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("addpma", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "到期新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 存入查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dposel")
	public Map<String, Object> dposel(@RequestParam Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (req.get("q_prodcd") != null && req.get("q_prodcd") != "") {
			map.put("prodcd", req.get("q_prodcd"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("pagesize", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dposel", map);
		resmap.put("data",
				resmap.get("infos") == null ? "[]" : resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));
		return resmap;
	}

	/**
	 * 存入删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dposel", method = { RequestMethod.DELETE })
	public Map<String, Object> dpodel(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dpodel", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "存入删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 存入新增
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dpoins")
	public Map<String, Object> dpoins(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("存入新增" + req);
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("addppt", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "存入新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 存入计划查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dplsel")
	public Map<String, Object> dplsel(@RequestParam Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (req.get("q_prodcd") != null && req.get("q_prodcd") != "") {
			map.put("prodcd", req.get("q_prodcd"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("pagesize", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dplsel", map);
		resmap.put("data",
				resmap.get("infos") == null ? "[]" : resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));
		return resmap;
	}

	/**
	 * 存入计划删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dplsel", method = { RequestMethod.DELETE })
	public Map<String, Object> dpldel(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dpldel", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "存入计划删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 存入计划新增
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dplins")
	public Map<String, Object> dplins(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("存入计划新增" + req);
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("adptpl", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "存入计划新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 产品违约支取利息查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/ddfsel")
	public Map<String, Object> ddfsel(@RequestParam Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (req.get("q_prodcd") != null && req.get("q_prodcd") != "") {
			map.put("prodcd", req.get("q_prodcd"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("pagesize", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("ddfsel", map);
		resmap.put("data",
				resmap.get("infos") == null ? "[]" : resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));
		return resmap;
	}

	/**
	 * 产品违约支取利息删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/ddfsel", method = { RequestMethod.DELETE })
	public Map<String, Object> ddfdel(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("ddfdel", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "产品违约支取利息删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 产品违约支取利息新增
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/ddfins")
	public Map<String, Object> ddfins(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("产品违约支取利息新增" + req);
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("addpdf", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "产品违约支取利息新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 产品支取计划表查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dwpsel")
	public Map<String, Object> dwpsel(@RequestParam Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (req.get("q_prodcd") != null && req.get("q_prodcd") != "") {
			map.put("prodcd", req.get("q_prodcd"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("pagesize", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dwpsel", map);
		resmap.put("data",
				resmap.get("infos") == null ? "[]" : resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));
		return resmap;
	}

	/**
	 * 产品支取计划表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dwpsel", method = { RequestMethod.DELETE })
	public Map<String, Object> dwpdel(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("ddfdel", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "产品支取计划表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 产品支取计划表新增
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dwpins")
	public Map<String, Object> dwpins(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("产品支取计划表新增" + req);
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("addwpl", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "产品支取计划表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 产品利息利率定义表查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dinsel")
	public Map<String, Object> dinsel(@RequestParam Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (req.get("q_prodcd") != null && req.get("q_prodcd") != "") {
			map.put("prodcd", req.get("q_prodcd"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("pagesize", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dinsel", map);
		resmap.put("data",
				resmap.get("infos") == null ? "[]" : resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));
		return resmap;
	}

	/**
	 * 产品利息利率定义表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dinsel", method = { RequestMethod.DELETE })
	public Map<String, Object> dindel(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dindel", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "产品利息利率定义表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 产品利息利率定义表新增
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dinins")
	public Map<String, Object> dinins(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("产品利息利率定义表新增" + req);
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("addpit", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "产品利息利率定义表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 产品核算定义表查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/datsel")
	public Map<String, Object> datsel(@RequestParam Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (req.get("q_prodcd") != null && req.get("q_prodcd") != "") {
			map.put("prodcd", req.get("q_prodcd"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("pagesize", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("datsel", map);
		resmap.put("data",
				resmap.get("infos") == null ? "[]" : resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));
		return resmap;
	}

	/**
	 * 产品核算定义表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/datsel", method = { RequestMethod.DELETE })
	public Map<String, Object> datdel(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("datdel", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "产品核算定义表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 产品核算定义表新增
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/datins")
	public Map<String, Object> datins(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("产品核算定义表新增" + req);
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("addpat", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "产品核算定义表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	
	/**
	 * 	支取控制查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/ddrsel")
	public Map<String, Object> ddrsel(@RequestParam Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (req.get("q_prodcd") != null && req.get("q_prodcd") != "") {
			map.put("prodcd", req.get("q_prodcd"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("pagesize", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("ddrsel", map);
		resmap.put("data",
				resmap.get("infos") == null ? "[]" : resmap.get("infos"));
		resmap.put("iTotalDisplayRecords",resmap.get("count") == null ? "0" : resmap.get("count") );
		resmap.put("iTotalRecords", resmap.get("count") == null ? "0" : resmap.get("count"));
		return resmap;
	}
	
	/**
	 * 支取控制删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/ddrsel", method = { RequestMethod.DELETE })
	public Map<String, Object> ddrdel(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("ddrdel", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "支取控制删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
	/**
	 * 支取控制新增
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/ddrins")
	public Map<String, Object> ddrins(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("支取控制新增" + req);
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("addpdw", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "支取控制新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
}
