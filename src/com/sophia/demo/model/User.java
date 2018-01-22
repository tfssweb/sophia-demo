package com.sophia.demo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户模型
 */
public class User implements Serializable {

	private static final long serialVersionUID = 8724931845164869509L;
	private Long id;
	private String name;
	private Integer age;
	private Date birthday;
	
	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age
				+ ", birthday=" + birthday + "]";
	}
	
}
