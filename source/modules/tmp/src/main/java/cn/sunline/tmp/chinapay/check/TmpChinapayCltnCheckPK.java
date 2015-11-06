package cn.sunline.tmp.chinapay.check;

import java.io.Serializable;

public class TmpChinapayCltnCheckPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private String checkDate;
	
	private String merchantDt;

	public String getMerchantDt() {
		return merchantDt;
	}

	public void setMerchantDt(String merchantDt) {
		this.merchantDt = merchantDt;
	}

	private String billNo;

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

}
