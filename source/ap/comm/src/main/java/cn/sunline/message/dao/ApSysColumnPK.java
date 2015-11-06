package cn.sunline.message.dao;

import java.io.Serializable;

public class ApSysColumnPK implements Serializable{

	private static final long serialVersionUID = -1858432441320264585L;

	private String msgCd;
	
	private String columnCd;

	public String getMsgCd() {
		return msgCd;
	}

	public void setMsgCd(String msgCd) {
		this.msgCd = msgCd;
	}

	public String getColumnCd() {
		return columnCd;
	}

	public void setColumnCd(String columnCd) {
		this.columnCd = columnCd;
	}
	
}
