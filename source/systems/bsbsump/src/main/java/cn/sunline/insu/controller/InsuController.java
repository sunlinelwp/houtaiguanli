package cn.sunline.insu.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.sunline.client.ClientImpl;
import cn.sunline.dict.ApSysDict;
import cn.sunline.dict.ApSysDictService;
import cn.sunline.domain.endauth.BSBUser;
import cn.sunline.exception.SumpException;
import cn.sunline.file.ExcelEntity;
import cn.sunline.file.ExcelUtil;
import cn.sunline.tmp.insu.check.InsuSetlle;
import cn.sunline.tmp.insu.check.InsuSetlleService;
import cn.sunline.tmp.insu.check.TmpBackInsuBill;
import cn.sunline.tmp.insu.check.TmpBackInsuBillService;
import cn.sunline.utils.DateTools;
import cn.sunline.utils.PageToDataTable;

@Controller("InsuController")
@RequestMapping(value = "/rest/insu")
@ResponseBody
@SessionAttributes("User")
public class InsuController {
	@Autowired
	private ApSysDictService apSysDictService;
	
	@Autowired
	private TmpBackInsuBillService backService;
	
	@Autowired
	private InsuSetlleService insuSetlleService;
	
	@Autowired
	private TmpBackInsuBillService tmpBackInsuBillService;
	
	@Autowired
	ClientImpl clict;
	
	private static Logger logger = LoggerFactory.getLogger(InsuController.class);
	
	@RequestMapping(value ="/loanFile")
	public String loanFile(HttpServletRequest request,     
            HttpServletResponse response , @ModelAttribute("User") BSBUser user){
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;     
        // 获得文件：     
        MultipartFile file = multipartRequest.getFile("myfile");   
		logger.debug("myfiles is empty ? "+file.isEmpty());
		String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = file.getOriginalFilename();  
//      String fileName = new Date().getTime()+".jpg";  
        logger.debug(path);
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
        	 return "文件上传异常，请重试！";
        }
        //解析文件
        ExcelUtil excelUtil = new ExcelUtil(fileName,path+File.separator);
        List<List<ExcelEntity>> cells = new ArrayList<List<ExcelEntity>>();
        excelUtil.readExcel(1, cells);
        String date = DateTools.getNow("yyyyMMdd");
        BigDecimal ouTotlam = BigDecimal.ZERO;//赎回总金额
        //循环插入
        List<TmpBackInsuBill> bills = new ArrayList<TmpBackInsuBill>();
        
    	for(List<ExcelEntity> list : cells){
    		TmpBackInsuBill bill = new TmpBackInsuBill();
    		    		
        	bill.setChkStatus(formartInsuStatus(list.get(5).getData()));
        	bill.setCustna(list.get(3).getData());
        	bill.setDealdt(date);
        	bill.setPasstm(list.get(6).getData());
        	bill.setPolino(list.get(2).getData());
        	bill.setProcna(list.get(0).getData());
        	bill.setTranam(new BigDecimal(list.get(4).getData()));
        	bill.setTransq(list.get(1).getData());
        	bill.setCheckStatus("0");//录入时默认状态为待处理
        	bills.add(bill);
        	ouTotlam = ouTotlam.add(new BigDecimal(list.get(4).getData()));
        }
    	
