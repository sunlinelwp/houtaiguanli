package cn.sunline.tmp.allinpay.check;

import java.math.BigDecimal;
import java.util.ArrayList;
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
		List<String[]> dataArray = new ArrayList<>();
		for (String[] data : datas) {
			
			if ("0".equals(data[3])) {
				TmpAllinPayPayCheck pay = new TmpAllinPayPayCheck();
				pay.setCheckDate(inputDate);
				
				pay.setMerchantCd("商户号");
				pay.setMerchantDt(data[0]);
				pay.setBillno(data[1]);
				pay.setAcctno(data[4]);
				pay.setTransAmt(NumberTools.string2BigDecimalMill(data[5]));
				pay.setFeeAmt(NumberTools.string2BigDecimalMill(data[6]));
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
				
				ls.add(pay);
			} else if ("1".equals(data[3])) {
				dataArray.add(data);
			}
			
		}
		//将代扣时候的数据插入到代扣的表中
		if (dataArray.size() > 0) {
			tmpAllinPayCltnCheckDataWriter.writerDBData(dataArray, inputDate);
		}
		if (ls.size() > 0) {
			tmpAllinPayPayCheckService.saveTmpAllinPayPayCheck(ls);
		}
	}

	@Override
	public void writerHeadData(String[] datas, String inputDate) {
		TmpAllinPayPayHeadCheck pay = new TmpAllinPayPayHeadCheck();
		pay.setCheckDate(inputDate);
		pay.setFee(new BigDecimal(datas[5]));
		pay.setAmount(new BigDecimal(datas[4]));
		pay.setCheckStatus("N");
		tmpAllinPayPayCheckService.saveTmpAllinPayPayHeadCheck(pay);
	}

}
