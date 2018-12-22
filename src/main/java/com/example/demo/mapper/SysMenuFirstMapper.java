package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.pojo.vo.SysMenuFirst;

public interface SysMenuFirstMapper {

	public List<SysMenuFirst> selectAll();

	public Boolean create(@Param("id")String id,@Param("name") String name,@Param("order") Integer order);

	public Boolean update(@Param("id")String id,@Param("name") String name,@Param("order") Integer order);

	public Boolean delete(@Param("id")String id);

}
