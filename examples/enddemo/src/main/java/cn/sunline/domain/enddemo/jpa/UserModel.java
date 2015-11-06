package cn.sunline.domain.enddemo.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbl_user")
public class UserModel {

	@Id
	private Integer uuid;
	private String name;
	private Integer age;
	
	/**
	 * @OneToMany一对多注解标识
	 * mapped属性是用于双相关联实体时使用
	 * fetch属性是实体加载方式，默认为惰性加载LAZY
	 * cascade属性是级联样式类型，表示当对实体操作时，级联关系表是否受到影响的策略
	 */
	@OneToMany(mappedBy="um", fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	private Set<DepModel> setDep = new HashSet<DepModel>();
	
	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Set<DepModel> getSetDep() {
		return setDep;
	}

	public void setSetDep(Set<DepModel> setDep) {
		this.setDep = setDep;
	}
	
	public String toString(){
		return "userModel[uuid:" + uuid + ", name:" + name + ", age:" + age +"]";
	}
}
