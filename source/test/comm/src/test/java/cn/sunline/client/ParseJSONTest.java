package cn.sunline.client;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ParseJSONTest {

	private final static Logger logger = LoggerFactory
			.getLogger(ParseJSONTest.class);

	@Resource
	private Client client;

	private Map<String, Object> m = new HashMap<String, Object>();

	@Before
	public void testBefore() throws Exception {
		m.put("userid", "32434");
		m.put("passwd", "1234");
		m.put("pswdfg", "1");
	}

	@Test
	public void test1() {
		try{
		logger.info("测试登陆交易JSON报文============");

		// 测试登陆报文
		Map<String, Object> r = client.callClient("userin", m);
		logger.info("测试登陆交易JSON报文============" + r);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
