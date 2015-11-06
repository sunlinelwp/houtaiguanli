package cn.sunline.enddemo.junit;

import org.junit.Test;

/*
 * 目的：演示@Test注解的timeout参数的用法
 *     
 * 范围：针对每个测试方法
 * 
 */

public class TimeOutTest2 {
	public static String log;

    @Test(timeout=1000)
    public void testInfiniteLoop1() {
        log += "ran1";
        for (;;) {
        }
    }

    @Test(timeout=2000)
    public void testInfiniteLoop2() {
        log += "ran2";
        for (;;) {
        }
    }
}
