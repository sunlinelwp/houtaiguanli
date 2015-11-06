package cn.sunline.enddemo.jpa;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.sunline.domain.enddemo.jpa.Customer;
import cn.sunline.service.enddemo.jpa.CustomerService;

//注解指定使用的单元测试执行类
@RunWith(SpringJUnit4ClassRunner.class)
//注解指定Spring配置文件所在的路径
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CustomerTest {
	
	//注解标识默认按照名称自动装配，名称找不到才按照类型
	@Resource
	private CustomerService customerService;
	private final static Logger logger = LoggerFactory.getLogger(CustomerTest.class);
	
	@Before
	public void testBefore(){
		Customer c1 = new Customer();
		c1.setCustId(1001);
		c1.setCustName("xiaoming");
		c1.setAge(22);
		c1.setSex("F");
		customerService.saveCustomer(c1);
		
		Customer c2 = new Customer();
		c2.setCustId(1002);
		c2.setCustName("xiaohua");
		c2.setAge(24);
		c2.setSex("M");
		customerService.saveCustomer(c2);
		
		Customer c3 = new Customer();
		c3.setCustId(1003);
		c3.setCustName("xiaotian");
		c3.setAge(24);
		c3.setSex("F");
		customerService.saveCustomer(c3);
	}
	
	
	/**
	 * 测试调用JpaRepository接口save方法
	 */
	@Test//注解标识测试方法
	public void testSaveCustomer(){
		Customer c1 = new Customer();
		c1.setCustId(1004);
		c1.setCustName("test");
		Customer tmp = customerService.saveCustomer(c1);
		Assertions.assertThat(tmp.getCustId()).isEqualTo(1004);
	}
	
	/**
	 * 测试jpa自动进行方法名解析
	 */
	@Test
	public void testFindByCustId(){
		Customer c = new Customer();
		c = customerService.findByCustId(1001);
		Assertions.assertThat(c.getCustId()).isEqualTo(1001);
	}
	
	/**
	 * 测试jpa自动进行方法名解析
	 */
	@Test
	public void testFindByCustName(){
		List<Customer> cList = customerService.findByCustName("xiaoming");
		Assertions.assertThat(cList.size()).isEqualTo(1);
		Customer c = (Customer)cList.get(0);
		Assertions.assertThat(c.getCustId()).isEqualTo(1001);
	}
	
	/**
	 * 测试jpa中@NamedQuery的用法
	 * 根据配置文件中 jpa:repositories提供的query-lookup-strategy属性
	 * "create-if-not-found"表示先找查询语句@Query，再找命名查询@NamedQuery,最后才按名字进行解析
	 */
	@Test
	public void testFindByAge(){
		Integer age = new Integer(23);
		Integer custId = new Integer(1001);
		List<Customer> cList = customerService.findByAge(age);
		Assertions.assertThat(cList.size()).isEqualTo(1);
		Customer c = (Customer)cList.get(0);
		Assertions.assertThat(c.getCustId()).isEqualTo(custId);
	}
	
	/**
	 * 测试解析名字查询中的关键字“OrderBy”
	 */
	@Test
	public void testFindByAgeOrderByCustIdAsc(){
		List<Customer> cList = customerService.findByAgeOrderByCustIdAsc(24);
		Assertions.assertThat(cList.size()).isEqualTo(2);
		Customer c = (Customer)cList.get(0);
		Assertions.assertThat(c.getCustId()).isEqualTo(1002);
		c = (Customer)cList.get(1);
		Assertions.assertThat(c.getCustId()).isEqualTo(1003);
	}
	
	/**
	 * 测试解析名字查询中的关键字“LessThan”
	 */
	@Test
	public void testFindByAgeLessThan(){
		List<Customer> cList = customerService.findByAgeLessThan(24);
		Assertions.assertThat(cList.size()).isEqualTo(1);
		Customer c = (Customer)cList.get(0);
		Assertions.assertThat(c.getCustId()).isEqualTo(1001);
	}
	
	/**
	 * 测试解析名字查询中的关键字“GreaterThan”
	 */
	@Test
	public void testFindByAgeGreaterThan(){
		List<Customer> cList = customerService.findByAgeGreaterThan(22);
		Assertions.assertThat(cList.size()).isEqualTo(2);
		Customer c = (Customer)cList.get(0);
		Assertions.assertThat(c.getCustId()).isEqualTo(1002);
		c = (Customer)cList.get(1);
		Assertions.assertThat(c.getCustId()).isEqualTo(1003);
	}
	
	/**
	 * 测试Jpa中@Query查询语句的用法
	 */
	@Test
	public void testFindByCustNameLike(){
		List<Customer> cList = customerService.findByCustNameLike("xiao");
		Assertions.assertThat(cList.size()).isEqualTo(3);
		Customer c = (Customer)cList.get(0);
		Assertions.assertThat(c.getCustId()).isEqualTo(1001);
		c = (Customer)cList.get(1);
		Assertions.assertThat(c.getCustId()).isEqualTo(1002);
		c = (Customer)cList.get(2);
		Assertions.assertThat(c.getCustId()).isEqualTo(1003);
	}
	
	/**
	 * 测试Jpa中@Query查询语句中更新操作的用法
	 */
	@Test
	public void testUpdateByCustName(){
		Integer custId = new Integer(1003);
		String newName = "xiaotian";
		Customer c = new Customer();
		c = customerService.findByCustId(custId);
		int cnt = customerService.updateByCustName(c.getCustName(), newName);
		Assertions.assertThat(cnt).isEqualTo(1);
		c = customerService.findByCustId(custId);
		Assertions.assertThat(c.getCustName()).isEqualTo(newName);
	}
	
	/**
	 * 测试自己实现的方法
	 */
	@Test
	public void testFindByCondition(){
		Customer c1 = new Customer();
		c1.setCustName("xiaoming");
		Page<Object[]> page = customerService.findByCondition(c1);
		List<Object[]> elements = page.getContent();
		logger.info("elements.size:" + elements.size());
		@SuppressWarnings("rawtypes")
		Iterator it = elements.iterator();
		while(it.hasNext()){
			Customer tmp = (Customer)it.next();
			logger.info(tmp.toString());
		}
	}
	
	/**
	 * 测试无返回参数的存储过程的调用
	 * 必须在数据库中先创建好存储过程updatecustomeragebyid
	 */
	@Test
	public void testUpdateAgeById(){
		/*Integer custId = new Integer(1001);
		Integer age1 = new Integer(22);
		Integer age2 = new Integer(48);
		customerService.updateAgeById(custId, age2);
		Customer cust = customerService.findByCustId(custId);
		Assertions.assertThat(cust.getAge()).isEqualTo(age2);
		customerService.updateAgeById(custId, age1);
		cust = customerService.findByCustId(custId);
		Assertions.assertThat(cust.getAge()).isEqualTo(age1);*/
	}
}
