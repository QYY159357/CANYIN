package com.example.demo.pojo.vo;

import java.util.Date;

public class Customer {
	private String id;
	private Date createTime;
	private Date updateTime;
	private String openid;
	private String nickName;
	private String sex;
	private String phone;
	private String avatarURL;
	private Double storageValue;
	private int integralNow;
	private int integralTotal;
	private String activationTime;
	private int status;
	
	public String getId() {
		return id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public String getAvatarURL() {
		return avatarURL;
	}
	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}
	public Double getStorageValue() {
		return storageValue;
	}
	public void setStorageValue(Double storageValue) {
		this.storageValue = storageValue;
	}
	
	public int getIntegralTotal() {
		return integralTotal;
	}
	public void setIntegralTotal(int integralTotal) {
		this.integralTotal = integralTotal;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public int getIntegralNow() {
		return integralNow;
	}
	public void setIntegralNow(int integralNow) {
		this.integralNow = integralNow;
	}
	public String getActivationTime() {
		return activationTime;
	}
	public void setActivationTime(String activationTime) {
		this.activationTime = activationTime;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", openid=" + openid
				+ ", nickName=" + nickName + ", sex=" + sex + ", phone="
				+ phone + ", avatarURL=" + avatarURL + ", storageValue="
				+ storageValue + ", integralNow=" + integralNow
				+ ", integralTotal=" + integralTotal + "]";
	}
	
}
