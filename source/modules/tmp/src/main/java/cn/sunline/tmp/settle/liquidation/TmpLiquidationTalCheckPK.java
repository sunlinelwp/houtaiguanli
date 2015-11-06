package cn.sunline.tmp.settle.liquidation;

import java.io.Serializable;

public class TmpLiquidationTalCheckPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private String checkDate;
	private String trafficFlow;
	private String accountingDate;
	
	public String getCheckDate() {
		return checkDate;
	}

	public String getTrafficFlow() {
		return trafficFlow;
	}

	public void setTrafficFlow(String trafficFlow) {
		this.trafficFlow = trafficFlow;
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

}
