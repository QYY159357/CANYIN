package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.pojo.vo.User;

public interface UserMapper {

	public Boolean create(@Param("id") String id, @Param("openid") String openid, @Param("nickName") String nickName,
			@Param("avatarUrl") String avatarUrl, @Param("gender") String gender);

	public User selectById(@Param("id") String id);

	public List<User> selectAll(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

	public Integer selectMaxSize();

	public Boolean update(@Param("id") String id, @Param("nickName") String nickName,
			@Param("avatarUrl") String avatarUrl, @Param("gender") String gender, @Param("phone") String phone);

}
