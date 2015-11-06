package cn.sunline.enddemo.junit;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * A sample test case, testing {@link java.util.ArrayList}.
 * 
 *     @Before:Junit在每个测试方法执行之前都要执行@Before注解的方法，完成初始化测试环境。方法名一般叫：setUp()
 *     @After:Junit在每个测试方法执行之后都要执行@After注解的方法，完成清理测试环境。方法名一般叫：tearDown()
 *     @BeforeClass：针对所有测试，只执行一次，且必须为static void。方法名一般叫：setUpBeforeClass
 *     @AfterClass：针对所有测试，只执行一次，且必须为static void。方法名一般叫:tearDownAfterClass
 *     @Test：测试方法，在这里可以测试期望异常和超时时间
 *     @Ignore：忽略的测试方法
 *     
 *     单元测试用例执行顺序为：@BeforeClass –> @Before –> @Test –>@After –> @AfterClass
 */
public class ListTest {
    protected List<Integer> fEmpty;
    protected List<Integer> fFull;
    protected static List<Integer> fgHeavy;

    public static void main(String... args) {
        junit.textui.TestRunner.run(suite());
    }

    @BeforeClass
    public static void setUpOnce() {
        fgHeavy = new ArrayList<Integer>();
        for (int i = 0; i < 1000; i++) {
            fgHeavy.add(i);
        }
    }

    @Before
    public void setUp() {
        fEmpty = new ArrayList<Integer>();
        fFull = new ArrayList<Integer>();
        fFull.add(1);
        fFull.add(2);
        fFull.add(3);
    }

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(ListTest.class);
    }

    @Ignore("this is ignored as a demonstration")
    @Test
    public void capacity() {
        int size = fFull.size();
        for (int i = 0; i < 100; i++) {
            fFull.add(i);
        }
        assertTrue(fFull.size() == 100 + size);
    }

    @Test
    public void testCopy() {
        List<Integer> copy = new ArrayList<Integer>(fFull.size());
        copy.addAll(fFull);
        assertTrue(copy.size() == fFull.size());
        assertTrue(copy.contains(1));
    }

    @Test
    public void contains() {
        assertTrue(fFull.contains(1));
        assertTrue(!fEmpty.contains(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void elementAt() {
        int i = fFull.get(0);
        assertTrue(i == 1);
        fFull.get(fFull.size()); // Should throw IndexOutOfBoundsException
    }

    @Test
    public void removeAll() {
        fFull.removeAll(fFull);
        fEmpty.removeAll(fEmpty);
        assertTrue(fFull.isEmpty());
        assertTrue(fEmpty.isEmpty());
    }

    @Test
    public void removeElement() {
        fFull.remove(new Integer(3));
        assertTrue(!fFull.contains(3));
    }
}