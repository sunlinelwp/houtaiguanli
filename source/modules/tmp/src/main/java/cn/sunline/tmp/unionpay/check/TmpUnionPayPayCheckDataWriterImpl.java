package cn.sunline.tmp.unionpay.check;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sunline.file.DataWriter;
import cn.sunline.utils.NumberTools;

@Service("tmpUnionPayPayCheckDataWriter")
public class TmpUnionPayPayCheckDataWriterImpl implements DataWriter{
	
	@Resource
	private TmpUnionPayPayCheckService tmpUnionPayPayCheckServiceImpl;

	

	@Override
	public void writerDBData(List<String[]> datas, String inputDate) {
		List<TmpUnionPayPayCheck> ls = new LinkedList<>();
		for (String[] data : datas) {
			TmpUnionPayPayCheck pay = new TmpUnionPayPayCheck();
			pay.setCheckDate(inputDate);
			
			pay.setMerchantCd(data[0]);
			pay.setMerchantDt(data[1]);
			pay.setBillno(data[2]);
			pay.setAcctno(data[3]);
			pay.setTransAmt(NumberTools.string2BigDecimalMill(data[5]));
			pay.setFeeAmt(NumberTools.string2BigDecimalMill(data[11]));
			pay.setCoreDate(data[8]);
			pay.setCoreSeqno(data[9]);
			pay.setChkStatus(data[10]);
			pay.setSignStatus("0");
			pay.setCheckStatus("N");

			ls.add(pay);
		}

		tmpUnionPayPayCheckServiceImpl.saveTmpUnionPayPayCheck(ls);
	}

	@Override
	public void writerHeadData(String[] datas, String inputDate) {
		TmpUnionPayPayHeadCheck pay = new TmpUnionPayPayHeadCheck();
		pay.setCheckDate(inputDate);
		pay.setFee(new BigDecimal(datas[1]));
		pay.setAmount(new BigDecimal(datas[2]));
		pay.setCheckStatus("N");
		tmpUnionPayPayCheckServiceImpl.saveTmpUnionPayPayHeadCheck(pay);
	}

}
