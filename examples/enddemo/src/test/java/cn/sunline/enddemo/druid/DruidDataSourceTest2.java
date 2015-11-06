package cn.sunline.enddemo.druid;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;

/*
 * 目的：Spring通过配置文件自动装配的“dataSource”bean,我们通过应用上下文（ApplicationContext）的getBean()方法获取“dataSource”实例
 * 
 */

public class DruidDataSourceTest2 {
	
	@Test
	public void test() throws Exception{
		
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		/*
		 * 通过获取Spring中定义的“dataSource”bean,得到DruidDataSource实例
		 * 
		 */
		DruidDataSource dataSource = (DruidDataSource) ctx.getBean("dataSource");
		
        
		TableOperator.createTable(dataSource);
		TableOperator.initData(dataSource);
		TableOperator.viewData(dataSource);
		TableOperator.dropTable(dataSource);
		
	}
}
