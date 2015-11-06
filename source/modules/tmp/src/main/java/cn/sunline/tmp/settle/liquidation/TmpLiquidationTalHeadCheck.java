package cn.sunline.tmp.settle.liquidation;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tmp_liquidation_tal_head_check")
public class TmpLiquidationTalHeadCheck {

	@Id
	@Column(name = "check_date")
	private String checkDate;

	private String operationFlow;

	private String totalNumber;

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

	public String getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(String totalNumber) {
		this.totalNumber = totalNumber;
	}

}
