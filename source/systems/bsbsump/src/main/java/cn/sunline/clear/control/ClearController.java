package cn.sunline.clear.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

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
import cn.sunline.setlle.controller.SetlleController;
import cn.sunline.tmp.allinpay.check.TmpAllinPayCltnCheckDataWriterImpl;
import cn.sunline.tmp.allinpay.check.TmpAllinPayCltnCheckService;
import cn.sunline.tmp.allinpay.check.TmpAllinPayPayCheckDataWriterImpl;
import cn.sunline.tmp.allinpay.check.TmpAllinPayPayCheckService;
import cn.sunline.tmp.allinpay.check.TmpAllinPayCltnCheck;
import cn.sunline.tmp.allinpay.check.TmpAllinPayCltnHeadCheck;
import cn.sunline.tmp.allinpay.check.TmpAllinPayPayCheck;
import cn.sunline.tmp.allinpay.check.TmpAllinPayPayHeadCheck;
import cn.sunline.tmp.allinpay.gate.check.TmpAinPayPayGateCheck;
import cn.sunline.tmp.allinpay.gate.check.TmpAinPayPayGateCheckDataWriterImpl;
import cn.sunline.tmp.allinpay.gate.check.TmpAinPayPayGateCheckService;
import cn.sunline.tmp.allinpay.gate.check.TmpAinPayPayGateHeadCheck;
import cn.sunline.utils.PageToDataTable;

@Controller("ClearController")
@RequestMapping(value = "/rest/clear")
@ResponseBody
@SessionAttributes("User")
public class ClearController {
	//认证支付
	@Resource
	private TmpAllinPayPayCheckDataWriterImpl tmpAllinPayPayCheckDataWriter;
	//认证支付
	@Resource
	private TmpAllinPayCltnCheckDataWriterImpl tmpAllinPayCltnCheckDataWriter;
	//认证支付
	@Autowired
	private TmpAllinPayPayCheckService payCheckService;
	//认证支付
	@Autowired
	private TmpAllinPayCltnCheckService cltnCheckService;
	//网关支付
	@Resource
	private TmpAinPayPayGateCheckDataWriterImpl tmpAinPayPayGateCheckDataWriter;
	//网关支付
	@Autowired
	private TmpAinPayPayGateCheckService payGateCheckService;
	
	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory.getLogger(SetlleController.class);

	
//===================================通联网关支付差错处理开始==============================================================================
	/**
	 * 通联网关支付---代付差错信息
	 */
	@RequestMapping(value = "/gcheck")
	public Map<String,Object> gcheck(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		String checkDate = reqmap.get("trandt").toString();
		if(payGateCheckService.checkIsExitByCheckDate(checkDate)){
			TmpAinPayPayGateHeadCheck head = payGateCheckService.getPayHeadCheckInfo((String)reqmap.get("trandt"));
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
			clict.readFile(reqmap.get("file").toString(), tmpAinPayPayGateCheckDataWriter,
					reqmap.get("trandt").toString());
		} catch (SumpException e) {
			map.put("retCode", e.getErrCode());
			map.put("retMsg", e.getErrMsg());
			return map;
		}
		//查询头信息
		TmpAinPayPayGateHeadCheck head = payGateCheckService.getPayHeadCheckInfo((String)reqmap.get("trandt"));
		map.put("retCode", "0000");
		map.put("retMsg", "");
		map.put("fee", head.getFee());
		map.put("amount", head.getAmount());
		map.put("status", head.getCheckStatus());
		return map;
	}
	
	/**
	 * 通联网关支付-- 查询代付差错信息，送到列表中
	 * @param reqmap
	 * @return
	 */
	@RequestMapping(value = "/gtpchk")
	public Map<String,Object> gatePayInfo(@RequestParam Map<String,Object> reqmap){
		logger.debug("===========查询差错信息开始===========");
		int length=Integer.parseInt((String)reqmap.get("length"),10);		
		int start=Integer.parseInt((String)reqmap.get("start"),10);		
		Pageable pageable=new PageRequest(start/length,length);
		TmpAinPayPayGateCheck payCheck = new TmpAinPayPayGateCheck();
		payCheck.setCheckDate((String)reqmap.get("trandt"));
		payCheck.setCheckStatus((String)reqmap.get("checkStatus"));
		Page<TmpAinPayPayGateCheck> page=payGateCheckService.queryEntitiesByTemplateWithPage(payCheck,pageable);	  
	    Map<String, Object> map=PageToDataTable.convert(page);
	    logger.debug(map.toString());
		return map;
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * 通联网关支付--代付差错处理
	 * 	取出差错信息，循环处理
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/checkpaygatedeal")
	public Map<String , Object> checkPayGateDeal(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user) {
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
				admap.put("acctno", pay.get("toacct"));
				admap.put("toacct", pay.get("acctno"));
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
					String cpSeqno = pay.get("billno").toString();
					String checkStatus = "Y";
					payGateCheckService.updateStatus(checkDate, merchantDt, cpSeqno, checkStatus);
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
					String cpSeqno = pay.get("billno").toString();
					String checkStatus = "Y";
					payGateCheckService.updateStatus(checkDate, merchantDt, cpSeqno, checkStatus);
					succAmount+=1;
				}
			} else if("5".equals(pay.get("chkStatus").toString())){
				//我方少订单，需要工作人员自己手工去录订单
				//修改记录状态
				String checkDate = pay.get("checkDate").toString();
				String merchantDt = pay.get("merchantDt").toString();
				String cpSeqno = pay.get("billno").toString();
				String checkStatus = "Y";
				payCheckService.updateStatus(checkDate, merchantDt, cpSeqno, checkStatus);
				succAmount+=1;
			}
		}
		map.put("retCode", "0000");
		map.put("retMsg", "");
		map.put("amount", amount);
		map.put("succAmount", succAmount);
		return map;
	}
	
