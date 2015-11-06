package cn.sunline.tmp.unionpay.check;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface TmpUnionPayCltnCheckDao {
	
	public Specification<TmpUnionPayCltnCheck> getSpecification(final TmpUnionPayCltnCheck tmp);
	/**
	 * 根据模版分页查询实体
	 * @param tmp 模版对象
     * @param pageable 分页排序模版
     * @return 查询到的实体对象集合
	 */
	public Page<TmpUnionPayCltnCheck> queryEntitiesByTemplate(final TmpUnionPayCltnCheck tmp,Pageable pageable);
}
