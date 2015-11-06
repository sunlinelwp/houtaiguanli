package cn.sunline.enddemo.druid;

import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

/*
 * 目的：通过DruidDataSource的配置方法，来手工配置数据源信息
 * 
 */

public class DruidDataSourceTest1 {
	
	@Test
	public void test() throws Exception{
		
		DruidDataSource dataSource = new DruidDataSource();
		

        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@192.168.1.12:1521:orcl");
        dataSource.setUsername("sunline");
        /*
         * 密码：若filters未设置为“config”,即“ConfigFilter”,则password为明文；
         *     反则需在存放druid的jar包目录，命令行窗口执行以下命令获得加密后的密码串
         *     注意：这里以druid-1.0.2版本为例，具体情况参考您使用的版本。
         *     java -cp druid-1.0.2.jar com.alibaba.druid.filter.config.ConfigTools your_password
         */
        dataSource.setPassword("EBDHQrqJ82FqnHAPXB0BfxeXujZtsAm6PEJA1l45b+azQtCqlvPBeKeiDXGpsrVBjQJyohL2fiX7KJ6z7NNLvg==");
        
        //打开PSCache缓存
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxOpenPreparedStatements(50);
        
        dataSource.setUseOracleImplicitCache(true);
        
        //若pasword使用加密密码，则需提示Druid数据源需要对数据库密码进行解密
        dataSource.setFilters("config");
        dataSource.setConnectionProperties("config.decrypt=true");
        
        TableOperator.createTable(dataSource);
        TableOperator.initData(dataSource);
        TableOperator.viewData(dataSource);
		TableOperator.dropTable(dataSource);
		
	}
}
