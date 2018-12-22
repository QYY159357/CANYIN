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
import com.example.demo.service.SysMenuFirstService;

@Validated
@Controller
@RequestMapping(value = "/sys/menu/first")
public class SysMenuFirstController {

	@Autowired
	private SysMenuFirstService sysMenuFirstService;

	@ResponseBody
	@RequestMapping(value = { "/select/all" }, method = { RequestMethod.GET })
	public ResultMap selectAll() {
		return sysMenuFirstService.selectAll();
	}

	@ResponseBody
	@RequestMapping(value = { "/create" }, method = { RequestMethod.POST })
	public ResultMap create(
			
			@Length(min = 1, max = 12, message = "一级菜单名长度应在{min}-{max}之间")
			@RequestParam(required = true) String name,
			
			@Min(value = 0, message = "排序标记最小值为{value}")
			@RequestParam(required = false, defaultValue = "0") Integer order) {
		return sysMenuFirstService.create(name, order);
	}

	@ResponseBody
	@RequestMapping(value = { "/update" }, method = { RequestMethod.POST })
	public ResultMap update(
			
			@Pattern(regexp = "[0-9a-z]{32}", message = "一级菜单ID格式不正确")
			@RequestParam(required = true) String id, 
			
			@Length(min = 1, max = 12, message = "一级菜单名长度应在{min}-{max}之间")
			@RequestParam(required = false) String name,
			
			@Min(value = 0, message = "排序标记最小值为{value}")
			@RequestParam(required = false) Integer order) {
		return sysMenuFirstService.update(id, name, order);
	}

	@ResponseBody
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public ResultMap delete(
			
			@Pattern(regexp = "[0-9a-z]{32}", message = "一级菜单ID格式不正确")
			@RequestParam(required = true) String id) {
		return sysMenuFirstService.delete(id);
	}

}
