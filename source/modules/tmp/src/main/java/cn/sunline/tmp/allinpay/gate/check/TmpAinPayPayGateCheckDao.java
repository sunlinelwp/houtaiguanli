package cn.sunline.tmp.allinpay.gate.check;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface TmpAinPayPayGateCheckDao {
	
	public Specification<TmpAinPayPayGateCheck> getSpecification(final TmpAinPayPayGateCheck tmp);
	/**
	 * 根据模版分页查询实体
	 * @param tmp 模版对象
     * @param pageable 分页排序模版
     * @return 查询到的实体对象集合
	 */
	public Page<TmpAinPayPayGateCheck> queryEntitiesByTemplate(final TmpAinPayPayGateCheck tmp,Pageable pageable);
}
