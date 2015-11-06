package cn.sunline.liquidation.controller;

import java.util.HashMap;

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
import cn.sunline.tmp.settle.liquidation.TmpLiquidationErrCheck;
import cn.sunline.tmp.settle.liquidation.TmpLiquidationErrCheckDataWriterImpl;
import cn.sunline.tmp.settle.liquidation.TmpLiquidationErrCheckService;
import cn.sunline.tmp.settle.liquidation.TmpLiquidationErrHeadCheck;
import cn.sunline.tmp.settle.liquidation.TmpLiquidationSubCheck;
import cn.sunline.tmp.settle.liquidation.TmpLiquidationSubCheckDataWriterImpl;
import cn.sunline.tmp.settle.liquidation.TmpLiquidationSubCheckService;
import cn.sunline.tmp.settle.liquidation.TmpLiquidationSubHeadCheck;
import cn.sunline.tmp.settle.liquidation.TmpLiquidationTalCheck;
import cn.sunline.tmp.settle.liquidation.TmpLiquidationTalCheckDataWriterImpl;
import cn.sunline.tmp.settle.liquidation.TmpLiquidationTalCheckService;
import cn.sunline.tmp.settle.liquidation.TmpLiquidationTalHeadCheck;
import cn.sunline.utils.PageToDataTable;

@Controller("LiquidaController")
@RequestMapping(value = "/rest/liquidation")
@ResponseBody
@SessionAttributes("User")
public class LiquidaController {

	@Resource
	private TmpLiquidationErrCheckDataWriterImpl tmpLiquidationErrCheckService;

	@Autowired
	private TmpLiquidationErrCheckService errCheckService;

	@Resource
	private TmpLiquidationSubCheckDataWriterImpl tmpLiquidationSubCheckService;

	@Autowired
	private TmpLiquidationSubCheckService subCheckService;

	@Resource
	private TmpLiquidationTalCheckDataWriterImpl tmpLiquidationTalCheckService;

	@Autowired
	private TmpLiquidationTalCheckService talCheckService;

	@Autowired
	ClientImpl clict;
	private static Logger logger = LoggerFactory
			.getLogger(LiquidaController.class);

	/**
	 * 清算差错头信息
	 * 
	 * @param reqmap
	 * @return
	 */
	@RequestMapping(value = "/check")
	public Map<String, Object> check(@RequestBody Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		String checkDate = reqmap.get("trandt").toString();
		if (errCheckService.checkIsExitByCheckDate(checkDate)) {
			errCheckService.deleteByCheckDate(checkDate);
			errCheckService.deleteByHeadCheckDate(checkDate);
		}

		try {
			clict.readFile(reqmap.get("file").toString(),
					tmpLiquidationErrCheckService, reqmap.get("trandt")
							.toString());
		} catch (SumpException e) {
			map.put("retCode", e.getErrCode());
			map.put("retMsg", e.getErrMsg());
			return map;
		}
		// 查询头信息
		TmpLiquidationErrHeadCheck head = errCheckService
				.getPayHeadCheckInfo((String) reqmap.get("trandt"));
		map.put("retCode", "0000");
		map.put("retMsg", "");
		map.put("totalDebit", head.getTotalDebit());
		map.put("totalCredit", head.getTotalCredit());
		map.put("operationFlow", head.getOperationFlow());
		return map;
	}

	/**
	 * 清算差错信息
	 * 
	 * @param reqmap
	 * @return
	 */
	@RequestMapping(value = "/cppchk")
	public Map<String, Object> checkInfo(
			@RequestParam Map<String, Object> reqmap) {
		logger.debug("===========查询差错信息开始===========");
		int length = Integer.parseInt((String) reqmap.get("length"), 10);
		int start = Integer.parseInt((String) reqmap.get("start"), 10);
		Pageable pageable = new PageRequest(start / length, length);
		TmpLiquidationErrCheck payCheck = new TmpLiquidationErrCheck();
		payCheck.setCheckDate((String) reqmap.get("trandt"));
		Page<TmpLiquidationErrCheck> page = errCheckService
				.queryEntitiesByTemplateWithPage(payCheck, pageable);
		Map<String, Object> map = PageToDataTable.convert(page);
		logger.debug(map.toString());
		return map;
	}

