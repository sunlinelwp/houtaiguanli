
package cn.sunline.auth.service.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.sunline.domain.endauth.BSBUser;
import cn.sunline.domain.endauth.RoleAuth;
import cn.sunline.domain.endauth.SifSysAuth;
import cn.sunline.domain.endauth.SifSysRoleAuth;
import cn.sunline.domain.endauth.SifSysRoleUser;
import cn.sunline.service.endauth.SifSysAuthService;
import cn.sunline.service.endauth.SifSysRoleAuthService;
import cn.sunline.service.endauth.SifSysRoleUserService;

@Controller("AuthController")
@RequestMapping(value = "/rest/auth")
@ResponseBody
@SessionAttributes("User")
public class AuthController {
	@Autowired
	private SifSysAuthService sifSysAuthService;

	@Autowired
	private SifSysRoleUserService sifSysRoleUserService;

	@Autowired
	private SifSysRoleAuthService sifSysRoleAuthService;

	private static String AUTHTYPE = "2";//菜单权限类型

	private String[] strs = {};//user用有权限AuthCd数组

	private static Logger logger = LoggerFactory
			.getLogger(AuthController.class);
	List<Order> orderList = new ArrayList<Order>();
	private static Sort sort=null;
	public AuthController(){
		Order order1 = new Order(Direction.ASC,"sortno");
		orderList.add(order1);
		Order order2 = new Order(Direction.ASC,"authCd");
		orderList.add(order2);
		sort =new Sort(orderList);
	}
	
	
/**
 *  获取user菜单权限
 * @param user
 * @return
 */
	@RequestMapping(value = "/menu")
	public Map<String, Object> menu(@ModelAttribute("User") BSBUser user) {
		
		SifSysRoleUser roleUser = new SifSysRoleUser();
		roleUser.setRegisterCd(user.getCorpno());
		roleUser.setAuthType(AUTHTYPE);// 2为菜单权限
		roleUser.setUserCd(user.getUserid());
		logger.debug(roleUser.toString());
		List<SifSysRoleUser> ruList = sifSysRoleUserService
				.queryEntitiesByTemplate(roleUser);// 一个柜员可能对应多个角色
		List<SifSysRoleAuth> authlist = new ArrayList<SifSysRoleAuth>();
		RoleAuth roleauth = new RoleAuth();
		for (SifSysRoleUser sifSysRoleUser : ruList) {
			// 通过角色来查询角色权限
			roleauth.setAuthType(sifSysRoleUser.getAuthType());
			roleauth.setRegisterCd(sifSysRoleUser.getRegisterCd());
			roleauth.setRoleCd(sifSysRoleUser.getRoleCd());
			authlist.addAll(sifSysRoleAuthService
					.queryEntitiesByTemplate(roleauth));
		}
		//权限去重复
		HashSet<SifSysRoleAuth> hashSet = new HashSet<SifSysRoleAuth>(authlist);
		authlist.clear();
		authlist.addAll(hashSet);
		int k = 0;
		strs = new String[authlist.size()];
		for (SifSysRoleAuth sifSysRoleAuth : authlist) {
			strs[k] = sifSysRoleAuth.getAuthCd();
			k++;
		}

		// 查询所有菜单
		SifSysAuth tmp = new SifSysAuth();
		tmp.setAuthType(AUTHTYPE);// 2 为菜单权限
		tmp.setRank(1);//从第一级开始取
		tmp.setRegisterCd(user.getCorpno());//法人号
		List<SifSysAuth> menu = new ArrayList<SifSysAuth>();
		menu.addAll(sifSysAuthService.queryEntitiesByTemplateWithSort(tmp, sort));		
		Map<String, Object> mapOne = new HashMap<String, Object>();
		mapOne.put("menu", reGetMenu(tmp, tmp.getRank(),menu,true));
		logger.debug("---------菜单" + mapOne.toString());
		return mapOne;
	}
	
	/**
	 * 根据sifsysauth查询子项
	 * @param tmp  权限模板
	 * @param rank  层级
	 * @param fuMenu 父级菜单权限
	 * @param flag 是否控制权限 true控制权限 false不控制
	 * @return
	 */
	private List<SifSysAuth> reGetMenu(SifSysAuth tmp, int rank,List<SifSysAuth>  fuMenu,Boolean flag) {
		// 取1级菜单		
		List<SifSysAuth> delList = new ArrayList<SifSysAuth>();// list 遍历元素时不允许删除元素，创建一个List用于储存删除的元素，遍历后集中集中删除
		/*
		 * 循环遍历这一级菜单，分别获取下一级级菜单
		 */
			for (SifSysAuth sifSysAuth : fuMenu) {// 循环处理父菜单
				/*
				 * 判断user是否拥有权限
				 * 无父级菜单权限，子菜单权限无效
				 */
				if (strInArray(sifSysAuth.getAuthCd(), strs)&&flag) { 
					delList.add(sifSysAuth);// 放入删除List中
					continue;
				}
				tmp.setRank(rank + 1);// 取下一级菜单级菜单
				tmp.setParentAuthCd(sifSysAuth.getAuthCd());// 设置父级cd
				List<SifSysAuth> ziMenu = new ArrayList<SifSysAuth>();
				ziMenu.addAll(sifSysAuthService.queryEntitiesByTemplateWithSort(tmp,sort));
				if (ziMenu.size() > 0) {
					sifSysAuth.setChildren(reGetMenu(tmp, tmp.getRank(),ziMenu,flag));// 递归处理
					sifSysAuth.setHaschild("Y");
				}
			}

		fuMenu.removeAll(delList);
		return fuMenu;
	}

