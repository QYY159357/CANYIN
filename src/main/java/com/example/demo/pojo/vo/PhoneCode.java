package com.example.demo.pojo.vo;

import java.io.Serializable;

public class PhoneCode implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String phone;

	private String code;

	public PhoneCode() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
