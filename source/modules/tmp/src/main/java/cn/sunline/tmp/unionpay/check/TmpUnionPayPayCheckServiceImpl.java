package cn.sunline.tmp.unionpay.check;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("TmpUnionPayPayCheckService")
public class TmpUnionPayPayCheckServiceImpl implements
		TmpUnionPayPayCheckService {

	@Resource
	private TmpUnionPayPayCheckRepository tmpUnionPayPayCheckRepository;
	
	@Autowired
	TmpUnionPayPayCheckDao tmpUnionPayPayCheckDao;
	@Resource
	private TmpUnionPayPayHeadCheckRepository tmpUnionPayPayHeadCheckRepository;

	@Override
	public int deleteByCheckDate(String checkDate) {
		return tmpUnionPayPayCheckRepository.deleteByCheckDate(checkDate);
	}

	@Override
	public int saveTmpUnionPayPayCheck(List<TmpUnionPayPayCheck> entity) {
		List<TmpUnionPayPayCheck> ret = tmpUnionPayPayCheckRepository
				.save(entity);

		tmpUnionPayPayCheckRepository.flush();

		return ret.size();
	}

	@Override
	public int deleteByHeadCheckDate(String checkDate) {
		return tmpUnionPayPayHeadCheckRepository.deleteByHeadCheckDate(checkDate);
	}

	@Override
	@Transactional
	public void saveTmpUnionPayPayHeadCheck(TmpUnionPayPayHeadCheck entity) {
		tmpUnionPayPayHeadCheckRepository.saveAndFlush(entity);

	}

	@Override
	public Page<TmpUnionPayPayCheck> queryEntitiesByTemplateWithPage(TmpUnionPayPayCheck tmp,
			Pageable pageable) {
		return tmpUnionPayPayCheckDao.queryEntitiesByTemplate(tmp,pageable);
	}

	@Override
	@Transactional
	public int updateStatus(String checkDate, String merchantDt, String cpSeqno,String checkStatus) {
		return tmpUnionPayPayCheckRepository.updateStatus(checkDate, merchantDt, cpSeqno,checkStatus);
	}

	@Override
	public boolean checkIsExitByCheckDate(String checkDate) {
		return tmpUnionPayPayHeadCheckRepository.exists(checkDate);
	}

	@Override
	@Transactional
	public int upHeadCheckStatus(String checkDate, String checkStatus) {
		return tmpUnionPayPayHeadCheckRepository.upByHeadCheckDate(checkDate, checkStatus);
	}

	@Override
	public TmpUnionPayPayHeadCheck getPayHeadCheckInfo(String checkdate) {
		
		return tmpUnionPayPayHeadCheckRepository.findOne(checkdate);
	}

	@Override
	public int getUnDealCount(String checkDate, String status) {
		return tmpUnionPayPayCheckRepository.getCPCltnCountByStatus(checkDate, status);
	}

}
