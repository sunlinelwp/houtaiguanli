package cn.sunline.tmp.yqrx.check;

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
	@Column(name = "amouid")
	private String amouid;

	@Column(name = "acctno")
	private String acctno;

	@Column(name = "payeac")
	private String payeac;

	@Column(name = "payena")
	private String payena;

	@Column(name = "tranam")
	private String tranam;

	@Column(name = "crcycd")
	private String crcycd;

	@Column(name = "chgeam")
	private String chgeam;

	@Column(name = "pswdtp")
	private String pswdtp;

	@Column(name = "pwflag")
	private String pwflag;

	@Column(name = "tranpw")
	private String tranpw;

	@Column(name = "remark")
	private String remark;

	@Column(name = "banknm")
	private String banknm;

	@Column(name = "provic")
	private String provic;

	@Column(name = "garden")
	private String garden;

	@Column(name = "ftbkcd")
	private String ftbkcd;

	@Column(name = "acctpr")
	private String acctpr;

	@Column(name = "chnlcd")
	private String chnlcd;

	@Column(name = "pytype")
	private String pytype;

	@Column(name = "cometp")
	private String cometp;

	@Column(name = "states")
	private String states;

	@Column(name = "amoudt")
	private String amoudt;

	public String getAmouid() {
		return amouid;
	}

	public void setAmouid(String amouid) {
		this.amouid = amouid;
	}

	public String getAcctno() {
		return acctno;
	}

	public void setAcctno(String acctno) {
		this.acctno = acctno;
	}

	public String getPayeac() {
		return payeac;
	}

	public void setPayeac(String payeac) {
		this.payeac = payeac;
	}

	public String getPayena() {
		return payena;
	}

	public void setPayena(String payena) {
		this.payena = payena;
	}

	public String getTranam() {
		return tranam;
	}

	public void setTranam(String tranam) {
		this.tranam = tranam;
	}

	public String getCrcycd() {
		return crcycd;
	}

	public void setCrcycd(String crcycd) {
		this.crcycd = crcycd;
	}

	public String getChgeam() {
		return chgeam;
	}

	public void setChgeam(String chgeam) {
		this.chgeam = chgeam;
	}

	public String getPswdtp() {
		return pswdtp;
	}

	public void setPswdtp(String pswdtp) {
		this.pswdtp = pswdtp;
	}

	public String getPwflag() {
		return pwflag;
	}

	public void setPwflag(String pwflag) {
		this.pwflag = pwflag;
	}

	public String getTranpw() {
		return tranpw;
	}

	public void setTranpw(String tranpw) {
		this.tranpw = tranpw;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBanknm() {
		return banknm;
	}

	public void setBanknm(String banknm) {
		this.banknm = banknm;
	}

	public String getProvic() {
		return provic;
	}

	public void setProvic(String provic) {
		this.provic = provic;
	}

	public String getGarden() {
		return garden;
	}

	public void setGarden(String garden) {
		this.garden = garden;
	}

	public String getFtbkcd() {
		return ftbkcd;
	}

	public void setFtbkcd(String ftbkcd) {
		this.ftbkcd = ftbkcd;
	}

	public String getAcctpr() {
		return acctpr;
	}

	public void setAcctpr(String acctpr) {
		this.acctpr = acctpr;
	}

	public String getChnlcd() {
		return chnlcd;
	}

	public void setChnlcd(String chnlcd) {
		this.chnlcd = chnlcd;
	}

	public String getPytype() {
		return pytype;
	}

	public void setPytype(String pytype) {
		this.pytype = pytype;
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

	public String getAmoudt() {
		return amoudt;
	}

	public void setAmoudt(String amoudt) {
		this.amoudt = amoudt;
	}

}
