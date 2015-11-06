package cn.sunline.tmp.settle.liquidation;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class TmpLiquidationSubCheckPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private String checkDate;
	
	private String accountingDate;
	
	private String packageSubject;

	private String lendingDirection;
	
	public String getPackageSubject() {
		return packageSubject;
	}

	public void setPackageSubject(String packageSubject) {
		this.packageSubject = packageSubject;
	}


	public String getLendingDirection() {
		return lendingDirection;
	}

	public void setLendingDirection(String lendingDirection) {
		this.lendingDirection = lendingDirection;
	}

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
	
}
