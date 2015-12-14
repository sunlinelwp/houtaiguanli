package cn.sunline.paychannel.controller;

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

@Controller("PaychannelController")
@RequestMapping(value = "/rest/paychannel")
@ResponseBody
@SessionAttributes("User")
public class PaychannelController {

	@Autowired
	Client client;

	private static Logger logger = LoggerFactory
			.getLogger(LoanController.class);

	/**
	 * 查询渠道信息
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrrole")
	public Map<String, Object> qrrole(@RequestParam Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------查询渠道信息开始------------------------"
				+ req.toString());
		Map<String, Object> map = new HashMap<String, Object>();

		if (req.get("n_chnlnm") != null && req.get("n_chnlnm") != "") {
			map.put("chnlnm", req.get("n_chnlnm"));
		}
		if (req.get("q_status") != null && req.get("q_status") != "") {
			map.put("status", req.get("q_status"));
		}

		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pagenm", start / length + 1);// 当前页数
		map.put("rcrdnm", length); // 每页记录数
		map.put("target", '1');
		Map<String, Object> resmap = new HashMap<String, Object>();

		logger.debug("0000000000000" + map.toString());
		resmap = client.callClient("qychnl", map);
		logger.debug("-----------------" + resmap + "-----------------");
		resmap.put(
				"data",
				resmap.get("listnm") == null ? new ArrayList<Object>() : resmap
						.get("listnm"));
		resmap.put("iTotalDisplayRecords", resmap.get("totanm") == null ? "0"
				: resmap.get("totanm"));
		resmap.put("iTotalRecords",
				resmap.get("totanm") == null ? "0" : resmap.get("totanm"));
		logger.debug("-----------------查询渠道信息结束-----------------");
		return resmap;
	}

	/**
	 * 新增或修改角色
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/adchnl")
	public Map<String, Object> adrole(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增或修改渠道开始------------------------"
				+ req.toString());
		String jiaoyi = "";
		if (req.get("n_yin") != null && req.get("n_yin") != "") {
			req.put("mduser", user.getUserna());// 创建人
			req.put("target", '1');
			jiaoyi = "upchnl";
		} else {
			req.put("target", '1');
			jiaoyi = "adchnl";
		}
		Map<String, Object> resmap = client.callClient(jiaoyi, req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("retMsg", "success");
			resmap.put("msg", "银行渠道表操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增或修改渠道结束------------------------");
		return resmap;
	}

	/**
	 * 删除角色
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrrole", method = { RequestMethod.DELETE })
	public Map<String, Object> derole(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		req.put("target", '1');
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("rmchnl", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "银行渠道表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 银行信息查询
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qybank")
	public Map<String, Object> qybank(@RequestParam Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------查询银行信息查询开始------------------------"
				+ req.toString());
		Map<String, Object> map = new HashMap<String, Object>();

		if (req.get("n_chnlnm") != null && req.get("n_chnlnm") != "") {
			map.put("chnlnm", req.get("n_chnlnm"));
		}
		if (req.get("n_banknm") != null && req.get("n_banknm") != "") {
			map.put("banknm", req.get("n_banknm"));
		}
		if (req.get("n_pytype") != null && req.get("n_pytype") != "") {
			map.put("pytype", req.get("n_pytype"));
		}
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pagenm", start / length + 1);// 当前页数
		map.put("rcrdnm", length); // 每页记录数
		map.put("target", '1');
		Map<String, Object> resmap = new HashMap<String, Object>();

		logger.debug("0000000000000" + map.toString());
		resmap = client.callClient("qybank", map);
		logger.debug("-----------------" + resmap + "-----------------");
		resmap.put(
				"data",
				resmap.get("listnm") == null ? new ArrayList<Object>() : resmap
						.get("listnm"));
		resmap.put("iTotalDisplayRecords", resmap.get("totanm") == null ? "0"
				: resmap.get("totanm"));
		resmap.put("iTotalRecords",
				resmap.get("totanm") == null ? "0" : resmap.get("totanm"));
		logger.debug("-----------------银行信息查询结束-----------------");
		return resmap;
	}

	/**
	 * 新增或修改银行信息
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/adbank")
	public Map<String, Object> adbank(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增或修改银行信息开始------------------------"
				+ req.toString());
		String jiaoyi = "";
		if (req.get("n_yin") != null && req.get("n_yin") != "") {
			req.put("mduser", user.getUserna());// 创建人
			req.put("target", '1');
			jiaoyi = "upbank";
		} else {
			req.put("target", '1');
			jiaoyi = "adbank";
		}
		Map<String, Object> resmap = client.callClient(jiaoyi, req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("retMsg", "success");
			resmap.put("msg", "银行信息表操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增或修改银行信息结束------------------------");
		return resmap;
	}

	/**
	 * 删除角色
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/rmbank", method = { RequestMethod.DELETE })
	public Map<String, Object> rmbank(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		req.put("target", '1');
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("rmbank", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "银行信息表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	/**
	 * 限额信息查询
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qylimt")
	public Map<String, Object> qylimt(@RequestParam Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------查询限额信息查询开始------------------------"
				+ req.toString());
		Map<String, Object> map = new HashMap<String, Object>();

		if (req.get("n_chnlnm") != null && req.get("n_chnlnm") != "") {
			map.put("chnlnm", req.get("n_chnlnm"));
		}
		if (req.get("n_banknm") != null && req.get("n_banknm") != "") {
			map.put("banknm", req.get("n_banknm"));
		}
		if (req.get("n_pytype") != null && req.get("n_pytype") != "") {
			map.put("pytype", req.get("n_pytype"));
		}
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pagenm", start / length + 1);// 当前页数
		map.put("rcrdnm", length); // 每页记录数
		map.put("target", '1');
		Map<String, Object> resmap = new HashMap<String, Object>();

		logger.debug("0000000000000" + map.toString());
		resmap = client.callClient("qylimt", map);
		logger.debug("-----------------" + resmap + "-----------------");
		resmap.put(
				"data",
				resmap.get("listnm") == null ? new ArrayList<Object>() : resmap
						.get("listnm"));
		resmap.put("iTotalDisplayRecords", resmap.get("totanm") == null ? "0"
				: resmap.get("totanm"));
		resmap.put("iTotalRecords",
				resmap.get("totanm") == null ? "0" : resmap.get("totanm"));
		logger.debug("-----------------限额信息查询结束-----------------");
		return resmap;
	}

	/**
	 * 新增或修改限额信息
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/adlimt")
	public Map<String, Object> adlimt(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增或修改限额信息开始------------------------"
				+ req.toString());
		String jiaoyi = "";
		if (req.get("n_yin") != null && req.get("n_yin") != "") {
			req.put("mduser", user.getUserna());// 创建人
			req.put("target", '1');
			jiaoyi = "uplimt";
		} else {
			req.put("target", '1');
			jiaoyi = "adlimt";
		}
		Map<String, Object> resmap = client.callClient(jiaoyi, req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("retMsg", "success");
			resmap.put("msg", "限额信息表操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增或修改限额信息结束------------------------");
		return resmap;
	}

	/**
	 * 删除限额角色
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/rmlimt", method = { RequestMethod.DELETE })
	public Map<String, Object> rmlimt(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		req.put("target", '1');
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("rmlimt", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "限额信息表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 手续费查询
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qychge")
	public Map<String, Object> qychge(@RequestParam Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------查询手续费查询查询开始------------------------"
				+ req.toString());
		Map<String, Object> map = new HashMap<String, Object>();

		if (req.get("n_chnlnm") != null && req.get("n_chnlnm") != "") {
			map.put("chnlnm", req.get("n_chnlnm"));
		}
		if (req.get("n_banknm") != null && req.get("n_banknm") != "") {
			map.put("banknm", req.get("n_banknm"));
		}
		if (req.get("n_pytype") != null && req.get("n_pytype") != "") {
			map.put("pytype", req.get("n_pytype"));
		}
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pagenm", start / length + 1);// 当前页数
		map.put("rcrdnm", length); // 每页记录数
		map.put("target", '1');
		Map<String, Object> resmap = new HashMap<String, Object>();

		logger.debug("0000000000000" + map.toString());
		resmap = client.callClient("qychge", map);
		logger.debug("-----------------" + resmap + "-----------------");
		resmap.put(
				"data",
				resmap.get("listnm") == null ? new ArrayList<Object>() : resmap
						.get("listnm"));
		resmap.put("iTotalDisplayRecords", resmap.get("totanm") == null ? "0"
				: resmap.get("totanm"));
		resmap.put("iTotalRecords",
				resmap.get("totanm") == null ? "0" : resmap.get("totanm"));
		logger.debug("-----------------接入手续费查询结束-----------------");
		return resmap;
	}

	/**
	 * 新增或修改手续费查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/adchge")
	public Map<String, Object> adchge(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增或修改手续费查询开始------------------------"
				+ req.toString());
		String jiaoyi = "";
		if (req.get("n_yin") != null && req.get("n_yin") != "") {
			req.put("mduser", user.getUserna());// 创建人
			req.put("target", '1');
			jiaoyi = "upchge";
		} else {
			req.put("target", '1');
			jiaoyi = "adchge";
		}
		Map<String, Object> resmap = client.callClient(jiaoyi, req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("retMsg", "success");
			resmap.put("msg", "接入渠道信息表操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增或修改手续费查询结束------------------------");
		return resmap;
	}

	/**
	 * 删除手续费查询
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/rmchge", method = { RequestMethod.DELETE })
	public Map<String, Object> rmchge(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		req.put("target", '1');
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("rmchge", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "手续费查询表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
	/**
	 * 接入渠道配置信息查询
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qychcg")
	public Map<String, Object> qychcg(@RequestParam Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------接入渠道配置信息查询开始------------------------"
				+ req.toString());
		Map<String, Object> map = new HashMap<String, Object>();

		if (req.get("n_chnlnm") != null && req.get("n_chnlnm") != "") {
			map.put("chnlnm", req.get("n_chnlnm"));
		}
		if (req.get("n_banknm") != null && req.get("n_banknm") != "") {
			map.put("banknm", req.get("n_banknm"));
		}
		if (req.get("n_pytype") != null && req.get("n_pytype") != "") {
			map.put("pytype", req.get("n_pytype"));
		}
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pagenm", start / length + 1);// 当前页数
		map.put("rcrdnm", length); // 每页记录数
		map.put("target", '1');
		Map<String, Object> resmap = new HashMap<String, Object>();

		logger.debug("0000000000000" + map.toString());
		resmap = client.callClient("qychcg", map);
		logger.debug("-----------------" + resmap + "-----------------");
		resmap.put(
				"data",
				resmap.get("listnm") == null ? new ArrayList<Object>() : resmap
						.get("listnm"));
		resmap.put("iTotalDisplayRecords", resmap.get("totanm") == null ? "0"
				: resmap.get("totanm"));
		resmap.put("iTotalRecords",
				resmap.get("totanm") == null ? "0" : resmap.get("totanm"));
		logger.debug("-----------------接入渠道配置信息结束-----------------");
		return resmap;
	}

	/**
	 * 新增或修改接入渠道配置信息
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/adchcg")
	public Map<String, Object> adchcg(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增或修改接入渠道配置信息开始------------------------"
				+ req.toString());
		String jiaoyi = "";
		if (req.get("n_yin") != null && req.get("n_yin") != "") {
			req.put("mduser", user.getUserna());// 创建人
			req.put("target", '1');
			jiaoyi = "upchcg";
		} else {
			req.put("target", '1');
			jiaoyi = "adchcg";
		}
		Map<String, Object> resmap = client.callClient(jiaoyi, req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("retMsg", "success");
			resmap.put("msg", "手续费信息表操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增或修改接入渠道配置信息结束------------------------");
		return resmap;
	}

	/**
	 * 删除接入渠道配置角色
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/rmchcg", method = { RequestMethod.DELETE })
	public Map<String, Object> rmchcg(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		req.put("target", '1');
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("rmchcg", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "接入渠道配置信息表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	/**
	 * 接入渠道信息查询
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qyinch")
	public Map<String, Object> qyinch(@RequestParam Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------查询接入渠道信息查询开始------------------------"
				+ req.toString());
		Map<String, Object> map = new HashMap<String, Object>();

		if (req.get("n_chnlnm") != null && req.get("n_chnlnm") != "") {
			map.put("chnlnm", req.get("n_chnlnm"));
		}
		if (req.get("n_banknm") != null && req.get("n_banknm") != "") {
			map.put("banknm", req.get("n_banknm"));
		}
		if (req.get("n_pytype") != null && req.get("n_pytype") != "") {
			map.put("pytype", req.get("n_pytype"));
		}
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pagenm", start / length + 1);// 当前页数
		map.put("rcrdnm", length); // 每页记录数
		map.put("target", '1');
		Map<String, Object> resmap = new HashMap<String, Object>();

		logger.debug("0000000000000" + map.toString());
		resmap = client.callClient("qyinch", map);
		logger.debug("-----------------" + resmap + "-----------------");
		resmap.put(
				"data",
				resmap.get("listnm") == null ? new ArrayList<Object>() : resmap
						.get("listnm"));
		resmap.put("iTotalDisplayRecords", resmap.get("totanm") == null ? "0"
				: resmap.get("totanm"));
		resmap.put("iTotalRecords",
				resmap.get("totanm") == null ? "0" : resmap.get("totanm"));
		logger.debug("-----------------接入渠道信息查询结束-----------------");
		return resmap;
	}

	/**
	 * 新增或修改接入渠道信息
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/adinch")
	public Map<String, Object> adinch(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增或修改接入渠道信息信息开始------------------------"
				+ req.toString());
		String jiaoyi = "";
		if (req.get("n_yin") != null && req.get("n_yin") != "") {
			req.put("mduser", user.getUserna());// 创建人
			req.put("target", '1');
			jiaoyi = "upinch";
		} else {
			req.put("target", '1');
			jiaoyi = "adinch";
		}
		Map<String, Object> resmap = client.callClient(jiaoyi, req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("retMsg", "success");
			resmap.put("msg", "手续费信息表操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增或修改接入渠道信息信息结束------------------------");
		return resmap;
	}

	/**
	 * 删除接入渠道信息
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/rminch", method = { RequestMethod.DELETE })
	public Map<String, Object> rminch(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		req.put("target", '1');
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("rminch", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "接入渠道信息表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
}
