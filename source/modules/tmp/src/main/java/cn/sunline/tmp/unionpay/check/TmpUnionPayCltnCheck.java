package cn.sunline.tmp.unionpay.check;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@IdClass(TmpUnionPayCltnCheckPK.class)
@Table(name = "tmp_unionpay_cltn_check")
public class TmpUnionPayCltnCheck {
	
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
		
}
