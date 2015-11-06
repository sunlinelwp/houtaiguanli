package cn.sunline.tmp.chinapay.check;

import java.io.Serializable;

public class TmpChinapayPayCheckPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private String checkDate;

	private String merchantDt;

	private String cpSeqno;

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

	public String getCpSeqno() {
		return cpSeqno;
	}

	public void setCpSeqno(String cpSeqno) {
		this.cpSeqno = cpSeqno;
	}

}
