package cn.sunline.tmp.fund.settle;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

public interface FundSetlleDao {
	public Specification<FundSetlle> getAllSpecification(final FundSetlle tmp);
	/**
	 * 根据日期查询所有记录
	 * @param tmp
	 * @return
	 */
	List<FundSetlle> findAllByTemplate(final FundSetlle tmp);
}
