package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.pojo.vo.Administrators;
import com.example.demo.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminMapper adminMapper;
	@Override
	public ResultMap selectAdminByUser(String user, String password) {
		Administrators admin = adminMapper.selectByUserAndPass(user, password);
		if(admin == null){
			return ResultMap.newInstance().fail(1006, "无此用户");
		}
		return ResultMap.newInstance().success(admin);
	}

}
