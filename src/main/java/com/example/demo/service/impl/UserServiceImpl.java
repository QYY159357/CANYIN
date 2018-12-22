package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.bean.WeiXin;
import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.vo.LimitPage;
import com.example.demo.pojo.vo.Openid;
import com.example.demo.pojo.vo.User;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private WeiXin weixin;

	@Override
	public ResultMap login(String code, String nickName, String avatarUrl, String gender) {
		// TODO Auto-generated method stub
		// 获得openid
		Openid openidData = weixin.getOpenid(code);
		String openid = openidData.getOpenid();
		// 将openid转换成id
		String id = DigestUtils.md5Hex(openid);
		// 保存到数据库
		userMapper.create(id, openid, nickName, avatarUrl, gender);
		// 从数据库查询
		User user = userMapper.selectById(id);
		return ResultMap.newInstance().success(user);
	}

	@Override
	public ResultMap selectUserById(String id) {
		// TODO Auto-generated method stub
		User user = userMapper.selectById(id);
		return ResultMap.newInstance().success(user);
	}

	@Override
	public ResultMap selectAll(Integer currentPage, Integer pageSize) {
		// TODO Auto-generated method stub
		List<User> data = userMapper.selectAll((currentPage - 1) * pageSize, pageSize);

		// 分页
		LimitPage limitPage = new LimitPage();
		Integer maxSize = userMapper.selectMaxSize();
		limitPage.setMaxSize(maxSize);
		limitPage.setCurrentPage(currentPage);
		limitPage.setPageSize(pageSize);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", data);
		map.put("limitPage", limitPage);

		return ResultMap.newInstance().success(map);
	}

	@Override
	public ResultMap update(String id, String nickName, String avatarUrl, String gender) {
		// TODO Auto-generated method stub
		Boolean data = userMapper.update(id, nickName, avatarUrl, gender, null);
		return ResultMap.newInstance().success(data);
	}

}
