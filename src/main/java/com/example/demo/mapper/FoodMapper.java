package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.pojo.vo.Food;

public interface FoodMapper {

	public List<Food> selectAll();

	public Boolean create(@Param("id") String id, @Param("name") String name, @Param("image") String image,
			@Param("price") Double price, @Param("info") String info, @Param("menuClassifyId") String menuClassifyId,
			@Param("order") Integer order, @Param("stock") Integer stock, @Param("weightList") String weightList,
			@Param("tasteList") String tasteList, @Param("practiceList") String practiceList,@Param("ishide") Integer ishide);

	public Boolean update(@Param("id") String id, @Param("name") String name, @Param("image") String image,
			@Param("price") Double price, @Param("info") String info, @Param("menuClassifyId") String menuClassifyId,
			@Param("order") Integer order, @Param("stock") Integer stock, @Param("weightList") String weightList,
			@Param("tasteList") String tasteList, @Param("practiceList") String practiceList);

	public Boolean delete(@Param("id") String id);

	public Food selectById(@Param("id") String id);

	public List<Food> selectAllForSys(@Param("currentPage") Integer currentPage, 
			@Param("pageSize") Integer pageSize,@Param("classify") String classify,
			@Param("foodName") String foodName);

	public Food selectOneById(@Param("id") String id);

	public Integer selectMaxSize(@Param("classify") String classify,@Param("foodName") String foodName);
	
	public Integer isHideFood(@Param("id") String id,@Param("ishide") Integer ishide);

}
