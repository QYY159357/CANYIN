package com.example.demo.exception;

import com.example.demo.config.bean.entity.ResultMap;

public class CommonException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ResultMap resultMap;

	public ResultMap getResultMap() {
		return resultMap;
	}

	public void setResultMap(ResultMap resultMap) {
		this.resultMap = resultMap;
	}

	public CommonException(ResultMap resultMap) {
		this.resultMap = resultMap;
	}

}
