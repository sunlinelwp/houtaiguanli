package cn.sunline.tmp.insu.check;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TmpBackInsuBillService {
	
	int saveTmpBackInsuBill(List<TmpBackInsuBill> entity);
	
	int updateStatus(String polino , String checkStatus , String msg,String transq);
	
	boolean checkIsExitByDealdt(String dealdt);
	
	Page<TmpBackInsuBill> queryEntitiesByTemplateWithPage(TmpBackInsuBill tmp,Pageable pageable);
	
	TmpBackInsuBill getBillInfoByPolino(String polino);
	
	List<TmpBackInsuBill> selectAll(String acctdt); 
	
	/**
	 * 查询汇总数据
	 * @param dealdt
	 * @return
	 */
	BigDecimal getTotlamByDealdt(String dealdt);
}
