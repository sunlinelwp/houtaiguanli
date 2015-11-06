package cn.sunline.tmp.settle.liquidation;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("TmpLiquidationSubCheckService")
public class TmpLiquidationSubCheckServiceImpl implements
		TmpLiquidationSubCheckService {

	@Resource
	private TmpLiquidationSubCheckRepository tmpLiquidationSubCheckRepository;

	@Autowired
	TmpLiquidationSubCheckDao tmpLiquidationSubCheckDao;
	@Resource
	private TmpLiquidationSubHeadCheckRepository tmpLiquidationSubHeadCheckRepository;

	@Override
	@Transactional
	public int deleteByCheckDate(String checkDate) {
		return tmpLiquidationSubCheckRepository.deleteByCheckDate(checkDate);
	}

	@Override
	public int saveTmpLiquidationSubCheck(List<TmpLiquidationSubCheck> entity) {
		List<TmpLiquidationSubCheck> ret = tmpLiquidationSubCheckRepository
				.save(entity);

		tmpLiquidationSubCheckRepository.flush();

		return ret.size();
	}

	@Override
	@Transactional
	public int deleteByHeadCheckDate(String checkDate) {
		return tmpLiquidationSubHeadCheckRepository
				.deleteByHeadCheckDate(checkDate);
	}

	@Override
	@Transactional
	public void saveTmpLiquidationSubHeadCheck(TmpLiquidationSubHeadCheck entity) {
		tmpLiquidationSubHeadCheckRepository.saveAndFlush(entity);

	}

	@Override
	public Page<TmpLiquidationSubCheck> queryEntitiesByTemplateWithPage(
			TmpLiquidationSubCheck tmp, Pageable pageable) {
		return tmpLiquidationSubCheckDao.queryEntitiesByTemplate(tmp, pageable);
	}

	@Override
	public boolean checkIsExitByCheckDate(String checkDate) {
		return tmpLiquidationSubHeadCheckRepository.exists(checkDate);
	}

	@Override
	public TmpLiquidationSubHeadCheck getPayHeadCheckInfo(String checkdate) {

		return tmpLiquidationSubHeadCheckRepository.findOne(checkdate);
	}

}
