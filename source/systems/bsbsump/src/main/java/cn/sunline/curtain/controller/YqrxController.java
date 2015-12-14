package cn.sunline.curtain.controller;

import java.util.HashMap;
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
import cn.sunline.tmp.yqrx.check.TmpYqrxAmou;
import cn.sunline.tmp.yqrx.check.TmpYqrxAmouDataWriterImpl;
import cn.sunline.tmp.yqrx.check.TmpYqrxAmouPK;
import cn.sunline.tmp.yqrx.check.TmpYqrxAmouService;
import cn.sunline.utils.PageToDataTable;

@Controller("YqrxController")
@RequestMapping(value = "/rest/yqrx")
@ResponseBody
@SessionAttributes("User")
public class YqrxController {
	
	@Autowired
	ClientImpl clict;

	@Autowired
	private TmpYqrxAmouService tmpYqrxAmouService;
	
	@Resource
	private TmpYqrxAmouDataWriterImpl tmpYqrxAmouDataWriterImpl;
	
	private static Logger logger = LoggerFactory.getLogger(YqrxController.class);
	
	
	/**
	 * <p>有钱任信 回款/失败交易 文件导入</p>
	 * @param reqmap
	 * @param user
	 * <p>create by kangyu AT TIME 2015年12月10日 下午10:25:01</p>
	 */
	@RequestMapping(value = "/readFile")
	public Map<String,Object> readFile(@RequestBody Map<String,Object> reqmap , @ModelAttribute("User") BSBUser user){
		logger.debug("-------------------------有钱任信 回款/失败交易 文件导入开始-------------------------"+reqmap.get("trandt").toString());
		Map<String, Object> map = new HashMap<String, Object>();
		//获得清算日期
		String amoudt = reqmap.get("trandt").toString();
		String cometp = reqmap.get("cometp").toString();
		boolean flag = tmpYqrxAmouService.checkIsExitByAmoudt(amoudt,cometp);
		if(flag){
			map.put("retCode", "0099");
			map.put("retMsg", reqmap.get("file").toString()+amoudt+"文件已导入");
			map.put("states", "1");
			return map;
		}
		try {
		clict.readFile(reqmap.get("file").toString(), tmpYqrxAmouDataWriterImpl,
					reqmap.get("trandt").toString());
		}catch (SumpException e) {
			e.printStackTrace();
			map.put("retCode", "1111");
			map.put("retMsg", "导入差错信息异常，请确认文件是否存在");
			return map;
		}

		map.put("retCode", "0000");
		map.put("retMsg", "");
		logger.debug("-------------------------有钱任信 回款/失败交易 文件导入结束-------------------------");
		return map;
	}
	/**
	 * 有钱任信 出金列表查询
	 * @param reqmap
	 * @return
	 */
	@RequestMapping(value = "/selectPage")
	public Map<String,Object> selectPage(@RequestParam Map<String,Object> reqmap){
		logger.debug("-------------------------有钱任信 回款列表查询开始-------------------------");
		int length=Integer.parseInt((String)reqmap.get("length"),10);		
		int start=Integer.parseInt((String)reqmap.get("start"),10);		
		Pageable pageable=new PageRequest(start/length,length);
		TmpYqrxAmou tya = new TmpYqrxAmou();
		tya.setAmoudt(reqmap.get("trandt").toString());
		tya.setCometp(reqmap.get("cometp").toString());
		tya.setStates(reqmap.get("states").toString());

		Page<TmpYqrxAmou> page=tmpYqrxAmouService.queryEntitiesByTemplateWithPage(tya, pageable);
	    Map<String, Object> map=PageToDataTable.convert(page);
	    logger.debug(map.toString());
		logger.debug("-------------------------有钱任信 回款列表查询结束-------------------------");
		return map;
	}
	
	/**
	 * 有钱任信 出金
	 * @param reqmap
	 * @return
	 */
	@RequestMapping(value = "/senaou")
	public Map<String,Object> senaou(@RequestBody Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.debug("-------------------------有钱任信 出金开始-------------------------");
		TmpYqrxAmouPK tyaPK = new TmpYqrxAmouPK();
		tyaPK.setAmouid(reqmap.get("amouid").toString());
		TmpYqrxAmou tya = tmpYqrxAmouService.queryOneEntities(tyaPK);
		//根据电子账号查询行号和行名称
		reqmap.put("custac", tya.getAcctno());
		Map<String, Object> mapcard = clict.callClient("secaac", reqmap);
		reqmap.put("banknm", mapcard.get("brchna"));
		reqmap.put("ftbkcd", mapcard.get("brchno"));
		
		reqmap.put("userid", user.getUserid());
		reqmap.put("acctno", tya.getAcctno());
		reqmap.put("payeac", tya.getPayeac());
		reqmap.put("payena", tya.getPayena());
		reqmap.put("tranam", tya.getTranam());
		reqmap.put("crcycd", tya.getCrcycd());
		reqmap.put("chgeam", tya.getChgeam());
		reqmap.put("pswdtp", tya.getPswdtp());
		reqmap.put("pwflag", tya.getPwflag());
		reqmap.put("tranpw", tya.getTranpw());
		reqmap.put("remark", tya.getRemark());
//		reqmap.put("banknm", tya.getBanknm());
		reqmap.put("provic", tya.getProvic());
		reqmap.put("garden", tya.getGarden());
//		reqmap.put("ftbkcd", tya.getFtbkcd());
		reqmap.put("acctpr", tya.getAcctpr());
		reqmap.put("chnlcd", tya.getChnlcd());
		reqmap.put("pytype", tya.getPytype());
		reqmap.put("target", "1");//前置拦截
		logger.debug(reqmap+"----------123");
		Map<String, Object> map = clict.callClient("hfaxtx", reqmap);
		if(map.get("retCode")=="0000"){
			int i = tmpYqrxAmouService.updateStates(reqmap.get("amouid").toString(), "1");
		}
		logger.debug("-------------------------有钱任信 出金结束-------------------------");
		return map;
	}
}
