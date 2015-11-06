package cn.sunline.perm.controller;

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



@Controller("PermController")
@RequestMapping(value = "/rest/perm")
@ResponseBody
@SessionAttributes("User")
public class PermController {
	
	@Autowired
	Client client;

	private static Logger logger = LoggerFactory
			.getLogger(LoanController.class);

	
	/**
	 * 查询角色权限定义
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrrope")
	public Map<String, Object> qrrope(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询角色权限定义开始-----------------" + reqmap);
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_permcd") != null && reqmap.get("q_permcd") != "") {
			map.put("permcd", reqmap.get("q_permcd"));
		}
		if (reqmap.get("q_roleid") != null && reqmap.get("q_roleid") != "") {
			map.put("roleid", reqmap.get("q_roleid"));
		}
		if (reqmap.get("q_scencd") != null && reqmap.get("q_scencd") != "") {
			map.put("scencd", reqmap.get("q_scencd"));
		}
		map.put("userid", user.getUserid()); // 设置操作柜员
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagesz", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrrope", map);
		resmap.put(
				"data",
				resmap.get("ropeInfos") == null ? new ArrayList<Object>() : resmap
						.get("ropeInfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count") == null ? "0"
				: resmap.get("count"));
		resmap.put("iTotalRecords",
				resmap.get("count") == null ? "0" : resmap.get("count"));
		logger.debug("-----------------查询角色权限定义结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增角色权限定义属性
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/adrope")
	public Map<String, Object> adrope(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增新增角色权限定义属性开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("adrope", req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "角色权限定义操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增角色权限定义结束------------------------");
		return resmap;
	}

	/**
	 * 角色权限定义删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrrope",method = { RequestMethod.DELETE })
	public Map<String, Object> derope(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("derope", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "角色权限定义删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	
	
	/**
	 * 查询权限代码定义
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrperm")
	public Map<String, Object> qrperm(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询权限代码定义开始-----------------" + reqmap);
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_permcd") != null && reqmap.get("q_permcd") != "") {
			map.put("permcd", reqmap.get("q_permcd"));
		}
		if (reqmap.get("q_syscod") != null && reqmap.get("q_syscod") != "") {
			map.put("syscod", reqmap.get("q_syscod"));
		}
		if (reqmap.get("q_permtp") != null && reqmap.get("q_permtp") != "") {
			map.put("permtp", reqmap.get("q_permtp"));
		}
		map.put("userid", user.getUserid()); // 设置操作柜员
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagesz", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrperm", map);
		resmap.put(
				"data",
				resmap.get("permInfos") == null ? new ArrayList<Object>() : resmap
						.get("permInfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count") == null ? "0"
				: resmap.get("count"));
		resmap.put("iTotalRecords",
				resmap.get("count") == null ? "0" : resmap.get("count"));
		logger.debug("-----------------查询权限代码定义结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增权限代码定义属性
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/adperm")
	public Map<String, Object> adperm(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增权限代码定义属性开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("adperm", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "权限代码定义操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
			logger.debug("--------------------新增权限代码定义结束------------------------");
			return resmap;
		}


	
	
	
	/**
	 * 查询操作权限代码
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qroper")
	public Map<String, Object> qroper(@RequestParam  Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询角色权限定义开始-----------------" + reqmap);
		reqmap.put("userid", user.getUserid()); // 设置操作柜员
		if (reqmap.get("q_permcd") != null && reqmap.get("q_permcd") != "") {
			reqmap.put("permcd", reqmap.get("q_permcd"));
		}
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		reqmap.put("pageno", start / length + 1);// 当前页数
		reqmap.put("pagesz", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qroper", reqmap);
		resmap.put(
				"data",
				resmap.get("operInfos") == null ? new ArrayList<Object>() : resmap
						.get("operInfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count") == null ? "0"
				: resmap.get("count"));
		resmap.put("iTotalRecords",
				resmap.get("count") == null ? "0" : resmap.get("count"));
		logger.debug("-----------------查询角色权限定义结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增修改操作权限代码
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/adoper")
	public Map<String, Object> edoper(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增新增角色权限定义属性开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("adoper", req);
		logger.debug("--------------------新增新增角色权限定义结束------------------------");
		return resmap;
	}

	/**
	 * 操作权限代码删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qroper" , method = { RequestMethod.DELETE })
	public Map<String, Object> deoper(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("deoper", req); //
		return resmap;
	}
	
	/**
	 * 查询操作权限代码
	 * 
	 * @param reqmap
	 *            查询条件输入
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrchck")
	public Map<String, Object> qrchck(@RequestParam  Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询角色权限定义开始-----------------" + reqmap);
		reqmap.put("userid", user.getUserid()); // 设置操作柜员
		if (reqmap.get("q_permcd") != null && reqmap.get("q_permcd") != "") {
			reqmap.put("permcd", reqmap.get("q_permcd"));
		}
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		reqmap.put("pageno", start / length + 1);// 当前页数
		reqmap.put("pagesz", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrchck", reqmap);
		resmap.put(
				"data",
				resmap.get("chckInfos") == null ? new ArrayList<Object>() : resmap
						.get("chckInfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count") == null ? "0"
				: resmap.get("count"));
		resmap.put("iTotalRecords",
				resmap.get("count") == null ? "0" : resmap.get("count"));
		logger.debug("-----------------查询角色权限定义结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增修改操作权限代码
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/adchck")
	public Map<String, Object> edchck(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增新增角色权限定义属性开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("adchck", req);
		logger.debug("--------------------新增新增角色权限定义结束------------------------");
		return resmap;
	}

	/**
	 * 操作权限代码删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrchck" , method = { RequestMethod.DELETE })
	public Map<String, Object> dechck(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dechck", req); //
		return resmap;
	}

	/**
	 * 权限代码定义删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrperm", method = { RequestMethod.DELETE })
	public Map<String, Object> deperm(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("deperm", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "权限代码定义删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	
	
	
	
	
	
	/**
	 * 查询角色信息
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrrole")
	public Map<String, Object> qrrole(
			@RequestParam  Map<String,Object> req ,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------查询角色信息开始------------------------"+req.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		if (map.get("q_roleid") != null && map.get("q_roleid") != "") {
			map.put("roleid", req.get("q_roleid"));
		}
		if (map.get("q_rolena") != null && map.get("q_rolena") != "") {
			map.put("rolena", req.get("q_rolena"));
		}
		if (map.get("q_roletp") != null && map.get("q_roletp") != "") {
			map.put("roletp", req.get("q_roletp"));
		}
		if (map.get("q_syscod") != null && map.get("q_syscod") != "") {
			map.put("syscod", req.get("q_syscod"));
		}
		map.put("userid", user.getUserid()); // 设置操作柜员
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// 当前页数
		map.put("pagesz", length); // 每页记录数
		Map<String, Object> resmap = new HashMap<String, Object>();
		logger.debug("0000000000000"+map.toString());
		resmap = client.callClient("qrrole", map);
		resmap.put(
				"data",
				resmap.get("roleInfos") == null ? new ArrayList<Object>() : resmap
						.get("roleInfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count") == null ? "0"
				: resmap.get("count"));
		resmap.put("iTotalRecords",
				resmap.get("count") == null ? "0" : resmap.get("count"));
		logger.debug("-----------------查询角色信息结束-----------------");
		return resmap;
	}
	
	/**
	 * 新增角色
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/adrole")
	public Map<String, Object> adrole(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增角色开始------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("adrole", req);

		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "角色定义表操作成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}

		logger.debug("--------------------新增角色结束------------------------");
		return resmap;
	}
	
	/**
	 * 删除角色
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrrole", method = { RequestMethod.DELETE })
	public Map<String, Object> derole(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("derole", req); //
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "角色定义表删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}

	/**
	 * 查询柜员角色对应信息
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qruser")
	public Map<String, Object> selKcmtmplprop(
			@RequestParam Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------查询柜员角色信息开始------------------------"+req.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("length")) ? (String) req.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) req
				.get("start")) ? (String) req.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("pagesz", length);
		map.put("usercd", req.get("usercd")==null?"":req.get("usercd"));
		map.put("roleid", req.get("roleid")==null?"":req.get("roleid"));
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qruser", map);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "查询柜员角色对应信息成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------查询柜员角色信息结束------------------------");
		resmap.put("data", resmap.get("userInfos")==null?"[]":resmap.get("userInfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));
		
		return resmap;
	}
	
	/**
	 * 删除柜员角色对应信息
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qruser", method = { RequestMethod.DELETE })
	public Map<String, Object> delKcmtmplprop(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------删除柜员角色对应信息开始------------------------"+req.toString());
		req.put("userid", user.getUserid());
		req.put("usercd", req.get("usercd"));
		req.put("roleid", req.get("roleid"));
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("deuser", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "柜员角色删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.debug("--------------------删除柜员角色对应信息结束------------------------");
		return resmap;
	}
	
	/**
	 * 增加柜员角色对应信息
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/aduser")
	public Map<String, Object> addKcmtmplprop(
			@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增柜员角色对应信息开始------------------------"+req.toString());
		req.put("userid", user.getUserid());
//		req.put("corpno", user.getCorpno());
		req.put("usercd", req.get("usercd"));
		req.put("roleid", req.get("roleid"));
		req.put("rostat", req.get("rostat"));
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("aduser", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "增加柜员角色对应信息");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------新增柜员角色对应信息结束------------------------");		
		return resmap;
	}
}
