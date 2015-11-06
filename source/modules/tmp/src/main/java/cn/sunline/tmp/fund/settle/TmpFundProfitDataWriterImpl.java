package cn.sunline.tmp.fund.settle;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.sunline.file.DataWriter;
import cn.sunline.utils.NumberTools;

@Service("tmpFundProfitDataWriter")
public class TmpFundProfitDataWriterImpl implements DataWriter {
	private static Logger logger = LoggerFactory.getLogger(TmpFundProfitDataWriterImpl.class);
	@Resource
	private TmpFundProfitService tmpFundProfitService;
	
	@Override
	public void writerDBData(List<String[]> data, String inputDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writerHeadData(String[] datas, String inputDate) {
		TmpFundProfit profit = new TmpFundProfit();
		
		profit.setDealdt(inputDate);
		profit.setChekst("N");
		profit.setPrftam(NumberTools.string2BigDecimalMill(datas[7]));
		
		logger.debug("============profit.getPrftm"+profit.getPrftam());
		
		tmpFundProfitService.saveTmpFundProfit(profit);
		
	}

}
