package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;

public interface SysMenuSecondMapper {

	public Boolean create(@Param("id")String id,@Param("name") String name,@Param("url") String url,@Param("sysMenuFirstId") String sysMenuFirstId,@Param("order") Integer order);

	public Boolean update(@Param("id")String id,@Param("name") String name,@Param("url") String url,@Param("sysMenuFirstId") String sysMenuFirstId,@Param("order") Integer order);

	public Boolean delete(@Param("id")String id);

}
