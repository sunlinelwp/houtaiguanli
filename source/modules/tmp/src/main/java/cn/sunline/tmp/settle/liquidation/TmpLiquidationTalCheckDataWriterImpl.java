package cn.sunline.tmp.settle.liquidation;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sunline.file.DataWriter;


@Service("tmpLiquidationTalCheckDataWriter")
public class TmpLiquidationTalCheckDataWriterImpl implements DataWriter{
	
	@Resource
	private TmpLiquidationTalCheckService tmpLiquidationTalCheckServiceImpl;

	

	@Override
	public void writerDBData(List<String[]> datas, String inputDate) {
		List<TmpLiquidationTalCheck> ls = new LinkedList<>();
		for (String[] data : datas) {
			TmpLiquidationTalCheck pay = new TmpLiquidationTalCheck();
			pay.setSyndicatedNumber(data[0]);
			pay.setCheckDate(inputDate);
			pay.setAccountingDate(data[1]);
			pay.setTrafficFlow(data[2]);
			pay.setLendingDirection(data[3]);
			pay.setCostOccurrenceAmount(new BigDecimal(data[4].toString()));
			pay.setFlowOccurrenceAmount(new BigDecimal(data[5].toString()));
			pay.setDifference(new BigDecimal(data[6].toString()));
			ls.add(pay);
		}

		tmpLiquidationTalCheckServiceImpl.saveTmpLiquidationTalCheck(ls);
	}

	@Override
	public void writerHeadData(String[] datas, String inputDate) {
		TmpLiquidationTalHeadCheck pay = new TmpLiquidationTalHeadCheck();
		pay.setCheckDate(datas[0]);
		pay.setOperationFlow(datas[1]);
		pay.setTotalNumber(datas[2]);
		tmpLiquidationTalCheckServiceImpl.saveTmpLiquidationTalHeadCheck(pay);
	}

}
