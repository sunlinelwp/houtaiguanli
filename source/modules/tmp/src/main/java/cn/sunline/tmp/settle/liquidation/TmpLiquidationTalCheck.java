package cn.sunline.tmp.settle.liquidation;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(TmpLiquidationTalCheckPK.class)
@Table(name = "tmp_liquidation_tail_check")
public class TmpLiquidationTalCheck {
	
	@Column(name = "syndicated_number")
	private String syndicatedNumber;

	@Id
	@Column(name = "check_date")
	private String checkDate;
	
	@Id
	@Column(name = "accounting_date")
	private String accountingDate;
	
	@Id
	@Column(name = "traffic_flow")
	private String trafficFlow;

	@Column(name = "lending_direction")
	private String lendingDirection;

	@Column(name = "cost_occurrence_amount")
	private BigDecimal costOccurrenceAmount;

	@Column(name = "flow_occurrence_amount")
	private BigDecimal flowOccurrenceAmount;

	@Column(name = "difference")
	private BigDecimal Difference;

	public String getSyndicatedNumber() {
		return syndicatedNumber;
	}

	public void setSyndicatedNumber(String syndicatedNumber) {
		this.syndicatedNumber = syndicatedNumber;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getTrafficFlow() {
		return trafficFlow;
	}

	public void setTrafficFlow(String trafficFlow) {
		this.trafficFlow = trafficFlow;
	}

	public String getLendingDirection() {
		return lendingDirection;
	}

	public void setLendingDirection(String lendingDirection) {
		this.lendingDirection = lendingDirection;
	}

	public BigDecimal getCostOccurrenceAmount() {
		return costOccurrenceAmount;
	}

	public void setCostOccurrenceAmount(BigDecimal costOccurrenceAmount) {
		this.costOccurrenceAmount = costOccurrenceAmount;
	}

	public BigDecimal getFlowOccurrenceAmount() {
		return flowOccurrenceAmount;
	}

	public void setFlowOccurrenceAmount(BigDecimal flowOccurrenceAmount) {
		this.flowOccurrenceAmount = flowOccurrenceAmount;
	}

	public BigDecimal getDifference() {
		return Difference;
	}

	public void setDifference(BigDecimal difference) {
		Difference = difference;
	}

	public String getAccountingDate() {
		return accountingDate;
	}

	public void setAccountingDate(String accountingDate) {
		this.accountingDate = accountingDate;
	}

}
