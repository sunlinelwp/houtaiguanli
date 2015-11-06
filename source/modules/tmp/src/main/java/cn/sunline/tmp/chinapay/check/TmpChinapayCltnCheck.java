package cn.sunline.tmp.chinapay.check;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(TmpChinapayCltnCheckPK.class)
@Table(name = "tmp_chinapay_cltn_check")
public class TmpChinapayCltnCheck {

	@Id
	@Column(name = "check_date")
	private String checkDate;
	@Column(name = "merchant_cd")
	private String merchantCd;
	@Id
	@Column(name = "merchant_dt")
	private String merchantDt;
	@Id
	@Column(name = "billno")
	private String billNo;
	@Column(name = "acctno")
	private String acctno;
	@Column(name = "trans_amt")
	private BigDecimal transAmt;
	@Column(name = "cp_status")
	private String cpStatus;
	@Column(name = "chk_status")
	private String chkStatus;
	@Column(name = "fee_amt")
	private BigDecimal feeAmt;
	@Column(name = "sign_status")
	private String signStatus;
	@Column(name = "check_status")
	private String checkStatus;
	
	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getCpStatus() {
		return cpStatus;
	}

	public void setCpStatus(String cpStatus) {
		this.cpStatus = cpStatus;
	}
	
	public String getMerchantCd() {
		return merchantCd;
	}

	public void setMerchantCd(String merchantCd) {
		this.merchantCd = merchantCd;
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

	public String getAcctno() {
		return acctno;
	}

	public void setAcctno(String acctno) {
		this.acctno = acctno;
	}

	public BigDecimal getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}

	public String getChkStatus() {
		return chkStatus;
	}

	public void setChkStatus(String chkStatus) {
		this.chkStatus = chkStatus;
	}

	public BigDecimal getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}


}
