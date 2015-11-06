package cn.sunline.auth.service.webservice;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sunline.domain.endauth.Role;
import cn.sunline.domain.endauth.RoleAuth;
import cn.sunline.domain.endauth.SifSysRole;
import cn.sunline.domain.endauth.SifSysRoleAuth;
import cn.sunline.service.endauth.SifSysRoleAuthService;
import cn.sunline.service.endauth.SifSysRoleService;
import cn.sunline.utils.PageToDataTable;

@Controller("RoleController")
@RequestMapping(value = "/rest/auth")
@ResponseBody
public class RoleController {
	@Autowired
	private SifSysRoleService sifSysRoleService;	
	@Autowired
	private SifSysRoleAuthService sifSysRoleAuthService;
	
	private static Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	/**
	 * 查询
	 * @param reqmap
	 * @return
	 */
	@RequestMapping(value="/allrole")
	public Map<String, Object> allRole(@RequestParam Map<String,Object> reqmap){
		Role role=new Role();
		if(reqmap.get("q_authType")!=null&&reqmap.get("q_authType")!=""){
			role.setAuthType(reqmap.get("q_authType").toString());
		}
		if(reqmap.get("q_roleCd")!=null&&reqmap.get("q_roleCd")!=""){
			role.setRoleCd(reqmap.get("q_roleCd").toString());	
		}
		if(reqmap.get("q_roleName")!=null&&reqmap.get("q_roleName")!=""){
			role.setRoleName(reqmap.get("q_roleName").toString());
		}
		int length=Integer.parseInt((String)reqmap.get("length"),10);		
		int start=Integer.parseInt((String)reqmap.get("start"),10);		
		Pageable pageable=new PageRequest(start/length,length);
		Page<SifSysRole> page=sifSysRoleService.queryEntitiesByTemplateWithPage(role, pageable);	  
	    Map<String, Object> map=PageToDataTable.convert(page);

		return map;
	}
    
	/**
	 * 修改
	 * @param sifSysRole
	 * @return
	 */
	@SuppressWarnings("null")
	@RequestMapping(value="saverole")
	@Transactional(propagation= Propagation.REQUIRED)
	public Map<String,String> saveRole (@RequestBody SifSysRole sifSysRole){
		/*
		 * 保存role
		 */
		Map<String,String> map=new HashMap<String, String>();
		
		if(sifSysRole==null){
			map.put("ret", "error");
			map.put("msg",sifSysRole.getRoleCd()+ "请求数据丢失");
			return map;
		}
		//System.out.println(sifSysRole.getRegisterCd());
		SifSysRole s=new SifSysRole();
		s=sifSysRoleService.updateEntity(sifSysRole);
		if(s==null){
			logger.debug("修改ROLE失败，"+sifSysRole.getRoleCd()+ "已不存在,修改失败");
			map.put("ret", "error");
			map.put("msg",sifSysRole.getRoleCd()+ "已不存在,修改失败");
			return map;
		}
		map.put("ret", "success");
		map.put("msg",s.getRoleCd()+ "修改成功");
		return map;	
	}
	/**
	 * 删除
	 * @param sifSysRole
	 * @return
	 */
	@RequestMapping(value="/allrole",method = {RequestMethod.DELETE})
	@Transactional(propagation = Propagation.REQUIRED)	
	public Map<String, String> delRole(@RequestBody SifSysRole sifSysRole){
		/*
		 * 单个删除权限
		 */	
		Map<String,String> map=new HashMap<String, String>();
		SifSysRole s=new SifSysRole();
		s=sifSysRoleService.expandEntity(sifSysRole);
		if(s!=null){
			//TODO 删除子表
			sifSysRoleService.deleteEntity(sifSysRole);
			map.put("retCode", "success");
			map.put("retMsg",s.getRoleCd()+ "删除成功");
			return map;	
		}	
		map.put("retCode", "success");
		map.put("retMsg",sifSysRole.getRoleCd()+ "删除失败");
		return map;	
	}
	
