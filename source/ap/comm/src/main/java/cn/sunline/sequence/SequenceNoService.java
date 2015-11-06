package cn.sunline.sequence;

public interface SequenceNoService {
	
	/**
	 * 获取下一个顺序号
	 * @param key
	 * @return 顺序号
	 */
	String next(String key);

	/**
	 * 生成用0左占位顺序号
	 * 
	 * @param key
	 * @param scale
	 * @return 按照指定个数用0左占位返回顺序号
	 */
	String nextLpad(String key,int scale);

	/**
	 * 生成用0右占位顺序号
	 * 
	 * @param key
	 * @param scale
	 * @return 按照指定个数用0右占位返回顺序号
	 */
	String nextRpad(String key, int scale);
	
}
