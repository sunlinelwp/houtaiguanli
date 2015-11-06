package cn.sunline.domain.enddemo.jpa;

import java.io.Serializable;

/**
 *
 * 复合主键类，用来存放需要生产联合主键的属性，该类需要实现序列化
 *
 */
@SuppressWarnings("serial")
public class CustomerGroupMapPK implements Serializable{
	
	private String phone;//电话号码
	private Integer tagGroupId;//标签组id
	
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
	
	
}
