package cn.sunline.auth.service.webservice;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.sunline.client.Client;
import cn.sunline.domain.endauth.BSBUser;
import cn.sunline.domain.endauth.Role;
import cn.sunline.domain.endauth.RoleAuth;
import cn.sunline.domain.endauth.SifSysRole;
import cn.sunline.domain.endauth.SifSysRoleUser;
import cn.sunline.exception.SumpException;
import cn.sunline.message.Message;
import cn.sunline.service.endauth.SifSysRoleAuthService;
import cn.sunline.service.endauth.SifSysRoleService;
import cn.sunline.service.endauth.SifSysRoleUserService;
import cn.sunline.utils.Digests;
import cn.sunline.utils.PageToDataTable;

/**
 * 
 * @author lwp ResponseBody 支持json配置
 */
@Controller("UserController")
@ResponseBody
@RequestMapping(value = "/rest/auth")
@SessionAttributes("User")
public class UserController {
	@Autowired
	private Message message;
	@Autowired
	private Client client;
	@Autowired
	private SifSysRoleUserService sifSysRoleUserService;
	@Autowired
	private SifSysRoleService sifSysRoleService;
	@Autowired
	private SifSysRoleAuthService sifSysRoleAuthService;

	private static Logger logger = LoggerFactory
			.getLogger(UserController.class);
	//新增用户默认密码
	private static String  PASSWORD="123456";

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login")
	public Map<String, Object> login(@RequestBody BSBUser user, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resmap = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		map.put("passwd", Digests.GetMD5Code(user.getPasswd()));
		map.put("pswdfg", user.getPswdfg());
		// 通讯
		resmap = client.callClient("userin", map);
		if (resmap.get("retCode").toString().equals("0000") ) {
			logger.debug("------------------用户登陆-----------------"+resmap.toString());
			user.setUserna(resmap.get("userna").toString());
			user.setSystdt(resmap.get("systdt").toString());
			user.setBrchno(resmap.get("brchno").toString());
			resmap.put("ret", "success");
			resmap.put("msg", "登陆成功");
			model.addAttribute("User", user);//写入session
		} else {			
			resmap.put("msg", resmap.get("retMsg").toString());
				}
		return resmap;
	}

	/**
	 * 用户退出
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public void loginOut(HttpServletRequest request,Model model,
			@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
	    client.callClient("pbusgt", map);
		HttpSession session = request.getSession(false);  
		Enumeration<String> em = session.getAttributeNames();
		while (em.hasMoreElements()) {
			request.getSession().removeAttribute(
					em.nextElement().toString());
		}
		 session.removeAttribute("User");
		 session.invalidate();//session无效处理
		 model.asMap().clear();//清除model中的对象
	}

	/**
	 * 用户密码修改
	 * 
	 * @param oldpwd
	 * @param passwd
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ucpswd")
	public Map<String, Object> changePWD(@RequestBody Map map,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------用户密码修改-----------------");
		Map<String, Object> resmap = new HashMap<String, Object>();
		if (map != null) {
			map.put("userid", user.getUserid());
			map.put("passwd",Digests.GetMD5Code(map.get("passwd").toString()));
			map.put("nwpswd",Digests.GetMD5Code(map.get("nwpswd").toString()));
		} else {
			throw new SumpException("1001");
		}
		resmap = client.callClient("ucpswd", map);
		logger.debug("------------------用户密码修改结束-----------------");
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "密码修改成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());		
		}
		return resmap;
	}

	/**
	 * 用户重置状态
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/setUSt")
	public Map<String, Object> setUserStatus(@RequestBody BSBUser user) {
		logger.debug("------------------用户重置状态接口开始-----------------");
		Map<String, Object> resmap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		map.put("passwd", Digests.GetMD5Code(user.getPasswd()));
		map.put("usercd", user.getUserid());
		resmap = client.callClient("pbuslg", map);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "状态重置成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());	
		}
		logger.debug("------------------用户重置状态接口结束-----------------");
		return resmap;

	}

	/**
	 * 条件查询所有柜员
	 * 
	 * @param inmap
	 * @return
	 */
	@RequestMapping(value = "/allUser")
	public Map<String, Object> findByPage(
			@RequestParam Map<String, Object> inmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询柜员接口对接开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (inmap.get("q_userid") != null && inmap.get("q_userid") != "") {
			map.put("userid", inmap.get("q_userid"));
		}
		if (inmap.get("q_userna") != null && inmap.get("q_userna") != "") {
			map.put("userna", inmap.get("q_userna"));
		}
		;
		map.put("usercd", user.getUserid());

		int length = Integer.parseInt((String) inmap.get("length"), 10);
		int start = Integer.parseInt((String) inmap.get("start"), 10);
		map.put("pageno", start / length+1);
		map.put("recdct", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("pbslus", map);			
		resmap.put("data", resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));		
		return resmap;
	}

