package cn.sunline.enddemo.logback;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class DisplayByHtmlTest {
	@Test
	public void test() throws InterruptedException{
		 Logger logger = LoggerFactory.getLogger(DisplayByHtmlTest.class);
		 LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		 
		//定义一个(JoranConfigurator)配置器
			JoranConfigurator configurator = new JoranConfigurator();
			//将当前应用的logger context的关联到到configurator对象
			configurator.setContext(lc);
			//清除以前的配置器中的所有内容
			lc.reset();
			
			try {
				//指定路径
				configurator.doConfigure("src/test/java/cn/sunline/enddemo/logback/logback-displayByHtml.xml");
			} catch (JoranException e) {
				//将此处异常也记录到日志
				logger.error("JoranException occur at:"+e.getMessage());
				
				e.printStackTrace();
			}
			
			for (int i = 0; i < 6; i++) {
			      if (i % 5 == 0) {
			       logger.warn("a warning message " + i);	//声明一条warn级别的日志消息
			    	  //logger.info("a warning message " + i);
			      } else {
			        logger.debug("test number " + i);
			      }
			}
			
			logger.error("Finish off with fireworks", new Exception("Just testing"));
	}
}
