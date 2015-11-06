package cn.sunline.service.enddemo.cache;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.sunline.domain.enddemo.cache.Account;

@Service("AccountService")
public class AccountService {

	private final static Logger logger = LoggerFactory.getLogger(AccountService.class);
	
   //注解表示所有对对象的此方法调用都会启动一个名称为“defaultCache”的缓存，condition属性表示满足该SpEL表达式缓存功能才生效（即满足name属性的长度大于等于4）
   @Cacheable(value="defaultCache", condition="#userName.length()>=4")
   public Account getAccountByName(String userName) { 
       //方法内部实现不考虑缓存逻辑，直接实现业务
	   //logger.info("realquery account->"+userName);
	   return getFromDB(userName); 
   }
   
   //注解可以确保方法被执行，同时方法的返回值也被记录到缓存中
   @CachePut(value="defaultCache", key="#account.getName()")
   public Account updateAccount(Account account){
	   //logger.info("realupdate account->"+account.getName());
	   return updateDB(account);
   }
   
   //清空所有缓存，allEntries缺省为false,
   //beforeInvocation属性表示是否在方法执行前就清空，缺省为 false;如果指定为 true，则在方法还没有执行的时候就清空缓存，缺省情况下，如果方法执行抛出异常，则不会清空缓存
   @CacheEvict(value="defaultCache", allEntries=true, beforeInvocation=true)
   public void reload(){
	   
   }
   
   //缓存中有多个参数
   @Cacheable(value="defaultCache", key="#userName.concat(#password)")
   public Account getAccount(String userName, String password, boolean sendLog){
	   return getFromDB(userName,password);
   }

   private Account getFromDB(String userName) { 
	   logger.info("real querying db...userName="+userName);
	   return new Account(userName); 
   }
   
   private Account getFromDB(String userName, String password){
	   logger.info("real querying db...userName="+userName+" password="+password);
	   return new Account(userName, password);
   }

   private  Account updateDB(Account account){
	   logger.info("real update db...userName=" + account.getName());
	   return account;
   }
   
 }