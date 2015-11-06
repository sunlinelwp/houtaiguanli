package cn.sunline.domain.enddemo.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//注解标识实体对象
@Entity
//注解指定了Entity对象所映射的数据库表名
@Table(name = "customer")
//注解定义了实体类中的命名查询，name属性指定名称，query属性指定查询语句
@NamedQuery(name = "Customer.findByAge", query = "select c from Customer c where c.age <= ?1")
public class Customer{

	//指定表的主键
	@Id
	//注解定义了成员属性对应的数据库列
	@Column(name = "cust_id")
	private Integer custId;

	@Column(name = "cust_name")
	private String custName;
	private String sex;
	private Integer age;
	
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String toString(){
		return "Customer[custId:"+custId+", custName:"+custName+", sex:"+sex+", age:"+age+"]";
	}
	
}
