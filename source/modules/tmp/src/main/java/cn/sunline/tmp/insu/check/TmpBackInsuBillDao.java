package cn.sunline.tmp.insu.check;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface TmpBackInsuBillDao {
	public Specification<TmpBackInsuBill> getSpecification(final TmpBackInsuBill tmp);
	/**
	 * 根据模版分页查询实体
	 * @param tmp 模版对象
     * @param pageable 分页排序模版
     * @return 查询到的实体对象集合
	 */
	public Page<TmpBackInsuBill> queryEntitiesByTemplate(final TmpBackInsuBill tmp,Pageable pageable);
	
	
	public Specification<TmpBackInsuBill> getAllSpecification(final TmpBackInsuBill tmp);
	/**
	 * 根据日期查询所有记录
	 * @param tmp
	 * @return
	 */
	List<TmpBackInsuBill> findAllByTemplate(final TmpBackInsuBill tmp);
}