	@RequestMapping(value = "/sub")
	@Transactional
	public Map<String, Object> sub(@RequestBody Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		String checkDate = reqmap.get("trandt").toString();
		if (subCheckService.checkIsExitByCheckDate(checkDate)) {
			subCheckService.deleteByCheckDate(checkDate);
			subCheckService.deleteByHeadCheckDate(checkDate);
		}

		try {
			clict.readFile(reqmap.get("file").toString(),
					tmpLiquidationSubCheckService, reqmap.get("trandt")
							.toString());
		} catch (SumpException e) {
			map.put("retCode", e.getErrCode());
			map.put("retMsg", e.getErrMsg());
			return map;
		}

		TmpLiquidationSubHeadCheck head = subCheckService
				.getPayHeadCheckInfo((String) reqmap.get("trandt"));
		map.put("retCode", "0000");
		map.put("retMsg", "");
		map.put("operationFlow", head.getOperationFlow());
		map.put("getSuccessfulIdentification",
				head.getSuccessfulIdentification());
		return map;
	}

	@RequestMapping(value = "/subchk")
	public Map<String, Object> subInfo(@RequestParam Map<String, Object> reqmap) {
		logger.debug("===========查询差错信息开始===========");
		int length = Integer.parseInt((String) reqmap.get("length"), 10);
		int start = Integer.parseInt((String) reqmap.get("start"), 10);
		Pageable pageable = new PageRequest(start / length, length);
		TmpLiquidationSubCheck payCheck = new TmpLiquidationSubCheck();
		payCheck.setCheckDate((String) reqmap.get("trandt"));
		Page<TmpLiquidationSubCheck> page = subCheckService
				.queryEntitiesByTemplateWithPage(payCheck, pageable);
		Map<String, Object> map = PageToDataTable.convert(page);
		logger.debug(map.toString());
		return map;
	}

	@RequestMapping(value = "/tal")
	@Transactional
	public Map<String, Object> tal(@RequestBody Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		String checkDate = reqmap.get("trandt").toString();
		if (talCheckService.checkIsExitByCheckDate(checkDate)) {
			talCheckService.deleteByCheckDate(checkDate);
			talCheckService.deleteByHeadCheckDate(checkDate);
		}

		try {
			clict.readFile(reqmap.get("file").toString(),
					tmpLiquidationTalCheckService, reqmap.get("trandt")
							.toString());
		} catch (SumpException e) {
			map.put("retCode", e.getErrCode());
			map.put("retMsg", e.getErrMsg());
			return map;
		}
		TmpLiquidationTalHeadCheck head = talCheckService
				.getPayHeadCheckInfo((String) reqmap.get("trandt"));
		map.put("retCode", "0000");
		map.put("retMsg", "");
		map.put("totalDebit", head.getOperationFlow());
		return map;
	}

	@RequestMapping(value = "/talchk")
	public Map<String, Object> talInfo(@RequestParam Map<String, Object> reqmap) {
		logger.debug("===========查询差错信息开始===========");
		int length = Integer.parseInt((String) reqmap.get("length"), 10);
		int start = Integer.parseInt((String) reqmap.get("start"), 10);
		Pageable pageable = new PageRequest(start / length, length);
		TmpLiquidationTalCheck payCheck = new TmpLiquidationTalCheck();
		payCheck.setCheckDate((String) reqmap.get("trandt"));
		Page<TmpLiquidationTalCheck> page = talCheckService
				.queryEntitiesByTemplateWithPage(payCheck, pageable);
		Map<String, Object> map = PageToDataTable.convert(page);
		logger.debug(map.toString());
		return map;
	}
}
