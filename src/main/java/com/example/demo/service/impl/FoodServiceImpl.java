package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.mapper.FoodMapper;
import com.example.demo.pojo.vo.Food;
import com.example.demo.pojo.vo.LimitPage;
import com.example.demo.service.FoodService;
import com.example.demo.util.Util;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodMapper foodMapper;

	@Override
	public ResultMap selectAll() {
		// TODO Auto-generated method stub
		List<Food> foodList = foodMapper.selectAll();
		// 将list转成字符串
		Map<String, Food> data = listToMap(foodList);
		return ResultMap.newInstance().success(data);
	}

	@Override
	public ResultMap selectOneById(String id) {
		// TODO Auto-generated method stub
		Food data = foodMapper.selectOneById(id);
		return ResultMap.newInstance().success(data);
	}

	private Map<String, Food> listToMap(List<Food> foodList) {
		Map<String, Food> map = new HashMap<String, Food>();
		for (Food food : foodList) {
			map.put(food.getId(), food);
		}
		return map;
	}

	@Override
	public ResultMap create(String name, String image, Double price, String info, String menuClassifyId, Integer order,
			Integer stock, String weightList, String tasteList, String practiceList,Integer ishide,String salesMode) {
		// TODO Auto-generated method stub
		String id = Util.UUID();
		Boolean data = foodMapper.create(id, name, image, price, info, menuClassifyId, order, stock, weightList,
				tasteList, practiceList,ishide,salesMode);
		return ResultMap.newInstance().success(data);
	}

	@Override
	public ResultMap update(String id, String name, String image, Double price, String info, String menuClassifyId,
			Integer order, Integer stock, String weightList, String tasteList, String practiceList) {
		// TODO Auto-generated method stub
		Boolean data = foodMapper.update(id, name, image, price, info, menuClassifyId, order, stock, weightList,
				tasteList, practiceList);
		return ResultMap.newInstance().success(data);
	}

	@Override
	public ResultMap delete(String id) {
		// TODO Auto-generated method stub
		Boolean data = foodMapper.delete(id);
		return ResultMap.newInstance().success(data);
	}

	@Override
	public ResultMap selectAllForSys(Integer currentPage, Integer pageSize,String classify,String foodName) {
		// TODO Auto-generated method stub
		int num = 0;
		if("0".equals(classify)){
			classify = "";
		}
		List<Food> data = foodMapper.selectAllForSys((currentPage - 1) * pageSize, pageSize,classify,foodName);

		// 分页
		LimitPage limitPage = new LimitPage();
		Integer maxSize = foodMapper.selectMaxSize(classify,foodName);
		limitPage.setMaxSize(maxSize);
		limitPage.setCurrentPage(currentPage);
		limitPage.setPageSize(pageSize);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", data);
		map.put("limitPage", limitPage);

		return ResultMap.newInstance().success(map);
	}

	@Override
	public ResultMap isHideFood(String id,Integer ishide) {
		Integer result = foodMapper.isHideFood(id, ishide);
		return ResultMap.newInstance().success(result);
	}

	@Override
	public ResultMap updateFoodPrice(Float discountPrice) {
		Integer result = foodMapper.updateFoodPrice(discountPrice);
		return ResultMap.newInstance().success(result);
	}

}
