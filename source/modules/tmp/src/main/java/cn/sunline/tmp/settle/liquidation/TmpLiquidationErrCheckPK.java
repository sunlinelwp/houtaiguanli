package cn.sunline.tmp.settle.liquidation;


import java.io.Serializable;

public class TmpLiquidationErrCheckPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private String checkDate;
	
	private String accountingDate;

	private String trafficflow;

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

}
