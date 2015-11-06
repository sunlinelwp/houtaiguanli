package cn.sunline.tmp.fund.settle;

import java.io.Serializable;


public class BillDetailPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fundno;
	
	private String trandt;
	
	public String getFundno() {
		return fundno;
	}

	public void setFundno(String fundno) {
		this.fundno = fundno;
	}

	
	public String getTrandt() {
		return trandt;
	}

	public void setTrandt(String trandt) {
		this.trandt = trandt;
	}

	
	

}
