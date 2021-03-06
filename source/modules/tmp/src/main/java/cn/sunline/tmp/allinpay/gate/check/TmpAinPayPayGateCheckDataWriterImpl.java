package cn.sunline.tmp.allinpay.gate.check;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sunline.file.DataWriter;
import cn.sunline.utils.NumberTools;

@Service("tmpAinPayPayCheckDataWriter")
public class TmpAinPayPayGateCheckDataWriterImpl implements DataWriter{
	
	@Resource
	private TmpAinPayPayGateCheckService tmpAinPayPayGateCheckService;

	@Override
	public void writerDBData(List<String[]> datas, String inputDate) {
		List<TmpAinPayPayGateCheck> ls = new LinkedList<>();
		List<String[]> dataArray = new ArrayList<>();
		for (String[] data : datas) {
			
//			if ("0".equals(data[3])) {
				TmpAinPayPayGateCheck pay = new TmpAinPayPayGateCheck();
				pay.setCheckDate(inputDate);
				
				
				pay.setMerchantDt(data[0]);
				pay.setBillno(data[1]);
				pay.setAcctno(data[4]);
				if (data[5] == null || "".equals(data[5])) {
					pay.setTransAmt(BigDecimal.ZERO);
				} else {
					pay.setTransAmt(new BigDecimal(data[5]));
				}
				if (data[6] == null || "".equals(data[6])) {
					pay.setFeeAmt(BigDecimal.ZERO);
				} else {
					pay.setFeeAmt(new BigDecimal(data[6]));
				}
				pay.setCoreDate(data[12]);
				pay.setCoreSeqno(data[13]);
				pay.setChkStatus(data[10]);
				pay.setAcoutStatus(data[8]);
				pay.setTradeStatus(data[9]);
				if (data[7] == null || "".equals(data[7])) {
					pay.setClearAmt("");
				} else {
					pay.setClearAmt(data[7]);
				}
				pay.setKeyElement(data[11]);
				
				pay.setSignStatus("0");
				pay.setCheckStatus("N");
				
				ls.add(pay);
//			} else if ("1".equals(data[3])) {
//				dataArray.add(data);
//			}
			
		}
		//将代扣时候的数据插入到代扣的表中
//		if (dataArray.size() > 0) {
//			tmpAinPayCltnCheckDataWriter.writerDBData(dataArray, inputDate);
//		}
		if (ls.size() > 0) {
			tmpAinPayPayGateCheckService.saveTmpAinPayPayGateCheck(ls);
		}
	}

	@Override
	public void writerHeadData(String[] datas, String inputDate) {
		TmpAinPayPayGateHeadCheck pay = new TmpAinPayPayGateHeadCheck();
		pay.setCheckDate(inputDate);
		if (datas[6] == null || "".equals(datas[6])) {
			pay.setFee(BigDecimal.ZERO);
		} else {
			pay.setFee(new BigDecimal(datas[6]));
		}
		if (datas[6] == null || "".equals(datas[6])) {
			pay.setAmount(BigDecimal.ZERO);
		} else {
			pay.setAmount(new BigDecimal(datas[3]));
		}
		pay.setCheckSeqno(datas[10]);
		System.out.println("111"+datas[7]+"222");
		if (datas[7] == null) {
			pay.setClearAmt(BigDecimal.ZERO);
		} else if ("".equals(datas[7])) {
			pay.setClearAmt(BigDecimal.ZERO);
		} else {
			pay.setClearAmt(new BigDecimal(datas[7]));
		}
		pay.setClearDate(datas[8]);
		if (datas[5] == null || "".equals(datas[5])) {
			pay.setRefAmt(BigDecimal.ZERO);
		} else {
			pay.setRefAmt(new BigDecimal(datas[5]));
		}
		pay.setRefund(datas[4]);
		pay.setSuccesCount(datas[2]);
		pay.setTradeCount(datas[1]);
		pay.setCheckStatus("N");
		tmpAinPayPayGateCheckService.saveTmpAinPayPayGateHeadCheck(pay);
	}

}
