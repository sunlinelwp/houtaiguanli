package cn.sunline.tmp.allinpay.check;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sunline.file.DataWriter;
import cn.sunline.utils.NumberTools;
@Service("tmpAllinPayCltnCheckDataWriter")
public class TmpAllinPayCltnCheckDataWriterImpl implements DataWriter {

	@Resource
	private TmpAllinPayCltnCheckService tmpAllinPayCltnCheckService;
	@Resource
	private TmpAllinPayPayCheckDataWriterImpl tmpAllinPayPayCheckDataWriter;
	

	@Override
	public void writerDBData(List<String[]> datas, String inputDate) {
		List<TmpAllinPayCltnCheck> ls = new LinkedList<>();
		List<String[]> dataArray = new ArrayList<>();
		for (String[] data : datas) {
			
			if ("0".equals(data[3])) {
				//代付
				dataArray.add(data);
			} else if ("1".equals(data[3])) {
				//代扣，代收
				TmpAllinPayCltnCheck cltn = new TmpAllinPayCltnCheck();
				cltn.setCheckDate(inputDate);
				
				cltn.setMerchantCd("商户号");
				cltn.setMerchantDt(data[0]);
				cltn.setBillNo(data[1]);
				cltn.setAcctno(data[4]);
				if (data[5] == null || "".equals(data[5])) {
					cltn.setTransAmt(BigDecimal.ZERO);
				} else {
					cltn.setTransAmt(NumberTools.string2BigDecimalMill(data[5]));
				}
				if (data[6] == null || "".equals(data[6])) {
					cltn.setFeeAmt(BigDecimal.ZERO);
				} else {
					cltn.setFeeAmt(NumberTools.string2BigDecimalMill(data[6]));
				}
				cltn.setCoreDate(data[12]);
				cltn.setCoreSeqno(data[13]);
				cltn.setAcoutStatus(data[7]);
				cltn.setBillid(data[2]);
				cltn.setTradeStatus(data[8]);
				cltn.setTlStatus(data[9]);
				cltn.setKeyElement(data[11]);
				//状态不符时处理
				String chkStatus = data[10];
				cltn.setChkStatus(chkStatus);
				cltn.setSignStatus("0"); //验签结果 暂定为0-通过
				cltn.setCheckStatus("N");
				
				ls.add(cltn);
				
			}
			
		}
		//将代付时候的数据插入到代付的表中
		if (dataArray.size() > 0) {
			tmpAllinPayPayCheckDataWriter.writerDBData(dataArray, inputDate);
		}
		if (ls.size() > 0) {
			tmpAllinPayCltnCheckService.saveTmpAllinPayCltnCheck(ls);
		}
	}

	@Override
	public void writerHeadData(String[] datas, String inputDate) {
		TmpAllinPayCltnHeadCheck cltn = new TmpAllinPayCltnHeadCheck();
		cltn.setCheckDate(inputDate);
		if (datas[6] == null || "".equals(datas[6])) {
			cltn.setFee(BigDecimal.ZERO);
		} else {
			cltn.setFee(NumberTools.string2BigDecimalMill(datas[6]));
		}
		BigDecimal amount = BigDecimal.ZERO;
		if (datas[2] == null || "".equals(datas[2])) {
			cltn.setAmount(amount);
		} else {
			cltn.setAmount(NumberTools.string2BigDecimalMill(datas[2]));
		}
		cltn.setCheckStatus("N");
		tmpAllinPayCltnCheckService.saveTmpAllinPayCltnHeadCheck(cltn);
		
	}
}
