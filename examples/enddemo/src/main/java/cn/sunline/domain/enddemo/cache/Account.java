package cn.sunline.domain.enddemo.cache;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Account implements Serializable{
	
	private Integer id;
	private String name;
	private String password;
	
	public Account(String name){
		this.name = name;
	}
	
	public Account(String name, String password){
		this.name = name;
		this.password = password;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
