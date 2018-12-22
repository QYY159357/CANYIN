package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.mapper.MenuClassifyMapper;
import com.example.demo.pojo.vo.LimitPage;
import com.example.demo.pojo.vo.MenuClassify;
import com.example.demo.service.MenuClassifyService;
import com.example.demo.util.Util;

@Service
public class MenuClassifyServiceImpl implements MenuClassifyService {

	@Autowired
	private MenuClassifyMapper menuClassifyMapper;

	@Override
	public ResultMap selectAll() {
		// TODO Auto-generated method stub
		List<MenuClassify> data = menuClassifyMapper.selectAll();
		return ResultMap.newInstance().success(data);
	}

	@Override
	public ResultMap selectAllForSys(Integer currentPage, Integer pageSize) {
		// TODO Auto-generated method stub
		List<MenuClassify> data = menuClassifyMapper.selectAllForSys((currentPage - 1) * pageSize, pageSize);

		// 分页
		LimitPage limitPage = new LimitPage();
		Integer maxSize = menuClassifyMapper.selectMaxSize();
		limitPage.setMaxSize(maxSize);
		limitPage.setCurrentPage(currentPage);
		limitPage.setPageSize(pageSize);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", data);
		map.put("limitPage", limitPage);
		return ResultMap.newInstance().success(map);
	}

	@Override
	public ResultMap selectOneById(String id) {
		// TODO Auto-generated method stub
		MenuClassify data = menuClassifyMapper.selectOneById(id);
		return ResultMap.newInstance().success(data);
	}

	@Override
	public ResultMap create(String name, Integer order) {
		// TODO Auto-generated method stub
		String id = Util.UUID();
		Boolean data = menuClassifyMapper.create(id, name, order);
		return ResultMap.newInstance().success(data);
	}

	@Override
	public ResultMap update(String id, String name, Integer order) {
		// TODO Auto-generated method stub
		Boolean data = menuClassifyMapper.update(id, name, order);
		return ResultMap.newInstance().success(data);
	}

	@Override
	public ResultMap delete(String id) {
		// TODO Auto-generated method stub
		Boolean data = menuClassifyMapper.delete(id);
		return ResultMap.newInstance().success(data);
	}

}
