package cn.sunline.enddemo.logback;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

/*
 * 测试目的：介绍logback配置中的filter(过滤器)的使用。
 *       通过过滤器中指定的class，来执行过滤操作。例如 SampleFilter过滤器类
 *       >> 1.创建自己的过滤器，对日志的内容进行匹配过滤，按照过滤条件打印日志。  
 * 
 * @author xiaoxu
 */

public class DeclareFilterTest {
    private static Logger logger = LoggerFactory.getLogger(DeclareFilterTest.class);
	
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
			//重新指定logback的配置文件，并加载
			configurator.doConfigure("src/test/java/cn/sunline/enddemo/logback/logback-declareFilter.xml");
		} catch (JoranException e) {
			e.printStackTrace();
		}
		//StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
		
		//循环输出调用日志打印，测试过滤器 SampleFilter
		int i = 0;
		while (i<3) {
			logger.debug("userid=128,xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			logger.debug("userid=122,xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			logger.debug("userid=121,xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			logger.debug("userid=2,xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			logger.debug("userid=4,xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			try {
				//休眠1秒
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
	}


}
