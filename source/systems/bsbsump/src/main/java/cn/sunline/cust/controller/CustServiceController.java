package cn.sunline.cust.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
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

import cn.sunline.client.ClientImpl;
import cn.sunline.domain.endauth.BSBUser;
import cn.sunline.file.ExcelEntity;
import cn.sunline.file.ExcelUtil;
import cn.sunline.utils.DateTools;

@Controller("CustServiceController")
@RequestMapping(value = "/rest/custService")
@ResponseBody
@SessionAttributes("User")
public class CustServiceController {
	@Autowired
	ClientImpl client;
	private static Logger logger = LoggerFactory.getLogger(CustServiceController.class);
	
	/**
	 * 分页查询客户信息
	 */
	@RequestMapping(value ="/qrcuif")
	public Map<String,Object> getCust(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.info("客户信息查询开始---------------------------");	
		reqmap.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		reqmap.put("pageno", start / length + 1);//当前页数
		reqmap.put("pagesz", length); //每页记录数
		Map<String,Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrcuif", reqmap);
		resmap.put("data",resmap.get("fcinfo") == null ? new ArrayList<Object>() : resmap.get("fcinfo"));
		resmap.put("iTotalDisplayRecords",resmap.get("counts") == null ? "0" : resmap.get("counts"));
		resmap.put("iTotalRecords",resmap.get("counts") == null ? "0" : resmap.get("counts"));
		logger.info("客户信息查询结束---------------------------");
		return resmap;
	}
	
	/**
	 * 根据电子账号查询投资信息
	 */
	@RequestMapping(value = "/fclnqr")
	public Map<String, Object> fclnqr(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------投资信息开始-----------------"+reqmap);
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_custac") != null && reqmap.get("q_custac") != "") {
			map.put("custac", reqmap.get("q_custac"));
		}
		
		if (reqmap.get("q_phoneNo") != null && reqmap.get("q_phoneNo") != "") {
			map.put("mobile", reqmap.get("q_phoneNo"));
		}
		
		map.put("userid", user.getUserid()); 

		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("record", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("fclnqr", map);
		resmap.put(
				"data",
				resmap.get("lninfo") == null ? new ArrayList<Object>() : resmap
						.get("lninfo"));
		resmap.put("iTotalDisplayRecords", resmap.get("totshu") == null ? "0"
				: resmap.get("totshu"));
		resmap.put("iTotalRecords",
				resmap.get("totshu") == null ? "0" : resmap.get("totshu"));
		logger.debug("-----------------投资信息结束-----------------"+resmap);
		return resmap;
	}
	
	
	/**
	 * 根据电子账号查询转让信息
	 */
	@RequestMapping(value = "/qrdbsb")
	public Map<String, Object> qrdbsb(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------转让信息开始-----------------"+reqmap);
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_custac") != null && reqmap.get("q_custac") != "") {
			map.put("custac", reqmap.get("q_custac"));
		}
		
		if (reqmap.get("q_phoneNo") != null && reqmap.get("q_phoneNo") != "") {
			map.put("mobile", reqmap.get("q_phoneNo"));
		}
		
		map.put("trantp", "3"); //已转出
		map.put("userid", user.getUserid()); 

		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("record", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrdbsb", map);
		resmap.put(
				"data",
				resmap.get("subjif") == null ? new ArrayList<Object>() : resmap
						.get("subjif"));
		resmap.put("iTotalDisplayRecords", resmap.get("counts") == null ? "0"
				: resmap.get("counts"));
		resmap.put("iTotalRecords",
				resmap.get("counts") == null ? "0" : resmap.get("counts"));
		logger.debug("-----------------转让信息结束-----------------"+resmap);
		return resmap;
	}
	
	
	
	/**
	 * 根据电子账号查询转让信息
	 */
	@RequestMapping(value = "/qrdbsbin")
	public Map<String, Object> qrdbsbin(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------转让信息开始-----------------"+reqmap);
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_custac") != null && reqmap.get("q_custac") != "") {
			map.put("custac", reqmap.get("q_custac"));
		}
		
		if (reqmap.get("q_phoneNo") != null && reqmap.get("q_phoneNo") != "") {
			map.put("mobile", reqmap.get("q_phoneNo"));
		}
		
