package cn.sunline.inac.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.sunline.client.ClientImpl;
import cn.sunline.domain.endauth.BSBUser;
import cn.sunline.exception.SumpException;

@Controller("InacController")
@RequestMapping(value = "/rest/inac")
@ResponseBody
@SessionAttributes("User")
public class InacController {
	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory.getLogger(InacController.class);
	/*
	 * 内部户信息查询
	 */
	@RequestMapping(value ="/qryac")
	public Map<String,Object> qryInacInfo(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Date date = new Date();
		logger.info("req========="+reqmap);
		//设置交易参数
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", reqmap.get("start"));
		map.put("pagect", reqmap.get("length"));
		map.put("userid", user.getUserid());
		logger.info("转换的参数===================="+map);
		//调用
		Map<String,Object> infos = clict.callClient("qryina", map);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		rspmap.put("sEcho", date.getTime());
		rspmap.put("iTotalRecords", infos.get("count"));
		rspmap.put("iTotalDisplayRecords", infos.get("count"));
		rspmap.put("data",infos.get("infos"));
		logger.info("返回参数=========================="+infos);
		return rspmap;
	}
	
	/*
	 * 内部户开户
	 */
	@RequestMapping(value ="/add")
	public Map<String,Object> addInac(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		logger.info("@@@@@@@@@@@@@@@@@@@@"+reqmap.toString());
		Map<String,Object> rspmap = clict.callClient("inopen", reqmap);
		return rspmap;
	}
	/*
	 * 内部户销户
	 */
	@RequestMapping(value ="/qryac" , method = {RequestMethod.DELETE})
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String , Object> delInac(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("acctno", reqmap.get("acctno"));
		map.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String,Object>();
		try {
			rspmap = clict.callClient("inclos", map);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		return rspmap;
	}
	/*
	 * 内部户维护
	 */
	@RequestMapping(value ="/update")
	public Map<String,Object> update(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		logger.info("@@@@@@@@@@@@@@@@@@@@"+reqmap.toString());
		Map<String,Object> rspmap = clict.callClient("upacif", reqmap);
		return rspmap;
	}
	/*
	 * 查询科目名称
	 */
	@RequestMapping(value ="/getItemName")
	public Map<String,Object> getItemName(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		logger.info("@@@@@@@@@@@@@@@@@@@@"+reqmap.toString());
		Map<String,Object> rspmap = clict.callClient("getina", reqmap);
		return rspmap;
	}
	/*
	 * 查询科目列表
	 */
	@RequestMapping(value ="/item")
	public Map<String,Object> getItem(@RequestParam Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		// 设置交易参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startid", reqmap.get("start"));
		map.put("recdct", reqmap.get("length"));
		map.put("userid", user.getUserid());
		logger.info("@@@@@@@@@@@@@@@@@@@@"+reqmap.toString());
		Map<String,Object> infos = clict.callClient("getitm", map);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		Date date = new Date();
		rspmap.put("sEcho", date.getTime());
		rspmap.put("iTotalRecords", "1");
		rspmap.put("iTotalDisplayRecords", infos.get("count"));
		rspmap.put("data",infos.get("itinfo"));
//		String info = "{   \"sEcho\": 3,"+
//	"\"iTotalRecords\" : 2,"+
//	"\"iTotalDisplayRecords\" : 2,"+
//	"\"data\" : [{"+
//	        "\"itemcd\":\"2011\","+
//	        "\"itemna\":\"个人活期存款\","+
//			"\"itemlv\" : \"1\","+
//			"\"dtittg\" : \"1\","+
//			"\"blncdn\":\"D\","+
//			"\"ioflag\":\"I\","+
//			"\"aslbtp\":\"20\","+
//			"\"ittype\":\"2\","+
//			"\"proftp\":\"B\","+
//			"\"itemrg\":\"00\","+
//			"\"itemtp\":\"11\","+
//			"\"busino\":\"IA2231\""+
//			 "},"+			 
//			 "{"+
//			 "\"itemcd\":\"2011\","+
//		        "\"itemna\":\"个人活期存款\","+
//				"\"itemlv\" : \"1\","+
//				"\"dtittg\" : \"1\","+
//				"\"blncdn\":\"D\","+
//				"\"ioflag\":\"I\","+
//				"\"aslbtp\":\"11\","+
//				"\"ittype\":\"2\","+
//				"\"proftp\":\"B\","+
//				"\"itemrg\":\"01\","+
//				"\"itemtp\":\"11\","+
//				"\"busino\":\"IA2231\""+
//				 "}]"+
//			 "}";
		return rspmap;
	}
	
	/*
	 * 科目维护
	 */
	@RequestMapping(value ="/updateItem")
	public Map<String,Object> updateItem(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		logger.info("@@@@@@@@@@@@@@@@@@@@"+reqmap.toString());
		Map<String,Object> rspmap = clict.callClient("uptitm", reqmap);
		return rspmap;
	}
	
	/*
	 * 新增科目
	 */
	@RequestMapping(value ="/addItem")
	public Map<String,Object> addItem(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		logger.info("@@@@@@@@@@@@@@@@@@@@"+reqmap.toString());
		Map<String,Object> rspmap = clict.callClient("insitm", reqmap);
		return rspmap;
	}
	
	/**
	 * 查询业务编码信息
	 */
	@RequestMapping(value ="/busino")
	public Map<String, Object> getBusino(@RequestParam Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		
		Date date = new Date();
		Map<String, Object> requestm = new HashMap<String, Object>();
		requestm.put("userid", user.getUserid());
		requestm.put("starno", reqmap.get("start"));
		requestm.put("pageno", reqmap.get("length"));
		logger.info("请求报文@@@@@@@@@@@@@@@@@@@@"+requestm.toString());
		Map<String,Object> responsem = clict.callClient("qrbusi", requestm);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		rspmap.put("sEcho", date.getTime());
		rspmap.put("iTotalRecords", responsem.get("count"));
		rspmap.put("iTotalDisplayRecords", responsem.get("count"));
		rspmap.put("data",responsem.get("businoInfos"));
		logger.info("反馈报文###################"+responsem.toString());
		
		return rspmap;
	}
	
	/**
	 * 维护业务编码信息
	 */
	@RequestMapping(value="/upbusi")
	public Map<String, Object> updateBusino(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		Map<String, Object> rspmap = clict.callClient("upbusi", reqmap);
		return rspmap;
	}
	
	/**
	 * 新增业务编码
	 */
	@RequestMapping(value="/inbusi")
	public Map<String, Object> addBusino(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		Map<String, Object> rspmap = clict.callClient("inbusi", reqmap);
		return rspmap;
	}
	
	/**
	 * 查询属性类型
	 */
	@RequestMapping(value="/qrbztp")
	public Map<String, Object> getBzprtp(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		Map<String, Object> rspmap = clict.callClient("qrbztp", reqmap);
		return rspmap;
	}
	
	/**
	 * 查询科目
	 */
	@RequestMapping(value="/qritem")
	public Map<String, Object> getItemcd(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		Map<String, Object> rspmap = clict.callClient("qritem", reqmap);
		return rspmap;
	}
	
	/**
	 * 查询业务代码属性
	 */
	@RequestMapping(value="/bzprtp")
	public Map<String, Object> selBzprtp(@RequestParam Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		Date date = new Date();
		Map<String, Object> requestm = new HashMap<String, Object>();
		requestm.put("userid", user.getUserid());
		requestm.put("starno", reqmap.get("start"));
		requestm.put("pageno", reqmap.get("length"));
		Map<String, Object> responsem = clict.callClient("qrbzpr", requestm);
		Map<String, Object> rspmap = new HashMap<String, Object>();
		rspmap.put("sEcho", date.getTime());
		rspmap.put("iTotalRecords", responsem.get("count"));
		rspmap.put("iTotalDisplayRecords", responsem.get("count"));
		rspmap.put("data", responsem.get("bzprtpInfo"));
		logger.debug("反馈报文@@@@@@@@@@@@@@@@@"+rspmap.toString());
		return rspmap;
	}
	
	/**
	 * 新增业务代码属性
	 */
	@RequestMapping(value="/inbztp")
	public Map<String, Object> addBzprtp(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		Map<String, Object> rspmap = clict.callClient("inbztp", reqmap);
		return rspmap;
	}
	
	/**
	 * 内部户交易明细查询
	 * @param acctno 内部户账号
	 * @param startdt 查询的开始日期
	 * @param enddt 查询的结束日期
	 * @param pageno 起始页数
	 * @param pagesize 每页记录数
	 * @author L H
	 * @since 2015年9月21日10:35:52
	 */
	@RequestMapping(value ="/qryTrans")
	public Map<String,Object> qryTrans(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("req========="+reqmap);
		int start = Integer.parseInt((String)reqmap.get("start"));
		int length = Integer.parseInt((String)reqmap.get("length"));
		int pageno = start/length + 1;
		//设置交易参数
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("acctno", reqmap.get("acctno"));
		map.put("bgdate", reqmap.get("startdt"));
		map.put("endate", reqmap.get("enddt"));
		map.put("pageno", pageno);
		map.put("pagesize", reqmap.get("length"));
		map.put("userid", user.getUserid());
		logger.info("转换的参数===================="+map);
		//调用
		Map<String,Object> infos = clict.callClient("inqbil", map);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		rspmap.put("iTotalRecords", infos.get("count"));
		rspmap.put("iTotalDisplayRecords", infos.get("count"));
		rspmap.put("data",infos.get("billInfos"));
		logger.info("返回参数=========================="+infos);
		return rspmap;
	}
	
	/**
	 * <p>查询内部户账户号与名称 </p>
	 * 
	 */
	@RequestMapping(value ="/qrinna")
	public Map<String,Object> qrinna(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("qrinna开始---------------------------"+reqmap);	
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("qrinna", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		List<Map<String,Object>> list =(List<Map<String,Object>>) rspmap.get("inAcctInfo");
		List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
		for (Map<String,Object> bankna : list) {
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("id", bankna.get("acctno"));
			map1.put("text", bankna.get("acctna"));
			list1.add(map1);
		}
		rspmap.put("data", list1);
		logger.info("qrinna结束---------------------------"+reqmap);
		return rspmap;
	}
}
