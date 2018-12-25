package com.example.demo.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.service.FoodService;
import com.example.demo.validator.CheckJson;

@Validated
@Controller
@RequestMapping(value = { "food" })
public class FoodController {

	@Autowired
	private FoodService foodService;

	@ResponseBody
	@RequestMapping(value = "/select/all", method = { RequestMethod.GET })
	public ResultMap selectAll() {
		return foodService.selectAll();
	}

	@ResponseBody
	@RequestMapping(value = "/select/one/by/id", method = { RequestMethod.GET })
	public ResultMap selectOneById(
			
			@Pattern(regexp = "[0-9a-z]{32}", message = "菜品ID格式不正确")
			@RequestParam(required = true) String id) {
		return foodService.selectOneById(id);
	}

	@ResponseBody
	@RequestMapping(value = "/select/all/for/sys", method = { RequestMethod.GET })
	public ResultMap selectAllForSys(
			
			@Min(value = 1, message = "页数最小值为{value}")
			@RequestParam(required = false, defaultValue = "1") Integer currentPage,
			
			@Min(value = 1, message = "显示条数最小值为{value}")
			@RequestParam(required = false, defaultValue = "10") Integer pageSize,
			
			@RequestParam(required = false) String classify,
			
			@RequestParam(required = false) String foodName) {
		return foodService.selectAllForSys(currentPage, pageSize,classify,foodName);
	}

	/**
	 * 
	 * @param name
	 *            菜品名
	 * @param image
	 *            菜品图片
	 * @param price
	 *            菜品单价
	 * @param info
	 *            菜品简介
	 * @param menuClassifyId
	 *            菜品分类ID
	 * @param order
	 *            菜品排序
	 * @param stock
	 *            菜品库存
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/create" }, method = { RequestMethod.POST })
	public ResultMap create(

			@Size(min = 2, max = 8, message = "菜品名长度在{min}-{max}字以内") 
			@RequestParam(required = true) String name,

			@RequestParam(required = true) String image,

			@Min(value = 0, message = "价格不能小于{value}") 
			@RequestParam(required = true) Double price,

			@RequestParam(required = false, defaultValue = "") String info,

			@Pattern(regexp = "[0-9a-z]{32}", message = "菜品分类ID格式不正确") 
			@RequestParam(required = true) String menuClassifyId,
			
			@Min(value = 0, message = "请选择是否隐藏菜品！") 
			@RequestParam(required = true) String ishide,
			
			@Min(value = 0, message = "请选择售卖方式！") 
			@RequestParam(required = true) String salesMode,
			
			@Min(value = 0, message = "排序不能小于{value}") 
			@RequestParam(required = false, defaultValue = "0") Integer order,

			@Min(value = 0, message = "库存不能小于{value}") 
			@RequestParam(required = true) Integer stock,

			@CheckJson(isCollection = true, message = "规格-重量不是正确的格式") 
			@RequestParam(required = true) String weightList,

			@CheckJson(isCollection = true, message = "规格-口味不是正确的格式") 
			@RequestParam(required = true) String tasteList,

			@CheckJson(isCollection = true, message = "规格-做法不是正确的格式") 
			@RequestParam(required = true) String practiceList) {
		return foodService.create(name, image, price, info, menuClassifyId, order, stock, weightList, tasteList,
				practiceList,Integer.parseInt(ishide),salesMode);
	}

	@ResponseBody
	@RequestMapping(value = { "/update" }, method = { RequestMethod.POST })
	public ResultMap update(
			
			@Pattern(regexp = "[0-9a-z]{32}", message = "菜品ID格式不正确") 
			@RequestParam(required = true) String id,

			@Size(min = 2, max = 8, message = "菜品名长度在{min}-{max}字以内")
			@RequestParam(required = false) String name,

			@RequestParam(required = false) String image,

			@Min(value = 0, message = "价格不能小于{value}") 
			@RequestParam(required = false) Double price,

			@RequestParam(required = false) String info,

			@Pattern(regexp = "[0-9a-z]{32}", message = "菜品分类ID格式不正确") 
			@RequestParam(required = false) String menuClassifyId,

			@Min(value = 0, message = "排序不能小于{value}") 
			@RequestParam(required = false) Integer order,

			@Min(value = 0, message = "库存不能小于{value}") 
			@RequestParam(required = false) Integer stock,

			@CheckJson(isCollection = true, message = "规格-重量不是正确的格式")
			@RequestParam(required = true) String weightList,

			@CheckJson(isCollection = true, message = "规格-口味不是正确的格式") 
			@RequestParam(required = true) String tasteList,

			@CheckJson(isCollection = true, message = "规格-做法不是正确的格式") 
			@RequestParam(required = true) String practiceList) {
		return foodService.update(id, name, image, price, info, menuClassifyId, order, stock, weightList, tasteList,
				practiceList);
	}

	@ResponseBody
	@RequestMapping(value = { "/delete/one/by/id" }, method = { RequestMethod.POST })
	public ResultMap delete(
			
			@Pattern(regexp = "[0-9a-z]{32}", message = "菜品ID格式不正确")
			@RequestParam(required = true) String id) {
		return foodService.delete(id);
	}
	
	@ResponseBody
	@RequestMapping(value = { "/update/one/by/id" }, method = { RequestMethod.POST })
	public ResultMap isHideFood(
			@RequestParam(required = true) String id,
			@RequestParam(required = true) String ishide) {
		
		return foodService.isHideFood(id,Integer.parseInt(ishide));
	}
	/**
	 * 全场折扣值
	 */
	@ResponseBody
	@RequestMapping(value = { "/update/price/for/all" }, method = { RequestMethod.POST })
	public ResultMap updateFoodPrice(
			//@Pattern(regexp = "[0-9a-z]{32}", message = "只能输入纯数据,如:8.8")
			@RequestParam(required = true) String discountPrice) {
		
		return foodService.updateFoodPrice(Float.parseFloat(discountPrice));
	}
	/**
	 * 全场折扣值
	 */
	@ResponseBody
	@RequestMapping(value = { "/query/discountPrice" }, method = { RequestMethod.POST })
	public ResultMap queryFoodPrice() {
		
		return foodService.queryFoodPrice();
	}

}
