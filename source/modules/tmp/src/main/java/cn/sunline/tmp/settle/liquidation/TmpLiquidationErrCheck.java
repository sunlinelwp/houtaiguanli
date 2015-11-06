package cn.sunline.tmp.settle.liquidation;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(TmpLiquidationErrCheckPK.class)
@Table(name = "tmp_liquidation_err_check")
public class TmpLiquidationErrCheck {

	@Id
	@Column(name = "check_date")
	private String checkDate;
	
	@Id
	@Column(name = "accounting_date")
	private String accountingDate;
	
	@Id
	@Column(name = "traffic_flow")
	private String trafficflow;

	@Column(name = "lending_direction")
	private String lendingdirection;

	@Column(name = "transaction_amount")
	private BigDecimal transactionamount;

	@Column(name = "account_number")
	private String accountnumber;

	@Column(name = "reconciliation")
	private String reconciliation;

	@Column(name = "syndicated_number")
	private String syndicatednumber;

	@Column(name = "bank_number")
	private String banknumber;

	@Column(name = "mechanism_number")
	private String mechanismnumber;

	@Column(name = "iou_number")
	private String iounumber;

	@Column(name = "loan_status")
	private String loanstatus;

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getAccountingDate() {
		return accountingDate;
	}

	public void setAccountingDate(String accountingDate) {
		this.accountingDate = accountingDate;
	}

	public String getTrafficflow() {
		return trafficflow;
	}

	public void setTrafficflow(String trafficflow) {
		this.trafficflow = trafficflow;
	}

	public String getLendingdirection() {
		return lendingdirection;
	}

	public void setLendingdirection(String lendingdirection) {
		this.lendingdirection = lendingdirection;
	}

	public BigDecimal getTransactionamount() {
		return transactionamount;
	}

	public void setTransactionamount(BigDecimal transactionamount) {
		this.transactionamount = transactionamount;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getReconciliation() {
		return reconciliation;
	}

	public void setReconciliation(String reconciliation) {
		this.reconciliation = reconciliation;
	}

	public String getSyndicatednumber() {
		return syndicatednumber;
	}

	public void setSyndicatednumber(String syndicatednumber) {
		this.syndicatednumber = syndicatednumber;
	}

	public String getBanknumber() {
		return banknumber;
	}

	public void setBanknumber(String banknumber) {
		this.banknumber = banknumber;
	}

	public String getMechanismnumber() {
		return mechanismnumber;
	}

	public void setMechanismnumber(String mechanismnumber) {
		this.mechanismnumber = mechanismnumber;
	}

	public String getIounumber() {
		return iounumber;
	}

	public void setIounumber(String iounumber) {
		this.iounumber = iounumber;
	}

	public String getLoanstatus() {
		return loanstatus;
	}

	public void setLoanstatus(String loanstatus) {
		this.loanstatus = loanstatus;
	}

}
