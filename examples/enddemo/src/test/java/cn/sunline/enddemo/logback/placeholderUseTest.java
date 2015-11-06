package cn.sunline.enddemo.logback;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import ch.qos.logback.classic.LoggerContext;
//import ch.qos.logback.core.util.StatusPrinter;

/*
 * 测试目的：展示logger中占位符{}的使用
 *        Note the use of {}-placeholders
 * 
 * author:xiaoxu
 */

public class placeholderUseTest {
	final Logger logger = LoggerFactory.getLogger(placeholderUseTest.class);
	Integer t;
	Integer oldT;
	
	public void setTemp(Integer temp){
		oldT = t;
		t = temp;
		
		//通过{}可输出变量的值，类似C语言print()方法
		//logger.error("t set to {}. oldT set to {}.",t,oldT);
		//类似
		//logger.error("t set to "+ t + ". oldT set to " + oldT);
		
		//若输出中包括{},则需如下使用
		logger.error("t set to {{}}. oldT set to {{}}.",t,oldT);
		//输出如下：t set to {26}. oldT set to {25}.
		
		if(temp.intValue() > 50){
			logger.error("temp has risen above 50 degress");
		}
		
	}
	
	@Test
	public void test(){
		Integer i = 0;
		while(i<53){
			setTemp(i++);
			
			//打印logback内部状态
			//LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory(); 
			//StatusPrinter.print(lc);
		}
	}
	
}
