package cn.sunline.tmp.insu.check;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("TmpBackInsuBillService")
public class TmpBackInsuBillServiceImpl implements TmpBackInsuBillService {
	
	@Resource
	private TmpBackInsuBillRepository tmpBackInsuBillRepository;
	
	@Autowired
	TmpBackInsuBillDao tmpBackInsuBillDao;
	
	@Override
	@Transactional
	public int saveTmpBackInsuBill(List<TmpBackInsuBill> entity) {
		List<TmpBackInsuBill> ret = tmpBackInsuBillRepository
				.save(entity);

		tmpBackInsuBillRepository.flush();

		return ret.size();
	}

	@Override
	@Transactional
	public int updateStatus(String polino, String checkStatus , String msg , String transq) {
		return tmpBackInsuBillRepository.updateStatus(polino, checkStatus , msg , transq);
	}

	@Override
	public boolean checkIsExitByDealdt(String dealdt) {
		int ret = 0;
		if(ret == 0){
			return false;
		}
		return true;
	}

	@Override
	public Page<TmpBackInsuBill> queryEntitiesByTemplateWithPage(
			TmpBackInsuBill tmp, Pageable pageable) {
		
		return tmpBackInsuBillDao.queryEntitiesByTemplate(tmp, pageable);
	}

	@Override
	public TmpBackInsuBill getBillInfoByPolino(String polino) {
		TmpBackInsuBillPK id = new TmpBackInsuBillPK();
		id.setPolino(polino);
		return tmpBackInsuBillRepository.findOne(id);
	}

	@Override
	public List<TmpBackInsuBill> selectAll(String acctdt) {
		TmpBackInsuBill tmp = new TmpBackInsuBill();
		tmp.setDealdt(acctdt);
		 return tmpBackInsuBillDao.findAllByTemplate(tmp);
	}

	@Override
	public BigDecimal getTotlamByDealdt(String dealdt) {
		// TODO Auto-generated method stub
		return tmpBackInsuBillRepository.getTotlam(dealdt);
	}

}
