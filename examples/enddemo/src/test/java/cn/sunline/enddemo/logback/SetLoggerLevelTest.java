package cn.sunline.enddemo.logback;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

/*
 * 目的：使用setLevel()方法，手工设置logger的有效级别
 *     org.slfej.logger 没有setLevel方法；这里使用ch.qos.logback.classic.Logger;（后者类实现了前者的接口）
 *     
 *     但需将LoggerFactory.getLogger()返回的实例进行强制转换。
 * 
 * author:xiaoxu
 */

public class SetLoggerLevelTest {
	//调用LoggerFactory类的静态方法getLogger取得一个Logger实例，将该实例赋值给变量logger
	//但需进行强制转换
	public static Logger logger = (Logger) LoggerFactory.getLogger(HelloWorld.class);
	
	@Test
	public void test(){

		/*
		 * 调用logback。classic。Logger中的setLevel()方法，设定日志记录器的有效级别
		 * 
		 * Level.TRACE
		 * Level.DEBUG
		 * Level.INFO
		 * Level.WARN
		 * Level.ERROR
		 * 
		 * trace < debug < info < warn < error
		 */
		logger.setLevel(Level.TRACE);
		//若指定DEBUG级别，则下面的日志请求打印语句不会被执行
		//logger.setLevel(Level.DEBUG);
		
		//调用日志记录方法打印：trace、debug、info、warn、error
		if(logger.isTraceEnabled()){
			logger.trace("Hello World");
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("Hello World");
		}
		
	}
}
