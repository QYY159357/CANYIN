package com.example.demo.validator;

import java.io.Serializable;

public class JsonObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String weight;

	private Double price;

	private String name;

	public JsonObject() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
