package cn.sunline.webcore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.sunline.client.Client;
import cn.sunline.dict.ApSysDict;
import cn.sunline.dict.ApSysDictService;
import cn.sunline.domain.endauth.BSBUser;
import cn.sunline.domain.endauth.Role;
import cn.sunline.domain.endauth.SifSysAuth;
import cn.sunline.domain.endauth.SifSysRole;
import cn.sunline.exception.SumpException;
import cn.sunline.service.endauth.SifSysAuthService;
import cn.sunline.service.endauth.SifSysRoleService;
/**
 * 
 * @author lwp
 * 获取字典型类
 * 公用/dict
 * 从别的表中获取字典取
 */
@Controller("DictController")
@ResponseBody
@RequestMapping(value = "/rest")
@SessionAttributes("User")
public class DictController {
	
	@Autowired
	private ApSysDictService apSysDictService;
	
	@Autowired
	private SifSysAuthService sifSysAuthService;
	
	@Autowired
	private SifSysRoleService sifSysRoleService;
	
	@Autowired 
	Client client;
	
	private static Logger logger = LoggerFactory
			.getLogger(DictController.class);
	
	/**
	 * 字典表中获取字典
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/dict")
	public List<ApSysDict> findDictByType(@RequestBody ApSysDict req){			
			List<ApSysDict> list=apSysDictService.getDictsByDictType(req.getDictType());
		return list;
	}
	
	/**
	 * auth表中获取字典
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/dict/auth")
	public List<SifSysAuth> findAuthByTmp(@RequestBody ApSysDict req){		
		SifSysAuth temp=new SifSysAuth();
		temp.setAuthType(req.getDictType());		
		List<SifSysAuth> list=sifSysAuthService.queryEntitiesByTemplate(temp);
	    return list;
	
    }
	
	/**
	 * 查询已有权限
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dict/role")
	public List<SifSysRole> findRoleDict(@ModelAttribute("User") BSBUser user){
		Role role =new Role();
		role.setRegisterCd(user.getCorpno());
		List<SifSysRole> list=sifSysRoleService.queryEntitiesByTemplate(role);		
		return list;		
	}
	
	/**
	 * 查询机构字典，接口实现
	 * @param brchno
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/dict/brch")
	public List<Map<String,Object>> findBrch(@ModelAttribute("User") BSBUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> rspmap = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		logger.debug("机构字典:"+map);
		rspmap = client.callClient("pbqrbr", map);
		List<Map<String,Object>> list=null;
		try{
			list= (List<Map<String,Object>>) rspmap.get("brinfo");
		}catch(Exception e){
			throw(new SumpException("获取机构字典列表转换异常！"));
		}
		return list;		
	}
	
	/**
	 * 查询所有商户字典，接口实现
	 * @param brchno
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/dict/merc")
	public List<Map<String,Object>> findMerc(@ModelAttribute("User") BSBUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> rspmap = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		logger.debug("商户字典:"+map);
		rspmap = client.callClient("kcqrmc", map);
		List<Map<String,Object>> list= (List<Map<String,Object>>) rspmap.get("mercls");
		return list;		
	}
	
	/**
	 * 参考利率字典
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/dict/rfir")
	public List<Map<String,Object>> findCKIntr(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.debug("参考利率字典:"+reqmap);
		Map<String, Object> rspmap = new HashMap<String, Object>();
		Map<String,Object> map=(Map<String,Object>) reqmap.get("dictType");
		map.put("userid", user.getUserid());
		map.put("pageno", 1); 
		map.put("pagesize", 20);
		rspmap = client.callClient("selrfi", map);
		logger.debug("参考利率返回:"+rspmap);
		List<Map<String,Object>> list=(List<Map<String,Object>>) rspmap.get("infos");
		for (Map<String, Object> rfirmap : list) {
			rfirmap.put("rfirdd",rfirmap.get("efctdt")+"-"+rfirmap.get("inefdt"));
		}
		return list;
	}
	
	/**
	 * 查询定价属性字典
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/dict/prop")
	public List<Map<String,Object>> findProp(@RequestBody Map<String,Object> map,@ModelAttribute("User") BSBUser user){
		Map<String, Object> rspmap = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		logger.debug("定价属性字典:"+map);
		rspmap = client.callClient("kccltp", map);
		List<Map<String,Object>> list= (List<Map<String,Object>>) rspmap.get("clkctp");
		return list;		
	}

	/**
	 * 查询对公产品基础属性
	 * 
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dict/dppb")
	public List<Map<String,Object>> seldppDict(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询产品基础属性接口对接开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		map.put("prodtp", 2); //2--对公产品类型
		map.put("pageno", 1);
		map.put("pagesize", 100);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("dpqrpd", map);
		logger.debug("------------------查询产品基础属性接口对接结束-----------------");
		List<Map<String,Object>> list= (List<Map<String,Object>>) resmap.get("infos");
		return list;
	}
	
	/**
	 * 查询角色字典，接口实现
	 * @param brchno
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/dict/roleid")
	public List<Map<String,Object>> findRole(@ModelAttribute("User") BSBUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> rspmap = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		logger.debug("角色字典:"+map);
		rspmap = client.callClient("pbqrro", map);
		List<Map<String,Object>> list=null;
		try{
			list= (List<Map<String,Object>>) rspmap.get("roleInfos");
		}catch(Exception e){
			throw(new SumpException("获取角色字典列表转换异常！"));
		}
		return list;		
	}
	
	/**
	 * 查询角色字典，接口实现
	 * @param brchno
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/dict/permcd")
	public List<Map<String,Object>> findPerm(@ModelAttribute("User") BSBUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> rspmap = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		logger.debug("权限字典:"+map);
		rspmap = client.callClient("pbqrpm", map);
		List<Map<String,Object>> list=null;
		try{
			list= (List<Map<String,Object>>) rspmap.get("permInfos");
		}catch(Exception e){
			throw(new SumpException("获取权限字典列表转换异常！"));
		}
		logger.debug("===========================================权限字段:"+list.toString());
		return list;		
	}
}
