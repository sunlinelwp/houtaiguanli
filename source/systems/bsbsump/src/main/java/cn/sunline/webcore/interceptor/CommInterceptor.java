package cn.sunline.webcore.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.sunline.domain.endauth.BSBUser;

public class CommInterceptor implements HandlerInterceptor{
	private static Logger logger = LoggerFactory
			.getLogger(CommInterceptor.class);
	public CommInterceptor() {  
    }  
  
    private String [] mappingURL;//利用数组设置不拦截的路径    
    
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws IOException {
		 HttpSession session = request.getSession(true);  	    	   	 
         // 从session 里面获取用户名的信息  
         BSBUser user = (BSBUser)session.getAttribute("User"); 
     	
         String url=request.getRequestURL().toString().replace(request.getContextPath(), ""); 	        
	        if(null!=mappingURL&&mappingURL.length>=1){    		 
	    		 for (String strURL : mappingURL) {
					if(url.contains(strURL)){
                    return true;
					}
				}	 
	    	 }	
	         // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆  
	         if (user != null && StringUtils.isNotEmpty(user.getUserid())) { 	
	        	 logger.info("==============session用户================"+user.toString());
	        	 return true;  	        	 
	         }  else{
	        	 logger.info("==============未登录登陆跳转================：");
					response.sendRedirect("../auth/login");
                 return false;
	         }
	          
	    
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	public String[] getMappingURL() {
		return mappingURL;
	}

	public void setMappingURL(String[] mappingURL) {
		this.mappingURL = mappingURL;
	}

	

}
