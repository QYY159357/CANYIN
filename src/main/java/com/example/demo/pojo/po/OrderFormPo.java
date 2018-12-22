package com.example.demo.pojo.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderFormPo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String seat;

	private String remarks = "";

	private Double priceDiscount = 0.0;

	private Double priceTotal;

	private String phone = "";

	private String userWeiXinId;

	private List<OrderFormInfoPo> orderFormInfoPoList = new ArrayList<OrderFormInfoPo>();

	public OrderFormPo() {
		// TODO Auto-generated constructor stub
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getPriceDiscount() {
		return priceDiscount;
	}

	public void setPriceDiscount(Double priceDiscount) {
		this.priceDiscount = priceDiscount;
	}

	public Double getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(Double priceTotal) {
		this.priceTotal = priceTotal;
	}

	public List<OrderFormInfoPo> getOrderFormInfoPoList() {
		return orderFormInfoPoList;
	}

	public void setOrderFormInfoPoList(List<OrderFormInfoPo> orderFormInfoPoList) {
		this.orderFormInfoPoList = orderFormInfoPoList;
	}

	public String getUserWeiXinId() {
		return userWeiXinId;
	}

	public void setUserWeiXinId(String userWeiXinId) {
		this.userWeiXinId = userWeiXinId;
	}

}
