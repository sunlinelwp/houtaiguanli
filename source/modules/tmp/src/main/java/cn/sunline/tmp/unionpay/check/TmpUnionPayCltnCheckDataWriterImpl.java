package cn.sunline.tmp.unionpay.check;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sunline.file.DataWriter;
import cn.sunline.utils.NumberTools;
@Service("tmpUnionPayCltnCheckDataWriter")
public class TmpUnionPayCltnCheckDataWriterImpl implements DataWriter {

	@Resource
	private TmpUnionPayCltnCheckService tmpUnionPayCltnCheckService;
	

	@Override
	public void writerDBData(List<String[]> datas, String inputDate) {
		List<TmpUnionPayCltnCheck> ls = new LinkedList<>();
		for (String[] data : datas) {
			TmpUnionPayCltnCheck cltn = new TmpUnionPayCltnCheck();
			cltn.setCheckDate(inputDate);

			cltn.setMerchantCd(data[0]);
			cltn.setMerchantDt(data[1]);
			cltn.setBillNo(data[2]);
			cltn.setAcctno(data[3]);
			cltn.setTransAmt(NumberTools.string2BigDecimalMill(data[5]));
			cltn.setFeeAmt(NumberTools.string2BigDecimalMill(data[9]));
			//状态不符时处理
			String chkStatus = data[8];
			cltn.setChkStatus(chkStatus);
			cltn.setSignStatus("0"); //验签结果 暂定为0-通过
			cltn.setCheckStatus("N");

			ls.add(cltn);
		}

		tmpUnionPayCltnCheckService.saveTmpUnionPayCltnCheck(ls);
	}

	@Override
	public void writerHeadData(String[] datas, String inputDate) {
		TmpUnionPayCltnHeadCheck cltn = new TmpUnionPayCltnHeadCheck();
		cltn.setCheckDate(inputDate);
		cltn.setFee(new BigDecimal(datas[1]));
		cltn.setAmount(new BigDecimal(datas[2]));
		cltn.setCheckStatus("N");

		tmpUnionPayCltnCheckService.saveTmpUnionPayCltnHeadCheck(cltn);
		
	}
}
