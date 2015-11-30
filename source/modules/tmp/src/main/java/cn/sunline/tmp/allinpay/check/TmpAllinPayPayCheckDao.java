package cn.sunline.tmp.allinpay.check;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface TmpAllinPayPayCheckDao {
	
	public Specification<TmpAllinPayPayCheck> getSpecification(final TmpAllinPayPayCheck tmp);
	/**
	 * 根据模版分页查询实体
	 * @param tmp 模版对象
     * @param pageable 分页排序模版
     * @return 查询到的实体对象集合
	 */
	public Page<TmpAllinPayPayCheck> queryEntitiesByTemplate(final TmpAllinPayPayCheck tmp,Pageable pageable);
}
