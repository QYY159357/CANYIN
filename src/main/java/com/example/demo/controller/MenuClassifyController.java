package com.example.demo.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.service.MenuClassifyService;

@Validated
@Controller
@RequestMapping(value = { "menu/classify" })
public class MenuClassifyController {

	@Autowired
	private MenuClassifyService menuClassifyService;

	@ResponseBody
	@RequestMapping(value = { "/select/all" }, method = { RequestMethod.GET })
	public ResultMap selectAll() {
		return menuClassifyService.selectAll();
	}

	@ResponseBody
	@RequestMapping(value = { "/select/all/for/sys" }, method = { RequestMethod.GET })
	public ResultMap selectAllForSys(
			
			@Min(value = 1, message = "页数最小值为{value}")
			@RequestParam(required = false, defaultValue = "1") Integer currentPage,
			
			@Min(value = 1, message = "显示条数最小值为{value}")
			@RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		return menuClassifyService.selectAllForSys(currentPage, pageSize);
	}

	@ResponseBody
	@RequestMapping(value = { "/select/one/by/id" }, method = { RequestMethod.GET })
	public ResultMap selectOneById(
			
			@Pattern(regexp = "[0-9a-z]{32}", message = "菜品分类ID格式不正确")
			@RequestParam(required = true) String id) {
		return menuClassifyService.selectOneById(id);
	}

	@ResponseBody
	@RequestMapping(value = { "/create" }, method = { RequestMethod.POST })
	public ResultMap create(
			
			@Length(min = 1, max = 12, message = "菜品分类名长度应在{min}-{max}之间")
			@RequestParam(required = true) String name,
			
			@Min(value = 0, message = "排序标记最小值为{value}")
			@RequestParam(required = false, defaultValue = "0") Integer order) {
		return menuClassifyService.create(name, order);
	}

	@ResponseBody
	@RequestMapping(value = { "/update" }, method = { RequestMethod.POST })
	public ResultMap update(
			
			@Pattern(regexp = "[0-9a-z]{32}", message = "菜品分类ID格式不正确")
			@RequestParam(required = true) String id, 
			
			@Length(min = 1, max = 12, message = "菜品分类名长度应在{min}-{max}之间")
			@RequestParam(required = false) String name,
			
			@Min(value = 0, message = "排序标记最小值为{value}")
			@RequestParam(required = false) Integer order) {
		return menuClassifyService.update(id, name, order);
	}

	@ResponseBody
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public ResultMap delete(
			
			@Pattern(regexp = "[0-9a-z]{32}", message = "菜品分类ID格式不正确")
			@RequestParam(required = true) String id) {
		return menuClassifyService.delete(id);
	}

}
