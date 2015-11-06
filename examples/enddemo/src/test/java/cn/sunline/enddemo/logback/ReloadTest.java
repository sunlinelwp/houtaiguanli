package cn.sunline.enddemo.logback;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * 测试目的：调用 JoranConfigurator 实现logback的配置自定义，不使用其默认配置处理机制。
 *        Logback 的默认配置机制是调用JoranConfigurator对classpath上的默认配置文件 logback.xml 进行处理
 *        
 * @author xiaoxu
 */

public class ReloadTest {
	//定义一个全局的记录器，通过LoggerFactory获取
	private static Logger logger = (Logger) LoggerFactory.getLogger(ReloadTest.class);

	@Test
	public void loadConfig() {
		//通过getILoggerFactory()方法得到logger上下文件环境，logback默认获得当前应用的logger context
		//得到当前应用中logger上下文
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		
		//定义一个(JoranConfigurator)配置器
		JoranConfigurator configurator = new JoranConfigurator();
		//将当前应用的logger context的关联到到configurator对象
		configurator.setContext(lc);
		//清除以前的配置器中的所有内容
		lc.reset();
		
		try {
			//加载配置文件：1.获取路径  2.指定文件路径
			//1.获取配置文件的相对路径
			String path = LoggerLevelTest.class.getResource("/").getPath();
			//接收从命令行传入的参数，加载配置文件，并设置到配置器
			configurator.doConfigure(path + "cn/sunline/enddemo/logback/logback-reload.xml");
			
			//2.指定路径
			//configurator.doConfigure("src/test/java/cn/sunline/enddemo/logback/logback-reload.xml");
		} catch (JoranException e) {
			//将此处异常也记录到日志
			logger.error("JoranException occur at:"+e.getMessage());
			
			e.printStackTrace();
		}
		
		
		// StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
		logger.info("Entering application.demo class Foo >>>");	
	    LoggerLevelTest loggerLevelTest = new LoggerLevelTest();
	    loggerLevelTest.testLogger();
	    logger.info("Exiting application. demo class Foo <<<");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReloadTest reloadTest = new ReloadTest();
		reloadTest.loadConfig();
	}

}
