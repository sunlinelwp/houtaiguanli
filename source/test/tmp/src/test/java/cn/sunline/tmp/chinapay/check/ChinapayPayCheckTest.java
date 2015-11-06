package cn.sunline.tmp.chinapay.check;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.sunline.client.Client;
import cn.sunline.file.DataWriter;
import cn.sunline.utils.PageToDataTable;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ChinapayPayCheckTest {

	private final static Logger logger = LoggerFactory
			.getLogger(ChinapayPayCheckTest.class);

	@Resource
	private DataWriter tmpChinapayPayCheckDataWriter;

	@Resource
	private Client client;
	
	@Autowired
	private TmpChinapayPayCheckService payCheckService;
	@Test
	public void test1() {
		logger.info("测试ChinaPay代付差错文件============start");

		// 测试登陆报文
		client.readFile("chpy_nomacth_pay_", tmpChinapayPayCheckDataWriter,
				"20150415");
		logger.info("测试ChinaPay代付差错文件============end");
	}
	@Test
	public void test2(){
		int length=10;		
		int start=0;		
		Pageable pageable=new PageRequest(start/length,length);
		Page<TmpChinapayPayCheck> page=payCheckService.queryEntitiesByTemplateWithPage(pageable);	  
	    Map<String, Object> map=PageToDataTable.convert(page);
	    logger.info("查询信息结果：====="+map);
	}

}