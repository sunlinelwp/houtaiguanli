package cn.sunline.enddemo.transaction;

import javax.annotation.Resource;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.sunline.domain.enddemo.transaction.Trade;
import cn.sunline.service.enddemo.transaction.TradeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TradeTest {
	
	@Resource
	private TradeService tradeService;
	private final static Logger logger = LoggerFactory.getLogger(TradeTest.class);
	
	/**
	 * 测试@Transactional事物管理readOnly属性
	 */
	@Test
	public void test1(){
		//readOnly=true
		Trade trade1 = new Trade(100001,"grxc","000");
		tradeService.insertTrade01(trade1);
		Assertions.assertThat(tradeService.countByTradeId(100001)).isEqualTo(0);
		
		//readOnly=false
		Trade trade2 = new Trade(100002,"grxc","000");
		tradeService.insertTrade02(trade2);
		Assertions.assertThat(tradeService.countByTradeId(100002)).isEqualTo(1);
	}

	/**
	 * 测试@Transactional事物管理rollbackFor属性
	 * 1.默认情况下Spring对于Exception不进行事物回滚
	 * 2.在整个方法前加上 @Transactional(rollbackFor=Exception.class)，让Exception也回滚
	 */
	@Test
	public void test2(){
		//默认情况下，事物中抛出Exception
		Trade trade1 = new Trade(10001,"grxq","001");
		try {
			tradeService.insertTrade1(trade1);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		Assertions.assertThat(tradeService.countByTradeId(10001)).isEqualTo(1);
		
		//加上rollbackFor属性后，抛出Exception
		Trade trade2 = new Trade(10002,"grxq","002");
		try {
			tradeService.insertTrade2(trade2);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		Assertions.assertThat(tradeService.countByTradeId(10002)).isEqualTo(0);
	}
	
	/**
	 * 测试@Transactional事物管理noRollbackFor属性
	 * 1.默认情况下Spring对于RuntimeException会进行事物回滚
	 * 2.在整个方法前加上@Transactional(noRollbackFor=RuntimeException.class)，让RuntimeException不进行回滚
	 */
	@Test
	public void test3(){
		////默认情况下，事物中抛出RuntimeException
		Trade trade1 = new Trade(10003,"grxq","003");
		try {
			tradeService.insertTrade3(trade1);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		Assertions.assertThat(tradeService.countByTradeId(10003)).isEqualTo(0);
		
		//加上noRollbackFor属性后，抛出RuntimeException
		Trade trade2 = new Trade(10004,"dgxq","000");
		try {
			tradeService.insertTrade4(trade2);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		Assertions.assertThat(tradeService.countByTradeId(10004)).isEqualTo(1);
	}
	
	/**
	 * 测试@Transactional事物管理propagation属性
	 * 1.REQUIRED：业务方法需要在一个容器里运行。如果方法运行时，已经处在一个事务中，那么加入到这个事务，否则自己新建一个新的事务
	 * 2.NOT_SUPPORTED:声明方法不需要事务。如果方法没有关联到一个事务，容器不会为他开启事务，如果方法在一个事务中被调用，该事务会被挂起，调用结束后，原先的事务会恢复执行
	 * 3.REQUIRES_NEW:不管是否存在事务，该方法总汇为自己发起一个新的事务。如果方法已经运行在一个事务中，则原有事务挂起，新的事务被创建
	 * 4.MANDATORY：该方法只能在一个已经存在的事务中执行，业务方法不能发起自己的事务。如果在没有事务的环境下被调用，容器抛出例外
	 * 5.SUPPORTS:该方法在某个事务范围内被调用，则方法成为该事务的一部分。如果方法在该事务范围外被调用，该方法就在没有事务的环境下执行
	 * 6.NEVER：该方法绝对不能在事务范围内执行。如果在就抛例外。只有该方法没有关联到任何事务，才正常执行
	 * 7.NESTED:如果一个活动的事务存在，则运行在一个嵌套的事务中。如果没有活动事务，则按REQUIRED属性执行
	 */
	
	/**
	 * 测试REQUIRED和REQUIRES_NEW
	 * 1.使用REQUIRED如果方法运行时，已经处在一个事务中，那么加入到这个事务，否则自己新建一个新的事务
	 * 2.使用 REQUIRES_NEW 事务属性时，如果存在现有事务上下文，当前的事务会被挂起并启动一个新事务。方法结束后，新的事务被提交，原来的事务继续执行。
	 * @throws Exception 
	 */
	@Test
	public void test4() throws Exception{
		//已存在事务环境中，运行REQUIRED注解的方法
		Trade trade1 = new Trade(10005,"dgxq","001");
		try {
			tradeService.insertTrade5(trade1);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		Assertions.assertThat(tradeService.countByTradeId(10005)).isEqualTo(0);
		
		//已存在事务环境中，运行REQUIRED_NEW注解的方法
		Trade trade2 = new Trade(10006,"dgxq","002");
		try {
			tradeService.insertTrade6(trade2);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		Assertions.assertThat(tradeService.countByTradeId(10006)).isEqualTo(1);
	}
	
	/**
	 * 测试MANDATORY
	 * 该方法只能在一个已经存在的事务中执行，业务方法不能发起自己的事务。如果在没有事务的环境下被调用，容器抛出例外
	 */
	@Test
	public void test5(){
		//开启Spring事务管理，运行MANDATORY注解方法
		Trade trade1 = new Trade(10007,"dgxq","003");
		tradeService.insertTrade71(trade1);
		Assertions.assertThat(tradeService.countByTradeId(10007)).isEqualTo(1);

		//没有开启事务，运行MANDATORY注解方法
		Trade trade2 = new Trade(10008,"dgxq","004");
		try{
			tradeService.insertTrade72(trade2);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		Assertions.assertThat(tradeService.countByTradeId(10008)).isEqualTo(0);
	}
	
	/**
	 * 测试SUPPORTS
	 * 该方法在某个事务范围内被调用，则方法成为该事务的一部分。如果方法在该事务范围外被调用，该方法就在没有事务的环境下执行
	 */
	@Test
	public void test6(){
		//开启Spring事务管理，运行SUPPORTS注解方法
		Trade trade1 = new Trade(10009,"dgxq","005");
		try{
			tradeService.insertTrade81(trade1);
		}
		catch(Exception e){
			logger.info(e.getMessage());
		}
		Assertions.assertThat(tradeService.countByTradeId(10009)).isEqualTo(0);
		
		//没有开启事务，运行SUPPORTS注解方法
		Trade trade2 = new Trade(10010,"dgxq","006");
		try{
			tradeService.insertTrade82(trade2);
		}
		catch(Exception e){
			logger.info(e.getMessage());
		}
		//Spring没有开启事务的时候，使用的是本地事务，本地是默认自动提交的
		Assertions.assertThat(tradeService.countByTradeId(10010)).isEqualTo(1);
	}
	
	/**
	 * 测试NEVER
	 * 该方法绝对不能在事务范围内执行。如果在就抛例外。只有该方法没有关联到任何事务，才正常执行
	 */
	@Test
	public void test7(){
		//开启Spring事务管理
		Trade trade1 = new Trade(10011,"dgxq","007");
		try{
			tradeService.insertTrade91(trade1);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		Assertions.assertThat(tradeService.countByTradeId(10011)).isEqualTo(0);
		
		//没有事务环境
		Trade trade2 = new Trade(10012,"dgxq","008");
		tradeService.insertTrade92(trade2);
		Assertions.assertThat(tradeService.countByTradeId(10012)).isEqualTo(1);
	}
	
	/**
	 * 测试NESTED
	 * 如果一个活动的事务存在，则运行在一个嵌套的事务中。如果没有活动事务，则按REQUIRED属性执行
	 */
	@Test
	public void test8(){
		//没有事务环境，运行NESTED注解方法
		Trade trade1 = new Trade(10013,"dgxq","009");
		try{
			tradeService.insertTrade101(trade1);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		Assertions.assertThat(tradeService.countByTradeId(10013)).isEqualTo(0);
		
		//开启Spirng事务管理，运行NESTED注解方法
		Trade trade2 = new Trade(10014,"dgxq","010");
		try{
			tradeService.insertTrade102(trade2);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		Assertions.assertThat(tradeService.countByTradeId(10014)).isEqualTo(0);
	}
	
	/**
	 * 测试NOT_SUPPORTED
	 * 声明方法不需要事务。如果方法没有关联到一个事务，容器不会为他开启事务，如果方法在一个事务中被调用，该事务会被挂起，调用结束后，原先的事务会恢复执行
	 */
	@Test
	public void test9(){
		//开启Spring事务管理，运行@NOT_SUPPORTED注解方法
		Trade trade1 = new Trade(10015,"dgxq","011");
		try{
			tradeService.insertTrade111(trade1);
		}
		catch(Exception e){
			logger.info(e.getMessage());
		}
		Assertions.assertThat(tradeService.countByTradeId(10015)).isEqualTo(1);
		
		//没有事务环境，运行@NOT_SUPPORTED注解方法
		Trade trade2 = new Trade(10016,"dgxq","012");
		try{
			tradeService.insertTrade112(trade2);
		}
		catch(Exception e){
			logger.info(e.getMessage());
		}
		Assertions.assertThat(tradeService.countByTradeId(10016)).isEqualTo(1);
	}
	
	/**
	 * 测试timeout属性
	 */
	@Test
	public void test10(){
		//不超时
		Trade trade1 = new Trade(10017,"dgxq","013");
		try{
			tradeService.insertTrade121(trade1);
		}
		catch(Exception e){
			logger.info(e.getMessage());
		}
		Assertions.assertThat(tradeService.countByTradeId(10017)).isEqualTo(1);
		
		//超时
		Trade trade2 = new Trade(10018,"dgxq","014");
		try{
			tradeService.insertTrade122(trade2);
		}
		catch(Exception e){
			logger.info(e.getMessage());
		}
		Assertions.assertThat(tradeService.countByTradeId(10018)).isEqualTo(0);
	}
	
	/**
	 * isolation属性
	 * HibernateJpaDialect does not support custom isolation levels due to limitations in standard JPA
	 */
	
}