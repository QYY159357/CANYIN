package com.example.demo.pojo.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private Date createTime;

	private Date updateTime;
	
	@JsonIgnore
	private String openid;

	private String nickName;

	private String sex;

	private String phone;

	private String avatarUrl;

	private Double storageValue;

	private Integer integralNow;

	private Integer integralTotal;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Double getStorageValue() {
		return storageValue;
	}

	public void setStorageValue(Double storageValue) {
		this.storageValue = storageValue;
	}

	public Integer getIntegralNow() {
		return integralNow;
	}

	public void setIntegralNow(Integer integralNow) {
		this.integralNow = integralNow;
	}

	public Integer getIntegralTotal() {
		return integralTotal;
	}

	public void setIntegralTotal(Integer integralTotal) {
		this.integralTotal = integralTotal;
	}

}
