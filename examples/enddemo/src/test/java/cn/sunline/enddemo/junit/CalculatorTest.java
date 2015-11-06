package cn.sunline.enddemo.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/*
 * 目的：对Calculator类中的方法进行测试，通过注释的方式，设置测试类中不同方法的执行顺序
 *     @Before:Junit在每个测试方法执行之前都要执行@Before注解的方法，完成初始化测试环境。方法名一般叫：setUp()
 *     @After:Junit在每个测试方法执行之后都要执行@After注解的方法，完成清理测试环境。方法名一般叫：tearDown()
 *     @BeforeClass：针对所有测试，只执行一次，且必须为static void。方法名一般叫：setUpBeforeClass
 *     @AfterClass：针对所有测试，只执行一次，且必须为static void。方法名一般叫:tearDownAfterClass
 *     @Test：测试方法，在这里可以测试期望异常和超时时间
 *     @Ignore：忽略的测试方法
 *     
 *     单元测试用例执行顺序为：@BeforeClass –> @Before –> @Test –>@After –> @AfterClass
 * 
 */

public class CalculatorTest {
	private Calculator c;  
	  
    @BeforeClass  
    public static void setUpBeforeClass(){  
        System.out.println("=====static init=======");  
    }  
      
    @AfterClass  
    public static void tearDownAfterClass(){  
        System.out.println("=====static destory=======");  
    }  
      
    @Before  
    public void setUp(){  
        System.out.println("=======@before=======");  
        c=new Calculator();  
    }  
      
    @After  
    public void tearDown(){  
        System.out.println("=======@after=======");  
    }  
      
    @Test  
    public void testAdd(){  
        int sum=c.add(1, 2);  
        Assert.assertEquals(3, sum);  
    }  
    
    /*
     * The @Test annotation has an optional parameter "expected" that 
     * takes as values subclasses of Throwable. 
     * 
     */
    @Test(expected=cn.sunline.enddemo.junit.MyException.class)  
    public void testDiv(){  
        c.div(1, 0);  
    }  
      
    @Ignore  
    public void testDiv1(){  
        int d=c.div(1, 5);  
        Assert.assertEquals(0, d);  
    }  
}
