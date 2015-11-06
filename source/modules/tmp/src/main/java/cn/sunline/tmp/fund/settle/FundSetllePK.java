package cn.sunline.tmp.fund.settle;

import java.io.Serializable;

public class FundSetllePK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dealdt;
	
	private String trantp;

	public String getDealdt() {
		return dealdt;
	}

	public void setDealdt(String dealdt) {
		this.dealdt = dealdt;
	}

	public String getTrantp() {
		return trantp;
	}

	public void setTrantp(String trantp) {
		this.trantp = trantp;
	}
	
	
}
