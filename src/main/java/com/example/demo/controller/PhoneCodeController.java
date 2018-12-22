package com.example.demo.controller;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.PhoneCodeService;

@Validated
@Controller
@RequestMapping(value = { "phone/code" })
public class PhoneCodeController {

	@Autowired
	private PhoneCodeService phoneCodeService;

	@ResponseBody
	@RequestMapping(value = "/send/for/{business}", method = { RequestMethod.GET })
	public Object sendForBusiness(
			
			@Pattern(regexp = "[0-9a-z]{32}", message = "用户ID格式不正确")
			@RequestParam(required = true) String id, 
			
			@Pattern(regexp = "((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}", message = "手机号不是正确的手机号码")
			@RequestParam(required = true) String phone,
			
			@PathVariable("business") String business) {
		return phoneCodeService.sendForBusiness(id, phone, business);
	}

	@ResponseBody
	@RequestMapping(value = "/check/for/{business}", method = { RequestMethod.GET })
	public Object checkForBusiness(
			
			@Pattern(regexp = "[0-9a-z]{32}", message = "用户ID格式不正确")
			@RequestParam(required = true) String id, 
			
			@Pattern(regexp = "[0-9]{5}", message = "验证码格式应为5位的数字")
			@RequestParam(required = true) String code,
			
			@Pattern(regexp = "((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}", message = "手机号不是正确的手机号码")
			@RequestParam(required = true) String phone, 
			
			@PathVariable("business") String business) {
		return phoneCodeService.checkForBusiness(id, code, phone, business);
	}

}
