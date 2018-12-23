package com.example.demo.service;

import com.example.demo.config.bean.entity.ResultMap;

public interface FoodService {

	public ResultMap selectAll();

	public ResultMap create(String name, String image, Double price, String info, String menuClassifyId, Integer order,
			Integer stock, String weightList, String tasteList, String practiceList,Integer ishide,String salesMode);

	public ResultMap update(String id, String name, String image, Double price, String info, String menuClassifyId,
			Integer order, Integer stock, String weightList, String tasteList, String practiceList);

	public ResultMap delete(String id);

	public ResultMap selectAllForSys(Integer currentPage, Integer pageSize,String classify,String foodName);

	public ResultMap selectOneById(String id);
	
	public ResultMap isHideFood(String id,Integer ishide);

}
