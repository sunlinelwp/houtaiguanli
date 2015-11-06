package cn.sunline.tmp.fund.settle;

public interface TmpFundProfitService {
	
	/**
	 * 保存多条记录并提交到数据
	 * @param entity
	 * @return
	 */
	void saveTmpFundProfit(TmpFundProfit entity);
	
	/**
	 * 更新记录状态
	 * @param profit
	 * @return
	 */
	int updateStatus(String dealdt,String status);
	
	/**
	 * 
	 * @param tmp
	 * @param pageable
	 * @return
	 */
	TmpFundProfit getFundProfitInfo (String dealdt);
}
