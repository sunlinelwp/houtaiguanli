package cn.sunline.client;

import java.util.Map;

import cn.sunline.file.DataWriter;

public interface Client {

	/**
	 * 调用客户端
	 * @param prcscd 交易码
	 * @param reqData 请求数据
	 * @return 反馈数据
	 */
	Map<String,Object> callClient(String prcscd,Map<String,Object> reqData);
	
	/**
	 * 读取文件
	 * @param prcscd 交易码
	 * @param DataWriter 数据处理回调方法
	 * @param inputDate 处理日期
	 * @return 反馈文件内容
	 */
	void readFile(String prcscd,DataWriter w,String inputDate);
}
