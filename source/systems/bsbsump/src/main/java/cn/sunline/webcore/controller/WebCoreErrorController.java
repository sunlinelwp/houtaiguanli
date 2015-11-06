package cn.sunline.webcore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 错误页面跳转控制
 * 
 * @author cuijia
 * 
 */
@Controller
@RequestMapping(value = "/error")
public class WebCoreErrorController {

	/**
	 * 控制页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{number}")
	public String toPage(@PathVariable("number") String number) {
		StringBuffer path = new StringBuffer();
		path.append("error/error_inner_").append(number);

		return path.toString();
	}

}
