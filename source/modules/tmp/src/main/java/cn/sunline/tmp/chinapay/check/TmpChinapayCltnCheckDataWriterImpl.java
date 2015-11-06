package cn.sunline.tmp.chinapay.check;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sunline.file.DataWriter;
import cn.sunline.utils.NumberTools;

@Service("tmpChinapayCltnCheckDataWriter")
public class TmpChinapayCltnCheckDataWriterImpl implements DataWriter {

	@Resource
	private TmpChinapayCltnCheckService tmpChinapayCltnCheckService;
	

	@Override
	public void writerDBData(List<String[]> datas, String inputDate) {
		List<TmpChinapayCltnCheck> ls = new LinkedList<>();
		for (String[] data : datas) {
			TmpChinapayCltnCheck cltn = new TmpChinapayCltnCheck();
			cltn.setCheckDate(inputDate);

			cltn.setMerchantCd(data[0]);
			cltn.setMerchantDt(data[1]);
			cltn.setBillNo(data[2]);
			cltn.setAcctno(data[3]);
			cltn.setTransAmt(NumberTools.string2BigDecimalMill(data[5]));
			cltn.setFeeAmt(new BigDecimal(data[10]));
			cltn.setCpStatus(data[8]);
			//状态不符时处理
			String chkStatus = data[9];
			if("5".equals(chkStatus) && "1001".equals(data[8]) && !"0".equals(data[7])){
				chkStatus = "3";
			}
			cltn.setChkStatus(chkStatus);
			cltn.setSignStatus(data[11]);
			cltn.setCheckStatus("N");

			ls.add(cltn);
		}

		tmpChinapayCltnCheckService.saveTmpChinapayCltnCheck(ls);
	}

	@Override
	public void writerHeadData(String[] datas, String inputDate) {
		TmpChinapayCltnHeadCheck cltn = new TmpChinapayCltnHeadCheck();
		cltn.setCheckDate(inputDate);
		cltn.setFee(new BigDecimal(datas[1]));
		cltn.setAmount(new BigDecimal(datas[2]));
		cltn.setCheckStatus("N");

		tmpChinapayCltnCheckService.saveTmpChinapayCltnHeadCheck(cltn);
		
	}

}
