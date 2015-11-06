package cn.sunline.client.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ap_sys_encap")
public class ApSysEncap {

	// 报文封装编号
	@Id
	@Column(name = "encap_cd", length = 10)
	private String encapCd;

	// 封装说明
	@Column(name = "encap_remark", length = 100)
	private String encapRemark;

	// 请求报文
	@Column(name = "req_msg", length = 10)
	private String reqMsg;

	// 反馈报文
	@Column(name = "rsp_msg", length = 10)
	private String rspMsg;

	public String getEncapCd() {
		return encapCd;
	}

	public void setEncapCd(String encapCd) {
		this.encapCd = encapCd;
	}

	public String getEncapRemark() {
		return encapRemark;
	}

	public void setEncapRemark(String encapRemark) {
		this.encapRemark = encapRemark;
	}

	public String getReqMsg() {
		return reqMsg;
	}

	public void setReqMsg(String reqMsg) {
		this.reqMsg = reqMsg;
	}

	public String getRspMsg() {
		return rspMsg;
	}

	public void setRspMsg(String rspMsg) {
		this.rspMsg = rspMsg;
	}

}
