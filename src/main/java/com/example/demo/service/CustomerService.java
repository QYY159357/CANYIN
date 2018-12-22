package com.example.demo.service;

import org.apache.ibatis.annotations.Param;

import com.example.demo.config.bean.entity.ResultMap;

public interface CustomerService {

	//public ResultMap selectAll();

	public ResultMap selectAllCustomer(Integer currentPage, Integer pageSize);

	public ResultMap selectCusmByPhone(String phone);
	
	public ResultMap updateCusmStatue( String id,Integer status);
}
