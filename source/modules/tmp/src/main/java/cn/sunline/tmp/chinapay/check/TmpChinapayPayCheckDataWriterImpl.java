package cn.sunline.tmp.chinapay.check;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sunline.file.DataWriter;
import cn.sunline.utils.NumberTools;

@Service("tmpChinapayPayCheckDataWriter")
public class TmpChinapayPayCheckDataWriterImpl implements DataWriter {
	@Resource
	private TmpChinapayPayCheckService tmpChinapayPayCheckServiceImpl;

	@Override
	public void writerDBData(List<String[]> datas, String inputDate) {
		List<TmpChinapayPayCheck> ls = new LinkedList<>();
		for (String[] data : datas) {
			TmpChinapayPayCheck pay = new TmpChinapayPayCheck();
			pay.setCheckDate(inputDate);

			pay.setMerchantCd(data[0]);
			pay.setMerchantDt(data[1]);
			pay.setCpSeqno(data[2]);
			pay.setCpDt(data[3]);
			pay.setAcctno(data[5]);
			pay.setAcctName(data[6]);
			pay.setTransAmt(NumberTools.string2BigDecimalMill(data[7]));
			pay.setFeeAmt(NumberTools.string2BigDecimalMill(data[8]));
			pay.setStatus(data[11]);
			pay.setCoreDate(data[12]);
			pay.setCoreSeqno(data[13]);
			String chkStatus = data[14];
			if ("5".equals(chkStatus)
					&& ("6".equals(data[11]) || "9".equals(data[11]))
					&& "0".equals(data[9])) {
				chkStatus = "2";
			}
			pay.setChkStatus(chkStatus);
			pay.setSignStatus(data[15]);
			pay.setCheckStatus("N");

			ls.add(pay);
		}

		tmpChinapayPayCheckServiceImpl.saveTmpChinapayPayCheck(ls);
	}

	@Override
	public void writerHeadData(String[] datas, String inputDate) {
		TmpChinapayPayHeadCheck pay = new TmpChinapayPayHeadCheck();
		pay.setCheckDate(inputDate);
		pay.setFee(NumberTools.string2BigDecimalMill(datas[1]));
		pay.setAmount(NumberTools.string2BigDecimalMill(datas[2]));
		pay.setCheckStatus("N");
		tmpChinapayPayCheckServiceImpl.saveTmpChinapayPayHeadCheck(pay);
	}

}
