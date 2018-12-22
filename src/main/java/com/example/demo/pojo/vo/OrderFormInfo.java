package com.example.demo.pojo.vo;

import java.io.Serializable;
import java.util.Date;

public class OrderFormInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private Date createTime;

	private Date updateTime;

	private String image;

	private String name;

	private Double price;

	private Integer amount;

	private String orderFormId;

	private String weight;

	private String taste;

	private String practice;

	public OrderFormInfo() {
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getOrderFormId() {
		return orderFormId;
	}

	public void setOrderFormId(String orderFormId) {
		this.orderFormId = orderFormId;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
	}

	public String getPractice() {
		return practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	}

}
