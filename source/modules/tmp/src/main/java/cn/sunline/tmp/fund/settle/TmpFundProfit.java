package cn.sunline.tmp.fund.settle;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tmp_fund_profit")
public class TmpFundProfit {
	@Id
	@Column(name = "dealdt")
	private String dealdt;
	
	@Column(name = "prftam")
	private BigDecimal prftam;
	
	@Column(name = "chekst")
	private String chekst;

	public String getDealdt() {
		return dealdt;
	}

	public void setDealdt(String dealdt) {
		this.dealdt = dealdt;
	}

	public BigDecimal getPrftam() {
		return prftam;
	}

	public void setPrftam(BigDecimal prftam) {
		this.prftam = prftam;
	}

	public String getChekst() {
		return chekst;
	}

	public void setChekst(String chekst) {
		this.chekst = chekst;
	}
	
	
}
