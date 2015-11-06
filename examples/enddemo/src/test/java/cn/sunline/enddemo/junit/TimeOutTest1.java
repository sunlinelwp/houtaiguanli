package cn.sunline.enddemo.junit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/*
 * 目的：演示Timeout Rule(applies to entire test class)的用法
 * 
 * 范围：影响整个class
 */

public class TimeOutTest1 {
	public static String log;

    @Rule
    public Timeout globalTimeout = new Timeout(10000); // 10 seconds max per method tested

    @Test
    public void testInfiniteLoop1() {
        log += "ran1";
        for (;;) {
        }
    }

    @Test
    public void testInfiniteLoop2() {
        log += "ran2";
        for (;;) {
        }
    }
}
