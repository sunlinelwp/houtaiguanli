package cn.sunline.kcp.controller;

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
/**
 * 
 * @author lwp
 * 收费模块控制
 */

@Controller("KcpController")
@RequestMapping(value = "/rest/kcp")
@ResponseBody
@SessionAttributes("User")
public class KcpController {
	
	@Autowired
	Client client;

	private static Logger logger = LoggerFactory
			.getLogger(LoanController.class);
	
	
	/**
	 * 查询收费代码定义表
	 * 
	 * @param reqmap 查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrchrg")
	public Map<String, Object> qrchrg(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询收费代码定义表开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid()); //设置操作柜员		
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);//当前页数
		map.put("pagect", length); //每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qkcchr", map);
		resmap.put("data",resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap.get("pinfos"));
		resmap.put("iTotalDisplayRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		resmap.put("iTotalRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询收费代码定义表结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增修改收费代码定义表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edchrg")
	public Map<String, Object> edchrg(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改收费代码定义表开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());	
		Map<String, Object> resmap = client.callClient("ekcchr", req);
		
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "收费代码定义表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		
		logger.debug("--------------------新增修改收费代码定义表结束------------------------");
		return resmap;
	}
	
	/**
	 * 收费代码定义表删除
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
		resmap = client.callClient("dkcchr", req);								//		
		if (resmap.get("retCode").toString().equals("0000")) {
			
			resmap.put("ret", "success");
			resmap.put("msg", "收费代码定义表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
//	-------------------------------------------------
	
	/**
	 * 查询收费事件参数表
	 * 
	 * @param reqmap 查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrdefn")
	public Map<String, Object> qrdefn(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询收费事件参数表开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid()); //设置操作柜员		
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);//当前页数
		map.put("pagect", length); //每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qkcchd", map);
		resmap.put("data",resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap.get("pinfos"));
		resmap.put("iTotalDisplayRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		resmap.put("iTotalRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询收费事件参数表结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增修改收费事件参数表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/eddefn")
	public Map<String, Object> eddefn(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改收费事件参数表开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());	
		Map<String, Object> resmap = client.callClient("ekcchd", req);
		
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "收费事件参数表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		
		logger.debug("--------------------新增修改收费事件参数表结束------------------------");
		return resmap;
	}
	
	/**
	 * 收费事件参数表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrdefn", method = { RequestMethod.DELETE })
	public Map<String, Object> dedefn(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dkcchd", req);								//		
		if (resmap.get("retCode").toString().equals("0000")) {
			
			resmap.put("ret", "success");
			resmap.put("msg", "收费事件参数表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
//	-------------------------------------------------
	
	
	/**
	 * 查询事件收费控制表
	 * 
	 * @param reqmap 查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrevnt")
	public Map<String, Object> qrevnt(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询事件收费控制表开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid()); //设置操作柜员		
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);//当前页数
		map.put("pagect", length); //每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qkcevn", map);
		resmap.put("data",resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap.get("pinfos"));
		resmap.put("iTotalDisplayRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		resmap.put("iTotalRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询事件收费控制表结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增修改事件收费控制表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edevnt")
	public Map<String, Object> edevnt(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改事件收费控制表开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());	
		Map<String, Object> resmap = client.callClient("ekcevn", req);
		
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "事件收费控制表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		
		logger.debug("--------------------新增修改事件收费控制表结束------------------------");
		return resmap;
	}
	
	/**
	 * 事件收费控制表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrevnt", method = { RequestMethod.DELETE })
	public Map<String, Object> deevnt(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dkcevn", req);								//		
		if (resmap.get("retCode").toString().equals("0000")) {
			
			resmap.put("ret", "success");
			resmap.put("msg", "事件收费控制表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
//	-------------------------------------------------
	

	/**
	 * 查询收费公式定义表
	 * 
	 * @param reqmap 查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrfmdf")
	public Map<String, Object> qrfmdf(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询收费公式定义表开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid()); //设置操作柜员		
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);//当前页数
		map.put("pagect", length); //每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qkcmdf", map);
		resmap.put("data",resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap.get("pinfos"));
		resmap.put("iTotalDisplayRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		resmap.put("iTotalRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询收费公式定义表结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增修改收费公式定义表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edfmdf")
	public Map<String, Object> edfmdf(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改收费公式定义表开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());	
		Map<String, Object> resmap = client.callClient("ekcmdf", req);
		
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "收费公式定义表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		
		logger.debug("--------------------新增修改收费公式定义表结束------------------------");
		return resmap;
	}
	
	/**
	 * 收费公式定义表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrfmdf", method = { RequestMethod.DELETE })
	public Map<String, Object> defmdf(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dkcmdf", req);								//		
		if (resmap.get("retCode").toString().equals("0000")) {
			
			resmap.put("ret", "success");
			resmap.put("msg", "收费公式定义表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
//	-------------------------------------------------
	
	
//	-------------------------------------------------
	

	/**
	 * 查询收费公式明细表
	 * 
	 * @param reqmap 查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrfmdt")
	public Map<String, Object> qrfmdt(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询收费公式明细表开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_chrgfm") != null && reqmap.get("q_chrgfm") != "") {
			map.put("chrgfm", reqmap.get("q_chrgfm"));
		}
		map.put("userid", user.getUserid()); //设置操作柜员		
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);//当前页数
		map.put("pagect", length); //每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qkcmdt", map);
		resmap.put("data",resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap.get("pinfos"));
		resmap.put("iTotalDisplayRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		resmap.put("iTotalRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询收费公式明细表结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增修改收费公式明细表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edfmdt")
	public Map<String, Object> edfmdt(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改收费公式明细表开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());	
		Map<String, Object> resmap = client.callClient("ekcmdt", req);
		
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "收费公式明细表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		
		logger.debug("--------------------新增修改收费公式明细表义表结束------------------------");
		return resmap;
	}
	
	/**
	 * 收费公式明细表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrfmdt", method = { RequestMethod.DELETE })
	public Map<String, Object> defmdt(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dkcmdt", req);								//		
		if (resmap.get("retCode").toString().equals("0000")) {
			
			resmap.put("ret", "success");
			resmap.put("msg", "收费公式明细表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
//	-------------------------------------------------
	
	
	/**
	 * 查询收费公式解析表
	 * 
	 * @param reqmap 查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrfmex")
	public Map<String, Object> qrfmex(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询收费公式解析表开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid()); //设置操作柜员		
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);//当前页数
		map.put("pagect", length); //每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qkcfme", map);
		resmap.put("data",resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap.get("pinfos"));
		resmap.put("iTotalDisplayRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		resmap.put("iTotalRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询收费公式解析表结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增修改收费公式解析表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edfmex")
	public Map<String, Object> edfmex(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改收费公式解析表开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());	
		Map<String, Object> resmap = client.callClient("ekcfme", req);
		
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "收费公式解析表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		
		logger.debug("--------------------新增修改收费公式解析表义表结束------------------------");
		return resmap;
	}
	
	/**
	 * 收费公式解析表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrfmex", method = { RequestMethod.DELETE })
	public Map<String, Object> defmex(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dkcfme", req);								//		
		if (resmap.get("retCode").toString().equals("0000")) {
			
			resmap.put("ret", "success");
			resmap.put("msg", "收费公式解析表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
//	-------------------------------------------------
	
	
//	-------------------------------------------------
	
	
	/**
	 * 查询收费代码与交易关系对应表
	 * 
	 * @param reqmap 查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrprcs")
	public Map<String, Object> qrprcs(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询收费代码与交易关系对应表开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid()); //设置操作柜员		
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);//当前页数
		map.put("pagect", length); //每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qkcprc", map);
		resmap.put("data",resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap.get("pinfos"));
		resmap.put("iTotalDisplayRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		resmap.put("iTotalRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询收费代码与交易关系对应表结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增修改收费代码与交易关系对应表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edprcs")
	public Map<String, Object> edprcs(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改收费代码与交易关系对应表开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());	
		Map<String, Object> resmap = client.callClient("ekcprc", req);
		
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "收费代码与交易关系对应表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		
		logger.debug("--------------------新增修改收费代码与交易关系对应表义表结束------------------------");
		return resmap;
	}
	
	/**
	 * 收费代码与交易关系对应表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrprcs", method = { RequestMethod.DELETE })
	public Map<String, Object> deprcs(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dkcprc", req);								//		
		if (resmap.get("retCode").toString().equals("0000")) {
			
			resmap.put("ret", "success");
			resmap.put("msg", "收费代码与交易关系对应表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
//	-------------------------------------------------
	
	
//	-------------------------------------------------
	
	
	/**
	 * 查询场景收费定义
	 * 
	 * @param reqmap 查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrscdf")
	public Map<String, Object> qrscdf(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询场景收费定义开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid()); //设置操作柜员		
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);//当前页数
		map.put("pagect", length); //每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qkcscd", map);
		resmap.put("data",resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap.get("pinfos"));
		resmap.put("iTotalDisplayRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		resmap.put("iTotalRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询场景收费定义结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增修改收场景收费定义
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edscdf")
	public Map<String, Object> edscdf(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改场景收费定义开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());	
		Map<String, Object> resmap = client.callClient("ekcscd", req);
		
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "场景收费定义新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		
		logger.debug("--------------------新增修改场景收费定义义表结束------------------------");
		return resmap;
	}
	
	/**
	 * 场景收费定义删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrscdf", method = { RequestMethod.DELETE })
	public Map<String, Object> descdf(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dkcscd", req);								//		
		if (resmap.get("retCode").toString().equals("0000")) {
			
			resmap.put("ret", "success");
			resmap.put("msg", "场景收费定义删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
//	-------------------------------------------------
	
//	-------------------------------------------------
	
	
	/**
	 * 查询公式自定义表
	 * 
	 * @param reqmap 查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrusdf")
	public Map<String, Object> qrusdf(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询公式自定义表开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid()); //设置操作柜员		
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);//当前页数
		map.put("pagect", length); //每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrusdf", map);
		resmap.put("data",resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap.get("pinfos"));
		resmap.put("iTotalDisplayRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		resmap.put("iTotalRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询公式自定义表结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增修改公式自定义表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edusdf")
	public Map<String, Object> edusdf(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改公式自定义表开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());	
		Map<String, Object> resmap = client.callClient("edusdf", req);
		
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "公式自定义表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		
		logger.debug("--------------------新增修改公式自定义表结束------------------------");
		return resmap;
	}
	
	/**
	 * 公式自定义表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrusdf", method = { RequestMethod.DELETE })
	public Map<String, Object> deusdf(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dlusdf", req);								//		
		if (resmap.get("retCode").toString().equals("0000")) {
			
			resmap.put("ret", "success");
			resmap.put("msg", "公式自定义表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
//	-------------------------------------------------
	
	
//	-------------------------------------------------
	
	
	/**
	 * 查询维度信息参数表
	 * 
	 * @param reqmap 查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrdime")
	public Map<String, Object> qrdime(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询维度信息参数表开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid()); //设置操作柜员		
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);//当前页数
		map.put("pagect", length); //每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrdime", map);
		resmap.put("data",resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap.get("pinfos"));
		resmap.put("iTotalDisplayRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		resmap.put("iTotalRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询维度信息参数表结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增修改维度信息参数表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/eddime")
	public Map<String, Object> eddime(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改维度信息参数表开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());	
		Map<String, Object> resmap = client.callClient("eddime", req);
		
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "维度信息参数表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		
		logger.debug("--------------------新增修改维度信息参数表结束------------------------");
		return resmap;
	}
	
	/**
	 * 维度信息参数表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrdime", method = { RequestMethod.DELETE })
	public Map<String, Object> dedime(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dldime", req);								//		
		if (resmap.get("retCode").toString().equals("0000")) {
			
			resmap.put("ret", "success");
			resmap.put("msg", "维度信息参数表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
//	-------------------------------------------------
	
	
//	-------------------------------------------------
	
	
	/**
	 * 查询场景事件定义
	 * 
	 * @param reqmap 查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrscde")
	public Map<String, Object> qrscde(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询场景事件定义开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid()); //设置操作柜员		
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);//当前页数
		map.put("pagect", length); //每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qkcdef", map);
		resmap.put("data",resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap.get("pinfos"));
		resmap.put("iTotalDisplayRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		resmap.put("iTotalRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询场景事件定义结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增修改场景事件定义
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edscde")
	public Map<String, Object> edscde(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改场景事件定义开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());	
		Map<String, Object> resmap = client.callClient("ekcdef", req);
		
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "场景事件定义新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		
		logger.debug("--------------------新增修改场景事件定义结束------------------------");
		return resmap;
	}
	
	/**
	 * 场景事件定义删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrscde", method = { RequestMethod.DELETE })
	public Map<String, Object> descde(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dkcdef", req);								//		
		if (resmap.get("retCode").toString().equals("0000")) {
			
			resmap.put("ret", "success");
			resmap.put("msg", "场景事件定义删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
//	-------------------------------------------------
	
	
//	-------------------------------------------------
	
	
	/**
	 * 查询场景事件明细表
	 * 
	 * @param reqmap 查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrdetl")
	public Map<String, Object> qrdetl(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询场景事件明细表义开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid()); //设置操作柜员		
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);//当前页数
		map.put("pagect", length); //每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrsedt", map);
		resmap.put("data",resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap.get("pinfos"));
		resmap.put("iTotalDisplayRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		resmap.put("iTotalRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询场景事件明细表结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增修改场景事件明细表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/eddetl")
	public Map<String, Object> eddetl(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改场景事件明细表开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());	
		Map<String, Object> resmap = client.callClient("edsedt", req);
		
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "场景事件明细表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		
		logger.debug("--------------------新增修改场景事件明细表结束------------------------");
		return resmap;
	}
	
	/**
	 * 场景事件明细表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrdetl", method = { RequestMethod.DELETE })
	public Map<String, Object> dedetl(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dlsedt", req);								//		
		if (resmap.get("retCode").toString().equals("0000")) {
			
			resmap.put("ret", "success");
			resmap.put("msg", "场景事件明细表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
//	-------------------------------------------------
	
//	-------------------------------------------------
	
	
	/**
	 * 查询场景事件维度值明细表
	 * 
	 * @param reqmap 查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrscdi")
	public Map<String, Object> qrscdi(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询场景事件维度值明细表义开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid()); //设置操作柜员		
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);//当前页数
		map.put("pagect", length); //每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrsedm", map);
		resmap.put("data",resmap.get("pinfos") == null ? new ArrayList<Object>() : resmap.get("pinfos"));
		resmap.put("iTotalDisplayRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		resmap.put("iTotalRecords",resmap.get("pcount") == null ? "0" : resmap.get("pcount"));
		logger.debug("-----------------查询场景事件维度值明细表结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增修改场景事件维度值明细表
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edscdi")
	public Map<String, Object> edscdi(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增修改场景事件维度值明细表开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());	
		Map<String, Object> resmap = client.callClient("edsedm", req);
		
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "场景事件维度值明细表新增成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		
		logger.debug("--------------------新增修改场景事件维度值明细表结束------------------------");
		return resmap;
	}
	
	/**
	 * 场景事件维度值明细表删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrscdi", method = { RequestMethod.DELETE })
	public Map<String, Object> descdi(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dlsedm", req);								//		
		if (resmap.get("retCode").toString().equals("0000")) {
			
			resmap.put("ret", "success");
			resmap.put("msg", "场景事件维度值明细表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
//	-------------------------------------------------
}
