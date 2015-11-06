package cn.sunline.tmp.fund.settle;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

public interface TmpFundProfitDao {
	
	public Specification<TmpFundProfit> getAllSpecification(final TmpFundProfit tmp);
	/**
	 * 根据日期查询所有记录
	 * @param tmp
	 * @return
	 */
	List<TmpFundProfit> findAllByTemplate(final TmpFundProfit tmp);
}