	/**
	 * 新增柜员
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/adduser")
	public Map<String, Object> saveUser(@RequestBody BSBUser user,
			@ModelAttribute("User") BSBUser cuser) {

		logger.debug("------------------新增柜员接口对接开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usercd", cuser.getUserid());
		map.put("userid", user.getUserid());
		map.put("userna", user.getUserna());
		if(user.getUsform()==null){
			map.put("usform", "0");
		}else{
			map.put("usform", user.getUsform());
		}
		map.put("usrole", "1");// 使用默认值
		map.put("brchno", user.getBrchno());
		map.put("passwd",Digests.GetMD5Code(PASSWORD));
		map.put("pwaect", user.getPwaect());
		map.put("userst", user.getUserst());
		map.put("userlv",user.getUserlv());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("pbinus", map);
		logger.debug("------------------新增柜员接口对接结束-----------------");
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "新增柜员成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());	
		}
		return resmap;

	}

	/**
	 * 柜员重置密码
	 * 
	 * @param user
	 * @param cuser
	 * @return
	 */
	@RequestMapping(value = "/urpswd")
	public Map<String, Object> reSetPWD(@RequestBody BSBUser user,
			@ModelAttribute("User") BSBUser cuser) {
		logger.debug("------------------柜员重置密码接口对接开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		map.put("nwpswd", Digests.GetMD5Code("123456"));
		map.put("usercd", cuser.getUserid());
		
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("urpswd", map);
		logger.debug("------------------柜员重置密码接口对接就结束-----------------");
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "新增柜员成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());

		}
		return resmap;
	}

	/**
	 * 柜员注销
	 * 
	 * @param user
	 * @param cuser
	 * @return
	 */
	@RequestMapping(value = "/allUser", method = { RequestMethod.DELETE })
	public Map<String, Object> delUser(@RequestBody BSBUser user,
			@ModelAttribute("User") BSBUser cuser) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		map.put("usercd", cuser.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("pbupus", map);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "注销柜员成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
	}
	/**
	 * 柜员信息修改
	 * @param user
	 * @param cuser
	 * @return
	 */
	@RequestMapping(value = "/upuser")
	public Map<String,Object> upUser(@RequestBody BSBUser user,
			@ModelAttribute("User") BSBUser cuser){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		map.put("userna", user.getUserna());
		map.put("pwaect", user.getPwaect());
		map.put("userst", user.getUserst());
		map.put("usercd", cuser.getUserid());
		map.put("userlv", user.getUserlv());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("urmain", map);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "修改柜员成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		return resmap;
		
	}

	/**
	 * //根据 User查询 SifSysRoleUser
	 * 
	 * @param sifSysRole
	 * @return
	 */

	@RequestMapping(value = "/allUserRole")
	public Map<String, Object> allUserRole(
			@RequestParam Map<String, Object> reqmap,@ModelAttribute("User") BSBUser user) {
		/*
		 * 保存role
		 */
		logger.debug("------------------根据 User查询 SifSysRoleUser------------------");
		SifSysRoleUser userRole = new SifSysRoleUser();
		userRole.setUserCd(reqmap.get("userCd").toString());
		userRole.setRegisterCd(user.getCorpno());
		int length = Integer.parseInt((String) reqmap.get("length"), 10);
		int start = Integer.parseInt((String) reqmap.get("start"), 10);
		Pageable pageable = new PageRequest(start / length, length);
		Page<SifSysRoleUser> page = sifSysRoleUserService
				.queryEntitiesByTemplateWithPage(userRole, pageable);
		Map<String, Object> map = PageToDataTable.convert(page);
		logger.debug("------------------根据 User查询 SifSysRoleUser 结束------------------"
				+ map.toString());
		return map;
	}

	/**
	 * 新增柜员角色
	 * 
	 * @param sifSysUserRole
	 * @return
	 */
	@RequestMapping(value = "/addUserRole")
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, String> addUserRole(
			@RequestBody SifSysRoleUser sifSysRoleUser,@ModelAttribute("User") BSBUser user) {
		/*
		 * 保存roleUser
		 */
		logger.debug("--------------------新增柜员角色开始------------------------");
		Map<String, String> map = new HashMap<String, String>();
		// 判断权限是否存在
		Role tmp = new Role();
		tmp.setRoleCd(sifSysRoleUser.getRoleCd());
		tmp.setRegisterCd(user.getCorpno());
		tmp.setAuthType(sifSysRoleUser.getAuthType());
		List<SifSysRole> list = sifSysRoleService
				.queryEntitiesByTemplate(tmp);
		if (list.size() == 0) {
			map.put("ret", "error");
			map.put("msg", "角色已不存在,新增失败");
			return map;
		}
		sifSysRoleUser.setRegisterCd(user.getCorpno());//法人号从sisson获取
		try {			
			sifSysRoleUserService.saveEntity(sifSysRoleUser);
		} catch (Throwable e) {
			map.put("ret", "error");
			map.put("msg", "角色已存在,新增失败");
			return map;
		}		
		map.put("ret", "success");
		map.put("msg", "角色新增成功");		
		logger.debug("--------------------新增柜员角色结束------------------------");		
		return map;
	}

	/**
	 * 删除柜员角色
	 * 
	 * @param sifSysRoleUser
	 * @return
	 */
	@RequestMapping(value = "/allUserRole", method = {RequestMethod.DELETE })
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, String> delUserRole(
			@RequestBody SifSysRoleUser sifSysRoleUser) {
		logger.debug("-------------------- 删除柜员角色开始------------------------");
		Map<String, String> map = new HashMap<String, String>();
		try {
			RoleAuth ra =new RoleAuth();
			ra.setRoleCd(sifSysRoleUser.getRoleCd());
			sifSysRoleUserService.deleteEntity(sifSysRoleUser);
		} catch (SumpException e) {
			map.put("ret", "error");
			map.put("msg", sifSysRoleUser.getUserCd() + "删除失败");
			return map;
		}
		map.put("ret", "success");
		map.put("msg", sifSysRoleUser.getUserCd() + "删除成功");
		logger.debug("-------------------- 删除柜员角色结束------------------------");
		return map;

	}
	
	
}
