package cn.sunline.cust.controller;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
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

import cn.sunline.client.ClientImpl;
import cn.sunline.dict.ApSysDict;
import cn.sunline.dict.ApSysDictService;
import cn.sunline.domain.endauth.BSBUser;
import cn.sunline.exception.SumpException;
import cn.sunline.file.ExcelEntity;
import cn.sunline.file.ExcelUtil;
import cn.sunline.utils.DateTools;

@Controller("CustController")
@RequestMapping(value = "/rest/cust")
@ResponseBody
@SessionAttributes("User")
public class CustController {
	@Autowired
	ClientImpl clict;
	@Autowired
	private ApSysDictService apSysDictService;

	private static Logger logger = LoggerFactory.getLogger(CustController.class);
	
	/*
	 * 客户信息查询
	 */
	@RequestMapping(value ="/custinfo")
	public Map<String,Object> getCust(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String , Object> map = new HashMap<String, Object>();
		Date date = new Date();
		map.put("startt", reqmap.get("start"));
		map.put("record", reqmap.get("length"));
		map.put("ecctno", reqmap.get("ecctno"));
		map.put("custna", reqmap.get("custna"));
		map.put("idtftp", reqmap.get("idtftp"));
		map.put("idtfno", reqmap.get("idtfno"));
		map.put("teleno", reqmap.get("teleno"));
		map.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		Map<String,Object> remap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("eaccts", map);
		} catch (SumpException e) {
			remap.put("retCode", e.getErrCode());
			remap.put("retMsg", e.getErrMsg());
		}
		remap.put("sEcho", date.getTime());
		remap.put("iTotalRecords", rspmap.get("recdsm"));
		remap.put("iTotalDisplayRecords", rspmap.get("recdsm"));
		remap.put("data", rspmap.get("ecctou"));
		
