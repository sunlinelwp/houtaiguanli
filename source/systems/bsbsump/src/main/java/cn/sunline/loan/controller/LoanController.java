package cn.sunline.loan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.sunline.client.Client;
import cn.sunline.domain.endauth.BSBUser;

@Controller("LoanController")
@RequestMapping(value = "/rest/loan")
@ResponseBody
@SessionAttributes("User")
public class LoanController {

	@Autowired
	Client client;

	private static Logger logger = LoggerFactory
			.getLogger(LoanController.class);

	/**
	 * 查询贷款产品基础属性
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrprod")
	public Map<String, Object> seldpp(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询贷款产品基础属性开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		}
		if (reqmap.get("q_prodna") != null && reqmap.get("q_prodna") != "") {
			map.put("prodna", reqmap.get("q_prodna"));
		}
		if (reqmap.get("q_prodtx") != null && reqmap.get("q_prodtx") != "") {
			map.put("prodtx", reqmap.get("q_prodtx"));
		}
		
		
		map.put("userid", user.getUserid()); // 设置操作柜员

		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagect", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrprod", map);
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? "0"
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询贷款产品基础属性结束-----------------");
		return resmap;
	}

	/**
	 * 查询贷款产品基础属性(单个)
	 * 
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrprsl")
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
		map.put("pagect", 10);
		resmap = client.callClient("qrprod", map);
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
	@RequestMapping(value = "/edprod")
	public Map<String, Object> edprod(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改贷款产品基础属性开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("edprod", req);
		logger.debug("--------------------新增修改贷款产品基础属性结束------------------------");
		return resmap;
	}

	/**
	 * 贷款贷款产品基础删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrprod", method = { RequestMethod.DELETE })
	public Map<String, Object> deprod(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("deprod", req); //
		return resmap;
	}

	/**
	 * 查询贷款产品计息属性属性
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrinst")
	public Map<String, Object> qrinst(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询贷款产品计息属性属性开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		}
		map.put("userid", user.getUserid()); // 设置操作柜员
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagect", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrinst", map);
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? "0"
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询贷款产品计息属性属性结束-----------------");
		return resmap;
	}
	
	/**
	 * 查询贷款产品计息属性属性
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrinst1")
	public Map<String, Object> qrinst1(@RequestBody Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询贷款产品计息属性属性开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("prodcd") != null && reqmap.get("prodcd") != "") {
			map.put("prodcd", reqmap.get("prodcd"));
		}
		map.put("userid", user.getUserid()); // 设置操作柜员
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagect", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrinst", map);
		
		logger.debug("-----------------查询贷款产品计息属性属性结束-----------------");
		return resmap;
	}

	/**
	 * 查询贷款产品还款属性
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrrule")
	public Map<String, Object> qrrule(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询贷款产品还款属性开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		}
		map.put("userid", user.getUserid()); // 设置操作柜员
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagect", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrrule", map);
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? "0"
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询贷款产品还款属性结束-----------------");
		return resmap;
	}

	/**
	 * 新增修改贷款产品还款属性
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edrule")
	public Map<String, Object> edrule(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改查询贷款产品还款属性开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("edrule", req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品还款属性表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增修改查询贷款产品还款属性结束------------------------");
		return resmap;
	}

	/**
	 * 贷款产品还款属性删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrrule", method = { RequestMethod.DELETE })
	public Map<String, Object> derule(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("derule", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品会计核算属性表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 新增修改贷款产品计息属性属性
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edinst")
	public Map<String, Object> edinst(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改贷款产品计息属性属性开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("edinst", req);
		logger.debug("--------------------新增修改贷款产品计息属性属性结束------------------------");
		return resmap;
	}

	/**
	 * 贷款贷款产品计息属性删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrinst", method = { RequestMethod.DELETE })
	public Map<String, Object> deinst(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("deinst", req); //
		return resmap;
	}

	/**
	 * 贷款产品放款属性表查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrlend")
	public Map<String, Object> qrlend(@RequestParam Map<String, Object> req,
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
		map.put("pagect", length); //
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrlend", map); //
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos")); //
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? "0"
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		return resmap;
	}

	/**
	 * 贷款产品放款属性表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrlend", method = { RequestMethod.DELETE })
	public Map<String, Object> delend(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("delend", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品放款属性表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	// *****************************↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*************************
	/**
	 * 贷款产品放款属性表新增
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edlend")
	public Map<String, Object> edlend(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("贷款产品放款属性表新增" + req);
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("edlend", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品放款属性表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	// ********************************↓↓↓↓↓↓↓↓↓↓↓↓↓******************************************
	/**
	 * 贷款产品会计核算属性表查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrdtit")
	public Map<String, Object> qrdtit(@RequestParam Map<String, Object> req,
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
		map.put("pagect", length); //
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrdtit", map); //
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos")); //
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? "0"
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		return resmap;
	}

	/**
	 * 贷款产品会计核算属性表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrdtit", method = { RequestMethod.DELETE })
	public Map<String, Object> dedtit(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dedtit", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品会计核算属性表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	// *****************************↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*************************
	/**
	 * 贷款产品会计核算属性表新增
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/eddtit")
	public Map<String, Object> eddtit(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("贷款产品会计核算属性表新增" + req);
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("eddtit", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品会计核算属性表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	// ********************************↓↓↓↓↓↓↓↓↓↓↓↓↓******************************************
	/**
	 * 贷款产品代理业务属性表查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qragnt")
	public Map<String, Object> qragnt(@RequestParam Map<String, Object> req,
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
		map.put("pagect", length); //
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qragnt", map); //
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos")); //
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? 0
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? 0 : resmap.get("pcount"));

		return resmap;
	}

	/**
	 * 贷款产品代理业务属性表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qragnt", method = { RequestMethod.DELETE })
	public Map<String, Object> deagnt(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("deagnt", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品代理业务属性表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	// *****************************↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*************************
	/**
	 * 贷款产品代理业务属性表新增
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edagnt")
	public Map<String, Object> edagnt(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("贷款产品代理业务属性表新增" + req);
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("edagnt", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品代理业务属性表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	// ********************************↓↓↓↓↓↓↓↓↓↓↓↓↓******************************************
	/**
	 * 贷款产品缺省控制表查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrctrl")
	public Map<String, Object> qrctrl(@RequestParam Map<String, Object> req,
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
		map.put("pagect", length); //
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrctrl", map); //
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos")); //
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? "0"
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		return resmap;
	}

	/**
	 * 贷款产品缺省控制表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrctrl", method = { RequestMethod.DELETE })
	public Map<String, Object> dectrl(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dectrl", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品缺省控制表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	// *****************************↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*************************
	/**
	 * 贷款产品缺省控制表新增
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edctrl")
	public Map<String, Object> edctrl(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("贷款产品缺省控制表新增" + req);
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("edctrl", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品缺省控制表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	// ********************************↓↓↓↓↓↓↓↓↓↓↓↓↓******************************************
	/**
	 * 贷款产品缺省控制表查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrbors")
	public Map<String, Object> qrbors(@RequestParam Map<String, Object> req,
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
		map.put("pagect", length); //
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrbors", map); //
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos")); //
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? "0"
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		return resmap;
	}

	/**
	 * 贷款产品缺省控制表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrbors", method = { RequestMethod.DELETE })
	public Map<String, Object> debors(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("debors", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品缺省控制表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	// *****************************↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*************************
	/**
	 * 贷款产品缺省控制表新增
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edbors")
	public Map<String, Object> edbors(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("贷款产品缺省控制表新增" + req);
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("edbors", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品缺省控制表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	// ********************************↓↓↓↓↓↓↓↓↓↓↓↓↓******************************************
	/**
	 * 贷款产品币种控制表查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrcrcy")
	public Map<String, Object> qrcrcy(@RequestParam Map<String, Object> req,
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
		map.put("pagect", length); //
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrcrcy", map); //
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos")); //
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? "0"
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		return resmap;
	}

	/**
	 * 贷款产品币种控制表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrcrcy", method = { RequestMethod.DELETE })
	public Map<String, Object> decrcy(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("decrcy", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品币种控制表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	// *****************************↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*************************
	/**
	 * 贷款产品币种控制表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edcrcy")
	public Map<String, Object> edcrcy(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("贷款产品缺省控制表新增" + req);
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("edcrcy", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品币种控制表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 查询贷款产品收费基础属性表
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrchrg")
	public Map<String, Object> qrchrg(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询贷款产品收费基础属性表开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		}
		map.put("userid", user.getUserid()); // 设置操作柜员
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagect", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrchrg", map);
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? "0"
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询贷款产品收费基础属性表结束-----------------");
		return resmap;
	}

	/**
	 * 新增修改贷款产品收费基础属性表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edchrg")
	public Map<String, Object> edchrg(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改贷款产品收费基础属性表开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("edchrg", req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品放款属性表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增修改贷款产品收费基础属性表结束------------------------");
		return resmap;
	}

	/**
	 * 贷款产品还款属性删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrchrg", method = { RequestMethod.DELETE })
	public Map<String, Object> dechrg(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dechrg", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品会计核算属性表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 贷款产品分类查询
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrgrup")
	public Map<String, Object> qrgrup(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询贷款产品分类开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		}
		map.put("userid", user.getUserid()); // 设置操作柜员
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagect", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrgrup", map);
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? "0"
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询贷款产品分类结束-----------------");
		return resmap;
	}

	/**
	 * 新增修改贷款产品分类
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edgrup")
	public Map<String, Object> edgrup(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改贷款产品分类开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("edgrup", req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品放款属性表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增修改贷款产品分类结束------------------------");
		return resmap;
	}

	/**
	 * 贷款产品分类删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrgrup", method = { RequestMethod.DELETE })
	public Map<String, Object> degrup(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("degrup", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品分类删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 贷款产品到期属性表查询
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrmatu")
	public Map<String, Object> qrmatu(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询贷款产品到期属性表开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		}
		map.put("userid", user.getUserid()); // 设置操作柜员
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagect", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrmatu", map);
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? "0"
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询贷款产品到期属性表结束-----------------");
		return resmap;
	}

	/**
	 * 新增修改贷款产品到期属性表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edmatu")
	public Map<String, Object> edmatu(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改贷款产品到期属性表开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("edmatu", req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品放款属性表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增修改贷款产品到期属性表结束------------------------");
		return resmap;
	}

	/**
	 * 贷款产品到期属性表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrmatu", method = { RequestMethod.DELETE })
	public Map<String, Object> dematu(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dematu", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品到期属性表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 贷款产品客户通知属性表查询
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrnote")
	public Map<String, Object> qrnote(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询贷款产品客户通知属性表开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		}
		map.put("userid", user.getUserid()); // 设置操作柜员
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagect", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrnote", map);
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? "0"
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询贷款产品客户通知属性表结束-----------------");
		return resmap;
	}

	/**
	 * 新增修改贷款产品客户通知属性表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/ednote")
	public Map<String, Object> ednote(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改贷款产品客户通知属性表开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("ednote", req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品放款属性表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增修改贷款产品客户通知属性表结束------------------------");
		return resmap;
	}

	/**
	 * 贷款产品客户通知属性表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrnote", method = { RequestMethod.DELETE })
	public Map<String, Object> denote(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("denote", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品客户通知属性表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 贷款产品适用范围控制表查询
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrscap")
	public Map<String, Object> qrscap(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询贷款产品适用范围控制表开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		}
		map.put("userid", user.getUserid()); // 设置操作柜员
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagect", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrscap", map);
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? "0"
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询贷款产品适用范围控制表结束-----------------");
		return resmap;
	}

	/**
	 * 新增修改贷款产品适用范围控制表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edscap")
	public Map<String, Object> edscap(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改贷款产品适用范围控制表开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("edscap", req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品放款属性表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增修改贷款产品适用范围控制表结束------------------------");
		return resmap;
	}

	/**
	 * 贷款产品适用范围控制表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrscap", method = { RequestMethod.DELETE })
	public Map<String, Object> descap(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("descap", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品适用范围控制表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 贷款产品还款方式组合表查询
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrcomp")
	public Map<String, Object> qrcomp(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询贷款产品还款方式组合表开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		}
		map.put("userid", user.getUserid()); // 设置操作柜员
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagect", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrcomp", map);
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? "0"
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询贷款产品还款方式组合表结束-----------------");
		return resmap;
	}

	/**
	 * 新增修改贷款产品还款方式组合表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edcomp")
	public Map<String, Object> edcomp(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改贷款产品还款方式组合表开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("edcomp", req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品放款属性表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增修改贷款产品还款方式组合表结束------------------------");
		return resmap;
	}

	/**
	 * 贷款产品还款方式组合表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrcomp", method = { RequestMethod.DELETE })
	public Map<String, Object> decomp(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("decomp", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品还款方式组合表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 贷款产品收费事件定义属性表查询
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrenvt")
	public Map<String, Object> qrenvt(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询贷款产品收费事件定义属性表开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		}
		map.put("userid", user.getUserid()); // 设置操作柜员
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagect", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrenvt", map);
		resmap.put(
				"data",
				resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap
						.get("pinfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("pcount") == null ? "0"
				: resmap.get("pcount"));
		resmap.put("iTotalRecords",
				resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询贷款产品收费事件定义属性表结束-----------------");
		return resmap;
	}

	/**
	 * 新增修改贷款产品收费事件定义属性表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edenvt")
	public Map<String, Object> edenvt(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改贷款产品收费事件定义属性表开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("edenvt", req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品放款属性表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增修改贷款产品收费事件定义属性表结束------------------------");
		return resmap;
	}

	/**
	 * 贷款产品收费事件定义属性表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrenvt", method = { RequestMethod.DELETE })
	public Map<String, Object> deenvt(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("deenvt", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款产品收费事件定义属性表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 查询贷款账户主表
	 * 
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrtran")
	public Map<String, Object> qrtran(@RequestBody Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("-----------------查询贷款账户主表开始-----------------");
		reqmap.put("userid", user.getUserid());
		Map<String, Object> rspmap = new HashMap<String, Object>();
		rspmap = client.callClient("qrtran", reqmap);
		logger.debug("-----------------查询贷款账户主表结束-----------------");
		return rspmap;
	}

	/**
	 * 强制贷款形态转移
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/lntrcl")
	public Map<String, Object> lntrcl(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------强制贷款形态转移开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("lntrcl", req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款形态转移成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------强制贷款形态转移结束------------------------");
		return resmap;
	}
	
	/**
	 * 贷款可核销查询贷款呆账状态-查询
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrxrep")
	public Map<String, Object> qrxrep(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询贷款可核销查询贷款呆账状态开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_acctno") != null && reqmap.get("q_acctno") != "") {
			map.put("acctno", reqmap.get("q_acctno"));
		}
		if (reqmap.get("q_lncfno") != null && reqmap.get("q_lncfno") != "") {
			map.put("lncfno", reqmap.get("q_lncfno"));
		}
		map.put("userid", user.getUserid()); // 设置操作柜员
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagect", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrxrep", map);
		resmap.put(
				"data",
				resmap.get("infos") == null ? new ArrayList<Object>() : resmap
						.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count") == null ? "0"
				: resmap.get("count"));
		resmap.put("iTotalRecords",
				resmap.get("count") == null ? "0" : resmap.get("count"));
		logger.debug("-----------------查询贷款可核销查询贷款呆账状态结束-----------------");
		return resmap;
	}
	
	/**
	 * 贷款核销
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/cancln")
	public Map<String, Object> cancln(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------贷款核销开始------------------------"+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("cancln", req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "贷款核销成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.debug("--------------------贷款核销结束------------------------");
		return resmap;
	}

}
