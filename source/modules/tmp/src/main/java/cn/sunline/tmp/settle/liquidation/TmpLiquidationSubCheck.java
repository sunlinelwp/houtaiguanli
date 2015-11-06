package cn.sunline.tmp.settle.liquidation;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(TmpLiquidationSubCheckPK.class)
@Table(name = "tmp_liquidation_sub_check")
public class TmpLiquidationSubCheck {

	@Id
	@Column(name = "check_date")
	private String checkDate;

	@Id
	@Column(name = "accounting_date")
	private String accountingDate;

	@Id
	@Column(name = "package_subject")
	private String packageSubject;

	@Column(name = "micro_subjects")
	private String microSubjects;

	@Id
	@Column(name = "lending_direction")
	private String lendingDirection;

	@Column(name = "daily_occurrence")
	private BigDecimal dailyOccurrence;

	@Column(name = "transitional_amount")
	private BigDecimal transitionalAmount;

	@Column(name = "flow_occurrence_amount")
	private BigDecimal flowOccurrenceAmount;

	@Column(name = "micro_balance")
	private BigDecimal microBalance;

	@Column(name = "micro_balance_direction")
	private String microBalanceDirection;

	@Column(name = "day_balance")
	private BigDecimal dayBalance;

	@Column(name = "day_balance_direction")
	private String dayBalanceDirection;

	@Column(name = "core_balance")
	private BigDecimal coreBalance;

	@Column(name = "check_ledger")
	private String checkLedger;

	@Column(name = "balance_results")
	private String balanceResults;

	@Column(name = "difference")
	private BigDecimal difference;

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

	public String getPackageSubject() {
		return packageSubject;
	}

	public void setPackageSubject(String packageSubject) {
		this.packageSubject = packageSubject;
	}

	public String getMicroSubjects() {
		return microSubjects;
	}

	public void setMicroSubjects(String microSubjects) {
		this.microSubjects = microSubjects;
	}

	public String getLendingDirection() {
		return lendingDirection;
	}

	public void setLendingDirection(String lendingDirection) {
		this.lendingDirection = lendingDirection;
	}

	public BigDecimal getDailyOccurrence() {
		return dailyOccurrence;
	}

	public void setDailyOccurrence(BigDecimal dailyOccurrence) {
		this.dailyOccurrence = dailyOccurrence;
	}

	public BigDecimal getTransitionalAmount() {
		return transitionalAmount;
	}

	public void setTransitionalAmount(BigDecimal transitionalAmount) {
		this.transitionalAmount = transitionalAmount;
	}

	public BigDecimal getFlowOccurrenceAmount() {
		return flowOccurrenceAmount;
	}

	public void setFlowOccurrenceAmount(BigDecimal flowOccurrenceAmount) {
		this.flowOccurrenceAmount = flowOccurrenceAmount;
	}

	public BigDecimal getMicroBalance() {
		return microBalance;
	}

	public void setMicroBalance(BigDecimal microBalance) {
		this.microBalance = microBalance;
	}

	public String getMicroBalanceDirection() {
		return microBalanceDirection;
	}

	public void setMicroBalanceDirection(String microBalanceDirection) {
		this.microBalanceDirection = microBalanceDirection;
	}

	public BigDecimal getDayBalance() {
		return dayBalance;
	}

	public void setDayBalance(BigDecimal dayBalance) {
		this.dayBalance = dayBalance;
	}

	public String getDayBalanceDirection() {
		return dayBalanceDirection;
	}

	public void setDayBalanceDirection(String dayBalanceDirection) {
		this.dayBalanceDirection = dayBalanceDirection;
	}

	public BigDecimal getCoreBalance() {
		return coreBalance;
	}

	public void setCoreBalance(BigDecimal coreBalance) {
		this.coreBalance = coreBalance;
	}

	public String getCheckLedger() {
		return checkLedger;
	}

	public void setCheckLedger(String checkLedger) {
		this.checkLedger = checkLedger;
	}

	public String getBalanceResults() {
		return balanceResults;
	}

	public void setBalanceResults(String balanceResults) {
		this.balanceResults = balanceResults;
	}

	public BigDecimal getDifference() {
		return difference;
	}

	public void setDifference(BigDecimal difference) {
		this.difference = difference;
	}

}
