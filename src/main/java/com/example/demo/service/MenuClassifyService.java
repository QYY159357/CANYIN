package com.example.demo.service;

import com.example.demo.config.bean.entity.ResultMap;

public interface MenuClassifyService {

	public ResultMap selectAll();

	public ResultMap create(String name, Integer order);

	public ResultMap update(String id, String name, Integer order);

	public ResultMap delete(String id);

	public ResultMap selectAllForSys(Integer currentPage, Integer pageSize);

	public ResultMap selectOneById(String id);

}
