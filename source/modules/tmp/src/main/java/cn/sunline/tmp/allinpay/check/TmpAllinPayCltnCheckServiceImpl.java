package cn.sunline.tmp.allinpay.check;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service("tmpAllinPayCltnCheckService")
public class TmpAllinPayCltnCheckServiceImpl implements
		TmpAllinPayCltnCheckService {
	@Resource
	private TmpAllinPayCltnCheckRepository tmpAllinPayCltnCheckRepository;
	
	@Resource
	private TmpAllinPayCltnHeadCheckRepository tmpAllinPayCltnHeadCheckRepository;
	
	@Autowired
	TmpAllinPayCltnCheckDao tmpAllinPayCltnCheckDao;

	@Override
	@Transactional
	public int deleteByCheckDate(String checkDate) {
		return tmpAllinPayCltnCheckRepository.deleteByCheckDate(checkDate);
	}

	@Override
	public int saveTmpAllinPayCltnCheck(List<TmpAllinPayCltnCheck> entity) {
		List<TmpAllinPayCltnCheck> ret = tmpAllinPayCltnCheckRepository
				.save(entity);

		tmpAllinPayCltnCheckRepository.flush();

		return ret.size();
	}

	@Override
	@Transactional
	public void deleteByHeadCheckDate(String checkDate) {
		tmpAllinPayCltnHeadCheckRepository.deleteByHeadCheckDate(checkDate);
		
	}

	@Override
	public void saveTmpAllinPayCltnHeadCheck(TmpAllinPayCltnHeadCheck entity) {
		tmpAllinPayCltnHeadCheckRepository.saveAndFlush(entity);
	}

	@Override
	public Page<TmpAllinPayCltnCheck> queryEntitiesByTemplateWithPage(
			TmpAllinPayCltnCheck tmp, Pageable pageable) {
		return tmpAllinPayCltnCheckDao.queryEntitiesByTemplate(tmp,pageable);
	}

	@Override
	@Transactional
	public int updateStatus(String checkDate, String merchantDt,String billNo, String checkStatus, String billid) {
		
		return tmpAllinPayCltnCheckRepository.updateStatus(checkDate,merchantDt, billNo, checkStatus, billid);
	}

	@Override
	public boolean checkIsExitByCheckDate(String checkDate) {
		return tmpAllinPayCltnHeadCheckRepository.exists(checkDate);
	}

	@Override
	@Transactional
	public int upHeadCheckStatus(String checkDate, String checkStatus) {
		return tmpAllinPayCltnHeadCheckRepository.upByHeadCheckDate(checkDate, checkStatus);
	}

	@Override
	public TmpAllinPayCltnHeadCheck getCltnHeadCheckInfo(String checkDate) {
		return tmpAllinPayCltnHeadCheckRepository.findOne(checkDate);
	}

	@Override
	public int getUnDealCount(String checkDate, String status) {
		return tmpAllinPayCltnCheckRepository.getCPCltnCountByStatus(checkDate, status);
	}

}
