package cn.sunline.tmp.yqrx.check;

import java.io.Serializable;

public class TmpYqrxAmouPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String keepdt;
	
	private String fronsq;
	
	private String frondt;

	public String getKeepdt() {
		return keepdt;
	}

	public void setKeepdt(String keepdt) {
		this.keepdt = keepdt;
	}

	public String getFronsq() {
		return fronsq;
	}

	public void setFronsq(String fronsq) {
		this.fronsq = fronsq;
	}

	public String getFrondt() {
		return frondt;
	}

	public void setFrondt(String frondt) {
		this.frondt = frondt;
	}
}
