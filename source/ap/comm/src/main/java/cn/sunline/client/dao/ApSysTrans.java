package cn.sunline.client.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ap_sys_trans")
public class ApSysTrans {
	@Id
	@Column(name = "trans_cd", length = 10)
	private String transCd; // 交易标识号

	@Column(name = "trans_name", length = 50)
	private String transName;// 交易名称

	@Column(name = "service_cd", length = 10)
	private String serviceCd;// 发送服务号

	@Column(name = "encap_cd", length = 10)
	private String encapCd;// 报文封装编号

	@Column(name = "trans_status")
	private String transStatus;// 交易状态

	@Column(name = "file_path", length = 500)
	private String filePath;// 本地文件路径

	@Column(name = "file_type", length = 10)
	private String fileType; // 文件类型

	@Column(name = "file_prefix", length = 20)
	private String filePrefix;// 文件前缀
	
	@Column(name = "file_suffix", length = 20)
	private String fileSufix;// 文件后缀

	@Column(name = "file_split")
	private String fileSplit;// 文件分隔符
	
	@Column(name = "deal_cnt")
	private Integer dealCnt;// 一次处理数据笔数

	@Column(name = "cal_flag")
	private String calFlag;// 首行数据为统计笔数
	
	@Column(name = "cal_char_at")
	private Integer calCharAt;// 首行统计数据所在位置
	
	@Column(name = "begin_line")
	private Integer beginLine;// 明细开始行数
	
	public Integer getBeginLine() {
		return beginLine;
	}

	public void setBeginLine(Integer beginLine) {
		this.beginLine = beginLine;
	}

	public String getCalFlag() {
		return calFlag;
	}

	public Integer getCalCharAt() {
		return calCharAt;
	}

	public void setCalCharAt(Integer calCharAt) {
		this.calCharAt = calCharAt;
	}

	public void setCalFlag(String calFlag) {
		this.calFlag = calFlag;
	}

	public Integer getDealCnt() {
		return dealCnt;
	}

	public void setDealCnt(Integer dealCnt) {
		this.dealCnt = dealCnt;
	}

	public String getFileSufix() {
		return fileSufix;
	}

	public void setFileSufix(String fileSufix) {
		this.fileSufix = fileSufix;
	}

	public String getTransCd() {
		return transCd;
	}

	public void setTransCd(String transCd) {
		this.transCd = transCd;
	}

	public String getTransName() {
		return transName;
	}

	public void setTransName(String transName) {
		this.transName = transName;
	}

	public String getServiceCd() {
		return serviceCd;
	}

	public void setServiceCd(String serviceCd) {
		this.serviceCd = serviceCd;
	}

	public String getEncapCd() {
		return encapCd;
	}

	public void setEncapCd(String encapCd) {
		this.encapCd = encapCd;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilePrefix() {
		return filePrefix;
	}

	public void setFilePrefix(String filePrefix) {
		this.filePrefix = filePrefix;
	}

	public String getFileSplit() {
		return fileSplit;
	}

	public void setFileSplit(String fileSplit) {
		this.fileSplit = fileSplit;
	}

}
