package cn.sunline.fund.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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

import cn.sunline.client.ClientImpl;
import cn.sunline.domain.endauth.BSBUser;
import cn.sunline.exception.SumpException;
import cn.sunline.tmp.fund.settle.BillDetail;
import cn.sunline.tmp.fund.settle.BillDetailDataWriterImpl;
import cn.sunline.tmp.fund.settle.BillDetailService;
import cn.sunline.tmp.fund.settle.FundSetlle;
import cn.sunline.tmp.fund.settle.FundSetlleService;
import cn.sunline.tmp.fund.settle.TmpFundProfit;
import cn.sunline.tmp.fund.settle.TmpFundProfitDataWriterImpl;
import cn.sunline.tmp.fund.settle.TmpFundProfitService;
import cn.sunline.utils.DateTools;
import cn.sunline.utils.PageToDataTable;

@Controller("FundController")
@RequestMapping(value = "/rest/fund")
@ResponseBody
@SessionAttributes("User")
public class FundController {
	private static Logger logger = LoggerFactory.getLogger(FundController.class);
	@Resource
	private TmpFundProfitDataWriterImpl tmpFundProfitDataWriterImpl;
	@Resource
	private BillDetailDataWriterImpl billDetailDataWriterImpl;
	
	@Autowired
	private TmpFundProfitService tmpFundService;
	@Autowired
	private BillDetailService billDetailService;
	@Autowired 
	private FundSetlleService fundSetlleService;
	@Autowired
	private FundSetlleService setlleService;
	@Autowired
	ClientImpl clict;
	
	@RequestMapping(value = "/getInfo")
	public Map<String,Object> getInfo(@RequestBody Map<String,Object> reqmap){
		Map<String,Object> rspmap = new HashMap<String, Object>();
		
		String dealdt = reqmap.get("dealdt").toString();
		logger.debug("dealdt =========== [%s]",dealdt);
		TmpFundProfit profit = tmpFundService.getFundProfitInfo(dealdt);
		logger.debug("profit =========== [%s]",profit);
		logger.debug("YES======================"+(profit == null));
		if(profit == null){
			try {
				clict.readFile(reqmap.get("file").toString(), tmpFundProfitDataWriterImpl,
						dealdt);
				
				//查询
				profit = tmpFundService.getFundProfitInfo(dealdt);
			} catch (SumpException e) {
				rspmap.put("retCode", e.getErrCode());
				rspmap.put("retMsg", e.getErrMsg());
				return rspmap;
			}
		} 
			rspmap.put("retCode", "0000");
			rspmap.put("retMsg", "");
			rspmap.put("profit", profit);
		
		return rspmap;
		
	}
	
	@RequestMapping(value = "/dealInfo")
	public Map<String,Object> dealInfo(@RequestBody Map<String,Object> reqmap ,@ModelAttribute("User") BSBUser user){
		Map<String,Object> rspmap = new HashMap<String, Object>();
		
		String dealdt = reqmap.get("dealdt").toString();
		
		TmpFundProfit profit = tmpFundService.getFundProfitInfo(dealdt);
		if(profit == null){
			rspmap.put("retCode", "1111");
			rspmap.put("retMsg", "查询数据有误！");
		} else {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("crcycd", "01");
			map.put("tranam", profit.getPrftam());
			map.put("userid", user.getUserid());
			try{
				Map<String, Object> remap = clict.callClient("bkpfac", map);
				if("0000".equals(remap.get("retCode").toString())){
					//修改记录状态
					tmpFundService.updateStatus(dealdt, "Y");
					rspmap.put("retCode", "0000");
					rspmap.put("retMsg", "入账操作成功");
				}
				else {
					rspmap.put("retCode", "1999");
					rspmap.put("retMsg", "入账失败:"+remap.get("retMsg"));
				}
			} catch(SumpException e){
				rspmap.put("retCode", e.getErrCode());
				rspmap.put("retMsg", e.getErrMsg());
				return rspmap;
			}
		}
		
		return rspmap;
		
	}
	/*
	 * 
	 */
	@RequestMapping(value = "/billdetail")
	public Map<String,Object> getbilldetail(@RequestBody Map<String,Object> reqmap){
		Map<String,Object> rspmap = new HashMap<String, Object>();
		String dealdt = reqmap.get("dealdt").toString();
		logger.debug("dealdt =========== [%s]",dealdt);
		int count = billDetailService.getBillCountByDate(dealdt);
		logger.debug("profit =========== [%s]",count);
		logger.debug("YES======================"+(count == 0));
		if(count == 0){
			try {
				clict.readFile(reqmap.get("file").toString(), billDetailDataWriterImpl,
						dealdt);
			} catch (SumpException e) {
				rspmap.put("retCode", e.getErrCode());
				rspmap.put("retMsg", e.getErrMsg());
				return rspmap;
			}
		} 
			
			rspmap.put("retCode", "0000");
			rspmap.put("retMsg", "");
			rspmap.put("infos", setlleService.getFundSetlleByDealDt(dealdt));
			Date trandt = DateTools.String2Date(dealdt, "yyyyMMdd");
			String trandt1 = DateTools.Date2String(DateTools.addDays(trandt, 1),"yyyyMMdd");
			logger.debug("日期======================================="+trandt1);
			rspmap.put("profit",tmpFundService.getFundProfitInfo(trandt1));
		return rspmap;
		
	}
	
