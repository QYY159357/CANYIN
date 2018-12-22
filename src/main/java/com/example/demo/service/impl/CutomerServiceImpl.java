package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.mapper.FoodMapper;
import com.example.demo.pojo.vo.Customer;
import com.example.demo.pojo.vo.Food;
import com.example.demo.pojo.vo.LimitPage;
import com.example.demo.service.CustomerService;
import com.example.demo.service.FoodService;
import com.example.demo.util.Util;

@Service
public class CutomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;

//	@Override
//	public ResultMap selectAll() {
//		// TODO Auto-generated method stub
//		List<Food> foodList = foodMapper.selectAll();
//		// 将list转成字符串
//		Map<String, Food> data = listToMap(foodList);
//		return ResultMap.newInstance().success(data);
//	}

	
	@Override
	public ResultMap selectAllCustomer(Integer currentPage, Integer pageSize) {
		// TODO Auto-generated method stub
		List<Customer> data = customerMapper.selectAllCustomer((currentPage - 1) * pageSize, pageSize);

		// 分页
		LimitPage limitPage = new LimitPage();
		Integer maxSize = customerMapper.selectMaxSize();
		limitPage.setMaxSize(maxSize);
		limitPage.setCurrentPage(currentPage);
		limitPage.setPageSize(pageSize);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", data);
		map.put("limitPage", limitPage);

		return ResultMap.newInstance().success(map);
	}

	@Override
	public ResultMap selectCusmByPhone(String phone) {
		Customer customer = customerMapper.selectCusmByPhone(phone);
		System.out.println("selectCusmByPhone=========");
		System.out.println(customer);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", customer);
		return ResultMap.newInstance().success(map);
	}

	@Override
	public ResultMap updateCusmStatue(String id, Integer status) {
		Boolean data = customerMapper.updateCusmStatue(id, status);
		return ResultMap.newInstance().success(data);
	}

}
