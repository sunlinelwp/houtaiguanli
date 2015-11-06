package cn.sunline.tmp.settle.liquidation;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("TmpLiquidationTalCheckService")
public class TmpLiquidationTalCheckServiceImpl implements
		TmpLiquidationTalCheckService {

	@Resource
	private TmpLiquidationTalCheckRepository tmpLiquidationTalCheckRepository;

	@Autowired
	TmpLiquidationTalCheckDao tmpLiquidationTalCheckDao;
	@Resource
	private TmpLiquidationTalHeadCheckRepository tmpLiquidationTalHeadCheckRepository;

	@Override
	@Transactional
	public int deleteByCheckDate(String checkDate) {
		return tmpLiquidationTalCheckRepository.deleteByCheckDate(checkDate);
	}

	@Override
	public int saveTmpLiquidationTalCheck(List<TmpLiquidationTalCheck> entity) {
		List<TmpLiquidationTalCheck> ret = tmpLiquidationTalCheckRepository
				.save(entity);

		tmpLiquidationTalCheckRepository.flush();

		return ret.size();
	}

	@Override
	@Transactional
	public int deleteByHeadCheckDate(String checkDate) {
		return tmpLiquidationTalHeadCheckRepository
				.deleteByHeadCheckDate(checkDate);
	}

	@Override
	@Transactional
	public void saveTmpLiquidationTalHeadCheck(TmpLiquidationTalHeadCheck entity) {
		tmpLiquidationTalHeadCheckRepository.saveAndFlush(entity);

	}

	@Override
	public Page<TmpLiquidationTalCheck> queryEntitiesByTemplateWithPage(
			TmpLiquidationTalCheck tmp, Pageable pageable) {
		return tmpLiquidationTalCheckDao.queryEntitiesByTemplate(tmp, pageable);
	}

	@Override
	public boolean checkIsExitByCheckDate(String checkDate) {
		return tmpLiquidationTalHeadCheckRepository.exists(checkDate);
	}

	@Override
	public TmpLiquidationTalHeadCheck getPayHeadCheckInfo(String checkdate) {

		return tmpLiquidationTalHeadCheckRepository.findOne(checkdate);
	}

}
