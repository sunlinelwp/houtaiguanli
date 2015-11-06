package cn.sunline.enddemo.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
 * 目的：将多个测试类聚集在一起，批量进行JUnit测试（Aggregating tests in Suites）
 *     使用SuiteClasses()方法
 * 
 * 英文描述：
 *     Using Suite as a runner allows you to manually build a suite containing tests from many classes. 
 *     It is the JUnit 4 equivalent of the JUnit 3.8.x static Test suite() method. 
 *     To use it, annotate a class with @RunWith(Suite.class) and @SuiteClasses(TestClass1.class, ...). 
 *     When you run this class, it will run all the tests in all the suite classes.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({CalcTest1.class,CalcTest2.class})

public class SuiteRunnerTest1 {

}
