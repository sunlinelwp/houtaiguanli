package cn.sunline.tmp.yqrx.check;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(TmpYqrxAmouPK.class)
@Table(name = "TMP_YQRX_AMOU")
public class TmpYqrxAmou {
	@Id
	@Column(name = "keepdt")
	private String keepdt;

	@Column(name = "crcycd")
	private String crcycd;

	@Column(name = "tranam")
	private BigDecimal tranam;

	@Column(name = "acctno")
	private String acctno;

	@Id
	@Column(name = "fronsq")
	private String fronsq;

	@Id
	@Column(name = "frondt")
	private String frondt;

	@Column(name = "cardno")
	private String cardno;

	@Column(name = "acctnm")
	private String acctnm;

	@Column(name = "tranfe")
	private BigDecimal tranfe;

	@Column(name = "linkno")
	private String linkno;

	@Column(name = "cometp")
	private String cometp;

	@Column(name = "states")
	private String states;

	public String getKeepdt() {
		return keepdt;
	}

	public void setKeepdt(String keepdt) {
		this.keepdt = keepdt;
	}

	public String getCrcycd() {
		return crcycd;
	}

	public void setCrcycd(String crcycd) {
		this.crcycd = crcycd;
	}

	public BigDecimal getTranam() {
		return tranam;
	}

	public void setTranam(BigDecimal tranam) {
		this.tranam = tranam;
	}

	public String getAcctno() {
		return acctno;
	}

	public void setAcctno(String acctno) {
		this.acctno = acctno;
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

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getAcctnm() {
		return acctnm;
	}

	public void setAcctnm(String acctnm) {
		this.acctnm = acctnm;
	}

	public BigDecimal getTranfe() {
		return tranfe;
	}

	public void setTranfe(BigDecimal tranfe) {
		this.tranfe = tranfe;
	}

	public String getLinkno() {
		return linkno;
	}

	public void setLinkno(String linkno) {
		this.linkno = linkno;
	}

	public String getCometp() {
		return cometp;
	}

	public void setCometp(String cometp) {
		this.cometp = cometp;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

}
