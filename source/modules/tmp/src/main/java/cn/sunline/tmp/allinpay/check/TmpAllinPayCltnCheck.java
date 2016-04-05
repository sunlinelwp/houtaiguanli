package cn.sunline.tmp.allinpay.check;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@IdClass(TmpAllinPayCltnCheckPK.class)
@Table(name = "tmp_allinpay_cltn_check")
public class TmpAllinPayCltnCheck {
	
	@Id
	@Column(name = "check_date")
	private String checkDate; //清算日期
	@Column(name = "merchant_cd")
	private String merchantCd;//商户号
	@Id
	@Column(name = "merchant_dt")
	private String merchantDt;//商户日期
	@Id
	@Column(name = "billno")
	private String billNo;//订单号
	@Column(name = "acctno")
	private String acctno;//电子账号
	@Column(name = "trans_amt")
	private BigDecimal transAmt;//交易金额
	@Column(name = "chk_status")
	private String chkStatus;//核对状态
	@Column(name = "fee_amt")
	private BigDecimal feeAmt;//手续费用
	@Column(name = "sign_status")
	private String signStatus;//银行状态
	@Column(name = "check_status")
	private String checkStatus;//处理状态
	@Column(name = "core_seqno")
	private String coreSeqno;//银行状态
	@Column(name = "core_date")
	private String coreDate;//处理状态

	@Id
	@Column(name = "billid")
	private String billid;//订单序号
	@Column(name = "acout_status")
	private String acoutStatus;//银行账务状态
	@Column(name = "trade_status")
	private String tradeStatus;//银行交易状态
	@Column(name = "tl_status")
	private String tlStatus;//通联状态
	@Column(name = "key_element")
	private String keyElement;//关键要素

	@Column(name = "tl_cardno")
	private String tlCardno;//通联交易卡号
	
	@Column(name = "bank_tranam")
	private BigDecimal bankTranam;//行内交易金额
	
	@Column(name = "bank_cardno")
	private String bankCardno;//行内交易卡号

	@Column(name = "timetm")
	private long timetm;//时间戳
	public String getTlCardno() {
		return tlCardno;
	}
	public void setTlCardno(String tlCardno) {
		this.tlCardno = tlCardno;
	}
	public BigDecimal getBankTranam() {
		return bankTranam;
	}
	public void setBankTranam(BigDecimal bankTranam) {
		this.bankTranam = bankTranam;
	}
	public String getBankCardno() {
		return bankCardno;
	}
	public void setBankCardno(String bankCardno) {
		this.bankCardno = bankCardno;
	}
	public long getTimetm() {
		return timetm;
	}
	public void setTimetm(long timetm) {
		this.timetm = timetm;
	}
	public String getKeyElement() {
		return keyElement;
	}
	public void setKeyElement(String keyElement) {
		this.keyElement = keyElement;
	}
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
	public String getBillid() {
		return billid;
	}
	public void setBillid(String billid) {
		this.billid = billid;
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
	public String getTlStatus() {
		return tlStatus;
	}
	public void setTlStatus(String tlStatus) {
		this.tlStatus = tlStatus;
	}
}
