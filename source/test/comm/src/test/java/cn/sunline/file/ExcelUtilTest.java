package cn.sunline.file;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ExcelUtilTest {
	
	private final static Logger logger = LoggerFactory
			.getLogger(ExcelUtilTest.class);
	
	@Test
	public void test1() {
		logger.info("Excel写入测试===========");
		
		
		List<String> l = new LinkedList<String>();
		l.add("产品名称");
		l.add("交易流水号");
		l.add("投保人姓名");
		l.add("投保金额");
		l.add("支付时间");
		l.add("保单状态");
		l.add("保单号");

		ExcelUtil e = new ExcelUtil("test.xlsx","d://");
		e.writeExcel("测试中文", l, null);
	}
	
	@Test
	public void test2() {
		logger.info("Excel读取测试===========");
		
		List<List<ExcelEntity>> l = new LinkedList<>();

		ExcelUtil e = new ExcelUtil("test1.xlsx","d://");
		e.readExcel(1, l);
		
		for(List<ExcelEntity> datas : l){
			for(ExcelEntity m : datas){
				System.out.println("数据====" + m.getDataType() + "====" + m.getData());
			}
		}
	}
}
