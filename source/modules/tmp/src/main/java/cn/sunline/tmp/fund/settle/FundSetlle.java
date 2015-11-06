package cn.sunline.tmp.fund.settle;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(FundSetllePK.class)
@Table(name = "fund_setlle")
public class FundSetlle {
	
	@Id
	@Column(name = "dealdt")
	private String dealdt;
	
	@Column(name = "totlam")
	private BigDecimal totlam;
	
	@Id
	@Column(name = "trantp")
	private String trantp;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dealdt == null) ? 0 : dealdt.hashCode());
		result = prime * result + ((remark1 == null) ? 0 : remark1.hashCode());
		result = prime * result + ((remark2 == null) ? 0 : remark2.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((totlam == null) ? 0 : totlam.hashCode());
		result = prime * result + ((trantp == null) ? 0 : trantp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FundSetlle other = (FundSetlle) obj;
		if (dealdt == null) {
			if (other.dealdt != null)
				return false;
		} else if (!dealdt.equals(other.dealdt))
			return false;
		if (remark1 == null) {
			if (other.remark1 != null)
				return false;
		} else if (!remark1.equals(other.remark1))
			return false;
		if (remark2 == null) {
			if (other.remark2 != null)
				return false;
		} else if (!remark2.equals(other.remark2))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (totlam == null) {
			if (other.totlam != null)
				return false;
		} else if (!totlam.equals(other.totlam))
			return false;
		if (trantp == null) {
			if (other.trantp != null)
				return false;
		} else if (!trantp.equals(other.trantp))
			return false;
		return true;
	}

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
