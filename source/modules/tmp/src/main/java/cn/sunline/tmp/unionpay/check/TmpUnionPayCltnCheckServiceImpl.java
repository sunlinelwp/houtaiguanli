package cn.sunline.tmp.unionpay.check;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service("TmpUnionPayCltnCheckService")
public class TmpUnionPayCltnCheckServiceImpl implements
		TmpUnionPayCltnCheckService {
	@Resource
	private TmpUnionPayCltnCheckRepository tmpUnionPayCltnCheckRepository;
	
	@Resource
	private TmpUnionPayCltnHeadCheckRepository tmpUnionPayCltnHeadCheckRepository;
	
	@Autowired
	TmpUnionPayCltnCheckDao tmpUnionPayCltnCheckDao;

	@Override
	@Transactional
	public int deleteByCheckDate(String checkDate) {
		return tmpUnionPayCltnCheckRepository.deleteByCheckDate(checkDate);
	}

	@Override
	public int saveTmpUnionPayCltnCheck(List<TmpUnionPayCltnCheck> entity) {
		List<TmpUnionPayCltnCheck> ret = tmpUnionPayCltnCheckRepository
				.save(entity);

		tmpUnionPayCltnCheckRepository.flush();

		return ret.size();
	}

	@Override
	@Transactional
	public void deleteByHeadCheckDate(String checkDate) {
		tmpUnionPayCltnHeadCheckRepository.deleteByHeadCheckDate(checkDate);
		
	}

	@Override
	public void saveTmpUnionPayCltnHeadCheck(TmpUnionPayCltnHeadCheck entity) {
		tmpUnionPayCltnHeadCheckRepository.saveAndFlush(entity);
	}

	@Override
	public Page<TmpUnionPayCltnCheck> queryEntitiesByTemplateWithPage(
			TmpUnionPayCltnCheck tmp, Pageable pageable) {
		return tmpUnionPayCltnCheckDao.queryEntitiesByTemplate(tmp,pageable);
	}

	@Override
	@Transactional
	public int updateStatus(String checkDate, String merchantDt,String billNo, String checkStatus) {
		
		return tmpUnionPayCltnCheckRepository.updateStatus(checkDate,merchantDt, billNo, checkStatus);
	}

	@Override
	public boolean checkIsExitByCheckDate(String checkDate) {
		return tmpUnionPayCltnHeadCheckRepository.exists(checkDate);
	}

	@Override
	@Transactional
	public int upHeadCheckStatus(String checkDate, String checkStatus) {
		return tmpUnionPayCltnHeadCheckRepository.upByHeadCheckDate(checkDate, checkStatus);
	}

	@Override
	public TmpUnionPayCltnHeadCheck getCltnHeadCheckInfo(String checkDate) {
		return tmpUnionPayCltnHeadCheckRepository.findOne(checkDate);
	}

	@Override
	public int getUnDealCount(String checkDate, String status) {
		return tmpUnionPayCltnCheckRepository.getCPCltnCountByStatus(checkDate, status);
	}

}
