package cn.sunline.enddemo.junit;

import junit.framework.Test; 
import junit.framework.TestSuite; 

/*
 * 目的：将多个测试类聚集在一起，批量进行JUnit测试
 *     使用addTestSuite()方法
 * 
 */
public class SuiteRunnerTest2 { 

    public static Test suite() { 
    	TestSuite suite = new TestSuite("Test for default package"); 
    	//$JUnit-BEGIN$ 
    	//使用addTestSuite()传入测试类，构造器将该构造测试类中所有以“test”开头的无参方法
    	suite.addTestSuite(CalcTest1.class); 
    	suite.addTestSuite(CalcTest2.class);
    	//$JUnit-END$ 
    	return suite; 
    } 

} 
