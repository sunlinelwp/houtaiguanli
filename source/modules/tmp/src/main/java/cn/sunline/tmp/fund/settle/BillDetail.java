package cn.sunline.tmp.fund.settle;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@IdClass(BillDetailPK.class)
@Table(name = "fund_buy_check")
public class BillDetail {
	@Id
	@Column(name = "fundno")
	private String fundno;
	
	@Column(name = "trantp")
	private String trantp;
	
	
	@Column(name = "custac")
	private String custac;
	
	
	@Column(name = "fundst")
	private String fundst;
	
	
	@Column(name = "tranam")
	private BigDecimal tranam;
	
	
	@Column(name = "trantm")
	private String trantm;
	
	@Id
	@Column(name = "trandt")
	private String trandt;
	
	public String getFundno() {
		return fundno;
	}

	public void setFundno(String fundno) {
		this.fundno = fundno;
	}

	public String getTrantp() {
		return trantp;
	}

	public void setTrantp(String trantp) {
		this.trantp = trantp;
	}

	public String getCustac() {
		return custac;
	}

	public void setCustac(String custac) {
		this.custac = custac;
	}

	public String getFundst() {
		return fundst;
	}

	public void setFundst(String fundst) {
		this.fundst = fundst;
	}

	public BigDecimal getTranam() {
		return tranam;
	}

	public void setTranam(BigDecimal tranam) {
		this.tranam = tranam;
	}

	public String getTrantm() {
		return trantm;
	}

	public void setTrantm(String trantm) {
		this.trantm = trantm;
	}

	public String getTrandt() {
		return trandt;
	}

	public void setTrandt(String trandt) {
		this.trandt = trandt;
	}

	
	

}
