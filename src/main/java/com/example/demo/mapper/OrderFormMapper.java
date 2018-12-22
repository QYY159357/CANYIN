package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.pojo.vo.OrderForm;

public interface OrderFormMapper {

	public Boolean create(@Param("id") String id, @Param("seat") String seat, @Param("remarks") String remarks,
			@Param("priceTotal") Double priceTotal, @Param("phone") String phone,
			@Param("priceDiscount") Double priceDiscount, @Param("status") Integer status,
			@Param("userWeiXinId") String userWeiXinId, @Param("orderNo") String orderNo);

	public List<OrderForm> selectAll(@Param("id") String id, @Param("isForUser") Boolean isForUser,
			@Param("userWeiXinId") String userWeiXinId, @Param("currentPage") Integer currentPage,
			@Param("pageSize") Integer pageSize);

	public OrderForm selectOneById(@Param("id") String id);

	public Boolean update(@Param("id") String id, @Param("status") Integer status);

	public Integer selectMaxSize(@Param("id") String id, @Param("isForUser") Boolean isForUser,
			@Param("userWeiXinId") String userWeiXinId);
	
	//add by qinyanyu 根据订单id删除 
	public Boolean deleteOrderFormByNo(@Param("orderNo") String orderNo);
	//end by qinyanyu 根据订单id删除 

}
