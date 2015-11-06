package cn.sunline.tmp.fund.settle;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("FundSetlleService")
public class FundSetlleServiceImpl implements FundSetlleService {
	
	@Resource
	private FundSetlleRepository repository;
	
	@Autowired
	FundSetlleDao setlleDao;
	
	@Override
	public List<FundSetlle> getFundSetlleByDealDt(String dealdt) {
		// TODO Auto-generated method stub
		FundSetlle tmp = new FundSetlle();
		tmp.setDealdt(dealdt);
		return setlleDao.findAllByTemplate(tmp);
	}

	@Override
	public void saveFundSetlle(List<FundSetlle> entity) {
		repository.save(entity);
		repository.flush();
	}

	@Override
	@Transactional
	public int updateStatus(String dealdt, String trantp, String status) {
		return repository.updateStatus(dealdt, trantp, status);
	}

	@Override
	public List<FundSetlle> getFundSetlleByDealDtAndTrantp(String dealdt,
			String trantp) {
		FundSetlle tmp = new FundSetlle();
		tmp.setDealdt(dealdt);
		tmp.setTrantp(trantp);
		return setlleDao.findAllByTemplate(tmp);
	}

}
