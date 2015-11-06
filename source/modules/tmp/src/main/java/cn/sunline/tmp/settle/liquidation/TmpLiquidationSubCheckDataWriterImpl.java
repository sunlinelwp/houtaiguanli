package cn.sunline.tmp.settle.liquidation;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sunline.file.DataWriter;

@Service("tmpLiquidationSubCheckDataWriter")
public class TmpLiquidationSubCheckDataWriterImpl implements DataWriter {

	@Resource
	private TmpLiquidationSubCheckService tmpLiquidationSubCheckServiceImpl;

	@Override
	public void writerDBData(List<String[]> datas, String inputDate) {
		List<TmpLiquidationSubCheck> ls = new LinkedList<>();
		for (String[] data : datas) {
			TmpLiquidationSubCheck pay = new TmpLiquidationSubCheck();
			pay.setCheckDate(inputDate);
			pay.setAccountingDate(data[0]);
			pay.setPackageSubject(data[1]);
			pay.setMicroSubjects(data[2]);
			pay.setLendingDirection(data[3]);
			pay.setDailyOccurrence(new BigDecimal(data[4].toString()));
			pay.setTransitionalAmount(new BigDecimal(data[5].toString()));
			pay.setFlowOccurrenceAmount(new BigDecimal(data[6].toString()));
			pay.setMicroBalance(new BigDecimal(data[7].toString()));
			pay.setMicroBalanceDirection(data[8]);
			pay.setDayBalance(new BigDecimal(data[9].toString()));
			pay.setDayBalanceDirection(data[10]);
			pay.setCoreBalance(new BigDecimal(data[11].toString()));
			pay.setCheckLedger(data[12]);
			pay.setBalanceResults(data[13]);
			pay.setDifference(new BigDecimal(data[14].toString()));
			ls.add(pay);
		}
		tmpLiquidationSubCheckServiceImpl.saveTmpLiquidationSubCheck(ls);
	}

	@Override
	public void writerHeadData(String[] datas, String inputDate) {
		TmpLiquidationSubHeadCheck pay = new TmpLiquidationSubHeadCheck();
		pay.setCheckDate(datas[0]);
		pay.setOperationFlow(datas[1]);
		pay.setTotalNumber(datas[2]);
		pay.setSuccessfulIdentification(datas[3]);
		tmpLiquidationSubCheckServiceImpl.saveTmpLiquidationSubHeadCheck(pay);
	}
}
