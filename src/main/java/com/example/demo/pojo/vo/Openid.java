package com.example.demo.pojo.vo;

import java.io.Serializable;

public class Openid implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String session_key;
	
	private String openid;
	
	public Openid() {
		// TODO Auto-generated constructor stub
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	

}
