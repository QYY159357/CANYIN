package com.example.demo.service;

import com.example.demo.config.bean.entity.ResultMap;

public interface UserService {

	public ResultMap login(String code, String nickName, String avatarUrl, String gender);

	public ResultMap selectUserById(String id);

	public ResultMap selectAll(Integer currentPage, Integer pageSize);

	public ResultMap update(String id, String nickName, String avatarUrl, String gender);

}
