package cn.sunline.tmp.settle.liquidation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TmpLiquidationSubCheckService {

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
	int saveTmpLiquidationSubCheck(List<TmpLiquidationSubCheck> entity);

	boolean checkIsExitByCheckDate(String checkDate);

	int deleteByHeadCheckDate(String checkDate);

	void saveTmpLiquidationSubHeadCheck(TmpLiquidationSubHeadCheck entity);

	Page<TmpLiquidationSubCheck> queryEntitiesByTemplateWithPage(
			TmpLiquidationSubCheck tmp, Pageable pageable);

	TmpLiquidationSubHeadCheck getPayHeadCheckInfo(String checkdate);
}
