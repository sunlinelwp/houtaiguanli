package cn.sunline.report.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.sunline.exception.SumpException;

@SuppressWarnings("deprecation")
@Controller("ReportController")
@RequestMapping(value = "/rest/repo")
@ResponseBody
@SessionAttributes("User")
public class ReportController {

	private static Logger logger = LoggerFactory
			.getLogger(ReportController.class);
	private static Properties config = null;

	// 创建HttpClient实例
	private static HttpClient httpclient = new DefaultHttpClient();
	static {
		InputStream in = ReportController.class
				.getResourceAsStream("/report.properties");
		config = new Properties();
		try {
			config.load(in);
			in.close();
		} catch (IOException e) {
			throw new SumpException("获取系统配置文件失败");
		}
	}

	/**
	 * 报表查看公共方法
	 * 
	 * @param reqmap
	 *            必须传入rpt_type=xxx ,xxx为报表编号
	 * @return
	 */
	@RequestMapping(value = "/qryrpt")
	public Map<String, Object> qryrpt(@RequestBody Map<String, String> reqmap) {
		logger.debug("qryrpt------------------报表查询" + reqmap);
		String rpt_code = reqmap.get("rpt_code");
		StringBuffer rpt_url = new StringBuffer(
				config.getProperty("rpt_baseurl") + rpt_code
						+ ".html?temp=temp");
		Set<String> keys = reqmap.keySet();
		Iterator<String> keysIt = keys.iterator();
		while (keysIt.hasNext()) {
			String key = keysIt.next();
			if (!"rpt_code".equals(key)) {
				rpt_url.append("&" + key + "=" + reqmap.get(key));
			}

		}
		if (reportLogin()) {

			logger.debug("qryrpt------------------报表查询url" + rpt_url);
			Map<String,Object> map = new HashMap<String,Object>();
			map = httpGet(rpt_url.toString());
			String content = map.get("html").toString();
			logger.debug(content);
			if(content.contains("CannotCreateTransactionException")){
				map.put("html", "");
			}
			return map;
		} else {
			throw new SumpException("报表系统登陆异常");
		}
	}

	/**
	 * 报表下载公共方法
	 * 
	 * @param map
	 *            报表所需参数
	 *            其中必须包含rpt_code=xxx&rpt_type=xxx,rpt_code为报表编号，如rptFundBuy
	 *            ,rpt_code为下载格式，如docx 参数如TRANDT=20150701
	 * @param response
	 * @return
	 */
	@RequestMapping("/downloadReport")
	public void downloadReport(@RequestParam Map<String, String> map,
			HttpServletResponse response) {
		String rpt_code = map.get("rpt_code");
		String rpt_type = map.get("rpt_type");
		if (rpt_code == null || "".equals(rpt_code.trim()) || rpt_type == null
				|| "".equals(rpt_type.trim())) {
			throw new SumpException("报表下载参数异常");
		}
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ rpt_code + "." + rpt_type);
		StringBuffer url = new StringBuffer(config.getProperty("rpt_baseurl"));
		Set<String> keys = map.keySet();
		Iterator<String> keysIt = keys.iterator();
		url.append(rpt_code + "." + rpt_type + "?temp=temp");
		while (keysIt.hasNext()) {
			String key = keysIt.next();
			if (!"rpt_code".equals(key) && !"rpt_type".equals(key)) {
				url.append("&" + key + "=" + map.get(key));
			}
		}
		if (reportLogin()) {
			HttpGet get = new HttpGet(url.toString());
			try {
				InputStream inputStream = httpclient.execute(get).getEntity()
						.getContent();
				OutputStream os = response.getOutputStream();
				byte[] b = new byte[1024];
				int length;
				while ((length = inputStream.read(b)) > 0) {
					os.write(b, 0, length);
				}
				inputStream.close();
			} catch (Exception e) {
				get.abort();
				throw new SumpException("报表系统下载异常");
			}
			get.abort();
		} else {
			throw new SumpException("报表系统登陆异常");
		}
	}

	/**
	 * http GET请求
	 * 
	 * @param url
	 * @return
	 */
	private Map<String, Object> httpGet(String url) {
		HttpGet httpgets = new HttpGet(url);// 创建Get方法实例
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			synchronized (httpgets) {
				HttpResponse response = httpclient.execute(httpgets);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instreams = entity.getContent();
					String str = convertStreamToString(instreams);
					httpgets.abort();
					res.put("html", str);
					return res;
				}
			}
		} catch (Exception e) {
			httpgets.abort();
			res.put("html", "<h2>获取报表异常</h2>");
		}
		httpgets.abort();
		return res;
	}

	/**
	 * 报表平台自动登录
	 * 
	 * @return
	 */
	private boolean reportLogin() {
		String loginurl = config.getProperty("login_url");
		HttpGet httpgetslogin = new HttpGet(loginurl);
		try {
			synchronized (httpgetslogin) {
				HttpResponse loginrsp = httpclient.execute(httpgetslogin);
				if (loginrsp.getStatusLine().getStatusCode() == 200) {
					httpgetslogin.abort();
					return true;
				}
			}

		} catch (IOException e) {
			httpgetslogin.abort();
			return false;
		}
		httpgetslogin.abort();
		return false;

	}

	/**
	 * 输入流转换
	 * 
	 * @param is
	 * @return
	 */
	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
}
