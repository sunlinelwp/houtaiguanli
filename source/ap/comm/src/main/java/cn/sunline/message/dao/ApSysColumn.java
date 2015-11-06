package cn.sunline.message.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(ApSysColumnPK.class)
@Table(name = "ap_sys_column")
public class ApSysColumn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1909948540002954866L;

	@Id
	@Column(name = "msg_cd", length = 10)
	private String msgCd;// 报文格式编号

	@Id
	@Column(name = "column_cd", length = 50)
	private String columnCd;// 字段号

	@Column(name = "column_name", length = 50)
	private String columnName;// 字段名称

	@Column(name = "column_length")
	private Integer columnLength;// 字段长度

	@Column(name = "column_mapping", length = 50)
	private String columnMapping;// 字段匹配号

	@Column(name = "column_type")
	private String columnType;// 字段类型

	@Column(name = "value_digit")
	private String valueDigit;// 数值进制

	@Column(name = "date_pattern")
	private String datePattern;// 日期格式

	@Column(name = "default_value", length = 50)
	private String defaultValue;// 默认值

	@Column(name = "cycling_flag")
	private String cyclingFlag;// 循环节点

	@Column(name = "sort_seq")
	private int sortSeq;// 排序

	@Column(name = "cycling_column", length = 50)
	private String cyclingColumn;// 循环字段

	@Column(name = "polish_type")
	private String polishType;// 补齐方式

	@Column(name = "polish_char", length = 10)
	private String polishChar;// 补齐填充字符

	
	public String getColumnCd() {
		return columnCd;
	}

	public void setColumnCd(String columnCd) {
		this.columnCd = columnCd;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Integer getColumnLength() {
		return columnLength;
	}

	public void setColumnLength(Integer columnLength) {
		this.columnLength = columnLength;
	}

	public String getColumnMapping() {
		return columnMapping;
	}

	public void setColumnMapping(String columnMapping) {
		this.columnMapping = columnMapping;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getValueDigit() {
		return valueDigit;
	}

	public void setValueDigit(String valueDigit) {
		this.valueDigit = valueDigit;
	}

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getCyclingFlag() {
		return cyclingFlag;
	}

	public void setCyclingFlag(String cyclingFlag) {
		this.cyclingFlag = cyclingFlag;
	}

	public int getSortSeq() {
		return sortSeq;
	}

	public void setSortSeq(int sortSeq) {
		this.sortSeq = sortSeq;
	}

	public String getCyclingColumn() {
		return cyclingColumn;
	}

	public void setCyclingColumn(String cyclingColumn) {
		this.cyclingColumn = cyclingColumn;
	}

	public String getPolishType() {
		return polishType;
	}

	public void setPolishType(String polishType) {
		this.polishType = polishType;
	}

	public String getPolishChar() {
		return polishChar;
	}

	public void setPolishChar(String polishChar) {
		this.polishChar = polishChar;
	}

	public String getMsgCd() {
		return msgCd;
	}

	public void setMsgCd(String msgCd) {
		this.msgCd = msgCd;
	}

}
