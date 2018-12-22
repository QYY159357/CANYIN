package com.example.demo.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.service.CustomerService;
import com.example.demo.service.FoodService;
import com.example.demo.validator.CheckJson;

@Validated
@Controller
@RequestMapping(value = { "customer" })
public class CustomerController {

	@Autowired
	private CustomerService customerService;

//	@ResponseBody
//	@RequestMapping(value = "/select/all", method = { RequestMethod.GET })
//	public ResultMap selectAll() {
//		return foodService.selectAll();
//	}

	@ResponseBody
	@RequestMapping(value = "/select/one/by/phone", method = { RequestMethod.GET })
	public ResultMap selectCusmByPhone(
			
			@Pattern(regexp = "^-?\\d+$", message = "请输入正确手机号!")
			@RequestParam(required = false) String phone) {
			
		return customerService.selectCusmByPhone(phone);
	}

	@ResponseBody
	@RequestMapping(value = "/select/all/for/cusm", method = { RequestMethod.GET })
	public ResultMap selectAllCustomer(
			
			@Min(value = 1, message = "页数最小值为{value}")
			@RequestParam(required = false, defaultValue = "1") Integer currentPage,
			
			@Min(value = 1, message = "显示条数最小值为{value}")
			@RequestParam(required = false, defaultValue = "10") Integer pageSize) {
	
		return customerService.selectAllCustomer(currentPage, pageSize);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/status", method = { RequestMethod.GET })
	public ResultMap updateCusmStatue(
			@RequestParam(required = true) String id,	
			@RequestParam(required = true) Integer status) {
	
		return customerService.updateCusmStatue(id, status);
	}

	

}