		return remap;
	}
	
	/*
	 * 客户交易信息查询
	 */
	@RequestMapping(value = "/cutrif")
	public Map<String,Object> getCustTransInfo(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String , Object> map = new HashMap<String, Object>();
		Date date = new Date();
		int start = Integer.parseInt(reqmap.get("start").toString());
		int length  = Integer.parseInt(reqmap.get("length").toString());
		int pageno = start/length+1;
		map.put("pageno", pageno);
		map.put("recdct", length);
		map.put("ecctno", reqmap.get("ecctno").toString());
		map.put("bgindt", reqmap.get("from").toString());
		map.put("eenddt", reqmap.get("to").toString());
		map.put("eccttp", reqmap.get("eccttp").toString());
		map.put("crcycd", reqmap.get("crcycd").toString());
		map.put("userid", user.getUserid());
		logger.debug("请求map========"+map);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		Map<String,Object> remap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("qrtrdl", map);
		} catch (SumpException e) {
			remap.put("retCode", e.getErrCode());
			remap.put("retMsg", e.getErrMsg());
		}
		remap.put("sEcho", date.getTime());
		remap.put("iTotalRecords", rspmap.get("recdsm"));
		remap.put("iTotalDisplayRecords", rspmap.get("recdsm"));
		remap.put("data", rspmap.get("ecctlt"));
		return remap;
	}
	/*
	 * 客户信息维护
	 */
	@RequestMapping(value = "/update")
	public Map<String,Object> updateCif(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("upecct", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		return rspmap;
	}
	
	/*
	 * 大额审核登记信息查询
	 */
	@RequestMapping(value = "/qrhpay")
	public Map<String,Object> qrhpay(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String , Object> map = new HashMap<String, Object>();
		int start = Integer.parseInt(reqmap.get("start").toString());
		int length  = Integer.parseInt(reqmap.get("length").toString());
		int pageno = start/length+1;
		map.put("pageno", pageno);
		map.put("pagect", length);
		map.put("frondt", reqmap.get("frondt") == null ? null : reqmap
				.get("frondt"));
		map.put("status", reqmap.get("status") == null ? null : reqmap
				.get("status"));
		map.put("custac", reqmap.get("custac") == null ? null : reqmap
				.get("custac"));
		map.put("custna", reqmap.get("custna") == null ? null : reqmap
				.get("custna"));
		map.put("enddat", reqmap.get("enddat") == null ? null : reqmap
				.get("enddat"));
		map.put("teleno", reqmap.get("teleno") == null ? null : reqmap
				.get("teleno"));
		map.put("servno", reqmap.get("servno") == null ? null : reqmap
				.get("servno"));
		map.put("userid", user.getUserid());
		logger.debug("请求map========"+map);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		Map<String,Object> remap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("qrhpay", map);
		} catch (SumpException e) {
			remap.put("retCode", e.getErrCode());
			remap.put("retMsg", e.getErrMsg());
		}
		remap.put(
				"data",
				rspmap.get("bsInfs") == null ? new ArrayList<Object>() : rspmap
						.get("bsInfs"));
		remap.put("iTotalDisplayRecords", rspmap.get("counts") == null ? Integer.parseInt("1")
				: rspmap.get("counts"));
		remap.put("iTotalRecords",
				rspmap.get("counts") == null ? Integer.parseInt("1") : rspmap.get("counts"));
		
		return remap;
	}
	
	/*
	 * 大额审核登记信息修改
	 */
	@RequestMapping(value = "/ckrtsd")
	public Map<String,Object> ckrtsd(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		reqmap.put("chckur", user.getUserid());
		SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy",  Locale.UK);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			reqmap.put("chckdt", sdf.format(sdf1.parse(user.getSystdt())));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		reqmap.put("target", "1");//让前置拦截
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("ckrtsd", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		if (rspmap.get("retCode").toString().equals("0000")) {
			rspmap.put("ret", "success");
			rspmap.put("msg", "大额提现审核登记信息修改操作成功");
//			try {
//				if(reqmap.get("ckstat").toString().equals("03")){
//					Map<String , Object> map = new HashMap<String, Object>();
//					map.put("phone", reqmap.get("teleno"));
//					String content = "尊敬的["+reqmap.get("custna")+"]，["+reqmap.get("frondt")+"]您申请的提现￥["+reqmap.get("tranam")+"]元，财务人员已经处理完毕，请您注意查收";
//					map.put("content", content);
//					logger.debug("请求map========"+map);
//					clict.callClient("sendSms", map);
//				}
//			} catch (SumpException e2) {
//				rspmap.put("msg", "大额提现审核登记信息修改操作成功,但短信发送异常");;
//			}
//		} else {
//			rspmap.put("msg", rspmap.get("retMsg").toString());
		}
		return rspmap;
	}
	
	/*
	 * 大额审核登记信息修改
	 */
	@RequestMapping(value = "/sendms")
	public Map<String,Object> sendms(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			if(reqmap.get("ckstat").toString().equals("03")){
				Map<String , Object> map = new HashMap<String, Object>();
				map.put("phone", reqmap.get("teleno"));
				String content = "尊敬的["+reqmap.get("custna")+"]，["+reqmap.get("frondt")+"]您申请的提现￥["+reqmap.get("tranam")+"]元，财务人员已经处理完毕，请您注意查收";
				map.put("content", content);
				logger.debug("请求map========"+map);
				clict.callClient("sendSms", map);
			}
		} catch (SumpException e2) {
			rspmap.put("msg", "大额提现审核登记信息修改操作成功,但短信发送异常");;
		}
		rspmap.put("ret", "success");
		rspmap.put("retCode", "0000");
		return rspmap;
	}
	
	
		/*
	 * 大额审核订单信息查询
	 */
	@RequestMapping(value = "/qrordr")
	public Map<String,Object> qrordr(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String , Object> map = new HashMap<String, Object>();
		int start = Integer.parseInt(reqmap.get("start").toString());
		int length  = Integer.parseInt(reqmap.get("length").toString());
		int pageno = start/length+1;
		map.put("pageno", pageno);
		map.put("pagect", length);
		map.put("fronsq", reqmap.get("q_fronsq") == null ? null : reqmap
				.get("q_fronsq"));
		map.put("frondt", reqmap.get("q_frondt") == null ? null : reqmap
				.get("q_frondt"));
		
		map.put("userid", user.getUserid());
		logger.debug("请求map========"+map);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		Map<String,Object> remap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("qrordr", map);
		} catch (SumpException e) {
			remap.put("retCode", e.getErrCode());
			remap.put("retMsg", e.getErrMsg());
		}
		remap.put(
				"data",
				rspmap.get("ordrinfo") == null ? new ArrayList<Object>() : rspmap
						.get("ordrinfo"));
		remap.put("iTotalDisplayRecords", rspmap.get("counts") == null ? Integer.parseInt("0")
				: rspmap.get("counts"));
		remap.put("iTotalRecords",
				rspmap.get("counts") == null ? Integer.parseInt("0") : rspmap.get("counts"));
		
		remap.put("totlam", rspmap.get("totlam") == null ? Integer.parseInt("0")
				: rspmap.get("totlam"));
		remap.put("inptam", rspmap.get("inptam") == null ? Integer.parseInt("0")
				: rspmap.get("inptam"));
		return remap;
	}
	
	/*
	 * 大额审核订单信息新增
	 */
	@RequestMapping(value = "/hrutsg")
	public Map<String,Object> hrutsg(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		reqmap.put("chckur", user.getUserid());
		reqmap.put("target", "1");//让前置拦截
		SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy",  Locale.UK);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			reqmap.put("chckdt", sdf.format(sdf1.parse(user.getSystdt())));
//			reqmap.put("ordrtm", sdf.format(sdf1.parse(user.getSystdt())));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("hrutsg", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		
		if (rspmap.get("retCode").toString().equals("0000")) {
			rspmap.put("ret", "success");
			rspmap.put("msg", "大额提现订单插入操作成功");
		} else {
			if (rspmap.get("retMsg").toString().startsWith("-1:[hfax0009]")) {
				rspmap.put("msg", rspmap.get("retMsg").toString().substring(13));
			} else {
				rspmap.put("msg", rspmap.get("retMsg").toString().substring(13));
			}
		}
		return rspmap;
	}
	
	
	/*
	 * 大额审核登记信息查询(对账之后修改信息)
	 */
	@RequestMapping(value = "/qrpyif")
	public Map<String,Object> qrpyif(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String , Object> map = new HashMap<String, Object>();
		int start = Integer.parseInt(reqmap.get("start").toString());
		int length  = Integer.parseInt(reqmap.get("length").toString());
		int pageno = start/length+1;
		map.put("pageno", pageno);
		map.put("pagect", length);
		map.put("frondt", reqmap.get("q_frondt_sign") == null ? null : reqmap
				.get("q_frondt_sign"));
		map.put("status", reqmap.get("q_status") == null ? null : reqmap
				.get("q_status"));
		map.put("userid", user.getUserid());
		logger.debug("请求map========"+map);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		Map<String,Object> remap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("qrpyif", map);
		} catch (SumpException e) {
			remap.put("retCode", e.getErrCode());
			remap.put("retMsg", e.getErrMsg());
		}
		
		remap.put(
				"data",
				rspmap.get("bsInfs") == null ? new ArrayList<Object>() : rspmap
						.get("bsInfs"));
		remap.put("iTotalDisplayRecords", rspmap.get("counts") == null ? Integer.parseInt("1")
				: rspmap.get("counts"));
		remap.put("iTotalRecords",
				rspmap.get("counts") == null ? Integer.parseInt("1") : rspmap.get("counts"));
		
		return remap;
	}
	
	/*
	 * 大额审核订单信息新增
	 */
	@RequestMapping(value = "/edhpay")
	public Map<String,Object> edhpay(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		reqmap.put("chckur", user.getUserid());
		reqmap.put("target", "1");//让前置拦截
		SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy",  Locale.UK);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			reqmap.put("chckdt", sdf.format(sdf1.parse(user.getSystdt())));
//			reqmap.put("ordrtm", sdf.format(sdf1.parse(user.getSystdt())));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if (reqmap.get("odorod") == null) {
			reqmap.put("odorod", "");
		}
		if (reqmap.get("scapno") != null && reqmap.get("scapno") != "") {
			String scapno = reqmap.get("scapno").toString();
			if (scapno.contains("[") || scapno.contains("]")) {
				reqmap.put("scapno",scapno.substring(scapno.indexOf("[")+1, scapno.indexOf("]")));
			}
		}
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("ordrmd", reqmap);
		} catch (SumpException e) {
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		
		if (rspmap.get("retCode").toString().equals("0000")) {
			rspmap.put("ret", "success");
			rspmap.put("msg", "大额提现订单插入操作成功");
		} else {
			if (rspmap.get("retMsg").toString().startsWith("-1:[hfax0009]")) {
				rspmap.put("msg", rspmap.get("retMsg").toString().substring(13));
			} else {
				rspmap.put("msg", rspmap.get("retMsg").toString().substring(13));
			}
		}
		return rspmap;
	}
	
	
	/*
	 * 大额审核订单信息查询(对账之后的订单信息)
	 */
	@RequestMapping(value = "/qrckod")
	public Map<String,Object> qrckod(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String , Object> map = new HashMap<String, Object>();
		int start = Integer.parseInt(reqmap.get("start").toString());
		int length  = Integer.parseInt(reqmap.get("length").toString());
		int pageno = start/length+1;
		map.put("pageno", pageno);
		map.put("pagect", length);
		map.put("fronsq", reqmap.get("q_fronsq") == null ? null : reqmap
				.get("q_fronsq"));
		map.put("frondt", reqmap.get("q_frondt") == null ? null : reqmap
				.get("q_frondt"));
		
		map.put("userid", user.getUserid());
		logger.debug("请求map========"+map);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		Map<String,Object> remap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("qrckod", map);
		} catch (SumpException e) {
			remap.put("retCode", e.getErrCode());
			remap.put("retMsg", e.getErrMsg());
		}
		remap.put(
				"data",
				rspmap.get("ordrinfo") == null ? new ArrayList<Object>() : rspmap
						.get("ordrinfo"));
		remap.put("iTotalDisplayRecords", rspmap.get("counts") == null ? Integer.parseInt("0")
				: rspmap.get("counts"));
		remap.put("iTotalRecords",
				rspmap.get("counts") == null ? Integer.parseInt("0") : rspmap.get("counts"));
		
		return remap;
	}
	
	
	/*
	 * 银行卡查询
	 * @author zjl
	 */
	@RequestMapping(value = "/bankcardif")
	public Map<String,Object> getCustBankCard(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		Map<String , Object> map = new HashMap<String, Object>();
		Date date = new Date();
		int start = Integer.parseInt(reqmap.get("start").toString());
		int length  = Integer.parseInt(reqmap.get("length").toString());
		int pageno = start/length+1;
		map.put("pageno", pageno);
		map.put("record", length);
		map.put("custac", reqmap.get("custac").toString());
		map.put("status", "1");
		map.put("userid", user.getUserid());
		logger.debug("请求map========"+map);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		Map<String,Object> remap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("caslbc", map);//请求核心接口
		} catch (SumpException e) {
			remap.put("retCode", e.getErrCode());
			remap.put("retMsg", e.getErrMsg());
		}
		remap.put("sEcho", date.getTime());
		remap.put("iTotalRecords", rspmap.get("recdsm"));
		remap.put("iTotalDisplayRecords", rspmap.get("recdsm"));
		remap.put("data", rspmap.get("selBindCardInfo"));
		return remap;
	}
	
	/**
	 * 生成大额提现登记薄文件
	 * 1.查询第一数据，获取总记录数和首页记录
	 * 2.根据总记录从第二页开始循环查询记录
	 * 3.写文件
	 * 4.请求重定向至文件，下载
	 * @param reqmap
	 * @param user
	 * @param response
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getSignFile")
	public Map<String, Object> getSignFile(HttpServletRequest request, @RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user,HttpServletResponse response) {
		String path = request.getSession().getServletContext().getRealPath("download")+File.separator;
		String fileName = "signinfo_"+DateTools.getNow("yyyyMMddHHmmss")+".xlsx";
		Map<String,Object> map = new HashMap<String, Object>();
		
		List<List<ExcelEntity>> cells = new ArrayList<List<ExcelEntity>>();
		logger.debug("------------------查询大额提现登记簿信息开始-----------------");
		map.put("userid", user.getUserid()); //设置操作柜员	
		map.put("frondt", reqmap.get("frondt") == null ? null : reqmap
				.get("frondt"));
		map.put("status", reqmap.get("status") == null ? null : reqmap
				.get("status"));
		map.put("custac", reqmap.get("custac") == null ? null : reqmap
				.get("custac"));
		map.put("custna", reqmap.get("custna") == null ? null : reqmap
				.get("custna"));
		map.put("enddat", reqmap.get("enddat") == null ? null : reqmap
				.get("enddat"));
		map.put("teleno", reqmap.get("teleno") == null ? null : reqmap
				.get("teleno"));
		map.put("servno", reqmap.get("servno") == null ? null : reqmap
				.get("servno"));
		map.put("pageno", "1");
		map.put("pagect", "10");
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = clict.callClient("qrhpay", map);
		resmap.put("data",resmap.get("bsInfs") == null ? new ArrayList<Object>() : resmap.get("bsInfs"));
		resmap.put("iTotalDisplayRecords",resmap.get("counts") == null ? "0" : resmap.get("counts"));
		resmap.put("iTotalRecords",resmap.get("counts") == null ? "0" : resmap.get("counts"));
		
		List<ApSysDict> aplist=apSysDictService.getDictsByDictType("E_CKSTAT");
		
		List<Map<String,Object>> list = (List<Map<String, Object>>) resmap.get("bsInfs");
		if(list.isEmpty()){
			logger.debug("是否为空===================="+"YES");
		} else {
			logger.debug("是否为空===================="+"NO");
			for(Map<String,Object> bill : list){
				List<ExcelEntity> row = new ArrayList<ExcelEntity>();
				ExcelEntity e1 = new ExcelEntity();
				e1.setData(bill.get("custac").toString());
				e1.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e1);
				
				ExcelEntity e2 = new ExcelEntity();
				e2.setData(bill.get("custna").toString());
				e2.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e2);
				
				ExcelEntity e3 = new ExcelEntity();
				e3.setData(bill.get("brchna").toString());
				e3.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e3);
				
				ExcelEntity e4 = new ExcelEntity();
				String tranam1 = String.format("%.2f", bill.get("tranam"));
				e4.setData(tranam1.toString());
				e4.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e4);
				
				ExcelEntity e5 = new ExcelEntity();
				String tranam2 = String.format("%.2f", bill.get("canuse"));
				e5.setData(tranam2.toString());
				e5.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e5);
				
				ExcelEntity e6 = new ExcelEntity();
				e6.setData(bill.get("fronsq").toString());
				e6.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e6);

//				ExcelEntity e6 = new ExcelEntity();
//				for (int i = 0; i < aplist.size(); i++) {
//					if (aplist.get(i).getDictId().equals(bill.get("intrst").toString())) {
//						e6.setData(aplist.get(i).getDictName());
//					}
//				}
//				e6.setDataType((short) Cell.CELL_TYPE_STRING);
//				row.add(e6);
				
				ExcelEntity e7 = new ExcelEntity();
				e7.setData(bill.get("frondt").toString());
				e7.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e7);
				
				ExcelEntity e8 = new ExcelEntity();
				e8.setData(bill.get("cardno").toString());
				e8.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e8);
				
				ExcelEntity e9 = new ExcelEntity();
				e9.setData(bill.get("teleno").toString());
				e9.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e9);
				
				ExcelEntity e10 = new ExcelEntity();
				for (int i = 0; i < aplist.size(); i++) {
					if (aplist.get(i).getDictId().equals(bill.get("ckstat").toString())) {
						e10.setData(aplist.get(i).getDictName());
					}
				}
				e10.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e10);
				
				ExcelEntity e11 = new ExcelEntity();
				e11.setData(bill.get("frontm").toString());
				e11.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e11);
				
				ExcelEntity e12 = new ExcelEntity();
				e12.setData(bill.get("servtp").toString());
				e12.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e12);
				cells.add(row);
			}
			int amount = (int) Math.ceil(Integer.parseInt(resmap.get("counts").toString())/10.0);
			if(amount>1){
				for(int i=2;i<=amount;i++){
					map.put("pageno", i);
					resmap = clict.callClient("qrhpay", map);
					list = (List<Map<String, Object>>) resmap.get("bsInfs");
					if(list == null){
						logger.debug("是否为空===================="+"YES");
					} else {
						logger.debug("是否为空===================="+"NO");
						for(Map<String,Object> bill : list){
							List<ExcelEntity> row = new ArrayList<ExcelEntity>();
							ExcelEntity e1 = new ExcelEntity();
							e1.setData(bill.get("custac").toString());
							e1.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e1);
							
							ExcelEntity e2 = new ExcelEntity();
							e2.setData(bill.get("custna").toString());
							e2.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e2);
							
							ExcelEntity e3 = new ExcelEntity();
							e3.setData(bill.get("brchna").toString());
							e3.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e3);
							
							ExcelEntity e4 = new ExcelEntity();
							String tranam1 = String.format("%.2f", bill.get("tranam"));
							e4.setData(tranam1.toString());
							e4.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e4);
							
							ExcelEntity e5 = new ExcelEntity();
							String tranam2 = String.format("%.2f", bill.get("canuse"));
							e5.setData(tranam2.toString());
							e5.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e5);
							
							ExcelEntity e6 = new ExcelEntity();
							e6.setData(bill.get("fronsq").toString());
							e6.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e6);

//							ExcelEntity e6 = new ExcelEntity();
//							for (int i = 0; i < aplist.size(); i++) {
//								if (aplist.get(i).getDictId().equals(bill.get("intrst").toString())) {
//									e6.setData(aplist.get(i).getDictName());
//								}
//							}
//							e6.setDataType((short) Cell.CELL_TYPE_STRING);
//							row.add(e6);
							
							ExcelEntity e7 = new ExcelEntity();
							e7.setData(bill.get("frondt").toString());
							e7.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e7);
							
							ExcelEntity e8 = new ExcelEntity();
							e8.setData(bill.get("cardno").toString());
							e8.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e8);
							
							ExcelEntity e9 = new ExcelEntity();
							e9.setData(bill.get("teleno").toString());
							e9.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e9);
							
							ExcelEntity e10 = new ExcelEntity();
							for (int j = 0; j < aplist.size(); j++) {
								if (aplist.get(j).getDictId().equals(bill.get("ckstat").toString())) {
									e10.setData(aplist.get(j).getDictName());
								}
							}
							e10.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e10);
							
							ExcelEntity e11 = new ExcelEntity();
							e11.setData(bill.get("frontm").toString());
							e11.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e11);
							
							ExcelEntity e12 = new ExcelEntity();
							e12.setData(bill.get("servtp").toString());
							e12.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e12);
							cells.add(row);
						}
					}
				}
			}
		}
		//文件头行
		List<String> head = new ArrayList<String>();
		head.add("客户账号");
		head.add("客户名称");
		head.add("开户行名称");
		head.add("交易金额");
		head.add("可用余额");
		head.add("流    水");
		head.add("日    期");
		head.add("银行卡号");
		head.add("电话号码");
		head.add("状    态");
		head.add("时    间");
		head.add("渠道号");
		
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		logger.debug("path >>>>>>>>>>>>>>>>>>>"+path);
		ExcelUtil excelUtil = new ExcelUtil(fileName,path);
		excelUtil.writeExcel("大额提现登记簿信息", head, cells);
		map.put("fileName", fileName);
		map.put("filePath", path);
		map.put("retCode", "0000");
		return map;
	}
}
