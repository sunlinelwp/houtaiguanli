package cn.sunline.dict;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(ApSysDictPK.class)
@Table(name = "ap_sys_dict")
public class ApSysDict implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497042714129869070L;

	@Id
	@Column(name = "dict_type", length = 19)
	private String dictType;
	
	@Id
	@Column(name = "dict_id", length = 19)
	private String dictId;

	@Column(name = "dict_name", length = 19)
	private String dictName;
	
	@Column(name = "parent_dict_type", length = 19)
	private String parentDictType;
	
	@Column(name = "parent_dict_id", length = 19)
	private String parentDictId;
	
	@Column(name = "status", length = 19)
	private String status;
	
	@Column(name = "sort_no", length = 19)
	private int sortNo;

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getParentDictType() {
		return parentDictType;
	}

	public void setParentDictType(String parentDictType) {
		this.parentDictType = parentDictType;
	}

	public String getParentDictId() {
		return parentDictId;
	}

	public void setParentDictId(String parentDictId) {
		this.parentDictId = parentDictId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSortNo() {
		return sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}
	
	
}


