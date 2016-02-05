package cn.sunline.comm.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.sunline.domain.endauth.BSBUser;

/**
 * <p>
 * <li>功能说明：</li>
 * <li>中台平台公用下载组建相关Controller</li>
 * <li>1、downLoadFile 下载服务器硬盘中的文件，支持所有格式</li>
 * <li>2、downLoadFileByStream </li>
 * </p>
 * 
 * @author wanggl
 *
 */
@Controller("CommDownLoadUtilsController")
@RequestMapping(value = "/rest/download")
@ResponseBody
@SessionAttributes("User")
public class CommDownLoadUtilsController {
	private static Logger logger = LoggerFactory.getLogger(CommDownLoadUtilsController.class);
	@RequestMapping("/downLoadFile")
	public ResponseEntity<byte[]> downLoadFile(@RequestParam Map<String,Object> reqmap,HttpServletResponse response,@ModelAttribute("User") BSBUser user){
		byte[] buffer = null;
		String url = null;
		try {
			//解决文件名中文乱码问题
			url = new String((reqmap.get("path").toString()).getBytes("iso-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		String urlType = url.substring((url.lastIndexOf(".")+1),url.length());
		logger.debug("====="+url.substring((url.lastIndexOf(".")+1),url.length()));
		HttpHeaders headers = new HttpHeaders();  
	   String fileName = url.substring((url.lastIndexOf(File.separator)+1),url.length());
	   logger.debug("fileName ======="+fileName);
		// 设置每种文件类型浏览器传过来的头
		if ("txt".equals(urlType) || urlType == "txt") {
			 headers.setContentType(MediaType.TEXT_PLAIN);  
		} else if ("pdf".equals(urlType) || urlType == "pdf") {
			 headers.setContentType(MediaType.ALL); 
		} else if ("jpg".equals(urlType) || urlType == "jpg") {
			headers.setContentType(MediaType.IMAGE_JPEG);
		}else if ("doc".equals(urlType) || urlType == "docx") {
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		} else if("gif".equals(urlType) || urlType == "gif") {
			headers.setContentType(MediaType.IMAGE_GIF);
		}else if("png".equals(urlType) || urlType == "png") {
			headers.setContentType(MediaType.IMAGE_PNG);
		}else{
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		}
		//读取本地文件
		try{
			buffer = FileUtils2.getContent(url);
			logger.debug("=======1111"+buffer.length);
		}catch (IOException e){
			e.printStackTrace();
		}
		
		
		try {
			headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(buffer, headers, HttpStatus.CREATED);
	}
}
