package cn.sunline.fc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import cn.sunline.domain.endauth.BSBUser;
@Controller("FcClearController")
@RequestMapping(value = "/rest/fc")
@ResponseBody
@SessionAttributes("User")
public class FcClearController {

	@Autowired
	private ClientImpl client;
	private static Logger logger = LoggerFactory.getLogger(FcClearController.class);
	
	@RequestMapping(value = "/findpay")
	public Map<String,Object> clearpay(@RequestParam Map<String,Object> reqmap,@ModelAttribute("User") BSBUser user){
		logger.debug("===========��ѯ�����Ϣ��ʼ===========");
		Map<String,Object> rspmap=new HashMap<String, Object>();
		
		reqmap.put("userid", user.getUserid());
		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		reqmap.put("startx", start / length + 1);
		reqmap.put("pagect", length);
		rspmap = client.callClient("qrcler", reqmap);
		rspmap.put("data",rspmap.get("clerif") == null ? "[]" : rspmap.get("clerif"));
		rspmap.put("iTotalDisplayRecords", rspmap.get("counts") == null ?  0 : rspmap.get("counts"));
		rspmap.put("iTotalRecords", rspmap.get("counts") == null ?  0 : rspmap.get("counts"));
	    logger.debug(rspmap.toString());
		return rspmap;
	}
	
	/**
	 * ��ѯ���ʲ�Ʒ��������
	 * 
	 * @param reqmap
	 *            ��ѯ��������
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/fcqpro")
	public Map<String, Object> selfcpro(@RequestParam Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------��ѯ���ʲ�Ʒ�������Կ�ʼ-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		}
		if (reqmap.get("q_prodna") != null && reqmap.get("q_prodna") != "") {
			map.put("prodna", reqmap.get("q_prodna"));
		}
		
		map.put("userid", user.getUserid()); // ���ò�����Ա

		int length = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("length")) ? (String) reqmap.get("length") : "10", 10);
		int start = Integer.parseInt(StringUtils.isNotEmpty((String) reqmap
				.get("start")) ? (String) reqmap.get("start") : "1", 10);
		map.put("pageno", start / length + 1);// ��ǰҳ��
		map.put("pagesz", length); // ÿҳ��¼��
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("fcqpro", map);
		resmap.put(
				"data",
				resmap.get("proInfos") == null ? new ArrayList<Object>() : resmap
						.get("proInfos"));
		resmap.put("iTotalDisplayRecords", resmap.get("count") == null ? "0"
				: resmap.get("count"));
		resmap.put("iTotalRecords",
				resmap.get("count") == null ? "0" : resmap.get("count"));
		logger.debug("-----------------��ѯ���ʲ�Ʒ�������Խ���-----------------");
		return resmap;
	}
	
	/**
	 *���ʴ����Ʒ����ɾ��
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/fcqpro", method = { RequestMethod.DELETE })
	public Map<String, Object> deprod(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("-----------------ɾ�����ʲ�Ʒ�������Կ�ʼ-----------------");
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = client.callClient("fcdpro", req); //
		logger.debug("-----------------ɾ�����ʲ�Ʒ�������Խ���-----------------");
		return resmap;
	}
	
	/**
	 * ��ѯ�����Ʒ��������(����)
	 * 
	 * @param reqmap
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/fcqrpr")
	public Map<String, Object> qrprsl(@RequestBody Map<String, Object> reqmap,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("------------------������ѯ��Ʒ�������Խӿڿ�ʼ-----------------");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resmap = new HashMap<String, Object>();
		if (reqmap.get("q_prodcd") != null && reqmap.get("q_prodcd") != "") {
			map.put("prodcd", reqmap.get("q_prodcd"));
		} else {
			resmap.put("ret", "error");
			resmap.put("msg", "��Ʒ���벻��Ϊ��");
			return resmap;
		}
		map.put("userid", user.getUserid());
		map.put("pageno", 1);
		map.put("pagesz", 10);
		resmap = client.callClient("fcqpro", map);
		logger.debug("------------------������ѯ��Ʒ�������Խӿڽ���-----------------");
		return resmap;
	}
	
	/**
	 * �����޸Ĵ����Ʒ��������
	 * 
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/fcuipr")
	public Map<String, Object> fcuipr(@RequestBody Map<String, Object> req,
			@ModelAttribute("User") BSBUser user) {
		logger.debug("--------------------�����޸����ʲ�Ʒ�������Կ�ʼ------------------------"
				+ req.toString());
		req.put("userid", user.getUserid());
		Map<String, Object> resmap = client.callClient("fcuipr", req);
		logger.debug("--------------------�����޸����ʲ�Ʒ�������Խ���------------------------");
		return resmap;
	}
}
