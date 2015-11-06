package cn.sunline.enddemo.logback;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

/*
 * 目的：如何通过SMTPAppender来发送Email邮件，输出日志
 * 
 * author:xiaoxu
 */

public class DisplayByEmailTest {
	public static void usage(String msg) {	//当参数传入错误时，提供的处理惯例
	    System.err.println(msg);
	    System.err.println("Usage: java " + DisplayByEmailTest.class.getName() +
	      " runLength configFile\n" +
	      "   runLength (integer) the number of logs to generate\n" +
	      "   configFile a logback configuration file in XML format." +
	      " XML files must have a '.xml' extension.");
	    System.exit(1);	//退出程序
    }
	
	@Test
	public void test(){
		//若要测试error,则修改args变量的数组长度，并将多余的注释掉
		String[] args = new String[2];
		args[0] = "15";
		args[1] = "src/test/java/cn/sunline/enddemo/logback/logback-displayByEmail.xml";
		
		if(args.length != 2){
			usage("Wrong number of arguments");
		}
		
		int runLength = Integer.parseInt(args[0]);
		
		String configFile = args[1];
		
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		////通过StatusManager组件可以访问logback生命期内发生的重要事件。目前，我们调用StatusPrinter类的print()方法来打印logback的内部状态
		StatusPrinter.print(lc);
		
	    JoranConfigurator configurator = new JoranConfigurator();
	    lc.reset();
	    configurator.setContext(lc);
	    
	    try {
			//指定路径
	    	configurator.doConfigure(configFile);
		} catch (JoranException e) {
			e.printStackTrace();
		}
	    
	    Logger logger = LoggerFactory.getLogger(DisplayByEmailTest.class);
	    
	    for (int i = 1; i <= runLength; i++) {
	        if ((i % 10) < 5) {
	          logger.debug("This is a debug message. Message number: " + i);
	        } else {
	          logger.warn("This is a warning message. Message number: " + i);
	        }
	    }
	    //定义一条error级别的日志输出,触发邮件的发送
	    logger.error("At last an error.", new Exception("Just testing"));
	    //打印logger内部状态
	    StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
	}
}
