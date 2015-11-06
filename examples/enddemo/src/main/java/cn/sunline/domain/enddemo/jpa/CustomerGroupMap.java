package cn.sunline.domain.enddemo.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 拥有复合主键的数据库表对应的类
 * 在该类中需要使用@IdClass(CustomerGroupMapPK.class)引入上面写的复合主键类
 * 
 */
@Entity
@IdClass(CustomerGroupMapPK.class)
@Table(name = "tbl_customer_group_map")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomerGroupMap {

	@Id
	private String phone;
	@Id
	private Integer tagGroupId;
	
	private String address;//地址
	private Integer members;//人数
	
	public CustomerGroupMap(){
		super();
	}
	
	public CustomerGroupMap(String phone, Integer tagGroupId){
		super();
		this.phone = phone;
		this.tagGroupId = tagGroupId;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getTagGroupId() {
		return tagGroupId;
	}
	public void setTagGroupId(Integer tagGroupId) {
		this.tagGroupId = tagGroupId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getMembers() {
		return members;
	}
	public void setMembers(Integer members) {
		this.members = members;
	}
	
	
}
