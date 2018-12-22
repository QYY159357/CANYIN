package com.example.demo.pojo.po;

import java.io.Serializable;

public class Weight implements Serializable {

	private static final long serialVersionUID = 1L;

	private String weight;

	private Double price;

	public Weight() {
		// TODO Auto-generated constructor stub
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