//===================================通联网关支付差错处理结束==============================================================================

	
//===================================通联认证支付差错处理开始==============================================================================
	/**
	 * 通联认证支付  
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/check")
	public Map<String,Object> check(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		String checkDate = reqmap.get("trandt").toString();
		if(payCheckService.checkIsExitByCheckDate(checkDate)){
			TmpAllinPayPayHeadCheck head = payCheckService.getPayHeadCheckInfo((String)reqmap.get("trandt"));
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
			clict.readFile(reqmap.get("file").toString(), tmpAllinPayPayCheckDataWriter,
					reqmap.get("trandt").toString());
		} catch (SumpException e) {
			map.put("retCode", e.getErrCode());
			map.put("retMsg", e.getErrMsg());
			return map;
		}
		//查询头信息
		TmpAllinPayPayHeadCheck head = payCheckService.getPayHeadCheckInfo((String)reqmap.get("trandt"));
		map.put("retCode", "0000");
		map.put("retMsg", "");
		map.put("fee", head.getFee());
		map.put("amount", head.getAmount());
		map.put("status", head.getCheckStatus());
		return map;
	}	
	
	
	/**
	 * 通联 代付差错信息
	 * @param reqmap
	 * @return
	 */
	@RequestMapping(value = "/cppchk")
	public Map<String,Object> checkInfo(@RequestParam Map<String,Object> reqmap){
		logger.debug("===========查询差错信息开始===========");
		int length=Integer.parseInt((String)reqmap.get("length"),10);		
		int start=Integer.parseInt((String)reqmap.get("start"),10);		
		Pageable pageable=new PageRequest(start/length,length);
		TmpAllinPayPayCheck payCheck = new TmpAllinPayPayCheck();
		payCheck.setCheckDate((String)reqmap.get("trandt"));
		payCheck.setCheckStatus((String)reqmap.get("checkStatus"));
		Page<TmpAllinPayPayCheck> page=payCheckService.queryEntitiesByTemplateWithPage(payCheck,pageable);	  
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
			TmpAllinPayCltnHeadCheck head = cltnCheckService.getCltnHeadCheckInfo((String)reqmap.get("trandt"));
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
		clict.readFile(reqmap.get("file").toString(), tmpAllinPayCltnCheckDataWriter,
					reqmap.get("trandt").toString());
		}catch (SumpException e) {
			e.printStackTrace();
			map.put("retCode", "1111");
			map.put("retMsg", e.getErrMsg());
			return map;
		}
		//查询头信息
		TmpAllinPayCltnHeadCheck head = cltnCheckService.getCltnHeadCheckInfo((String)reqmap.get("trandt"));
		map.put("retCode", "0000");
		map.put("retMsg", "");
		map.put("fee", head.getFee());
		map.put("amount", head.getAmount());
		map.put("status", head.getCheckStatus());
		return map;
	}
	
	/**
	 * 通联 代付差错信息
	 * @param reqmap
	 * @return
	 */
	@RequestMapping(value = "/cppchkc")
	public Map<String,Object> checkInfoC(@RequestParam Map<String,Object> reqmap){
		logger.debug("===========查询差错信息开始===========");
		int length=Integer.parseInt((String)reqmap.get("length"),10);		
		int start=Integer.parseInt((String)reqmap.get("start"),10);		
		Pageable pageable=new PageRequest(start/length,length);
		TmpAllinPayCltnCheck cltnCheck = new TmpAllinPayCltnCheck();
		cltnCheck.setCheckDate(reqmap.get("trandt").toString());
		cltnCheck.setCheckStatus(reqmap.get("checkStatus").toString());
		Page<TmpAllinPayCltnCheck> page=cltnCheckService.queryEntitiesByTemplateWithPage(cltnCheck, pageable);
		logger.debug(page.getSize()+"------------------------");
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
					String cpSeqno = pay.get("billno").toString();
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
					String cpSeqno = pay.get("billno").toString();
					String checkStatus = "Y";
					payCheckService.updateStatus(checkDate, merchantDt, cpSeqno, checkStatus);
					succAmount+=1;
				}
			} else if("5".equals(pay.get("chkStatus").toString())){
				//我方少订单，需要工作人员自己手工去录订单
				//修改记录状态
				String checkDate = pay.get("checkDate").toString();
				String merchantDt = pay.get("merchantDt").toString();
				String cpSeqno = pay.get("billno").toString();
				String checkStatus = "Y";
				payCheckService.updateStatus(checkDate, merchantDt, cpSeqno, checkStatus);
				succAmount+=1;
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
	@Transactional
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
				admap.put("frondt", pay.get("merchantDt"));
				admap.put("fronsq",pay.get("billNo"));
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
					String cpSeqno = pay.get("billno").toString();
					String checkStatus = "Y";
					cltnCheckService.updateStatus(checkDate, merchantDt, cpSeqno, checkStatus);
					succAmount+=1;
				}
			} else if("5".equals(pay.get("chkStatus").toString())){
				//我方少订单，需要工作人员自己手工去录订单
				//修改记录状态
				String checkDate = pay.get("checkDate").toString();
				String merchantDt = pay.get("merchantDt").toString();
				String cpSeqno = pay.get("billno").toString();
				String checkStatus = "Y";
				payCheckService.updateStatus(checkDate, merchantDt, cpSeqno, checkStatus);
				succAmount+=1;
			}
		}
		map.put("retCode", "0000");
		map.put("retMsg", "");
		map.put("amount", amount);
		map.put("succAmount", succAmount);
		return map;
	}
	
	
}