	/**
	 * 前台获取session信息
	 * @param user
	 * @param UserId
	 * @return
	 */
	@RequestMapping(value = "/UInfo")
	public Map<String, Object> getUserInfo(@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (user.getUserid() == null || user.getUserid() == "") {
			map.put("ret", "error");
			map.put("msg", "您未登录,请登录");
			return map;
		}
		logger.info(user.toString());
		logger.info(user.getUserid());
		map.put("user", user);
		map.put("ret", "success");
		map.put("msg", "成功");
		return map;

	}
	/**
	 * 查询所有菜单
	 * @return
	 */
	@RequestMapping(value = "/allMenu")
	public  Map<String, Object> allMenu(@ModelAttribute("User") BSBUser user){
		// 查询所有菜单
		SifSysAuth tmp = new SifSysAuth();
		tmp.setAuthType(AUTHTYPE);// 2 为菜单权限
		tmp.setRank(1);//从第一级开始取
		tmp.setRegisterCd(user.getCorpno());//机构号
		List<SifSysAuth> menu = sifSysAuthService.queryEntitiesByTemplateWithSort(tmp, sort);				
		Map<String, Object> mapOne = new HashMap<String, Object>();
		mapOne.put("menu", reGetMenu(tmp, tmp.getRank(),menu,false));
		logger.debug("---------菜单" + mapOne.toString());
		return mapOne;	
	}
	/**
	 * 删除权限
	 * @param auth
	 * @return
	 */
	@RequestMapping(value = "/delAuth")
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String,Object> delAuth(@RequestBody SifSysAuth auth,@ModelAttribute("User") BSBUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		auth.setRegisterCd(user.getCorpno());
		RoleAuth ra=new RoleAuth();
		ra.setAuthCd(auth.getAuthCd());
		List<SifSysRoleAuth> ssra=sifSysRoleAuthService.queryEntitiesByTemplate(ra);
		try{
		sifSysAuthService.deleteEntity(auth);
		if(ssra.size()>0){
		sifSysRoleAuthService.deleteEntities(ssra);
		}
		map.put("ret", "success");
		map.put("msg", "权限删除成功");
		}catch(Throwable e) {
			map.put("msg", "权限删除失败");
		}
		return map;
	}
	
	
	/**
	 * 新增权限
	 * @param auth
	 * @return
	 */
	@RequestMapping(value = "/addAuth")
	@Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> addAuth(@RequestBody SifSysAuth auth,@ModelAttribute("User") BSBUser user){
				auth.setRegisterCd(user.getCorpno());
    	SifSysAuth tmp=sifSysAuthService.expandEntity(auth);
    	Map<String, Object> map = new HashMap<String, Object>();
    	if(tmp==null){
    		try {
				sifSysAuthService.saveEntity(auth);
				map.put("ret", "success");
				map.put("msg", "权限新增成功");
			} catch (Throwable e) {
				map.put("msg", "权限新增失败");
			}
    		
    	}else{
    		map.put("msg", "权限已存在");
    	}
    	
    	return map;
    }
	/**
	 * 修改权限
	 * @param auth
	 * @return
	 */
	@RequestMapping(value = "/updAuth")
	@Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> updAuth(@RequestBody SifSysAuth auth,@ModelAttribute("User") BSBUser user){
		auth.setRegisterCd(user.getCorpno());
		Map<String, Object> map = new HashMap<String, Object>();
		SifSysAuth tmp=sifSysAuthService.updateEntity(auth);  	
    	if(tmp!=null){    	
				map.put("ret", "success");
				map.put("msg", "权限修改成功");   		
    	}else{
			map.put("msg", "权限修改失败"); 
    	}    	
    	return map;
    }
	/*
	 * 判断字符串数组是否包含字符串
	 */
	private boolean strInArray(String str, String[] strs) {
		for (int i = 0; i < strs.length; i++) {
			if (str.equals(strs[i])) {
				return false;
			}
		}
		return true;
	}

}
