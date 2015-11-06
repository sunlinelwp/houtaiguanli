package cn.sunline.enddemo.logback;

import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/*
 * 目的：介绍logger的简单使用方法，以及记录请求级别 与 记录有效记录之间的关系
 * 
 */

public class HelloWorld {
	@Test
	public void test(){
		//调用LoggerFactory类的静态方法getLogger取得一个Logger实例，将该实例赋值给变量logger
		//getLogger方法返回的是org.slf4j.Logger 实例。
		//logger 默认 继承logback.xml中root的日志有效级别 debug
		Logger logger = LoggerFactory.getLogger(HelloWorld.class);
		
		//调用日志记录方法打印：trace、debug、info、warn、error
		logger.trace("Hello World");//调用一个请求级别为trace的记录，不会显示
		logger.info("Hello World");//会显示
	}
}