	@RequestMapping(value = "/getBillData")
	public Map<String,Object> getBillData(@RequestParam Map<String,Object> reqmap){
		Page<BillDetail> billDetailPage = null;
		int length=Integer.parseInt((String)reqmap.get("length"),10);		
		int start=Integer.parseInt((String)reqmap.get("start"),10);		
		Pageable pageable=new PageRequest(start/length,length);
		BillDetail billDetail  = new BillDetail();
		billDetail.setTrandt(reqmap.get("dealdt").toString());
		billDetailPage =  billDetailService.getBillDetailPage(billDetail,  pageable);
		Map<String, Object> map=PageToDataTable.convert(billDetailPage);
	
		return map;
	}
	@RequestMapping(value = "/getclearFund")
	public Map<String,Object> getclearFund(@RequestBody Map<String,Object> reqmap){
		Map<String,Object> rspmap =new HashMap<String, Object>();
		List<FundSetlle> fundSetlles =
				fundSetlleService.getFundSetlleByDealDtAndTrantp(reqmap.get("trandt").toString(), reqmap.get("trantp").toString());
		if(fundSetlles.size()>0){
			FundSetlle fundSetlle =fundSetlles.get(0);
			rspmap.put("retCode", "0000");
			rspmap.put("fundSetlle", fundSetlle);
		}else{
			rspmap.put("retCode", "9999");
			rspmap.put("retMsg", "未找到数据");
		}
		return rspmap;
	}
    @RequestMapping(value ="/clearFund")
	public Map<String,Object> clearFund(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.debug("===========基金清算开始===========");
		Map<String,Object> rspmap = new HashMap<String, Object>();
		
		List<FundSetlle> fundSetlles=fundSetlleService.getFundSetlleByDealDtAndTrantp(reqmap.get("trandt").toString(), reqmap.get("trantp").toString());
		FundSetlle fundSetlle =new FundSetlle();
		if(fundSetlles.size()>0){
			fundSetlle=fundSetlles.get(0);
			if(!fundSetlle.getStatus().equals("0")){
				rspmap.put("retCode", "9999");
				rspmap.put("retMsg", "清算状态不正确");
				return rspmap;
			}
		}else{
			rspmap.put("retCode", "9999");
			rspmap.put("retMsg", "未找到数据");
			return rspmap;
		}
		reqmap.put("userid", user.getUserid());
			if(fundSetlle.getTrantp().equals("0")){
				rspmap =  clict.callClient("fdstin", reqmap);
				if(rspmap.get("retCode").equals("0000")){
					fundSetlleService.updateStatus(fundSetlle.getDealdt(), fundSetlle.getTrantp(), "1");
				}
			} else if(fundSetlle.getTrantp().equals("1")){
				rspmap =  clict.callClient("fdstou", reqmap);
				if(rspmap.get("retCode").equals("0000")){
					fundSetlleService.updateStatus(fundSetlle.getDealdt(), fundSetlle.getTrantp(), "1");
				}
			} else {
				rspmap.put("retCode", "9999");
				rspmap.put("retMsg", "清算类别错误");
			}
		
		return rspmap;
	}

}
