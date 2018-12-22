package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.pojo.vo.Administrators;

public interface AdminMapper {

	public Administrators selectByUserAndPass(@Param("user") String user,@Param("password") String password);

}
