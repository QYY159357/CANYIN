package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.pojo.vo.MenuClassify;

public interface MenuClassifyMapper {

	public List<MenuClassify> selectAll();

	public Boolean create(@Param("id") String id, @Param("name") String name, @Param("order") Integer order);

	public Boolean update(@Param("id") String id, @Param("name") String name, @Param("order") Integer order);

	public Boolean delete(@Param("id") String id);

	public List<MenuClassify> selectAllForSys(@Param("currentPage") Integer currentPage,
			@Param("pageSize") Integer pageSize);

	public MenuClassify selectOneById(@Param("id") String id);

	public Integer selectMaxSize();

}
