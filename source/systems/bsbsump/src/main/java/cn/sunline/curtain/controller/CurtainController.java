package cn.sunline.curtain.controller;

import java.util.ArrayList;
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
import cn.sunline.tmp.chinapay.check.TmpChinapayCltnCheck;
import cn.sunline.tmp.chinapay.check.TmpChinapayCltnCheckDataWriterImpl;
import cn.sunline.tmp.chinapay.check.TmpChinapayCltnCheckService;
import cn.sunline.tmp.chinapay.check.TmpChinapayCltnHeadCheck;
import cn.sunline.tmp.chinapay.check.TmpChinapayPayCheck;
import cn.sunline.tmp.chinapay.check.TmpChinapayPayCheckDataWriterImpl;
import cn.sunline.tmp.chinapay.check.TmpChinapayPayCheckService;
import cn.sunline.tmp.chinapay.check.TmpChinapayPayHeadCheck;
import cn.sunline.utils.Digests;
import cn.sunline.utils.PageToDataTable;

@Controller("CurtainController")
@RequestMapping(value = "/rest/curtain")
@ResponseBody
@SessionAttributes("User")
public class CurtainController {
	
	@Resource
	private TmpChinapayPayCheckDataWriterImpl tmpChinapayPayCheckService;
	
	@Resource
	private TmpChinapayCltnCheckDataWriterImpl tmpChinapayCltnCheckService;
	
	@Autowired
	private TmpChinapayPayCheckService payCheckService;
	
	@Autowired
	private TmpChinapayCltnCheckService cltnCheckService;
	
	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory.getLogger(CurtainController.class);
	
