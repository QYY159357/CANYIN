package com.example.demo.config.bean.entity;

import java.io.Serializable;
import java.util.HashMap;

public class ResultMap extends HashMap<String, Object> implements Serializable {

	private static final long serialVersionUID = 1L;

	public ResultMap() {
		// TODO Auto-generated constructor stub
	}

	public static ResultMap newInstance() {
		return new ResultMap();
	}

	public ResultMap set(String key, Object value) {
		this.put(key, value);
		return this;
	}

	public ResultMap setData(Object data) {
		this.put("data", data);
		return this;
	}

	public ResultMap success(Object data) {
		this.put("code", 200);
		this.put("msg", "success");
		this.put("data", data);
		return this;
	}

	public ResultMap fail(Integer code, String msg) {
		this.put("code", code);
		this.put("msg", msg);
		this.put("data", false);
		return this;
	}

}