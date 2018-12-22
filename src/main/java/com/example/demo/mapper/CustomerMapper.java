package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.pojo.vo.Customer;

public interface CustomerMapper {

//	public List<Food> selectAll();

	public List<Customer> selectAllCustomer(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

	public Customer selectCusmByPhone(@Param("phone") String phone);

	public Integer selectMaxSize();
	
	public Boolean updateCusmStatue(@Param("id") String id,@Param("status") Integer status);


}
