package cn.sunline.tmp.allinpay.check;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sunline.file.DataWriter;
import cn.sunline.utils.NumberTools;

@Service("tmpAllinPayPayCheckDataWriter")
public class TmpAllinPayPayCheckDataWriterImpl implements DataWriter{
	
	@Resource
	private TmpAllinPayPayCheckService tmpAllinPayPayCheckService;
	@Resource
	private TmpAllinPayCltnCheckDataWriterImpl tmpAllinPayCltnCheckDataWriter;
	

	@Override
	public void writerDBData(List<String[]> datas, String inputDate) {
		List<TmpAllinPayPayCheck> ls = new LinkedList<>();
		long i = 0L;
		for (String[] data : datas) {
			
			if ("0".equals(data[3])) {
				TmpAllinPayPayCheck pay = new TmpAllinPayPayCheck();
				pay.setCheckDate(inputDate);
				
				pay.setMerchantCd("商户号");
				pay.setMerchantDt(data[0]);
				pay.setBillno(data[1]);
				pay.setAcctno(data[4]);
				if (data[5] == null || "".equals(data[5])) {
					pay.setTransAmt(BigDecimal.ZERO);
				} else {
					pay.setTransAmt(NumberTools.string2BigDecimalMill(data[5]));
				}
				pay.setTransAmt(NumberTools.string2BigDecimalMill(data[5]));
				if (data[6] == null || "".equals(data[6])) {
					pay.setFeeAmt(BigDecimal.ZERO);
				} else {
					pay.setFeeAmt(NumberTools.string2BigDecimalMill(data[6]));
				}
				pay.setCoreDate(data[12]);
				pay.setCoreSeqno(data[13]);
				pay.setChkStatus(data[10]);
				pay.setAcoutStatus(data[7]);
				pay.setBillid(data[2]);
				pay.setTradeStatus(data[8]);
				pay.setTlStatus(data[9]);
				pay.setKeyElement(data[11]);
				pay.setSignStatus("0");
				pay.setCheckStatus("N");
				
				pay.setTlCardno(data[14]);
				if (data[15] == null || "".equals(data[15])) {
					pay.setBankTranam(BigDecimal.ZERO);
				} else {
					pay.setBankTranam(NumberTools.string2BigDecimalMill(data[15]));
				}
				pay.setBankCardno(data[16]);
				pay.setTimetm(System.currentTimeMillis()+i);

				i++;
				ls.add(pay);
			}
		}

		if (ls.size() > 0) {
			tmpAllinPayPayCheckService.saveTmpAllinPayPayCheck(ls);
		}
	}

	@Override
	public void writerHeadData(String[] datas, String inputDate) {
		TmpAllinPayPayHeadCheck pay = new TmpAllinPayPayHeadCheck();
		pay.setCheckDate(inputDate);
		if (datas[5] == null || "".equals(datas[5])) {
			pay.setFee(BigDecimal.ZERO);
		} else {
			pay.setFee(NumberTools.string2BigDecimalMill(datas[5]));
		}
		if (datas[4] == null || "".equals(datas[4])) {
			pay.setAmount(BigDecimal.ZERO);
		} else {
			pay.setAmount(NumberTools.string2BigDecimalMill(datas[4]));
		}
		pay.setCheckStatus("N");
		tmpAllinPayPayCheckService.saveTmpAllinPayPayHeadCheck(pay);
	}

}