	/**
	 * 新增
	 * @param sifSysRole
	 * @return
	 */
	@RequestMapping(value="/addrole")
	@Transactional(propagation = Propagation.REQUIRED)	
	public Map<String,String> addRole(@RequestBody SifSysRole sifSysRole){
		/*
		 * 保存role
		 */
		Map<String,String> map=new HashMap<String, String>();
		//System.out.println(sifSysRole.getRegisterCd());
		SifSysRole s=new SifSysRole();
		try {
			s=sifSysRoleService.saveEntity(sifSysRole);
		} catch (Throwable e) {
			logger.info("修改ROLE新增，"+sifSysRole.getRoleCd()+ "已存在,新增失败");
			map.put("ret", "error");
			map.put("msg",sifSysRole.getRoleCd()+ "已存在,新增失败");
			return map;
		}
		if(s==null){
			logger.info("修改ROLE新增，"+sifSysRole.getRoleCd()+ "新增失败");
			map.put("ret", "error");
			map.put("msg",sifSysRole.getRoleCd()+ "新增失败，请联系管理员");
			return map;
		}
		map.put("ret", "success");
		map.put("msg",s.getRoleCd()+ "新增成功");
		return map;		
	}
	/**
	 * //根据 SifSysRole查询 SifSysRoleAuth查询菜单权限
	 * @param sifSysRole
	 * @return
	 */
	
	@RequestMapping(value="/allroleauth")
	public Map<String, Object> allRoleAuth(@RequestParam Map<String,Object> reqmap){
	
		RoleAuth roleAuth=new RoleAuth();
		roleAuth.setRoleCd(reqmap.get("roleCd").toString());
		roleAuth.setAuthType(reqmap.get("authType").toString());
		roleAuth.setRegisterCd(reqmap.get("registerCd").toString());
		if(reqmap.get("qq_authCd")!=null&&reqmap.get("qq_authCd")!=""){
			roleAuth.setAuthCd(reqmap.get("qq_authCd").toString());
		}
		int length=Integer.parseInt((String)reqmap.get("length"),10);		
		int start=Integer.parseInt((String)reqmap.get("start"),10);	
		Pageable pageable=new PageRequest(start/length,length);
		Page<SifSysRoleAuth> page=sifSysRoleAuthService.queryEntitiesByTemplateWithPage(roleAuth, pageable);
		 Map<String, Object> map=PageToDataTable.convert(page);
		return map;		
	}
	
	/**
	 * 新增菜单权限
	 * @param sifSysRoleAuth
	 * @return
	 */
	@RequestMapping(value="/addRoleAuth")
	@Transactional(propagation = Propagation.REQUIRED)	
	public Map<String,String> addRoleAuth(@RequestBody SifSysRoleAuth sifSysRoleAuth){
		/*
		 * 保存role
		 */
		Map<String,String> map=new HashMap<String, String>();
		SifSysRoleAuth s=new SifSysRoleAuth();
		try {
			s=sifSysRoleAuthService.saveEntity(sifSysRoleAuth);
		} catch (Throwable e) {
			logger.info("Auth新增，"+sifSysRoleAuth.getAuthCd()+ "已存在,新增失败");
			map.put("ret", "error");
			map.put("msg",sifSysRoleAuth.getAuthCd()+ "已存在,新增失败");
			return map;
		}
		if(s==null){
			logger.info("Auth新增，"+sifSysRoleAuth.getAuthCd()+ "新增失败");
			map.put("ret", "error");
			map.put("msg",sifSysRoleAuth.getAuthCd()+ "新增失败，请联系管理员");
			return map;
		}
		map.put("ret", "success");
		map.put("msg",s.getAuthCd()+ "新增成功");
		return map;		
	}
	/**
	 * 删除角色权限
	 * @param sifSysRole
	 * @return
	 */
	@RequestMapping(value="/allroleauth",method = {RequestMethod.DELETE})
	@Transactional(propagation = Propagation.REQUIRED)	
	public Map<String, String> delRole(@RequestBody SifSysRoleAuth sifSysRoleAuth){
		/*
		 * 单个删除角色权限
		 */	
		Map<String,String> map=new HashMap<String, String>();
		SifSysRoleAuth s=new SifSysRoleAuth();
		s=sifSysRoleAuthService.expandEntity(sifSysRoleAuth);
		if(s!=null){
			sifSysRoleAuthService.deleteEntity(sifSysRoleAuth);
			map.put("retCode", "0000");
			map.put("retMsg", "删除成功");
			return map;	
		}	
		map.put("retCode", "9999");
		map.put("retMsg","删除失败");
		return map;	
	}
}
