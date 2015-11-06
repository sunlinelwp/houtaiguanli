package cn.sunline.enddemo.junit;

import java.util.Arrays;  
import java.util.Collection;  
  
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.junit.runners.Parameterized;  
import org.junit.runners.Parameterized.Parameters;  
  
import static org.junit.Assert.assertEquals;

/*
 * 目的：展示JUnit提供的参数化测试；Parameterized.test
 *     2.若类的属性为private,则需要一个传入参数的构造函数
 * 步骤：
 *     1）.测试类必须由Parameterized测试运行期修饰
 *     2）.准备数据。数据的准备需要在一个方法中进行,该方法需要满足以下条件：
 *       a.该方法必须由Parameters注解修饰
 *		 b.该方法必须是public  static的
 *		 c.该方法必须返回Collection类型
*		 d.该方法名字不要求，必须是无参的
 * 
 */
  
@RunWith(Parameterized.class)  
public class ParameterInjectionTest2 {  
    private int expected;  
    private int actual;  
      
    public ParameterInjectionTest2(int expected,int actual){  
        this.expected=expected;  
        this.actual=actual;   
    }  
    
    /*
     * 准备数据
     */
    @Parameters  
    public static Collection<Object[]> data(){  
        return Arrays.asList(new Object[][]{  
                {3,3},{5,4},{8,8},{11,9}  
        });  
    }  
    
    @Test  
    public void testEquals(){  
        assertEquals(expected,actual);  
    }  
} 