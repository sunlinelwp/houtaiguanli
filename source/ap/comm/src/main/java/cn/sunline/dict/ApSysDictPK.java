package cn.sunline.dict;

import java.io.Serializable;

public class ApSysDictPK implements Serializable{

	private static final long serialVersionUID = -1858432441320264585L;

	private String dictType;
	
	private String dictId;

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
	
}
