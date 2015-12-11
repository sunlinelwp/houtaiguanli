package cn.sunline.tmp.yqrx.check;

import java.util.ArrayList;
import java.util.List;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sunline.file.DataWriter;
import cn.sunline.utils.NumberTools;

@Service("TmpYqrxAmouDataWriter")
public class TmpYqrxAmouDataWriterImpl implements DataWriter {
	@Autowired
	private TmpYqrxAmouService tmpYqrxAmouService;

	@Override
	public void writerDBData(List<String[]> datas, String inputDate) {
		List<TmpYqrxAmou> list = new ArrayList<TmpYqrxAmou>();
		for(String[] data :datas ){
			TmpYqrxAmou tya = new TmpYqrxAmou();
			tya.setKeepdt(data[0]);//清算日期
			tya.setCrcycd(data[1]);//货币代号
			tya.setTranam(NumberTools.string2BigDecimalMill(data[2]));//交易金额
			tya.setAcctno(data[3]);//客户账号(电子账号)
			tya.setFronsq(data[4]);//前置流水
			tya.setFrondt(data[5]);//前置日期
			tya.setCardno(data[6]);//对方卡号
			tya.setAcctnm(data[7]);//客户名称
			tya.setTranfe(NumberTools.string2BigDecimalMill(data[8]));//费用
			tya.setLinkno(data[9]);//连笔号
			tya.setCometp(data[10]);//来源类型：1-失败交易，2-回款
			tya.setStates("0");//处理状态：0-未处理，1-已处理
			
			list.add(tya);
		}
		tmpYqrxAmouService.saveTmpYqrxAmou(list);
	}

	@Override
	public void writerHeadData(String[] data, String inputDate) {
		List<TmpYqrxAmou> list = new ArrayList<TmpYqrxAmou>();
			TmpYqrxAmou tya = new TmpYqrxAmou();
			tya.setKeepdt(data[0]);//清算日期
			tya.setCrcycd(data[1]);//货币代号
			tya.setTranam(NumberTools.string2BigDecimalMill(data[2]));//交易金额
			tya.setAcctno(data[3]);//客户账号(电子账号)
			tya.setFronsq(data[4]);//前置流水
			tya.setFrondt(data[5]);//前置日期
			tya.setCardno(data[6]);//对方卡号
			tya.setAcctnm(data[7]);//客户名称
			tya.setTranfe(NumberTools.string2BigDecimalMill(data[8]));//费用
			tya.setLinkno(data[9]);//连笔号
			tya.setCometp(data[10]);//来源类型：1-失败交易，2-回款
			tya.setStates("0");//处理状态：0-未处理，1-已处理
			
			list.add(tya);
		tmpYqrxAmouService.saveTmpYqrxAmou(list);
	}

}
