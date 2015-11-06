package cn.sunline.enddemo.logback;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试目的：logger的有效级别与请求级别关系，请求级别大于等于有效级别，才会打印
 * 展示logger的日志级别优先级：TRACE < DEBUG < INFO < WARN < ERROR
 * 
 * @author xiaoxu
 */

public class LoggerLevelTest {
	//定义一个记录器
	private final static Logger logger = LoggerFactory.getLogger(LoggerLevelTest.class);
	
	@Test
	public void testLogger(){
		/*配置文件：/endcore/src/main/resources/logback.xml
		 * 
		 * logback.xml 中定义的root logger的level=warn,则以下请求级别大于等于warn才会生效
		 */
		
		logger.trace("logger trace");
		logger.debug("logger debug");
		logger.info("logger info");
		logger.warn("logger warn");
		logger.error("logger error");
	}
	
	public static void main (String[] args){
		LoggerLevelTest test = new LoggerLevelTest();
		
		test.testLogger();
	}
}
