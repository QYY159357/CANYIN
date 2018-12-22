package com.example.demo.service;

import com.example.demo.config.bean.entity.ResultMap;

public interface AdminService {

	public ResultMap selectAdminByUser(String user,String password);


}
