package cn.sunline.tmp.unionpay.check;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TmpUnionPayPayCheckService {
	
	/**
	 * 根据核对日期删除临时表数据
	 * 
	 * @param checkDate
	 *            核对日期
	 * @return 删除条数
	 */
	int deleteByCheckDate(String checkDate);

	/**
	 * 保存多条记录并提交到数据
	 * 
	 * @param entity
	 *            提交多条记录
	 * @return
	 */
	int saveTmpUnionPayPayCheck(List<TmpUnionPayPayCheck> entity);
	
	boolean checkIsExitByCheckDate(String checkDate);
	
	int updateStatus(String checkDate , String merchantDt , String cpSeqno , String checkStatus);
	
	int upHeadCheckStatus(String checkDate,String checkStatus);
	
	int deleteByHeadCheckDate(String checkDate);

	void saveTmpUnionPayPayHeadCheck(TmpUnionPayPayHeadCheck entity);
	
	Page<TmpUnionPayPayCheck> queryEntitiesByTemplateWithPage(TmpUnionPayPayCheck tmp,Pageable pageable);
	
	TmpUnionPayPayHeadCheck getPayHeadCheckInfo(String checkdate);
	
	/**
	 * 查询某状态下的记录数  
	 * @param checkDate  日期
	 * @param status 状态
	 * @return
	 */
	int getUnDealCount(String checkDate,String status);
}
