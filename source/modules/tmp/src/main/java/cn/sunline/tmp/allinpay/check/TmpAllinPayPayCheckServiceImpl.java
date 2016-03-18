package cn.sunline.tmp.allinpay.check;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("tmpAllinPayPayCheckService")
public class TmpAllinPayPayCheckServiceImpl implements
		TmpAllinPayPayCheckService {

	@Resource
	private TmpAllinPayPayCheckRepository tmpAllinPayPayCheckRepository;
	
	@Autowired
	TmpAllinPayPayCheckDao tmpAllinPayPayCheckDao;
	@Resource
	private TmpAllinPayPayHeadCheckRepository tmpAllinPayPayHeadCheckRepository;

	@Override
	public int deleteByCheckDate(String checkDate) {
		return tmpAllinPayPayCheckRepository.deleteByCheckDate(checkDate);
	}

	@Override
	public int saveTmpAllinPayPayCheck(List<TmpAllinPayPayCheck> entity) {
		List<TmpAllinPayPayCheck> ret = tmpAllinPayPayCheckRepository
				.save(entity);

		tmpAllinPayPayCheckRepository.flush();

		return ret.size();
	}

	@Override
	public int deleteByHeadCheckDate(String checkDate) {
		return tmpAllinPayPayHeadCheckRepository.deleteByHeadCheckDate(checkDate);
	}

	@Override
	@Transactional
	public void saveTmpAllinPayPayHeadCheck(TmpAllinPayPayHeadCheck entity) {
		tmpAllinPayPayHeadCheckRepository.saveAndFlush(entity);

	}

	@Override
	public Page<TmpAllinPayPayCheck> queryEntitiesByTemplateWithPage(TmpAllinPayPayCheck tmp,
			Pageable pageable) {
		return tmpAllinPayPayCheckDao.queryEntitiesByTemplate(tmp,pageable);
	}

	@Override
	@Transactional
	public int updateStatus(String checkDate, String merchantDt, String cpSeqno,String checkStatus, String billid) {
		return tmpAllinPayPayCheckRepository.updateStatus(checkDate, merchantDt, cpSeqno,checkStatus,billid);
	}

	@Override
	public boolean checkIsExitByCheckDate(String checkDate) {
		return tmpAllinPayPayHeadCheckRepository.exists(checkDate);
	}

	@Override
	@Transactional
	public int upHeadCheckStatus(String checkDate, String checkStatus) {
		return tmpAllinPayPayHeadCheckRepository.upByHeadCheckDate(checkDate, checkStatus);
	}

	@Override
	public TmpAllinPayPayHeadCheck getPayHeadCheckInfo(String checkdate) {
		
		return tmpAllinPayPayHeadCheckRepository.findOne(checkdate);
	}

	@Override
	public int getUnDealCount(String checkDate, String status) {
		return tmpAllinPayPayCheckRepository.getCPCltnCountByStatus(checkDate, status);
	}

}
