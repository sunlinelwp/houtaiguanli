package cn.sunline.tmp.fund.settle;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service("BillDetailService")
public class BillDetailServiceImpl implements BillDetailService {
	@Resource
	private BillDetailRepository billDetailRepository;
	
	@Autowired
	BillDetailDao billDetailDao;
	
	@Override
	public void saveBillDetail(BillDetail entity) {

		billDetailRepository.saveAndFlush(entity);
	}

	
	@Override
	public Page<BillDetail> getBillDetailPage(BillDetail tmp,Pageable pageable) {
		return billDetailDao.billDetailByTemplate(tmp,pageable);
	}


	@Override
	public int getBillCountByDate(String date) {
		// TODO Auto-generated method stub
		return billDetailRepository.getBillCountByCheckDate(date);
	}

}
