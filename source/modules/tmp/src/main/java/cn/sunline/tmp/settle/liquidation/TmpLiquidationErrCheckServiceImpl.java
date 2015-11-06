package cn.sunline.tmp.settle.liquidation;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("TmpLiquidationErrCheckService")
public class TmpLiquidationErrCheckServiceImpl implements
		TmpLiquidationErrCheckService {

	@Resource
	private TmpLiquidationErrCheckRepository tmpLiquidationErrCheckRepository;

	@Autowired
	TmpLiquidationErrCheckDao tmpLiquidationErrCheckDao;
	@Resource
	private TmpLiquidationErrHeadCheckRepository tmpLiquidationErrHeadCheckRepository;

	@Override
	@Transactional
	public int deleteByCheckDate(String checkDate) {
		return tmpLiquidationErrCheckRepository.deleteByCheckDate(checkDate);
	}

	@Override
	public int saveTmpLiquidationErrCheck(List<TmpLiquidationErrCheck> entity) {
		List<TmpLiquidationErrCheck> ret = tmpLiquidationErrCheckRepository
				.save(entity);

		tmpLiquidationErrCheckRepository.flush();

		return ret.size();
	}

	@Override
	@Transactional
	public int deleteByHeadCheckDate(String checkDate) {
		return tmpLiquidationErrHeadCheckRepository
				.deleteByHeadCheckDate(checkDate);
	}

	@Override
	@Transactional
	public void saveTmpLiquidationErrHeadCheck(TmpLiquidationErrHeadCheck entity) {
		tmpLiquidationErrHeadCheckRepository.saveAndFlush(entity);

	}

	@Override
	public Page<TmpLiquidationErrCheck> queryEntitiesByTemplateWithPage(
			TmpLiquidationErrCheck tmp, Pageable pageable) {
		return tmpLiquidationErrCheckDao.queryEntitiesByTemplate(tmp, pageable);
	}

	@Override
	public boolean checkIsExitByCheckDate(String checkDate) {
		return tmpLiquidationErrHeadCheckRepository.exists(checkDate);
	}

	@Override
	public TmpLiquidationErrHeadCheck getPayHeadCheckInfo(String checkdate) {

		return tmpLiquidationErrHeadCheckRepository.findOne(checkdate);
	}

}
