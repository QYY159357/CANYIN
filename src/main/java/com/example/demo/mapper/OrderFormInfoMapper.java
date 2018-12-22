package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;

public interface OrderFormInfoMapper {

	public Boolean create(@Param("id") String id, @Param("image") String image, @Param("name") String name,
			@Param("price") Double price, @Param("amount") Integer amount, @Param("foodId") String foodId,
			@Param("orderFormId") String orderFormId, @Param("weight") String weight, @Param("taste") String taste,
			@Param("practice") String practice);
	//add by qinyanyu 根据订单id删除详情列表
	public Boolean deleteOrderInfoById(@Param("orderNos") String orderNos);
}
