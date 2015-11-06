package cn.sunline.domain.enddemo.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_dep")
public class DepModel {

	@Id
	private Integer uuid;
	private String name;
	
	//多对一注解标识
	@ManyToOne
	//注解表示dep表里面有user_id字段，且不能为空
	@JoinColumn(name="user_id", nullable=false)
	private UserModel um = new UserModel();

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserModel getUm() {
		return um;
	}

	public void setUm(UserModel um) {
		this.um = um;
	}
	
	public String toString(){
		return "depModel[uuid:" + uuid + ", name:" + name + "]";
	}
	
}
