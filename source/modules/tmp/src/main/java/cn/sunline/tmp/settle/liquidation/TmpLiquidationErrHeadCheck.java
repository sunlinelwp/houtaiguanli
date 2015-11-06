package cn.sunline.tmp.settle.liquidation;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tmp_liquidation_err_head_check")
public class TmpLiquidationErrHeadCheck {

	@Id
	@Column(name = "check_date")
	private String checkDate;

	private String operationFlow;

	private String totalNumber;

	private BigDecimal totalDebit;

	private BigDecimal totalCredit;

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getOperationFlow() {
		return operationFlow;
	}

	public void setOperationFlow(String operationFlow) {
		this.operationFlow = operationFlow;
	}

	public BigDecimal getTotalDebit() {
		return totalDebit;
	}

	public void setTotalDebit(BigDecimal totalDebit) {
		this.totalDebit = totalDebit;
	}

	public BigDecimal getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(BigDecimal totalCredit) {
		this.totalCredit = totalCredit;
	}

	public String getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(String totalNumber) {
		this.totalNumber = totalNumber;
	}
}
