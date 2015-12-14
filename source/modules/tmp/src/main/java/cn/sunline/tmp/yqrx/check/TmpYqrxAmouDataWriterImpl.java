package cn.sunline.tmp.yqrx.check;

import java.util.ArrayList;
import java.util.List;








import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sunline.file.DataWriter;
import cn.sunline.utils.DateTools;
import cn.sunline.utils.NumberTools;

@Service("TmpYqrxAmouDataWriter")
public class TmpYqrxAmouDataWriterImpl implements DataWriter {
	@Autowired
	private TmpYqrxAmouService tmpYqrxAmouService;

	@Override
	public void writerDBData(List<String[]> datas, String inputDate) {
		List<TmpYqrxAmou> list = new ArrayList<TmpYqrxAmou>();
		int i = 1;
		for(String[] data :datas ){
			TmpYqrxAmou tya = new TmpYqrxAmou();
			String time = DateTools.getNow("yyyyMMddHHmmssSSS");
			tya.setAmouid(time + i);
			tya.setAcctno(data[0]);
			tya.setPayeac(data[1]);
			tya.setPayena(data[2]);
			tya.setTranam(data[3]);
			tya.setCrcycd(data[4]);
			tya.setChgeam(data[5]);
			tya.setPswdtp(data[6]);
			tya.setPwflag(data[7]);
			tya.setTranpw(data[8]);
			tya.setRemark(data[9]);
			tya.setBanknm(data[10]);
			tya.setProvic(data[11]);
			tya.setGarden(data[12]);
			tya.setFtbkcd(data[13]);
			tya.setAcctpr(data[14]);
			tya.setChnlcd(data[15]);
			tya.setPytype(data[16]);
			tya.setCometp(data[17]);
			tya.setStates("0");
			tya.setAmoudt(inputDate);
			
			list.add(tya);
			i++;
		}
		tmpYqrxAmouService.saveTmpYqrxAmou(list);
	}

	@Override
	public void writerHeadData(String[] data, String inputDate) {

	}

}
