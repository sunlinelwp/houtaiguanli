package cn.sunline.message.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ap_sys_msg")
public class ApSysMsg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4663534774955424994L;

	@Id
	@Column(name = "msg_cd", length = 10)
	private String msgCd; // 报文格式编号

	@Column(name = "msg_remark", length = 100)
	private String msgRemark; // 报文格式说明

	@Column(name = "msg_define")
	private String msgDefine; // 格式定义

	@Column(name = "split_char", length = 5)
	private String splitChar; // 分隔符

	@Column(name = "head_length")
	private Integer headLength; // 报文头长度

	@Column(name = "head_msg", length = 10)
	private String headMsg; // 报文头格式定义

	@Column(name = "xml_coding", length = 10)
	private String xmlCoding; // XML字符集
	
	@Column(name="msg_len", length= 1)
	private String msgLen;

	public String getMsgLen() {
		return msgLen;
	}

	public void setMsgLen(String msgLen) {
		this.msgLen = msgLen;
	}

	public String getMsgCd() {
		return msgCd;
	}

	public void setMsgCd(String msgCd) {
		this.msgCd = msgCd;
	}

	public String getMsgRemark() {
		return msgRemark;
	}

	public void setMsgRemark(String msgRemark) {
		this.msgRemark = msgRemark;
	}

	public String getMsgDefine() {
		return msgDefine;
	}

	public void setMsgDefine(String msgDefine) {
		this.msgDefine = msgDefine;
	}

	public String getSplitChar() {
		return splitChar;
	}

	public void setSplitChar(String splitChar) {
		this.splitChar = splitChar;
	}

	public Integer getHeadLength() {
		return headLength;
	}

	public void setHeadLength(Integer headLength) {
		this.headLength = headLength;
	}

	public String getHeadMsg() {
		return headMsg;
	}

	public void setHeadMsg(String headMsg) {
		this.headMsg = headMsg;
	}

	public String getXmlCoding() {
		return xmlCoding;
	}

	public void setXmlCoding(String xmlCoding) {
		this.xmlCoding = xmlCoding;
	}

}
