package cn.sunline.tmp.chinapay.check;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.sunline.client.Client;
import cn.sunline.file.DataWriter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ChinapayCltnCheckTest {

	private final static Logger logger = LoggerFactory
			.getLogger(ChinapayCltnCheckTest.class);

	@Resource
	private DataWriter tmpChinapayCltnCheckDataWriter;

	@Resource
	private Client client;

	@Test
	public void test1() {
		logger.info("测试ChinaPay代付差错文件============start");

		// 测试登陆报文
		client.readFile("chpy_nomacth_cltn_", tmpChinapayCltnCheckDataWriter,
				"20150415");
		logger.info("测试ChinaPay代付差错文件============end");
	}

}
