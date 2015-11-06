package cn.sunline.enddemo.jpa;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.sunline.domain.enddemo.jpa.DepModel;
import cn.sunline.domain.enddemo.jpa.UserModel;
import cn.sunline.service.enddemo.jpa.DepModelService;
import cn.sunline.service.enddemo.jpa.UserModelService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DepModelTest {
	
	@Resource
	private DepModelService depModelService;
	@Resource
	private UserModelService userModelService;
	private final static Logger logger = LoggerFactory.getLogger(DepModelTest.class); 
	
	@Before
	public void testSaveDepModel(){
		//创建UserModel对象um1,并赋值
		UserModel um1 = new UserModel();
		um1.setUuid(1001);
		um1.setName("zhangsan");
		um1.setAge(30);
		//保存um1对象
		um1 = userModelService.saveUserModel(um1);
		
		//创建DepModel对象dm1，并赋值
		DepModel dm1 = new DepModel();
		dm1.setUuid(100);
		dm1.setName("kejibu");
		//将um1赋值给dm1的um属性
		dm1.setUm(um1);
		//保存dm1对象
		depModelService.saveDepModel(dm1);
		
		DepModel dm2 = new DepModel();
		dm2.setUuid(200);
		dm2.setName("caiwubu");
		dm2.setUm(um1);
		depModelService.saveDepModel(dm2);
	}
	
	/**
	 * 测试一对多
	 */
	@Test
	public void testFindByName1(){
		List<UserModel> userList = userModelService.findByName("zhangsan");
		UserModel um = userList.get(0);
		logger.info("UserModel.name:" + um.getName());
		Set<DepModel> depList = (Set<DepModel>) um.getSetDep();
		logger.info("depList.size:" + depList.size());
		Iterator<DepModel> it = depList.iterator();
		while(it.hasNext()){
			DepModel tmp = it.next();
			logger.info(tmp.toString());
		}
	}
	
	/**
	 * 测试多对一
	 */
	@Test
	public void testFindByName2(){
		List<DepModel> depList = depModelService.findByName("kejibu");
		DepModel dm = depList.get(0);
		UserModel um = dm.getUm();
		Assertions.assertThat(um.getUuid()).isEqualTo(1001);
	}
	
}
