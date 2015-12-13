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
		String keepdt = reqmap.get("trandt").toString();
		String cometp = reqmap.get("cometp").toString();
		boolean flag = tmpYqrxAmouService.checkIsExitByKeepdt(keepdt,cometp);
		if(flag){
			map.put("retCode", "0099");
			map.put("retMsg", keepdt+"已回款");
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
		tya.setKeepdt(reqmap.get("trandt").toString());
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
		reqmap.put("userid", user.getUserid());
		Map<String, Object> map = clict.callClient("senaou", reqmap);
		if(map.get("retCode")=="0000"){
			int i = tmpYqrxAmouService.updateStates(reqmap.get("frondt").toString(), reqmap.get("fronsq").toString(), "1");
		}
		logger.debug("-------------------------有钱任信 出金结束-------------------------");
		return map;
	}
}
