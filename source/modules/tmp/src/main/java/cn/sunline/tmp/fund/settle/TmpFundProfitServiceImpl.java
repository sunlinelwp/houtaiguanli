package cn.sunline.tmp.fund.settle;


import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("TmpFundProfitService")
public class TmpFundProfitServiceImpl implements TmpFundProfitService {
	@Resource
	private TmpFundProfitRepository tmpFundProfitRepository;
	
	@Autowired
	TmpFundProfitDao tmpFundProfitDao;
	
	@Override
	public void saveTmpFundProfit(TmpFundProfit entity) {

		tmpFundProfitRepository.saveAndFlush(entity);
	}

	@Override
	@Transactional
	public int updateStatus(String dealdt,String status) {
		return tmpFundProfitRepository.updateStatus(dealdt, status);
	}

	@Override
	public TmpFundProfit getFundProfitInfo(String dealdt) {
		return tmpFundProfitRepository.findOne(dealdt);
	}

}
