package cn.sunline.enddemo.druid;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/*
 * 目的：根据druid的常用配置项，编写properties文件，重载并获取properties对象，作为创建DruidDataSource实例的传入参数。
 * 
 */

public class DruidDataSourceTest3 {
	
	private static Logger logger  = LoggerFactory.getLogger(DruidDataSourceTest3.class);
	
	@Test
	public void test() throws Exception{
		
		ClassPathResource cpr = new ClassPathResource("jdbc-druid.properties");
		
		Properties p = new Properties();
		
		try {
            p.load(cpr.getInputStream());
        } catch (IOException ex) {
            //System.out.println("file is not exist");
        	logger.error("资源文件不存在");
        }
		
		/*
		 * 通过获得的druid数据源配置项properties对象，创建DataSource实例
		 * 
		 */
		DataSource dataSource = DruidDataSourceFactory.createDataSource(p);
        
        //TableOperator.dropTable(dataSource);
        TableOperator.createTable(dataSource);
        TableOperator.initData(dataSource);
        TableOperator.viewData(dataSource);
		TableOperator.dropTable(dataSource);
		
	}
}
