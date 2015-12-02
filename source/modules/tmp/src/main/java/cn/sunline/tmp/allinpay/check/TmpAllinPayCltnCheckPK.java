package cn.sunline.tmp.allinpay.check;

import java.io.Serializable;

public class TmpAllinPayCltnCheckPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String checkDate;
	
	private String merchantDt;
	
	private String billNo;

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getMerchantDt() {
		return merchantDt;
	}

	public void setMerchantDt(String merchantDt) {
		this.merchantDt = merchantDt;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	

}
