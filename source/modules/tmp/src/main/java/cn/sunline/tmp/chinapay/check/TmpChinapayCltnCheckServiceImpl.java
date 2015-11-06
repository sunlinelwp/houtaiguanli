package cn.sunline.tmp.chinapay.check;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("TmpChinapayCltnCheckService")
public class TmpChinapayCltnCheckServiceImpl implements
		TmpChinapayCltnCheckService {

	@Resource
	private TmpChinapayCltnCheckRepository tmpChinapayCltnCheckRepository;
	
	@Resource
	private TmpChinapayCltnHeadCheckRepository tmpChinapayCltnHeadCheckRepository;
	
	@Autowired
	TmpChinapayCltnCheckDao tmpChinapayCltnCheckDao;

	@Override
	@Transactional
	public int deleteByCheckDate(String checkDate) {
		return tmpChinapayCltnCheckRepository.deleteByCheckDate(checkDate);
	}

	@Override
	public int saveTmpChinapayCltnCheck(List<TmpChinapayCltnCheck> entity) {
		List<TmpChinapayCltnCheck> ret = tmpChinapayCltnCheckRepository
				.save(entity);

		tmpChinapayCltnCheckRepository.flush();

		return ret.size();
	}

	@Override
	@Transactional
	public void deleteByHeadCheckDate(String checkDate) {
		tmpChinapayCltnHeadCheckRepository.deleteByHeadCheckDate(checkDate);
		
	}

	@Override
	public void saveTmpChinapayCltnHeadCheck(TmpChinapayCltnHeadCheck entity) {
		tmpChinapayCltnHeadCheckRepository.saveAndFlush(entity);
	}

	@Override
	public Page<TmpChinapayCltnCheck> queryEntitiesByTemplateWithPage(
			TmpChinapayCltnCheck tmp, Pageable pageable) {
		return tmpChinapayCltnCheckDao.queryEntitiesByTemplate(tmp,pageable);
	}

	@Override
	@Transactional
	public int updateStatus(String checkDate, String merchantDt,String billNo, String checkStatus) {
		
		return tmpChinapayCltnCheckRepository.updateStatus(checkDate,merchantDt, billNo, checkStatus);
	}

	@Override
	public boolean checkIsExitByCheckDate(String checkDate) {
		return tmpChinapayCltnHeadCheckRepository.exists(checkDate);
	}

	@Override
	@Transactional
	public int upHeadCheckStatus(String checkDate, String checkStatus) {
		return tmpChinapayCltnHeadCheckRepository.upByHeadCheckDate(checkDate, checkStatus);
	}

	@Override
	public TmpChinapayCltnHeadCheck getCltnHeadCheckInfo(String checkDate) {
		return tmpChinapayCltnHeadCheckRepository.findOne(checkDate);
	}

	@Override
	public int getUnDealCount(String checkDate, String status) {
		return tmpChinapayCltnCheckRepository.getCPCltnCountByStatus(checkDate, status);
	}

}
