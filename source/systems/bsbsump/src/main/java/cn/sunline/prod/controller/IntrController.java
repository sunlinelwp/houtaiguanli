package cn.sunline.prod.controller;

import java.util.HashMap;
import java.util.Map;
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
/**
 * 
 * @author lwp
 * 利率
 */
@Controller("IntrController")
@RequestMapping(value = "/rest/intr")
@ResponseBody
@SessionAttributes("User")
public class IntrController {
	@Autowired
	Client client;
	private static Logger logger = LoggerFactory.getLogger(IntrController.class);

	/**
	 * 查询参考利率
	 * @param inmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/findIfir")
	public Map<String, Object> findIfirByPage(@RequestParam Map<String, Object> inmap,
			@ModelAttribute("User") BSBUser user){
		logger.debug("------------------查询参考利率接口对接开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (inmap.get("q_rfircd") != null && inmap.get("q_rfircd") != "") {
			map.put("rfircd", inmap.get("q_rfircd"));
		}
		if (inmap.get("q_crcycd") != null && inmap.get("q_crcycd") != "") {
			map.put("crcycd", inmap.get("q_crcycd"));
		}
		if (inmap.get("q_rfirtm") != null && inmap.get("q_rfirtm") != "") {
			map.put("rfirtm", inmap.get("q_rfirtm"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt((String) inmap.get("length"), 10);
		int start = Integer.parseInt((String) inmap.get("start"), 10);
		map.put("pageno", start / length+1);
		map.put("pagesize", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("selrfi", map);
		logger.debug("------------------查询参考利率接口对接结束-----------------");				
		resmap.put("data", resmap.get("infos")==null?"[]":resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));		
		return resmap;
				
	}
	
	/**
	 * 参考利率删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/findIfir", method = { RequestMethod.DELETE })
	public Map<String, Object> delIfir(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {	
		logger.debug("------------------参考利率删除接口开始-----------------"+req.toString());	
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("delrfi", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "参考利率删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.debug("------------------参考利率删除接口结束-----------------");
		return resmap;

	}
	
	/**
	 * 新增参考利率
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/addIfir")
	public Map<String, Object> addIfir(
			@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增参考利率开始------------------------"+req.toString());
		req.put("userid", user.getUserid());
		req.put("corpno", user.getCorpno());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("insrfi", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "新增参考利成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------新增参考利率结束------------------------");		
		return resmap;
	}
	/**
	 * 参考利率修改
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/upIfir")
	public Map<String,Object> upIfir(@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user){
		logger.debug("--------------------参考利率修改开始------------------------"+req.toString());
		Map<String, Object> resmap = new HashMap<String, Object>();	
		req.put("userid", user.getUserid());
		req.put("corpno", user.getCorpno());
		resmap = client.callClient("updrfi", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "参考利率修改成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------参考利率修改结束------------------------");			
		return resmap;		
	}

	/**
	 * 查询利率代码
	 * @param inmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/findInt")
	public Map<String, Object> findIntByPage(@RequestParam Map<String, Object> inmap,
			@ModelAttribute("User") BSBUser user){
		logger.debug("------------------查询利率代码接口对接开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (inmap.get("q_intrcd") != null && inmap.get("q_intrcd") != "") {
			map.put("intrcd", inmap.get("q_intrcd"));
		}
		if (inmap.get("q_intrna") != null && inmap.get("q_intrna") != "") {
			map.put("intrna", inmap.get("q_intrna"));
		}
		if (inmap.get("q_incdtp") != null && inmap.get("q_incdtp") != "") {
			map.put("incdtp", inmap.get("q_incdtp"));
		}
		if (inmap.get("q_rfirtp") != null && inmap.get("q_rfirtp") != "") {
			map.put("rfirtp", inmap.get("q_rfirtp"));
		}
		if (inmap.get("q_efctdt") != null && inmap.get("q_efctdt") != "") {
			map.put("efctdt", inmap.get("q_efctdt"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt((String) inmap.get("length"), 10);
		int start = Integer.parseInt((String) inmap.get("start"), 10);
		map.put("pageno", start / length+1);
		map.put("pagesize", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("selint", map); 
		logger.debug("------------------查询利率代码接口对接结束-----------------");				
		resmap.put("data", resmap.get("infos")==null?"[]":resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));		
		return resmap; 
				
	}
	/**
	 * 利率代码删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/findInt", method = { RequestMethod.DELETE })
	public Map<String, Object> delInt(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {	
		logger.debug("------------------利率代码删除接口开始-----------------"+req.toString());	
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("delint", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "利率代码删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.debug("------------------利率代码删除接口结束-----------------");
		return resmap;

	}
	/**
	 * 新增利率代码
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/addInt")
	public Map<String, Object> addInt(
			@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增利率代码开始------------------------"+req.toString());
		req.put("userid", user.getUserid());
		req.put("corpno", user.getCorpno());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("insint", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "新增参考利成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------新增利率代码结束------------------------");		
		return resmap;
	}
	/**
	 * 利率代码修改
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/upInt")
	public Map<String,Object> upInt(@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user){
		logger.debug("--------------------利率代码修改开始------------------------"+req.toString());
		Map<String, Object> resmap = new HashMap<String, Object>();	
		req.put("userid", user.getUserid());
		req.put("corpno", user.getCorpno());
		resmap = client.callClient("updint", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "利率代码修改成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------利率代码修改结束------------------------");			
		return resmap;		
	}	
	
	/**
	 * 查询行内基准利率
	 * @param inmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/findBki")
	public Map<String, Object> findBkiByPage(@RequestParam Map<String, Object> inmap,
			@ModelAttribute("User") BSBUser user){
		logger.debug("------------------查询行内基准利率接口对接开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (inmap.get("intrcd") != null && inmap.get("intrcd") != "") {
			map.put("intrcd", inmap.get("intrcd"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt((String) inmap.get("length"), 10);
		int start = Integer.parseInt((String) inmap.get("start"), 10);
		map.put("pageno", start / length+1);
		map.put("pagesize", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("selbki", map); 
		logger.debug("------------------查询行内基准利率接口对接结束-----------------");				
		resmap.put("data", resmap.get("infos")==null?"[]":resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));		
		return resmap; 				
	}
	
	/**
	 * 行内基准利率删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/findBki", method = { RequestMethod.DELETE })
	public Map<String, Object> delBki(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {	
		logger.debug("------------------行内基准利率删除接口开始-----------------"+req.toString());	
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("delbki", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "行内基准利率删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.debug("------------------行内基准利率删除接口结束-----------------");
		return resmap;

	}
	/**
	 * 新增行内基准利率
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/addBki")
	public Map<String, Object> addBki(
			@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增行内基准利率开始------------------------"+req.toString());
		req.put("userid", user.getUserid());
		req.put("corpno", user.getCorpno());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("insbki", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "新增参考利成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------新增行内基准利率结束------------------------");		
		return resmap;
	}
	/**
	 * 行内基准利率修改
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/upBki")
	public Map<String,Object> upBki(@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user){
		logger.debug("--------------------行内基准利率修改开始------------------------"+req.toString());
		Map<String, Object> resmap = new HashMap<String, Object>();	
		req.put("userid", user.getUserid());
		req.put("corpno", user.getCorpno());
		resmap = client.callClient("updbki", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "行内基准利率修改成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------行内基准利率修改结束------------------------");			
		return resmap;		
	}	
	/**
	 * 查询行内分层利率
	 * @param inmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/findFen")
	public Map<String, Object> findFenByPage(@RequestParam Map<String, Object> inmap,
			@ModelAttribute("User") BSBUser user){
		logger.debug("------------------查询行内分层利率接口对接开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (inmap.get("intrcd") != null && inmap.get("intrcd") != "") {
			map.put("intrcd", inmap.get("intrcd"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt((String) inmap.get("length"), 10);
		int start = Integer.parseInt((String) inmap.get("start"), 10);
		map.put("pageno", start / length+1);
		map.put("pagesize", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("selfen", map); 
		logger.debug("------------------查询行内分层利率接口对接结束-----------------");				
		resmap.put("data", resmap.get("infos")==null?"[]":resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));		
		return resmap; 				
	}
	
	/**
	 * 行内分层利率删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/findFen", method = { RequestMethod.DELETE })
	public Map<String, Object> delFen(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {	
		logger.debug("------------------行内分层利率删除接口开始-----------------"+req.toString());	
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("delfen", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "行内分层利率删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.debug("------------------行内分层利率删除接口结束-----------------");
		return resmap;

	}
	/**
	 * 新增行内分层利率
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/addFen")
	public Map<String, Object> addFen(
			@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增行内分层利率开始------------------------"+req.toString());
		req.put("userid", user.getUserid());
		req.put("corpno", user.getCorpno());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("insfen", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "新增参考利成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------新增行内分层利率结束------------------------");		
		return resmap;
	} 
	/**
	 * 行内分层利率修改
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/upFen")
	public Map<String,Object> upFen(@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user){
		logger.debug("--------------------行内分层利率修改开始------------------------"+req.toString());
		Map<String, Object> resmap = new HashMap<String, Object>();	
		req.put("userid", user.getUserid());
		req.put("corpno", user.getCorpno());
		resmap = client.callClient("updfen", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "行内分层利率修改成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------行内分层利率修改结束------------------------");			
		return resmap;		 
	}
	
	/**
	 * 查询行内靠档利率
	 * @param inmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/findRli")
	public Map<String, Object> findRliByPage(@RequestParam Map<String, Object> inmap,
			@ModelAttribute("User") BSBUser user){
		logger.debug("------------------查询行内靠档利率接口对接开始-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (inmap.get("intrcd") != null && inmap.get("intrcd") != "") {
			map.put("intrcd", inmap.get("intrcd"));
		}
		map.put("userid", user.getUserid());
		int length = Integer.parseInt((String) inmap.get("length"), 10);
		int start = Integer.parseInt((String) inmap.get("start"), 10);
		map.put("pageno", start / length+1);
		map.put("pagesize", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("selrli", map); 
		logger.debug("------------------查询行内靠档利率接口对接结束-----------------");				
		resmap.put("data", resmap.get("infos")==null?"[]":resmap.get("infos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count"));
		resmap.put("iTotalRecords", resmap.get("count"));		
		return resmap; 				
	}
	
	/**
	 * 行内靠档利率删除
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/findRli", method = { RequestMethod.DELETE })
	public Map<String, Object> delRli(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {	
		logger.debug("------------------行内靠档利率删除接口开始-----------------"+req.toString());	
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("delrli", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "行内靠档利率删除成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}
		logger.debug("------------------行内靠档利率删除接口结束-----------------");
		return resmap;

	}
	/**
	 * 新增行内靠档利率
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/addRli")
	public Map<String, Object> addRli(
			@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------新增行内靠档利率开始------------------------"+req.toString());
		req.put("userid", user.getUserid());
		req.put("corpno", user.getCorpno());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("insrli", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "新增参考利成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------新增行内靠档利率结束------------------------");		
		return resmap;
	}
	/**
	 * 行内靠档利率修改
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/upRli")
	public Map<String,Object> upRli(@RequestBody Map<String,Object> req,@ModelAttribute("User") BSBUser user){
		logger.debug("--------------------行内靠档利率修改开始------------------------"+req.toString());
		Map<String, Object> resmap = new HashMap<String, Object>();	
		req.put("userid", user.getUserid());
		req.put("corpno", user.getCorpno());
		resmap = client.callClient("updrli", req);
		if (resmap.get("retCode").toString().equals("0000")) {
			resmap.put("ret", "success");
			resmap.put("msg", "行内靠档利率修改成功");
		} else {
			resmap.put("msg", resmap.get("retMsg").toString());
		}			
		logger.debug("--------------------行内靠档利率修改结束------------------------");			
		return resmap;		
	}
}
