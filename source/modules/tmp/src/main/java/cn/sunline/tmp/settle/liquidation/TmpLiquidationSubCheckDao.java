package cn.sunline.tmp.settle.liquidation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface TmpLiquidationSubCheckDao {
	
	public Specification<TmpLiquidationSubCheck> getSpecification(final TmpLiquidationSubCheck tmp);
	/**
	 * 根据模版分页查询实体
	 * @param tmp 模版对象
     * @param pageable 分页排序模版
     * @return 查询到的实体对象集合
	 */
	public Page<TmpLiquidationSubCheck> queryEntitiesByTemplate(final TmpLiquidationSubCheck tmp,Pageable pageable);
}