	@RequestMapping(value ="/inactr")
	public Map<String,Object> inactr(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		if(reqmap.get("authpw")!=null){
			reqmap.put("authpw",Digests.GetMD5Code(reqmap.get("authpw").toString()));//复核密码转化·
		}
		reqmap.put("servtp", "MGR");//复核交易表示
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try{
			rspmap =  clict.callClient("inactr", reqmap);
		} catch(SumpException e){
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		return rspmap;
	}
	@RequestMapping(value = "/itmact")
	public Map<String , Object> itmact(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		logger.info("请求参数================="+reqmap);
		
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try{
			rspmap =  clict.callClient("itmact", reqmap);
		} catch(SumpException e){
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("返回参数================="+rspmap);
		return rspmap;
	}
	@RequestMapping(value ="/adjust")
	public Map<String,Object> adjust(@RequestBody Map<String,Object> reqmap, @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		if(reqmap.get("authpw")!=null){
			reqmap.put("authpw",Digests.GetMD5Code(reqmap.get("authpw").toString()));//复核密码转化·
		}
		reqmap.put("servtp", "MGR");//复核交易表示
		logger.info("请求参数================="+reqmap);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try{
			rspmap =  clict.callClient("inchba", reqmap);
		} catch(SumpException e){
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		return rspmap;
	}
	@RequestMapping(value = "/acct")
	public Map<String , Object> acct(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		logger.info("请求参数================="+reqmap);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try{
			rspmap =  clict.callClient("getacn", reqmap);
		} catch(SumpException e){
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("返回参数================="+rspmap);
		return rspmap;
	}

	@RequestMapping(value = "/strike")
	public Map<String , Object> strike(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		logger.info("请求参数================="+reqmap);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try{
			rspmap =  clict.callClient("api111", reqmap);
		} catch(SumpException e){
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("返回参数================="+rspmap);
		return rspmap;
	}
	@RequestMapping(value = "/check")
	public Map<String,Object> check(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		String checkDate = reqmap.get("trandt").toString();
		if(payCheckService.checkIsExitByCheckDate(checkDate)){
			TmpChinapayPayHeadCheck head = payCheckService.getPayHeadCheckInfo((String)reqmap.get("trandt"));
			map.put("retCode", "0000");
			map.put("retMsg", "");
			map.put("fee", head.getFee());
			map.put("amount", head.getAmount());
			map.put("status", head.getCheckStatus());
			return map;
		}
		//删除
//		payCheckService.deleteByCheckDate(checkDate);
//		payCheckService.deleteByHeadCheckDate(checkDate);
		try {
			clict.readFile(reqmap.get("file").toString(), tmpChinapayPayCheckService,
					reqmap.get("trandt").toString());
		} catch (SumpException e) {
			map.put("retCode", e.getErrCode());
			map.put("retMsg", e.getErrMsg());
			return map;
		}
		//查询头信息
		TmpChinapayPayHeadCheck head = payCheckService.getPayHeadCheckInfo((String)reqmap.get("trandt"));
		map.put("retCode", "0000");
		map.put("retMsg", "");
		map.put("fee", head.getFee());
		map.put("amount", head.getAmount());
		map.put("status", head.getCheckStatus());
		return map;
	}
	/**
	 * ChinaPay 代付差错信息
	 * @param reqmap
	 * @return
	 */
	@RequestMapping(value = "/cppchk")
	public Map<String,Object> checkInfo(@RequestParam Map<String,Object> reqmap){
		logger.debug("===========查询差错信息开始===========");
		int length=Integer.parseInt((String)reqmap.get("length"),10);		
		int start=Integer.parseInt((String)reqmap.get("start"),10);		
		Pageable pageable=new PageRequest(start/length,length);
		TmpChinapayPayCheck payCheck = new TmpChinapayPayCheck();
		payCheck.setCheckDate((String)reqmap.get("trandt"));
		payCheck.setCheckStatus((String)reqmap.get("checkStatus"));
		Page<TmpChinapayPayCheck> page=payCheckService.queryEntitiesByTemplateWithPage(payCheck,pageable);	  
	    Map<String, Object> map=PageToDataTable.convert(page);
	    logger.debug(map.toString());
		return map;
	}
	
	
	@RequestMapping(value = "/checkc")
	public Map<String,Object> checkC(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		String checkDate = reqmap.get("trandt").toString();
		boolean b = cltnCheckService.checkIsExitByCheckDate(checkDate);
		logger.debug("是否已经导入："+b);
		if(b){
			TmpChinapayCltnHeadCheck head = cltnCheckService.getCltnHeadCheckInfo((String)reqmap.get("trandt"));
			map.put("retCode", "0000");
			map.put("retMsg", "");
			map.put("fee", head.getFee());
			map.put("amount", head.getAmount());
			map.put("status", head.getCheckStatus());
			return map;
		}
		//删除
//		cltnCheckService.deleteByCheckDate(checkDate);
//		cltnCheckService.deleteByHeadCheckDate(checkDate);
		try {
		clict.readFile(reqmap.get("file").toString(), tmpChinapayCltnCheckService,
					reqmap.get("trandt").toString());
		}catch (SumpException e) {
			e.printStackTrace();
			map.put("retCode", "1111");
			map.put("retMsg", "导入差错信息异常，请确认文件是否存在");
			return map;
		}
		//查询头信息
		TmpChinapayCltnHeadCheck head = cltnCheckService.getCltnHeadCheckInfo((String)reqmap.get("trandt"));
		map.put("retCode", "0000");
		map.put("retMsg", "");
		map.put("fee", head.getFee());
		map.put("amount", head.getAmount());
		map.put("status", head.getCheckStatus());
		return map;
	}
	/**
	 * 代扣清算处理
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dealCltn")
	public Map<String,Object> dealCltn(@RequestBody Map<String,Object> reqmap, @ModelAttribute("User") BSBUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		//查询头信息
		TmpChinapayCltnHeadCheck head = cltnCheckService.getCltnHeadCheckInfo((String)reqmap.get("trandt"));
		logger.debug("是否记账============"+"N".equals(head.getCheckStatus()));
		if("N".equals(head.getCheckStatus())){
			int count = cltnCheckService.getUnDealCount((String)reqmap.get("trandt"), "N");
			if(count > 0){
				map.put("retCode", "1888");
				map.put("retMsg", "清算失败，还有未处理的差错记录！");
				return map;
			}
			logger.debug("总账记账金额============"+head.getAmount().toString());
			String tranam = head.getAmount().toString();
			String feeeam = head.getFee().toString();
			Map<String,Object> rpmap = new HashMap<String, Object>();
			rpmap.put("tranam", tranam);
			rpmap.put("feeaam", feeeam);
			rpmap.put("userid", user.getUserid());
			try{
				Map<String, Object> remap = clict.callClient("cpcltn", rpmap);
				logger.debug("返回报文======================="+remap);
				logger.debug("返回状态======================="+"0000".equals(remap.get("retCode").toString()));
				if("0000".equals(remap.get("retCode").toString())){
					//修改头记录状态
					logger.debug("返回报文======================="+remap);
					cltnCheckService.upHeadCheckStatus(head.getCheckDate(), "Y");
					logger.debug("修改状态成功");
				}
				else {
					map.put("retCode", "1999");
					map.put("retMsg", "清算失败:"+remap.get("retMsg"));
					return map;
				}
			} catch(SumpException e){
				map.put("retCode", e.getErrCode());
				map.put("retMsg", e.getErrMsg());
				return map;
			}
		} else {
			map.put("retCode", "1888");
			map.put("retMsg", "清算失败，当日资金已清算！");
			return map;
		}
		map.put("retCode", "0000");
		map.put("retMsg", "");
		return map;
	}
	/**
	 * 代付清算处理
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dealPay")
	public Map<String,Object> dealPay(@RequestBody Map<String,Object> reqmap, @ModelAttribute("User") BSBUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		//查询头信息
		TmpChinapayPayHeadCheck head = payCheckService.getPayHeadCheckInfo((String)reqmap.get("trandt"));
		logger.debug("是否记账=============="+"N".equals(head.getCheckStatus()));
		if("N".equals(head.getCheckStatus())){
			
			int count = payCheckService.getUnDealCount((String)reqmap.get("trandt"), "N");
			if(count > 0){
				map.put("retCode", "1888");
				map.put("retMsg", "清算失败，还有未处理的差错记录！");
				return map;
			}
			logger.debug("总账记账金额============"+head.getAmount().toString());
			String tranam = head.getAmount().toString();
			String feeeam = head.getFee().toString();
			Map<String,Object> rpmap = new HashMap<String, Object>();
			rpmap.put("tranam", tranam);
			rpmap.put("feeeam", feeeam);
			rpmap.put("userid", user.getUserid());
			try{
				Map<String, Object> remap = clict.callClient("rpcpou", rpmap);
				logger.debug("是否成功》》》》》》》》》》》"+remap.get("retCode"));
				if("0000".equals(remap.get("retCode").toString())){
					//修改头记录状态
					payCheckService.upHeadCheckStatus(head.getCheckDate(), "Y");
				} else {
					map.put("retCode", "1999");
					map.put("retMsg", "清算失败:"+remap.get("retMsg"));
					return map;
				}
			} catch(SumpException e){
				map.put("retCode", e.getErrCode());
				map.put("retMsg", e.getErrMsg());
				return map;
			}
		} else {
			map.put("retCode", "1888");
			map.put("retMsg", "清算失败，当日资金已清算！");
			return map;
		}
		map.put("retCode", "0000");
		map.put("retMsg", "");
		return map;
	}
	/**
	 * ChinaPay 代付差错信息
	 * @param reqmap
	 * @return
	 */
	@RequestMapping(value = "/cppchkc")
	public Map<String,Object> checkInfoC(@RequestParam Map<String,Object> reqmap){
		logger.debug("===========查询差错信息开始===========");
		int length=Integer.parseInt((String)reqmap.get("length"),10);		
		int start=Integer.parseInt((String)reqmap.get("start"),10);		
		Pageable pageable=new PageRequest(start/length,length);
		TmpChinapayCltnCheck cltnCheck = new TmpChinapayCltnCheck();
		cltnCheck.setCheckDate((String)reqmap.get("trandt"));
		cltnCheck.setCheckStatus((String)reqmap.get("checkStatus"));
		Page<TmpChinapayCltnCheck> page=cltnCheckService.queryEntitiesByTemplateWithPage(cltnCheck, pageable);
	    Map<String, Object> map=PageToDataTable.convert(page);
	    logger.debug(map.toString());
		return map;
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * 代付差错处理
	 * 	取出差错信息，循环处理
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/checkpaydeal")
	public Map<String , Object> checkPayDeal(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		logger.debug("代付请求参数=============="+reqmap);
		List<HashMap<String,Object>> rows = (ArrayList<HashMap<String,Object>>)reqmap.get("data");
		int amount = 0;
		int succAmount = 0 ;
		for (HashMap<String,Object> pay : rows){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			amount+=1;
			pay.put("userid", user.getUserid());
			logger.debug(pay.get("chkStatus").toString());
			logger.debug(("3".equals(pay.get("chkStatus").toString()))+"");
			if("3".equals(pay.get("chkStatus").toString())){
				//银行少账，重新记账
				Map<String,Object> admap = new HashMap<String, Object>();
				admap.put("acctno", pay.get("acctno"));
				admap.put("toacct", pay.get("toacct"));
				admap.put("tranam", pay.get("tranam"));
				admap.put("crcycd", "01");
				admap.put("quotfs", "1");
				admap.put("userid", user.getUserid());
				Map<String,Object> rspmap = new HashMap<String, Object>();
				try{
					rspmap =  clict.callClient("inchba", admap);
				} catch(SumpException e){
					rspmap.put("retCode", e.getErrCode());
					rspmap.put("retMsg", e.getErrMsg());
				}
				if("0000".equals(rspmap.get("retCode").toString())){
					//修改记录状态
					String checkDate = pay.get("checkDate").toString();
					String merchantDt = pay.get("merchantDt").toString();
					String cpSeqno = pay.get("cpSeqno").toString();
					String checkStatus = "Y";
					payCheckService.updateStatus(checkDate, merchantDt, cpSeqno, checkStatus);
					succAmount+=1;
				}else{
				}
			
			} else if("2".equals(pay.get("chkStatus").toString())){
				//冲账
				Map<String,Object> admap = new HashMap<String, Object>();
				admap.put("yszjylsh", pay.get("transq"));
				admap.put("beizhuuu", "差错对账");
				admap.put("userid", user.getUserid());
				Map<String,Object> rspmap = new HashMap<String, Object>();
				try{
					rspmap =  clict.callClient("api111", admap);
				} catch(SumpException e){
					rspmap.put("retCode", e.getErrCode());
					rspmap.put("retMsg", e.getErrMsg());
				}
				if("0000".equals(rspmap.get("retCode"))){
					//修改记录状态
					String checkDate = pay.get("checkDate").toString();
					String merchantDt = pay.get("merchantDt").toString();
					String cpSeqno = pay.get("cpSeqno").toString();
					String checkStatus = "Y";
					payCheckService.updateStatus(checkDate, merchantDt, cpSeqno, checkStatus);
					succAmount+=1;
				}
			}
		}
		map.put("retCode", "0000");
		map.put("retMsg", "");
		map.put("amount", amount);
		map.put("succAmount", succAmount);
		return map;
	}
	/**
	 * 代扣差错处理
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/checkcltndeal")
	public Map<String , Object> checkCltnDeal(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		logger.debug("代付请求参数=============="+reqmap);
		List<HashMap<String,Object>> rows = (ArrayList<HashMap<String,Object>>)reqmap.get("data");
		int amount = 0;
		int succAmount = 0 ;
		for (HashMap<String,Object> pay : rows){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			amount+=1;
			pay.put("userid", user.getUserid());
			logger.debug(pay.get("chkStatus").toString());
			logger.debug(("3".equals(pay.get("chkStatus").toString()))+"");
			if("3".equals(pay.get("chkStatus").toString())){
				//银行少账，重新记账
				logger.debug("银行少账，重新记账"+"3".equals(pay.get("chkStatus").toString()));
				Map<String,Object> admap = new HashMap<String, Object>();
				admap.put("acctno", pay.get("acctno"));
				admap.put("toacct", pay.get("toacct"));
				admap.put("tranam", pay.get("tranam"));
				admap.put("crcycd", "01");
				admap.put("quotfs", "1");
				admap.put("userid", user.getUserid());
				Map<String,Object> rspmap = new HashMap<String, Object>();
				try{
					rspmap =  clict.callClient("inchba", admap);
				} catch(SumpException e){
					rspmap.put("retCode", e.getErrCode());
					rspmap.put("retMsg", e.getErrMsg());
				}
				logger.debug("返回码："+rspmap.get("retCode"));
				if("0000".equals(rspmap.get("retCode").toString())){
					logger.debug("处理成功，修改状态"+"0000".equals(rspmap.get("retCode")));
					//修改记录状态
					String checkDate = pay.get("checkDate").toString();
					logger.debug(checkDate+"|修改请求");
					String merchantDt = pay.get("merchantDt").toString();
					logger.debug(checkDate+"|"+merchantDt+"|修改请求");
					String cpSeqno = pay.get("billNo").toString();
					String checkStatus = "Y";
					logger.debug(checkDate+"|"+merchantDt+"|"+cpSeqno+"|修改请求");
					int r = cltnCheckService.updateStatus(checkDate, merchantDt, cpSeqno, checkStatus);
					succAmount+=1;
					logger.debug("修改状态成功"+succAmount+"修改条数"+r);
				}else{
				}
			} else if("2".equals(pay.get("chkStatus").toString())){
				//冲账
				Map<String,Object> admap = new HashMap<String, Object>();
				admap.put("yszjylsh", pay.get("transq"));
				admap.put("beizhuuu", "差错对账");
				admap.put("userid", user.getUserid());
				Map<String,Object> rspmap = new HashMap<String, Object>();
				try{
					rspmap =  clict.callClient("api111", admap);
				} catch(SumpException e){
					rspmap.put("retCode", e.getErrCode());
					rspmap.put("retMsg", e.getErrMsg());
				}
				if("0000".equals(rspmap.get("retCode"))){
					//修改记录状态
					String checkDate = pay.get("checkDate").toString();
					String merchantDt = pay.get("merchantDt").toString();
					String cpSeqno = pay.get("cpSeqno").toString();
					String checkStatus = "Y";
					cltnCheckService.updateStatus(checkDate, merchantDt, cpSeqno, checkStatus);
					succAmount+=1;
				}
			}
		}
		map.put("retCode", "0000");
		map.put("retMsg", "");
		map.put("amount", amount);
		map.put("succAmount", succAmount);
		return map;
	}
/**
	 * 查询会计记账流水信息
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qryStrike")
	public Map<String,Object> qry(@RequestParam Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		Map<String , Object> map = new HashMap<String, Object>();
		Date date = new Date();
		int start = Integer.parseInt(reqmap.get("start").toString());
		int length  = Integer.parseInt(reqmap.get("length").toString());
		map.put("startx", start);
		map.put("pgsize", length);
		map.put("cuacno", reqmap.get("ecctno").toString());
		map.put("bgindt", reqmap.get("from").toString());
		map.put("endddt", reqmap.get("to").toString());
		map.put("userid", user.getUserid());
		logger.debug("请求map========"+map);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		Map<String,Object> remap = new HashMap<String, Object>();
		try {
			rspmap = clict.callClient("sltrif", map);
		} catch (SumpException e) {
			remap.put("retCode", e.getErrCode());
			remap.put("retMsg", e.getErrMsg());
		}
		remap.put("sEcho", date.getTime());
		remap.put("iTotalRecords", rspmap.get("counts"));
		remap.put("iTotalDisplayRecords", rspmap.get("counts"));
		remap.put("data", rspmap.get("tranif"));
		return remap;
	}
	
	/**
	 * 贷款信息查询
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qracct")
	public Map<String,Object> qracct(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		logger.info("请求参数================="+reqmap);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try{
			rspmap =  clict.callClient("qracct", reqmap);
		} catch(SumpException e){
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("返回参数================="+rspmap);
		return rspmap;
	}
	
	/**
	 * 预约展期
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/preexd")
	public Map<String,Object> preexd(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		logger.info("请求参数================="+reqmap);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try{
			rspmap =  clict.callClient("preexd", reqmap);
		} catch(SumpException e){
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("返回参数================="+rspmap);
		return rspmap;
	}
	
	/**
	 * 贷款账户展期登记簿查询
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/qrexpd")
	public Map<String,Object> qrpexpd(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		logger.info("请求参数================="+reqmap);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try{
			rspmap =  clict.callClient("qrexpd", reqmap);
		} catch(SumpException e){
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
		logger.info("返回参数================="+rspmap);
		return rspmap;
	}
	
	/**
	 * 线下充值
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/seapon")
	public Map<String,Object> seapon(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		reqmap.put("userid", user.getUserid());
		reqmap.put("target", "1");
		logger.info("请求参数================="+reqmap);
		Map<String,Object> rspmap = new HashMap<String, Object>();
		try{
			rspmap =  clict.callClient("seapon", reqmap);
		} catch(SumpException e){
			rspmap.put("retCode", e.getErrCode());
			rspmap.put("retMsg", e.getErrMsg());
		}
			
		logger.info("返回参数================="+rspmap);
		return rspmap;
	}
}
