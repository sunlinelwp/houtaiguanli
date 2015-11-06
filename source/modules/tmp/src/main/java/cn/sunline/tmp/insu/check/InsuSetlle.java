package cn.sunline.tmp.insu.check;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(InsuSetllePK.class)
@Table(name = "insu_setlle")
public class InsuSetlle {
	
	@Id
	@Column(name = "dealdt")
	private String dealdt;
	
	@Column(name = "totlam")
	private BigDecimal  totlam;
	
	@Id
	@Column(name = "trantp")
	private String trantp;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "remark1")
	private String remark1;
	
	@Column(name = "remark2")
	private String remark2;

	
	public String getDealdt() {
		return dealdt;
	}

	public void setDealdt(String dealdt) {
		this.dealdt = dealdt;
	}

	public BigDecimal getTotlam() {
		return totlam;
	}

	public void setTotlam(BigDecimal totlam) {
		this.totlam = totlam;
	}

	public String getTrantp() {
		return trantp;
	}

	public void setTrantp(String trantp) {
		this.trantp = trantp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	
}