    	try {
    		backService.saveTmpBackInsuBill(bills);
		} catch (Exception e) {
			e.printStackTrace();
			return "文件上传完成，导入数据库出错，存在重复保单！";
		}
        return "文件上传成功！";  
	}
	
	@RequestMapping(value ="/loanErrorFile")
	public String loanErrorFile(HttpServletRequest request,     
            HttpServletResponse response , @ModelAttribute("User") BSBUser user){
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;     
        // 获得文件：     
        MultipartFile file = multipartRequest.getFile("myfile");   
		logger.debug("myfiles is empty ? "+file.isEmpty());
		String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = file.getOriginalFilename();  
//      String fileName = new Date().getTime()+".jpg";  
        logger.debug(path);
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
        	 return "文件上传异常，请重试！";
        }
        //解析文件
        ExcelUtil excelUtil = new ExcelUtil(fileName,path+File.separator);
        List<List<ExcelEntity>> cells = new ArrayList<List<ExcelEntity>>();
        excelUtil.readExcel(3, cells);
        String date = DateTools.getNow("yyyyMMdd");
        //BigDecimal ouTotlam = BigDecimal.ZERO;//赎回总金额
        //循环插入
        List<TmpBackInsuBill> bills = new ArrayList<TmpBackInsuBill>();
        
    	for(List<ExcelEntity> list : cells){
    		TmpBackInsuBill bill = new TmpBackInsuBill();
    		    		
        	bill.setChkStatus("8");
        	bill.setCustna(list.get(6).getData());
        	bill.setDealdt(date);
        	bill.setPasstm(DateTools.Date2String(DateUtil.getJavaDate(Double.valueOf(list.get(14).getData().toString())), "yyyy-MM-dd HH:mm:ss"));
        	bill.setPolino(list.get(2).getData());
        	bill.setProcna(list.get(4).getData());
        	String tranamStr = list.get(13).getData();
        	String[] subStr = tranamStr.split(",");
        	String tranam = "";
        	logger.debug("交易================"+DateTools.Date2String(DateUtil.getJavaDate(Double.valueOf(list.get(14).getData().toString())), "yyyy-MM-dd HH:mm:ss"));
        	for(String str : subStr){
        		tranam = tranam+str;
        	}
        	logger.debug("交易金额================"+tranam);
        	bill.setTranam(new BigDecimal(tranam));
        	bill.setTransq(list.get(8).getData());
        	bill.setCheckStatus("0");//录入时默认状态为待处理
        	bills.add(bill);
        	//ouTotlam = ouTotlam.add(new BigDecimal(list.get(4).getData()));
        }
    	
    	try {
    		backService.saveTmpBackInsuBill(bills);
		} catch (Exception e) {
			return "文件上传完成，导入数据库出错，存在重复保单！";
		}
        return "文件上传成功！";  
	}
	
	@RequestMapping(value ="/getData")
	public Map<String,Object> getBackData(@RequestParam Map<String,Object> reqmap){
		logger.debug("===========查询退保信息开始===========");
		int length=Integer.parseInt((String)reqmap.get("length"),10);		
		int start=Integer.parseInt((String)reqmap.get("start"),10);		
		Pageable pageable=new PageRequest(start/length,length);
		TmpBackInsuBill payCheck = new TmpBackInsuBill();
		payCheck.setDealdt(reqmap.get("dealdt").toString());
		payCheck.setCheckStatus((String)reqmap.get("checkStatus"));
		Page<TmpBackInsuBill> page=backService.queryEntitiesByTemplateWithPage(payCheck, pageable);  
	    Map<String, Object> map=PageToDataTable.convert(page);
	    logger.debug(map.toString());
		return map;
	}
	
	@RequestMapping(value ="/deal")
	public Map<String,Object> dealBack(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> rows = (ArrayList<HashMap<String,Object>>)reqmap.get("data");
		
		List<String> errlog = new ArrayList<String>();
		for (HashMap<String,Object> bill : rows){
			TmpBackInsuBill backBill = backService.getBillInfoByPolino(bill.get("polino").toString());
			logger.debug("========"+backBill.getChkStatus());
			Map<String, Object> remap = new HashMap<String, Object>();
			if(backBill.getChkStatus().equals("8")){ 
				remap.put("polino", backBill.getPolino());
				remap.put("tranam", backBill.getTranam());
				remap.put("userid", user.getUserid());
				logger.debug("交易柜员=========="+user.getUserid());
				try {
					Map<String, Object> rspmap = clict.callClient("dfbkiu", remap);
	        		if(rspmap.get("retCode").toString().equals("0000")){
	        			//通知前置划款成功
	        			Map<String, Object> reqmap1 = new HashMap<String, Object>();
	        			reqmap1.put("OrderId", rspmap.get("ordrid"));//订单号
	        			reqmap1.put("RefundId", rspmap.get("refuid"));//退保订单号
	        			reqmap1.put("RefundProposalNo", rspmap.get("refuno"));//保全号
	        			reqmap1.put("PayTime", rspmap.get("payxtm"));//支付时间
	        			reqmap1.put("PayMoney", backBill.getTranam());//划账金额
	        			reqmap1.put("RefundPayOrderId", rspmap.get("repysq"));//退保支付交易号
	        			reqmap1.put("IsSuccess", "1");//划账结果
	        			reqmap1.put("FailReason", "");//失败原因
	        			reqmap1.put("PayAccountName", backBill.getCustna());//客户名称
	        			reqmap1.put("PayAccountId", rspmap.get("custac"));
	        			reqmap1.put("target", '1');
	        			Map<String, Object> rspmap1 = clict.callClient("dedunt", reqmap1);
	        			//处理成功，修改记录状态
	        			if(rspmap1.get("retCode").toString().equals("0000")){
	        				backService.updateStatus(backBill.getPolino(), "3" , "",rspmap.get("repysq").toString());
	        			} else{
	        				backService.updateStatus(backBill.getPolino(), "2" , rspmap1.get("retMsg").toString(),"");
	        			}
	        		} else  if(rspmap.get("retCode").toString().equals("4516")){
        				backService.updateStatus(backBill.getPolino(), "3" , "",rspmap.get("repysq").toString());
        			}else{
	        			/*//通知前置划款失败 不通知
	        			Map<String, Object> reqmap1 = new HashMap<String, Object>();
	        			reqmap1.put("OrderId", rspmap.get("ordrid"));//订单号
	        			reqmap1.put("RefundId", rspmap.get("refuid"));//退保订单号
	        			reqmap1.put("RefundProposalNo", rspmap.get("refuno"));//保全号
	        			reqmap1.put("PayTime", rspmap.get("payxtm"));//支付时间
	        			reqmap1.put("PayMoney", backBill.getTranam());//划账金额
	        			reqmap1.put("RefundPayOrderId", "");//退保支付交易号
	        			reqmap1.put("IsSuccess", "1");//划账结果
	        			reqmap1.put("FailReason", rspmap.get("retMsg"));//失败原因
	        			reqmap1.put("PayAccountName", backBill.getCustna());//客户名称
	        			reqmap1.put("PayAccountId", rspmap.get("custac"));
	        			Map<String, Object> rspmap1 = clict.callClient("dedunt", reqmap1);*/
	        			//划账失败，更改状态，登记错误信息
	        			backService.updateStatus(backBill.getPolino(), "1",rspmap.get("retMsg").toString(),"");
	        			errlog.add("处理保单["+backBill.getPolino()+"]退保异常："+rspmap.get("retMsg").toString());
	        		}
				} catch (SumpException e) {
					e.printStackTrace();
					errlog.add("处理保单["+backBill.getPolino()+"]退保异常："+e.getErrMsg());
				}
			} else if(backBill.getChkStatus().equals("7")) {
				remap.put("polino", backBill.getPolino());
				remap.put("insust", "3");//退保失败
				remap.put("userid", user.getUserid());
				logger.debug("交易柜员=========="+user.getUserid());
				try {
					Map<String, Object> rspmap = clict.callClient("dfupst", remap);
	        		if(rspmap.get("retCode").toString().equals("0000")){
	        			//处理成功，修改记录状态
	        			backService.updateStatus(backBill.getPolino(), "4" , rspmap.get("retMsg").toString(),"");
	        		} else {
	        			backService.updateStatus(backBill.getPolino(), "4" , rspmap.get("retMsg").toString(),"");
	        		}
				} catch (SumpException e) {
					errlog.add("处理保单["+backBill.getPolino()+"]退保异常："+e.getErrMsg());
				}
			}
		}
		map.put("errlog", errlog);
		map.put("retCode", "0000");
		map.put("retMsg", "");
		return map;
	}
	
	/**
	 * 生成投保客户支付明细文件
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
	@RequestMapping(value ="/getPoliFile")
	public Map<String,Object> getPoliFile(HttpServletRequest request, @RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user,HttpServletResponse response) {
		String path = request.getSession().getServletContext().getRealPath("download")+File.separator;
		String fileName = "cust_pay_detail_"+reqmap.get("filedt")+".xlsx";
		Map<String,Object> map = new HashMap<String, Object>();
		//File fileC = new File(path+fileName);
//		if(fileC.exists()){
//			map.put("fileName", "/download/"+fileName);
//			map.put("retCode", "0000");
//			return map;
//		}
		//查询数据
		
		List<List<ExcelEntity>> cells = new ArrayList<List<ExcelEntity>>();
		reqmap.put("userid", user.getUserid());
		reqmap.put("startt", "1");
		reqmap.put("record", "10");
		logger.debug("日期===================="+reqmap.get("trandt"));
		map = clict.callClient("dfqr03", reqmap);
		List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("detlls");
		BigDecimal inTotlam = BigDecimal.ZERO;
		if(list == null){
			logger.debug("是否为空===================="+"YES");
		} else {
			logger.debug("是否为空===================="+"NO");
			for(Map<String,Object> bill : list){
				List<ExcelEntity> row = new ArrayList<ExcelEntity>();
				ExcelEntity e1 = new ExcelEntity();
				e1.setData(bill.get("iutpna").toString());
				e1.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e1);
				
				ExcelEntity e2 = new ExcelEntity();
				e2.setData(bill.get("holdsq").toString());
				e2.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e2);
				
				ExcelEntity e3 = new ExcelEntity();
				e3.setData(bill.get("holdna").toString());
				e3.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e3);
				
				ExcelEntity e4 = new ExcelEntity();
				e4.setData(bill.get("totlam").toString());
				e4.setDataType((short) 3);
				row.add(e4);
				
				ExcelEntity e5 = new ExcelEntity();
				e5.setData(bill.get("trantm").toString());
				e5.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e5);
				logger.debug(formartInsuStatus(bill.get("insust").toString()));
				ExcelEntity e6 = new ExcelEntity();
				e6.setData(formartInsuStatus(bill.get("insust").toString()));
				e6.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e6);
				
				ExcelEntity e7 = new ExcelEntity();
				e7.setData(bill.get("polino").toString());
				e7.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e7);
				cells.add(row);
				inTotlam = inTotlam.add(new BigDecimal(bill.get("totlam").toString()));
			}
			int amount = (int) Math.ceil(Integer.parseInt(map.get("recdsm").toString())/10.0);
			if(amount>1){
				for(int i=2;i<=amount;i++){
					reqmap.put("startt", i);
					map = clict.callClient("dfqr03", reqmap);
					list = (List<Map<String, Object>>) map.get("detlls");
					if(list == null){
						logger.debug("是否为空===================="+"YES");
					} else {
						logger.debug("是否为空===================="+"NO");
						for(Map<String,Object> bill : list){
							List<ExcelEntity> row = new ArrayList<ExcelEntity>();
							ExcelEntity e1 = new ExcelEntity();
							e1.setData(bill.get("iutpna").toString());
							e1.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e1);
							
							ExcelEntity e2 = new ExcelEntity();
							e2.setData(bill.get("holdsq").toString());
							e2.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e2);
							
							ExcelEntity e3 = new ExcelEntity();
							e3.setData(bill.get("holdna").toString());
							e3.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e3);
							
							ExcelEntity e4 = new ExcelEntity();
							e4.setData(bill.get("totlam").toString());
							e4.setDataType((short) 3);
							row.add(e4);
							
							ExcelEntity e5 = new ExcelEntity();
							e5.setData(bill.get("trantm").toString());
							e5.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e5);
							
							ExcelEntity e6 = new ExcelEntity();
							e6.setData(formartInsuStatus(bill.get("insust").toString()));
							e6.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e6);
							
							ExcelEntity e7 = new ExcelEntity();
							e7.setData(bill.get("polino").toString());
							e7.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e7);
							cells.add(row);
							inTotlam = inTotlam.add(new BigDecimal(bill.get("totlam").toString()));
						}
					}
				}
			}
		}
		//记录支付汇总数据
		InsuSetlle insuSetlle = new InsuSetlle();
		insuSetlle.setDealdt(reqmap.get("filedt").toString());
		insuSetlle.setStatus("0");//未清算
		insuSetlle.setTotlam(inTotlam);
		insuSetlle.setTrantp("0");
		insuSetlleService.saveEntity(insuSetlle);
		//文件头行
		List<String> head = new ArrayList<String>();
		head.add("产品名称");
		head.add("交易流水号");
		head.add("投保人姓名");
		head.add("投保金额");
		head.add("支付时间");
		head.add("保单状态");
		head.add("保单号");
		
		
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		logger.debug("path >>>>>>>>>>>>>>>>>>>"+path);
		ExcelUtil excelUtil = new ExcelUtil(fileName,path);
		excelUtil.writeExcel("珠江汇赢1号终身寿险", head, cells);
		map.put("fileName", "/download/"+fileName);
		map.put("retCode", "0000");
		return map;
	}
	
	@RequestMapping(value ="/downFile")
	public Map<String,Object> getDownFile(HttpServletRequest request, @RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user,HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String, Object>();
		String path = request.getSession().getServletContext().getRealPath("download")+File.separator;
		String fileName = "cust_pay_detail_"+reqmap.get("filedt")+".xlsx";
		File file = new File(path+fileName);
		if(!file.exists()){
			logger.debug("文件不存在！");
			map.put("retCode", "1111");
			map.put("retMsg", "文件["+fileName+"]不存在");
			return map;
		}
		map.put("fileName", "/download/"+fileName);
		map.put("retCode", "0000");
		return map;
	}
	
	/**
	 * 生成投保客户应退明细文件
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
	@RequestMapping(value ="/getInsuReturnFile")
	public Map<String,Object> getInsuReturnFile(HttpServletRequest request, @RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user,HttpServletResponse response) {
		// 获取服务器完整路径
		String path = request.getSession().getServletContext().getRealPath("download")+File.separator;
		Map<String,Object> map = new HashMap<String, Object>();
		if(reqmap.get("filedt") == null && reqmap.get("filedt") == ""){
			map.put("retMsg", "请输入时间");
			return map;
		}
		// 生成文件名
		String fileName = "dfa_hold_detl_"+reqmap.get("filedt")+".xlsx";
		
		File fileC = new File(path+fileName);
		if(fileC.exists()){
//			map.put("fileName", "/download/"+fileName);
//			map.put("retCode", "0000");
//			return map;
			fileC.delete();
		}
		//查询数据
		List<List<ExcelEntity>> cells = new ArrayList<List<ExcelEntity>>();
		reqmap.put("userid", user.getUserid());
		reqmap.put("pageno", "1");
		reqmap.put("record", "10");
		logger.debug("日期===================="+reqmap.get("trandt"));
		map = clict.callClient("dfqrrt", reqmap);
		List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("rudfin");
		if(list == null){
			logger.debug("是否为空===================="+"YES");
		} else {
			logger.debug("是否为空===================="+"NO");
			for(Map<String,Object> bill : list){
				List<ExcelEntity> row = new ArrayList<ExcelEntity>();
				ExcelEntity e1 = new ExcelEntity();
				e1.setData(bill.get("prodna").toString());
				e1.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e1);
				
				ExcelEntity e2 = new ExcelEntity();
				e2.setData(bill.get("refusq").toString());
				e2.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e2);
				
				ExcelEntity e3 = new ExcelEntity();
				e3.setData(bill.get("polino").toString());
				e3.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e3);
				
				ExcelEntity e4 = new ExcelEntity();
				e4.setData(bill.get("holdna").toString());
				e4.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e4);
				
				ExcelEntity e5 = new ExcelEntity();
				e5.setData(bill.get("avrfam").toString());
				e5.setDataType((short) 3);
				row.add(e5);
				
				ExcelEntity e6 = new ExcelEntity();
				e6.setData(formartInsuStatus(bill.get("insust").toString()));
				e6.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e6);
				
				ExcelEntity e7 = new ExcelEntity();
				e7.setData(bill.get("refudt").toString());
				e7.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e7);
				cells.add(row);
			}
			int amount = (int) Math.ceil(Integer.parseInt(map.get("recdsm").toString())/10.0);
			if(amount>1){
				for(int i=2;i<=amount;i++){
					reqmap.put("pageno", i);
					map = clict.callClient("dfqrrt", reqmap);
					list = (List<Map<String, Object>>) map.get("rudfin");
					if(list == null){
						logger.debug("是否为空===================="+"YES");
					} else {
						logger.debug("是否为空===================="+"NO");
						for(Map<String,Object> bill : list){
							List<ExcelEntity> row = new ArrayList<ExcelEntity>();
							ExcelEntity e1 = new ExcelEntity();
							e1.setData(bill.get("prodna").toString());
							e1.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e1);
							
							ExcelEntity e2 = new ExcelEntity();
							e2.setData(bill.get("refusq").toString());
							e2.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e2);
							
							ExcelEntity e3 = new ExcelEntity();
							e3.setData(bill.get("polino").toString());
							e3.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e3);
							
							ExcelEntity e4 = new ExcelEntity();
							e4.setData(bill.get("holdna").toString());
							e4.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e4);
							
							ExcelEntity e5 = new ExcelEntity();
							e5.setData(bill.get("avrfam").toString());
							e5.setDataType((short) 3);
							row.add(e5);
							
							ExcelEntity e6 = new ExcelEntity();
							e6.setData(formartInsuStatus(bill.get("insust").toString()));
							e6.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e6);
							
							ExcelEntity e7 = new ExcelEntity();
							e7.setData(bill.get("refudt").toString());
							e7.setDataType((short) Cell.CELL_TYPE_STRING);
							row.add(e7);
							cells.add(row);
						}
					}
				}
			}
		}
		//文件头行
		List<String> head = new ArrayList<String>();
		head.add("产品名称");
		head.add("交易流水号");
		head.add("保单号");
		head.add("退保人姓名");
		head.add("实际退保金额");
		head.add("退保审核状态");
		head.add("退保审核通过时间");
		
		
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		logger.debug("path >>>>>>>>>>>>>>>>>>>"+path);
		ExcelUtil excelUtil = new ExcelUtil(fileName,path);
		excelUtil.writeExcel("退保客户应退明细", head, cells);
		map.put("fileName", "/download/"+fileName);
		map.put("retCode", "0000");
		return map;
	}
	
	/**
	 * 下载实退文件
	 * 1、当日数据全部处理才允许下载，否则提示未处理数据。
	 * @param request
	 * @param reqmap
	 * @param user
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/getInsureBackFile")
	public Map<String,Object> getInsureReturnFile(HttpServletRequest request, @RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user,HttpServletResponse response) {
		//根据日期查询数据
		String acctdt = reqmap.get("filedt").toString();
		// 获取服务器完整路径
		String path = request.getSession().getServletContext().getRealPath("download")+File.separator;
		// 生成文件名
		String fileName = "dfa_real_back_bill_"+reqmap.get("filedt")+".xlsx";
		
		Map<String,Object> map = new HashMap<String, Object>();
		//File fileC = new File(path+fileName);
//		if(fileC.exists()){
//			map.put("fileName", "/download/"+fileName);
//			map.put("retCode", "0000");
//			return map;
//		}
		List<TmpBackInsuBill> lstTmpBack = backService.selectAll(acctdt);
		//查询数据
		List<List<ExcelEntity>> cells = new ArrayList<List<ExcelEntity>>();
		if(lstTmpBack == null){
			logger.debug("是否为空===================="+"YES");
		} else {
			logger.debug("是否为空===================="+"NO");
			for(TmpBackInsuBill bill : lstTmpBack){
				//如果存在未处理的记录，报提示信息
				//Modified by wanggl 2015-09-06
				if(bill.getCheckStatus().equals("0")){
					map.put("retCode", "1111");
					map.put("retMsg", "当日有未处理的退保记录，请先处理后再下载文件");
					return map;
				}
				List<ExcelEntity> row = new ArrayList<ExcelEntity>();
				ExcelEntity e1 = new ExcelEntity();
				e1.setData(bill.getProcna());
				e1.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e1);
				
				ExcelEntity e2 = new ExcelEntity();
				e2.setData(bill.getTransq());
				e2.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e2);
				
				ExcelEntity e3 = new ExcelEntity();
				e3.setData(bill.getPolino());
				e3.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e3);
				
				ExcelEntity e4 = new ExcelEntity();
				e4.setData(bill.getCustna());
				e4.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e4);
				
				ExcelEntity e5 = new ExcelEntity();
				e5.setData(bill.getTranam().toString());
				e5.setDataType((short) 3);
				row.add(e5);
				
				ExcelEntity e6 = new ExcelEntity();
				e6.setData(bill.getCheckStatus().equals("1")?"划账失败":("3".equals(bill.getCheckStatus())?"划账成功":bill.getCheckStatus()));
				e6.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e6);
				
				ExcelEntity e7 = new ExcelEntity();
				e7.setData(bill.getPasstm());
				e7.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e7);
				
				ExcelEntity e8 = new ExcelEntity();
				e8.setData(bill.getMsg() == null ? "" : bill.getMsg());
				e8.setDataType((short) Cell.CELL_TYPE_STRING);
				row.add(e8);
				cells.add(row);
			}
		}
		//文件头行
		List<String> head = new ArrayList<String>();
		head.add("产品名称");
		head.add("交易流水号");
		head.add("保单号");
		head.add("退保人姓名");
		head.add("实际退保金额");
		head.add("退保状态");
		head.add("退保通过时间");
		head.add("退保失败原因");
		
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		logger.debug("path >>>>>>>>>>>>>>>>>>>"+path);
		ExcelUtil excelUtil = new ExcelUtil(fileName,path);
		excelUtil.writeExcel("退保客户实退明细", head, cells);
		map.put("fileName", "/download/"+fileName);
		map.put("retCode", "0000");
		logger.debug("状态"+formartInsuStatus("退保受理成功"));
		return map;
	}
	
	public String formartInsuStatus(String value){
		List<ApSysDict> list=apSysDictService.getDictsByDictType("insuDict");
		logger.debug("=========="+list);
		logger.debug("=========="+value);
		for(ApSysDict dict : list){
			logger.debug("==========+++++++"+(dict.getDictId() == value));
			if(dict.getDictId().equals(value)){
				logger.debug("=========="+dict.getDictName());
				return dict.getDictName();
			}
			if(dict.getDictName().equals(value)){
				logger.debug("=========="+dict.getDictName());
				return dict.getDictId();
			}
		}
		return value;
	}
	
	@RequestMapping(value ="/getTranam")
	public Map<String,Object> getClearTranam(@RequestBody Map<String,Object> reqmap){
		logger.debug("===========查询保险申购清算信息开始===========");
		Map<String,Object> rspmap = new HashMap<String, Object>();
		Date trandt = DateTools.String2Date(reqmap.get("trandt").toString(), "yyyyMMdd");
		String trandt1 = DateTools.Date2String(DateTools.addDays(trandt, 1),"yyyyMMdd");
		InsuSetlle insuSetlle =new InsuSetlle();
		logger.debug("日期======================="+trandt1);
		logger.debug("日期11======================="+reqmap.get("trandt").toString());
		insuSetlle.setDealdt(reqmap.get("trandt").toString());
		insuSetlle.setTrantp(reqmap.get("trantp").toString());
		insuSetlle=insuSetlleService.expandEntit(insuSetlle);
		if(insuSetlle==null){
			if(reqmap.get("trantp").equals("0")){
				rspmap.put("retMsg", "无清算数据");
				return rspmap;
			} else if(reqmap.get("trantp").equals("1")){
				
				BigDecimal totlam = tmpBackInsuBillService.getTotlamByDealdt(trandt1);
				if(totlam == null){
					rspmap.put("retMsg", "无清算数据");
					return rspmap;
				}
				logger.debug("===========查询保险申购清算信息开始==========="+totlam);
				InsuSetlle insuSetlleNew =new InsuSetlle();
				insuSetlleNew.setDealdt(reqmap.get("trandt").toString());
				insuSetlleNew.setTrantp(reqmap.get("trantp").toString());
				insuSetlleNew.setTotlam(totlam);
				insuSetlleNew.setStatus("0");
				insuSetlleNew=insuSetlleService.saveEntity(insuSetlleNew);
				rspmap.put("retCode", "0000");
				rspmap.put("retMsg", "交易成功");
				rspmap.put("insuSetlle", insuSetlleNew);
				return rspmap;
			}
			else{
				rspmap.put("retMsg", "清算类别错误");
				return rspmap;
			}
				
		}
		rspmap.put("retCode", "0000");
		rspmap.put("retMsg", "交易成功");
		rspmap.put("insuSetlle", insuSetlle);
		
		return rspmap;
	}
	
	@RequestMapping(value ="/clearTran")
	public Map<String,Object> clearTran(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.debug("===========保险清算开始===========");
		Map<String,Object> rspmap = new HashMap<String, Object>();
		
		InsuSetlle insuSetlle =new InsuSetlle();
		insuSetlle.setDealdt(reqmap.get("trandt").toString());
		insuSetlle.setTrantp(reqmap.get("trantp").toString());
		insuSetlle=insuSetlleService.expandEntit(insuSetlle);
		if(!insuSetlle.getStatus().equals("0")){
			rspmap.put("retMsg", "清算状态不正确");
			return rspmap;
		}
		reqmap.put("userid", user.getUserid());
		
		logger.debug("===========保险清算开始==========="+insuSetlle.getTrantp().toString());
		
		
		if(insuSetlle.getTrantp().equals("0")){
			rspmap =  clict.callClient("isstin", reqmap);
		} else if(insuSetlle.getTrantp().equals("1")){
			rspmap =  clict.callClient("isstou", reqmap);
		} else {
			rspmap.put("retMsg", "清算类别错误");
		}
		if("0000".equals(rspmap.get("retCode").toString())){
			insuSetlle.setStatus("1");
			insuSetlle=insuSetlleService.saveEntity(insuSetlle);	
			rspmap.put("retCode", "0000");
			rspmap.put("retMsg", "交易成功");
		} else {
			rspmap.put("retCode", "0000");
			rspmap.put("retMsg", rspmap.get("retMsg"));
		}

		return rspmap;
	}
	
	
}
