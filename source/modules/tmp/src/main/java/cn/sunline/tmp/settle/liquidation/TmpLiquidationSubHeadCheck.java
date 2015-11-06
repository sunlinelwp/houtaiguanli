package cn.sunline.tmp.settle.liquidation;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tmp_liquidation_sub_head_check")
public class TmpLiquidationSubHeadCheck {

	@Id
	@Column(name = "check_date")
	private String checkDate;

	@Column(name = "operation_flow")
	private String operationFlow;

	@Column(name = "total_number")
	private String totalNumber;

	@Column(name = "successful_identification")
	private String successfulIdentification;

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

	public String getSuccessfulIdentification() {
		return successfulIdentification;
	}

	public void setSuccessfulIdentification(String successfulIdentification) {
		this.successfulIdentification = successfulIdentification;
	}

}
