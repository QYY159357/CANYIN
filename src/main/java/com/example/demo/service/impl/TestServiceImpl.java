package com.example.demo.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.TestMapper;
import com.example.demo.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestMapper testMapper;
	
	@Autowired
	private HttpServletRequest request;

	@Override
	public Object test() {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("name"));
		return testMapper.test();
	}

}
