package cn.sunline.tmp.allinpay.gate.check;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tmp_ainpay_pay_gate_head_check")
public class TmpAinPayPayGateHeadCheck {
	@Id
	@Column(name = "check_date")
	private String checkDate;
	//手续费
	private BigDecimal fee;
	//金额
	private BigDecimal amount;
	//状态
	private String checkStatus;
	//交易笔数
	private String tradeCount;
	//成功笔数
	private String succesCount;
	//退款笔数
	private String refund;
	//退款金额
	private BigDecimal refAmt;
	//清算金额
	private BigDecimal clearAmt;
	//清算日期
	private String clearDate;
	//对账流水
	private String checkSeqno;
	public String getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(String tradeCount) {
		this.tradeCount = tradeCount;
	}

	public String getSuccesCount() {
		return succesCount;
	}

	public void setSuccesCount(String succesCount) {
		this.succesCount = succesCount;
	}

	public String getRefund() {
		return refund;
	}

	public void setRefund(String refund) {
		this.refund = refund;
	}

	public BigDecimal getRefAmt() {
		return refAmt;
	}

	public void setRefAmt(BigDecimal refAmt) {
		this.refAmt = refAmt;
	}

	public BigDecimal getClearAmt() {
		return clearAmt;
	}

	public void setClearAmt(BigDecimal clearAmt) {
		this.clearAmt = clearAmt;
	}

	public String getClearDate() {
		return clearDate;
	}

	public void setClearDate(String clearDate) {
		this.clearDate = clearDate;
	}

	public String getCheckSeqno() {
		return checkSeqno;
	}

	public void setCheckSeqno(String checkSeqno) {
		this.checkSeqno = checkSeqno;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	
	
}
