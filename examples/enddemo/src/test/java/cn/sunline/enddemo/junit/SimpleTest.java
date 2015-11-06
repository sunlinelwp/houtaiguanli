package cn.sunline.enddemo.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * 目的：初步接触JUnit
 *     @Before:Junit在每个测试方法执行之前都要执行@Before注解的方法，完成初始化测试环境。方法名一般叫：setUp()
 *     @After:Junit在每个测试方法执行之后都要执行@After注解的方法，完成清理测试环境。方法名一般叫：tearDown()
 *     @BeforeClass：针对所有测试，只执行一次，且必须为static void。方法名一般叫：setUpBeforeClass
 *     @AfterClass：针对所有测试，只执行一次，且必须为static void。方法名一般叫:tearDownAfterClass
 *     @Test：测试方法，在这里可以测试期望异常和超时时间
 *     @Ignore：忽略的测试方法
 *     
 *     单元测试用例执行顺序为：@BeforeClass –> @Before –> @Test –>@After –> @AfterClass
 */
public class SimpleTest {
    protected int fValue1;
    protected int fValue2;

    @Before
    public void setUp() {
        fValue1 = 2;
        fValue2 = 3;
    }

    public int unused;

    @Test
    public void divideByZero() {
        int zero = 0;
        int result = 8 / zero;
        unused = result; // avoid warning for not using result
    }

    @Test
    public void testEquals() {
        assertEquals(12, 12);
        assertEquals(12L, 12L);
        assertEquals(new Long(12), new Long(12));

        assertEquals("Size", 12, 13);
        assertEquals("Capacity", 12.0, 11.99, 0.0);
    }

}
