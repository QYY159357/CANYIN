package com.example.demo.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.pojo.po.OrderFormPo;
import com.example.demo.service.OrderFormService;

@Validated
@Controller
@RequestMapping(value = "/order/form")
public class OrderFormController {

	@Autowired
	private OrderFormService orderFormService;

	/**
	 * 统一下单
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/create", method = { RequestMethod.POST })
	public ResultMap create(@RequestBody OrderFormPo orderFormPo,@RequestParam(required = false) String orderNos) {
		//modify by qinyanyu 入参添加订单号 ，用于判断是否重复生成订单
		return orderFormService.create(orderFormPo,orderNos);
	}

	@ResponseBody
	@RequestMapping(value = "/wx/notify", method = { RequestMethod.GET, RequestMethod.POST })
	public Object wxNotify() {
		return orderFormService.wxNotify();
	}

	@ResponseBody
	@RequestMapping(value = "/select/all", method = { RequestMethod.GET })
	public ResultMap selectAll(
			
			//@Pattern(regexp = "[0-9a-z]{32}", message = "订单ID格式不正确")
			@RequestParam(required = false) String id,
			
			@RequestParam(required = true) Boolean isForUser, 
			
			@Pattern(regexp = "[0-9a-z]{32}", message = "用户ID格式不正确")
			@RequestParam(required = false) String userWeiXinId,
			
			@Min(value = 1, message = "页数最小值为{value}")
			@RequestParam(required = false, defaultValue = "1") Integer currentPage,
			
			@Min(value = 1, message = "显示条数最小值为{value}")
			@RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		return orderFormService.selectAll(id, isForUser, userWeiXinId, currentPage, pageSize);
	}

	@ResponseBody
	@RequestMapping(value = "/select/one/by/id", method = { RequestMethod.GET })
	public ResultMap selectOneById(
			
			@Pattern(regexp = "[0-9a-z]{32}", message = "订单ID格式不正确")
			@RequestParam(required = true) String id) {
		return orderFormService.selectOneById(id);
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = { RequestMethod.POST })
	public ResultMap update(
			
			@Pattern(regexp = "[0-9a-z]{32}", message = "订单ID格式不正确")
			@RequestParam(required = true) String id, 
			
			
			@RequestParam(required = true) Integer status) {
		return orderFormService.update(id, status);
	}

	@ResponseBody
	@RequestMapping(value = "/printing/order/info", method = { RequestMethod.POST })
	public ResultMap printingOrderInfo(
			@Pattern(regexp = "[0-9a-z]{32}", message = "订单ID格式不正确")
			@RequestParam(required = true) String id) {
		return orderFormService.printingOrderInfo(id);
	}
	
	
	}
