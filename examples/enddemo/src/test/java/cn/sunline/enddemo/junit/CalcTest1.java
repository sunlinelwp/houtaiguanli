package cn.sunline.enddemo.junit;

import junit.framework.TestCase;

public class CalcTest1 extends TestCase {
	Calc c=new Calc(); 

	//before:测试前的初始化
    protected void setUp() throws Exception { 
    	c.setA(2); 
    	c.setB(1); 
   } 

    //after:测试完后的处理
    protected void tearDown() throws Exception { 
    	c=null; 
    } 

    //test:测试方法
    public void testAdd() { 
    	int result=c.add();  
    	assertEquals(3,result,0); 
    	//  fail("尚未实现"); // TODO 
    } 
}
