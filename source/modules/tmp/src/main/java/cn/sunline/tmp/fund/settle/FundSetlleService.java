package cn.sunline.tmp.fund.settle;

import java.util.List;

public interface FundSetlleService {
	/**
	 * 根据交易日期查询清算金额
	 * @param dealdt
	 * @param trantp
	 * @return
	 */
	List<FundSetlle> getFundSetlleByDealDt(String dealdt);
	
	/**
	 * 保存多条记录并提交到数据
	 * @param entity
	 * @return
	 */
	void saveFundSetlle(List<FundSetlle> entity);
	
	/**
	 * 更新记录状态
	 * @param dealdt
	 * @param trantp
	 * @param status
	 * @return
	 */
	int updateStatus(String dealdt,String trantp,String status);
	/**
	 * 根据交易日期和交易类型查询清算金额
	 * @param dealdt
	 * @param trantp
	 * @return
	 */
	List<FundSetlle> getFundSetlleByDealDtAndTrantp(String dealdt , String trantp);
}