		map.put("trantp", "4"); //已转出
		map.put("userid", user.getUserid()); 

		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("record", length);
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrdbsb", map);
		resmap.put(
				"data",
				resmap.get("subjif") == null ? new ArrayList<Object>() : resmap
						.get("subjif"));
		resmap.put("iTotalDisplayRecords", resmap.get("counts") == null ? "0"
				: resmap.get("counts"));
		resmap.put("iTotalRecords",
				resmap.get("counts") == null ? "0" : resmap.get("counts"));
		logger.debug("-----------------转让信息结束-----------------"+resmap);
		return resmap;
	}
	
	
	/**
	 * 查询转让下的详细记录
	 * @author jianlei
	 */
	@RequestMapping(value = "/tranhistory")
	public Map<String, Object> tranferHistory(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		
		logger.debug("------------------转让的记录查询开始-----------------"+reqmap);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		map.put("custac", reqmap.get("custac")); //电子账号
		map.put("debtcd", reqmap.get("debtcd")); //转让标的编号
		
		map.put("userid", user.getUserid()); 

		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		
		map.put("pageno", start / length + 1);
		map.put("record", length);
		
		Map<String, Object> resmap = new HashMap<String, Object>();
		
		resmap = client.callClient("fczrdt", map);
		
		resmap.put(
				"data",
				resmap.get("tranif") == null ? new ArrayList<Object>() : resmap
						.get("tranif"));
		
		resmap.put("iTotalDisplayRecords", resmap.get("counts") == null ? "0"
				: resmap.get("counts"));
		
		resmap.put("iTotalRecords",
				resmap.get("counts") == null ? "0" : resmap.get("counts"));
		
		logger.debug("----------------转让的记录查询技术-----------------"+resmap);
		
		return resmap;
	}
	
	
	/**
	 * 生成收益明细文件
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
	@RequestMapping(value ="/getCustInfoFile")
	public Map<String,Object> getIncomeFile(HttpServletRequest request, @RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user,HttpServletResponse response) {
		String path = request.getSession().getServletContext().getRealPath("download")+File.separator;
		String fileName = "custInfo_"+DateTools.getNow("yyyyMMddHHmmss")+".xlsx";
		Map<String,Object> map = new HashMap<String, Object>();
		
		List<List<ExcelEntity>> cells = new ArrayList<List<ExcelEntity>>();
		reqmap.put("userid", user.getUserid());
		reqmap.put("pageno", "1");
		reqmap.put("pagesz", "10");
		logger.debug("路径===================="+path+fileName);
		map = client.callClient("qrcuif", reqmap);
		List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("fcinfo");
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
				e2.setData(bill.get("loanrd").toString());
				e2.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e2);
				
				ExcelEntity e3 = new ExcelEntity();
				e3.setData(bill.get("invest").toString());
				e3.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e3);
				
				ExcelEntity e4 = new ExcelEntity();
				e4.setData(bill.get("amount").toString());
				e4.setDataType((short) 3);
				row.add(e4);
				
				ExcelEntity e5 = new ExcelEntity();
				e5.setData(bill.get("usable").toString());
				e5.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e5);

				ExcelEntity e6 = new ExcelEntity();
				e6.setData(bill.get("freeze").toString());
				e6.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e6);
				
				ExcelEntity e7 = new ExcelEntity();
				e7.setData(bill.get("earsum").toString());
				e7.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e7);
				
				ExcelEntity e8 = new ExcelEntity();
				e8.setData(bill.get("rateen").toString());
				e8.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e8);
				
				ExcelEntity e9 = new ExcelEntity();
				e9.setData(bill.get("othear").toString());
				e9.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e9);
				
				ExcelEntity e10 = new ExcelEntity();
				e10.setData(bill.get("hassum").toString());
				e10.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e10);

				ExcelEntity e11 = new ExcelEntity();
				e11.setData(bill.get("haspal").toString());
				e11.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e11);

				ExcelEntity e12 = new ExcelEntity();
				e12.setData(bill.get("hasint").toString());
				e12.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e12);

				ExcelEntity e13 = new ExcelEntity();
				e13.setData(bill.get("forsum").toString());
				e13.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e13);

				ExcelEntity e14 = new ExcelEntity();
				e14.setData(bill.get("forpal").toString());
				e14.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e14);

				ExcelEntity e15 = new ExcelEntity();
				e15.setData(bill.get("forpay").toString());
				e15.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e15);

				ExcelEntity e16 = new ExcelEntity();
				e16.setData(bill.get("repsum").toString());
				e16.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e16);

				ExcelEntity e17 = new ExcelEntity();
				e17.setData(bill.get("reppal").toString());
				e17.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e17);

				ExcelEntity e18 = new ExcelEntity();
				e18.setData(bill.get("reppay").toString());
				e18.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e18);

				ExcelEntity e19 = new ExcelEntity();
				e19.setData(bill.get("foesum").toString());
				e19.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e19);

				ExcelEntity e20 = new ExcelEntity();
				e20.setData(bill.get("foepal").toString());
				e20.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e20);

				ExcelEntity e21 = new ExcelEntity();
				e21.setData(bill.get("foepay").toString());
				e21.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e21);
				
				cells.add(row);
			}
			int amount = (int) Math.ceil(Integer.parseInt(map.get("counts").toString())/10.0);
			if(amount>1){
				for(int i=2;i<=amount;i++){
					reqmap.put("pageno", i);
					map = client.callClient("mypage", reqmap);
					list = (List<Map<String, Object>>) map.get("syinfo");
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
							e2.setData(bill.get("loanrd").toString());
							e2.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e2);
							
							ExcelEntity e3 = new ExcelEntity();
							e3.setData(bill.get("invest").toString());
							e3.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e3);
							
							ExcelEntity e4 = new ExcelEntity();
							e4.setData(bill.get("amount").toString());
							e4.setDataType((short) 3);
							row.add(e4);
							
							ExcelEntity e5 = new ExcelEntity();
							e5.setData(bill.get("usable").toString());
							e5.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e5);

							ExcelEntity e6 = new ExcelEntity();
							e6.setData(bill.get("freeze").toString());
							e6.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e6);
							
							ExcelEntity e7 = new ExcelEntity();
							e7.setData(bill.get("earsum").toString());
							e7.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e7);
							
							ExcelEntity e8 = new ExcelEntity();
							e8.setData(bill.get("rateen").toString());
							e8.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e8);
							
							ExcelEntity e9 = new ExcelEntity();
							e9.setData(bill.get("othear").toString());
							e9.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e9);
							
							ExcelEntity e10 = new ExcelEntity();
							e10.setData(bill.get("hassum").toString());
							e10.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e10);

							ExcelEntity e11 = new ExcelEntity();
							e11.setData(bill.get("haspal").toString());
							e11.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e11);

							ExcelEntity e12 = new ExcelEntity();
							e12.setData(bill.get("hasint").toString());
							e12.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e12);

							ExcelEntity e13 = new ExcelEntity();
							e13.setData(bill.get("forsum").toString());
							e13.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e13);

							ExcelEntity e14 = new ExcelEntity();
							e14.setData(bill.get("forpal").toString());
							e14.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e14);

							ExcelEntity e15 = new ExcelEntity();
							e15.setData(bill.get("forpay").toString());
							e15.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e15);

							ExcelEntity e16 = new ExcelEntity();
							e16.setData(bill.get("repsum").toString());
							e16.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e16);

							ExcelEntity e17 = new ExcelEntity();
							e17.setData(bill.get("reppal").toString());
							e17.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e17);

							ExcelEntity e18 = new ExcelEntity();
							e18.setData(bill.get("reppay").toString());
							e18.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e18);

							ExcelEntity e19 = new ExcelEntity();
							e19.setData(bill.get("foesum").toString());
							e19.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e19);

							ExcelEntity e20 = new ExcelEntity();
							e20.setData(bill.get("foepal").toString());
							e20.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e20);

							ExcelEntity e21 = new ExcelEntity();
							e21.setData(bill.get("foepay").toString());
							e21.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e21);
							cells.add(row);
						}
					}
				}
			}
		}
		//文件头行
		List<String> head = new ArrayList<String>();
		head.add("电子账户");
		head.add("借款记录");
		head.add("投资记录");
		head.add("账户总额");
		head.add("可用余额");
		head.add("冻结金额");
		head.add("总收益");
		head.add("累计净收益");
		head.add("其他收益");
		head.add("已收总额");
		head.add("已收本金");
		head.add("已收利息");
		head.add("待收总额");
		head.add("待收本金");
		head.add("待收利息");
		head.add("已还总额");
		head.add("已还本金");
		head.add("已还利息");
		head.add("待还总额");
		head.add("待还本金");
		head.add("待还利息");
		
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		logger.debug("path >>>>>>>>>>>>>>>>>>>"+path);
		ExcelUtil excelUtil = new ExcelUtil(fileName,path);
		excelUtil.writeExcel("客户信息", head, cells);
		map.put("fileName", fileName);
		map.put("filePath", path);
		map.put("retCode", "0000");
		return map;
	}
	
	
	/***
	 * @auth zjl
	 * @date 2016年2月26日17:29:22
	 * @param reqmap 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/custTag")
	public Map<String, Object> custTag(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------查询用户标签开始-----------------"+reqmap);
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_custac") != null && reqmap.get("q_custac") != "") {
			map.put("custac", reqmap.get("q_custac"));
		}
		
		/*if (reqmap.get("q_phoneNo") != null && reqmap.get("q_phoneNo") != "") {
			map.put("mobile", reqmap.get("q_phoneNo"));
		}*/
		
		map.put("userid", user.getUserid()); 

		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);
		map.put("pagesz", length);
		
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("qrytag", map);//接收到返回值
		resmap.put(
				"data",
				resmap.get("tagsinfo") == null ? new ArrayList<Object>() : resmap
						.get("tagsinfo"));
		
		resmap.put("iTotalDisplayRecords", resmap.get("counts") == null ? "0"
				: resmap.get("counts"));
		
		resmap.put("iTotalRecords",
				resmap.get("counts") == null ? "0" : resmap.get("counts"));
		
		logger.debug("-----------------查询用户标签结束-----------------"+resmap);
		return resmap;
	}
}
