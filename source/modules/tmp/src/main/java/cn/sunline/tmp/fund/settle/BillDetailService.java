package cn.sunline.tmp.fund.settle;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BillDetailService {
	
	/**
	 * 保存多条记录并提交到数据
	 * @param entity
	 * @return
	 */
	void saveBillDetail(BillDetail entity);
	/**
	 * 分页查询多条记录
	 * @param tmp
	 * @param pageable
	 * @return
	 */
	Page<BillDetail> getBillDetailPage(BillDetail temBillDetail,Pageable pageable);
	/*
	 * 调用service中的根据日期查询记录的方法，判断当前选中日期下的记录是否存在
	 * 
	 * 
	*/
	int  getBillCountByDate(String date);
}
