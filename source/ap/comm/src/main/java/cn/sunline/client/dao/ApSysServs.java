package cn.sunline.client.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ap_sys_servs")
public class ApSysServs {

	// 服务编号
	@Id
	@Column(name = "service_cd", length = 10)
	private String serviceCd;

	// 服务名称
	@Column(name = "service_name", length = 50)
	private String serviceName;

	// 服务类型
	@Column(name = "service_type")
	private String serviceType;

	// 服务IP
	@Column(name = "service_ip", length = 20)
	private String serviceIp;

	// 服务端口
	@Column(name = "service_port", length = 5)
	private Integer servicePort;

	// 字符集
	@Column(name = "encode", length = 10)
	private String encode;

	// 服务路径
	@Column(name = "file_path", length = 500)
	private String filePath;

	// 登陆用户名
	@Column(name = "login_user", length = 20)
	private String loginUser;

	// 登陆密码
	@Column(name = "login_passwd", length = 20)
	private String loginPasswd;
	

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getLoginPasswd() {
		return loginPasswd;
	}

	public void setLoginPasswd(String loginPasswd) {
		this.loginPasswd = loginPasswd;
	}

	public String getServiceCd() {
		return serviceCd;
	}

	public void setServiceCd(String serviceCd) {
		this.serviceCd = serviceCd;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceIp() {
		return serviceIp;
	}

	public void setServiceIp(String serviceIp) {
		this.serviceIp = serviceIp;
	}

	public Integer getServicePort() {
		return servicePort;
	}

	public void setServicePort(Integer servicePort) {
		this.servicePort = servicePort;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
