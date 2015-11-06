package cn.sunline.file;

import java.util.List;

public interface DataWriter {

	/**
	 * 将数据写数据库
	 * @param data 读取数据
	 * @param inputDate 处理日期
	 */
	void writerDBData(List<String[]> data,String inputDate);
	
	/**
	 * 将头数据写数据库
	 * @param data 读取数据
	 * @param inputDate 处理日期
	 */
	void writerHeadData(String[] data,String inputDate);
}
