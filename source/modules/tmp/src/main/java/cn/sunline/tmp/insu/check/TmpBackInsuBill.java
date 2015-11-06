package cn.sunline.tmp.insu.check;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(TmpBackInsuBillPK.class)
@Table(name = "tmp_backinsu_bill")
public class TmpBackInsuBill {
	
	@Column(name = "dealdt")
	private String dealdt;
	
	@Column(name = "procna")
	private String procna;
	
	@Column(name = "transq")
	private String transq;
	
	@Id
	@Column(name = "polino")
	private String polino;
	
	@Column(name = "custna")
	private String custna;
	
	@Column(name = "tranam")
	private BigDecimal tranam;
	
	@Column(name = "chk_status")
	private String chkStatus;
	
	@Column(name = "passtm")
	private String passtm;
	
	@Column(name = "check_status")
	private String checkStatus;
	
	@Column(name = "msg")
	private String msg;
	
	public String getDealdt() {
		return dealdt;
	}

	public void setDealdt(String dealdt) {
		this.dealdt = dealdt;
	}

	public String getProcna() {
		return procna;
	}

	public void setProcna(String procna) {
		this.procna = procna;
	}

	public String getTransq() {
		return transq;
	}

	public void setTransq(String transq) {
		this.transq = transq;
	}

	public String getPolino() {
		return polino;
	}

	public void setPolino(String polino) {
		this.polino = polino;
	}

	public String getCustna() {
		return custna;
	}

	public void setCustna(String custna) {
		this.custna = custna;
	}

	public BigDecimal getTranam() {
		return tranam;
	}

	public void setTranam(BigDecimal tranam) {
		this.tranam = tranam;
	}

	public String getChkStatus() {
		return chkStatus;
	}

	public void setChkStatus(String chkStatus) {
		this.chkStatus = chkStatus;
	}

	public String getPasstm() {
		return passtm;
	}

	public void setPasstm(String passtm) {
		this.passtm = passtm;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
