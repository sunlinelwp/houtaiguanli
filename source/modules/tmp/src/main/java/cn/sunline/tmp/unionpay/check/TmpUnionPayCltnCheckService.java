package cn.sunline.tmp.unionpay.check;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TmpUnionPayCltnCheckService {
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
	 * @param entity
	 * @return
	 */
	int saveTmpUnionPayCltnCheck(List<TmpUnionPayCltnCheck> entity);
	
	int updateStatus(String checkDate ,String merchantDt, String billNo , String checkStatus);
	
	int upHeadCheckStatus(String checkDate,String checkStatus);
	
	boolean checkIsExitByCheckDate(String checkDate);
	
	void deleteByHeadCheckDate(String checkDate);

	void saveTmpUnionPayCltnHeadCheck(TmpUnionPayCltnHeadCheck entity);
	
	Page<TmpUnionPayCltnCheck> queryEntitiesByTemplateWithPage(TmpUnionPayCltnCheck tmp,Pageable pageable);
	
	TmpUnionPayCltnHeadCheck getCltnHeadCheckInfo(String checkDate);
	
	/**
	 * 查询某状态下的记录数
	 * @param checkDate
	 * @param status
	 * @return
	 */
	int getUnDealCount(String checkDate,String status);
}
