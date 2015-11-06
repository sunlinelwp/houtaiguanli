package cn.sunline.tmp.insu.check;

import java.io.Serializable;

public class InsuSetllePK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6083299641080013384L;
	
	private String dealdt;
	
	private String trantp;
	public InsuSetllePK(String dealdt,String trantp){
		this.dealdt = dealdt;
		this.trantp = trantp;
	}
	public InsuSetllePK(){
	    super();
	}

	public String getTrantp() {
		return trantp;
	}

	public void setTrantp(String trantp) {
		this.trantp = trantp;
	}

	public String getDealdt() {
		return dealdt;
	}

	public void setDealdt(String dealdt) {
		this.dealdt = dealdt;
	}
	
	


}
