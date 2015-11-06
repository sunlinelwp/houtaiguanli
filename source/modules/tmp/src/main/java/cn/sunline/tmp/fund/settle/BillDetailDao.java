package cn.sunline.tmp.fund.settle;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;



public interface BillDetailDao {
	public Specification<BillDetail> getAllSpecification(final BillDetail tmp);
	/**
	 * 根据日期查询所有记录
	 * @param tmp
	 * @return
	 */
	public Page<BillDetail> billDetailByTemplate(final BillDetail tmp,Pageable pageable);
}
