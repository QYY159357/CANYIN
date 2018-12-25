package com.example.demo.service;

import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.pojo.po.OrderFormPo;

public interface OrderFormService {
	//modify by qinyanyu 入参添加订单号 ，用于判断是否重复生成订单
	public ResultMap create(OrderFormPo orderFormPo,String orderNos);

	public String wxNotify();

	public ResultMap selectAll(String id,Boolean isForUser, String userWeiXinId, Integer currentPage, Integer pageSize);

	public ResultMap selectOneById(String id);

	public ResultMap update(String id, Integer status);

	public ResultMap printingOrderInfo(String id);

}
