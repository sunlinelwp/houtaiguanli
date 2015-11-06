package cn.sunline.tmp.unionpay.check;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(TmpUnionPayPayCheckPK.class)
@Table(name = "tmp_unionpay_pay_check")
public class TmpUnionPayPayCheck {
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
	private String billno;

	@Column(name = "acctno")
	private String acctno;

	@Column(name = "trans_amt")
	private BigDecimal transAmt;

	@Column(name = "fee_amt")
	private BigDecimal feeAmt;

	@Column(name = "core_date")
	private String coreDate;

	@Column(name = "core_seqno")
	private String coreSeqno;

	@Column(name = "chk_status")
	private String chkStatus;

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

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
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

	public BigDecimal getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getCoreDate() {
		return coreDate;
	}

	public void setCoreDate(String coreDate) {
		this.coreDate = coreDate;
	}

	public String getCoreSeqno() {
		return coreSeqno;
	}

	public void setCoreSeqno(String coreSeqno) {
		this.coreSeqno = coreSeqno;
	}

	public String getChkStatus() {
		return chkStatus;
	}

	public void setChkStatus(String chkStatus) {
		this.chkStatus = chkStatus;
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
