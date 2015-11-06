package cn.sunline.tmp.settle.liquidation;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sunline.file.DataWriter;
import cn.sunline.utils.NumberTools;

@Service("tmpLiquidationErrCheckDataWriter")
public class TmpLiquidationErrCheckDataWriterImpl implements DataWriter{
	
	@Resource
	private TmpLiquidationErrCheckService tmpLiquidationErrCheckServiceImpl;

	@Override
	public void writerDBData(List<String[]> datas, String inputDate) {
		List<TmpLiquidationErrCheck> ls = new LinkedList<>();
		for (String[] data : datas) {
			TmpLiquidationErrCheck pay = new TmpLiquidationErrCheck();
			pay.setCheckDate(inputDate);
			pay.setAccountingDate(data[0]);
			pay.setTrafficflow(data[1]);
			pay.setLendingdirection(data[2]);
			pay.setTransactionamount(new BigDecimal(data[3].toString()));
			pay.setAccountnumber(data[4]);
			pay.setReconciliation(data[5]);
			pay.setSyndicatednumber(data[6]);
			pay.setBanknumber(data[7]);
			pay.setMechanismnumber(data[8]);
			pay.setIounumber(data[9]);
			pay.setLoanstatus(data[10]);
			ls.add(pay);
		}

		tmpLiquidationErrCheckServiceImpl.saveTmpLiquidationErrCheck(ls);
	}

	@Override
	public void writerHeadData(String[] datas, String inputDate) {
		TmpLiquidationErrHeadCheck pay = new TmpLiquidationErrHeadCheck();
		pay.setCheckDate(datas[0]);
		pay.setOperationFlow(datas[1]);
		pay.setTotalNumber(datas[2]);
		pay.setTotalDebit(new BigDecimal(datas[3].toString()));
		pay.setTotalCredit(new BigDecimal(datas[4].toString()));
		tmpLiquidationErrCheckServiceImpl.saveTmpLiquidationErrHeadCheck(pay);
	}

}
