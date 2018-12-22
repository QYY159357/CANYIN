package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.service.AdminService;


@Validated
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@ResponseBody
	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public ResultMap login(		
			@RequestParam(required = true) String user,			
			@RequestParam(required = true) String password) {
		return adminService.selectAdminByUser(user, password);
	}

}
