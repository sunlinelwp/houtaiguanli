package cn.sunline.tmp.allinpay.gate.check;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("tmpAinPayPayGateCheckService")
public class TmpAinPayPayGateCheckServiceImpl implements
		TmpAinPayPayGateCheckService {

	@Resource
	private TmpAinPayPayGateCheckRepository tmpAinPayPayGateCheckRepository;
	
	@Autowired
	TmpAinPayPayGateCheckDao tmpAinPayPayGateCheckDao;
	@Resource
	private TmpAinPayPayGateHeadCheckRepository tmpAinPayPayGateHeadCheckRepository;

	@Override
	public int deleteByCheckDate(String checkDate) {
		return tmpAinPayPayGateCheckRepository.deleteByCheckDate(checkDate);
	}

	@Override
	public int saveTmpAinPayPayGateCheck(List<TmpAinPayPayGateCheck> entity) {
		List<TmpAinPayPayGateCheck> ret = tmpAinPayPayGateCheckRepository
				.save(entity);

		tmpAinPayPayGateCheckRepository.flush();

		return ret.size();
	}

	@Override
	public int deleteByHeadCheckDate(String checkDate) {
		return tmpAinPayPayGateHeadCheckRepository.deleteByHeadCheckDate(checkDate);
	}

	@Override
	@Transactional
	public void saveTmpAinPayPayGateHeadCheck(TmpAinPayPayGateHeadCheck entity) {
		tmpAinPayPayGateHeadCheckRepository.saveAndFlush(entity);

	}

	@Override
	public Page<TmpAinPayPayGateCheck> queryEntitiesByTemplateWithPage(TmpAinPayPayGateCheck tmp,
			Pageable pageable) {
		return tmpAinPayPayGateCheckDao.queryEntitiesByTemplate(tmp,pageable);
	}

	@Override
	@Transactional
	public int updateStatus(String checkDate, String merchantDt, String cpSeqno,String checkStatus) {
		return tmpAinPayPayGateCheckRepository.updateStatus(checkDate, merchantDt, cpSeqno,checkStatus);
	}

	@Override
	public boolean checkIsExitByCheckDate(String checkDate) {
		return tmpAinPayPayGateHeadCheckRepository.exists(checkDate);
	}

	@Override
	@Transactional
	public int upHeadCheckStatus(String checkDate, String checkStatus) {
		return tmpAinPayPayGateHeadCheckRepository.upByHeadCheckDate(checkDate, checkStatus);
	}

	@Override
	public TmpAinPayPayGateHeadCheck getPayHeadCheckInfo(String checkdate) {
		
		return tmpAinPayPayGateHeadCheckRepository.findOne(checkdate);
	}

	@Override
	public int getUnDealCount(String checkDate, String status) {
		return tmpAinPayPayGateCheckRepository.getCPCltnCountByStatus(checkDate, status);
	}

}
