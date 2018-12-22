package com.example.demo.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.service.UserService;

@Validated
@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public ResultMap login(
			
			@RequestParam(required = true) String code,
			
			
			@RequestParam(required = false, defaultValue = "") String nickName,
			
			
			@RequestParam(required = false, defaultValue = "") String avatarUrl,
			
			
			@RequestParam(required = false, defaultValue = "") String gender) {
		return userService.login(code, nickName, avatarUrl, gender);
	}

	@ResponseBody
	@RequestMapping(value = "/select/user/by/id", method = { RequestMethod.GET })
	public ResultMap selectUserById(
			
			@Pattern(regexp = "[0-9a-z]{32}", message = "用户ID格式不正确")
			@RequestParam(required = true) String id) {
		return userService.selectUserById(id);
	}

	@ResponseBody
	@RequestMapping(value = "/select/all", method = { RequestMethod.GET })
	public ResultMap selectAll(
			
			@Min(value = 1, message = "页数最小值为{value}")
			@RequestParam(required = false, defaultValue = "1") Integer currentPage,
			
			@Min(value = 1, message = "显示条数最小值为{value}")
			@RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		return userService.selectAll(currentPage, pageSize);
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = { RequestMethod.POST })
	public ResultMap update(
			
			@Pattern(regexp = "[0-9a-z]{32}", message = "用户ID格式不正确")
			@RequestParam(required = true) String id, 
			
			
			@RequestParam(required = false) String nickName,
			
			
			@RequestParam(required = false) String avatarUrl, 
			
			
			@RequestParam(required = false) String gender) {
		return userService.update(id, nickName, avatarUrl, gender);
	}

}
