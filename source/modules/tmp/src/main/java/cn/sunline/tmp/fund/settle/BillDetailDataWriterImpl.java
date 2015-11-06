package cn.sunline.tmp.fund.settle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.sunline.file.DataWriter;
import cn.sunline.utils.NumberTools;

@Service("billDetailDataWriterImpl")
public class BillDetailDataWriterImpl implements DataWriter {
	private static Logger logger = LoggerFactory.getLogger(BillDetailDataWriterImpl.class);
	@Resource
	private BillDetailService billDetailService;
	
	@Resource
	private FundSetlleService fundSetlleService;
	
	@Override
	public void writerDBData(List<String[]> data, String inputDate) {
		// TODO Auto-generated method stub
		BillDetail bill = new BillDetail();
		logger.debug("======================="+data.size());
		BigDecimal inTotlam = BigDecimal.ZERO;//申购总金额
		BigDecimal ouTotlam = BigDecimal.ZERO;//赎回总金额
		String trantp = "";
		for(int i = 0;i<data.size();i++) {
			
			String[] datas = data.get(i);
			
			trantp = datas[4];
			BigDecimal tranam = NumberTools.string2BigDecimalMill(datas[13]);
			if(trantp.equals("00")){
				inTotlam = inTotlam.add(tranam);
			} else if(trantp.equals("01") || trantp.equals("02")){ //赎回和普通赎回
				ouTotlam = ouTotlam.add(tranam);
			}
			bill.setTrandt(inputDate);
			bill.setFundno(datas[3]);
			bill.setTrantp(datas[4]);
			bill.setCustac(datas[8]);
			bill.setFundst(datas[12]);
			bill.setTranam(tranam);
			bill.setTrantm(datas[15]);
			bill.setTrandt(datas[16]);
			
			logger.debug("============bill.getTrantm"+bill.getTrantm());
			
			billDetailService.saveBillDetail(bill);
			logger.debug("申购金额==================="+inTotlam);
			logger.debug("赎回金额==================="+ouTotlam);
			
		}
		
		//登记汇总数据
		logger.debug("申购总金额==================="+inTotlam);
		logger.debug("赎回总金额==================="+ouTotlam);
		List<FundSetlle> list = new ArrayList<FundSetlle>();
		FundSetlle inFund = new FundSetlle();
		inFund.setDealdt(inputDate);
		inFund.setStatus("0");
		inFund.setTotlam(inTotlam);
		inFund.setTrantp("0");//申购
		list.add(inFund);
		FundSetlle ouFund = new FundSetlle();
		ouFund.setDealdt(inputDate);
		ouFund.setStatus("0");
		ouFund.setTotlam(ouTotlam);
		ouFund.setTrantp("1");//赎回
		list.add(ouFund);
		fundSetlleService.saveFundSetlle(list);
	}

	@Override
	public void writerHeadData(String[] datas, String inputDate) {

		
	}

}
