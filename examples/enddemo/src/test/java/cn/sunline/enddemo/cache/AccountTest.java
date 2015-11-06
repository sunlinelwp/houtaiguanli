package cn.sunline.enddemo.cache;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.sunline.domain.enddemo.cache.Account;
import cn.sunline.service.enddemo.cache.AccountService;


/**
 * 
 * 测试spring cache缓存的用法
 * 主要是@Cacheable、@CacheEvict和@CachePut
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AccountTest {
	
	@Resource
	private AccountService s ;
	private final static Logger logger = LoggerFactory.getLogger(AccountTest.class);  
	
	/**
	 * 测试缓存@Cacheable和清除缓存@CacheEvict的调用
	 */
	@Test
	public void test1() { 
        logger.info("start testing @Cacheable and @CacheEvict...");
        //第一次查询 
        logger.info("first query ...");
        s.getAccountByName("somebody");
        //第二次查询
        logger.info("second query ...");
        s.getAccountByName("somebody");
        //测试清除缓存
        logger.info("clean all cache ...");
        s.reload();
        //第一次查询 
        logger.info("first query ...");
        s.getAccountByName("somebody");
        //第二次查询
        logger.info("second query ...");
        logger.info("clean all cache ...");
        s.reload();
    }
	
	/**
	 * 测试缓存的启动条件condition
	 */
	@Test
	public void test2() { 
        logger.info("start testing condition...");
        //第一次查询 
        logger.info("first query ...");
        s.getAccountByName("somebody");
        s.getAccountByName("som");
        //第二次查询
        logger.info("second query ...");
        s.getAccountByName("somebody");
        s.getAccountByName("som");
        logger.info("clean all cache ...");
        s.reload();
    }
	
	/**
	 * 测试缓存有多个参数情况下，进行key组合
	 */
	@Test
	public void test3(){
        logger.info("start testing duplicate key...");
        s.getAccount("somebody", "123456", true);
        s.getAccount("somebody", "123456", true);
        s.getAccount("somebody", "123456", false);
        s.getAccount("somebody", "654321", true);
        s.getAccount("somebody", "654321", true);
        logger.info("clean all cache ...");
        s.reload();
	}
	
	/**
	 * 测试@CachePut
	 */
	@Test
	public void test4(){
        logger.info("start testing @CachePut...");
        Account acct = s.getAccountByName("somebody");
        acct.setPassword("123");
        s.updateAccount(acct);
        acct.setPassword("321");
        s.updateAccount(acct);
        acct = s.getAccountByName("somebody");
        logger.info(acct.getPassword());
        logger.info("clean all cache ...");
        s.reload();
	}
}
