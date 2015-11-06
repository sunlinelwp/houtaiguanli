package cn.sunline.domain.enddemo.transaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_trade")
public class Trade {

	@Id
	@Column(name="trade_id")
	private Integer tradeId;//交易Id
	
	@Column(name="trade_name")
	private String name;//名称

	@Column(name="trade_type")
	private String  type;//类型
	
	public Integer getTradeId() {
		return tradeId;
	}
	
	public Trade(){
		super();
	}
	
	public Trade(Integer tradeId,String name,String type){
		super();
		this.tradeId = tradeId;
		this.name = name;
		this.type = type;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String toString(){
		return "[tradeId:" + tradeId + ",name:" + name + ",type:" + type + "]";
	}
}
