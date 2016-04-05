package cn.sunline.tmp.allinpay.gate.check;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(TmpAinPayPayGateCheckPK.class)
@Table(name = "tmp_ainpay_pay_gate_check")
public class TmpAinPayPayGateCheck {
	@Id
	@Column(name = "check_date")
	private String checkDate;

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
	
	@Column(name = "acout_status")
	private String acoutStatus;//银行账务状态
	@Column(name = "trade_status")
	private String tradeStatus;//银行交易状态
	@Column(name = "tl_serial_no")
	private String tlSerialNo;//通联流水号
	@Column(name = "clear_amt")
	private String clearAmt;//清算金额
	@Column(name = "key_element")
	private String keyElement;//关键要素

	public String getKeyElement() {
		return keyElement;
	}

	public void setKeyElement(String keyElement) {
		this.keyElement = keyElement;
	}

	public String getAcoutStatus() {
		return acoutStatus;
	}

	public void setAcoutStatus(String acoutStatus) {
		this.acoutStatus = acoutStatus;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTlSerialNo() {
		return tlSerialNo;
	}

	public void setTlSerialNo(String tlSerialNo) {
		this.tlSerialNo = tlSerialNo;
	}

	public String getClearAmt() {
		return clearAmt;
	}

	public void setClearAmt(String clearAmt) {
		this.clearAmt = clearAmt;
	}

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
