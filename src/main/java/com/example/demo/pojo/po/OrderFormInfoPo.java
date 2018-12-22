package com.example.demo.pojo.po;

import java.io.Serializable;

public class OrderFormInfoPo implements Serializable {

	private static final long serialVersionUID = 1L;
	// 商品ID
	private String foodId;
	// 商品名
	private String name;
	// 商品单价
	private Double price;
	// 商品图片
	private String image;
	// 商品数据
	private Integer amount;
	// 规格-重量
	private Weight weight;
	// 规格-口味
	private String taste;
	// 规格-做法
	private String practice;

	public OrderFormInfoPo() {
		// TODO Auto-generated constructor stub
	}

	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Weight getWeight() {
		return weight;
	}

	public void setWeight(Weight weight) {
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

