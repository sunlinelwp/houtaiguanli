package cn.sunline.tmp.chinapay.check;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("TmpChinapayPayCheckService")
public class TmpChinapayPayCheckServiceImpl implements
		TmpChinapayPayCheckService {

	@Resource
	private TmpChinapayPayCheckRepository tmpChinapayPayCheckRepository;
	
	@Autowired
	TmpChinapayPayCheckDao tmpChinapayPayCheckDao;
	@Resource
	private TmpChinapayPayHeadCheckRepository tmpChinapayPayHeadCheckRepository;

	@Override
	public int deleteByCheckDate(String checkDate) {
		return tmpChinapayPayCheckRepository.deleteByCheckDate(checkDate);
	}

	@Override
	public int saveTmpChinapayPayCheck(List<TmpChinapayPayCheck> entity) {
		List<TmpChinapayPayCheck> ret = tmpChinapayPayCheckRepository
				.save(entity);

		tmpChinapayPayCheckRepository.flush();

		return ret.size();
	}

	@Override
	public int deleteByHeadCheckDate(String checkDate) {
		return tmpChinapayPayHeadCheckRepository.deleteByHeadCheckDate(checkDate);
	}

	@Override
	public void saveTmpChinapayPayHeadCheck(TmpChinapayPayHeadCheck entity) {
		tmpChinapayPayHeadCheckRepository.saveAndFlush(entity);

	}

	@Override
	public Page<TmpChinapayPayCheck> queryEntitiesByTemplateWithPage(TmpChinapayPayCheck tmp,
			Pageable pageable) {
		return tmpChinapayPayCheckDao.queryEntitiesByTemplate(tmp,pageable);
	}

	@Override
	@Transactional
	public int updateStatus(String checkDate, String merchantDt, String cpSeqno,String checkStatus) {
		return tmpChinapayPayCheckRepository.updateStatus(checkDate, merchantDt, cpSeqno,checkStatus);
	}

	@Override
	public boolean checkIsExitByCheckDate(String checkDate) {
		return tmpChinapayPayHeadCheckRepository.exists(checkDate);
	}

	@Override
	@Transactional
	public int upHeadCheckStatus(String checkDate, String checkStatus) {
		return tmpChinapayPayHeadCheckRepository.upByHeadCheckDate(checkDate, checkStatus);
	}

	@Override
	public TmpChinapayPayHeadCheck getPayHeadCheckInfo(String checkdate) {
		
		return tmpChinapayPayHeadCheckRepository.findOne(checkdate);
	}

	@Override
	public int getUnDealCount(String checkDate, String status) {
		return tmpChinapayPayCheckRepository.getCPCltnCountByStatus(checkDate, status);
	}


}
