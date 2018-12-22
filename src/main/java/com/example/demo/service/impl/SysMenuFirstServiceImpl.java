package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.mapper.SysMenuFirstMapper;
import com.example.demo.pojo.vo.SysMenuFirst;
import com.example.demo.service.SysMenuFirstService;
import com.example.demo.util.Util;

@Service
public class SysMenuFirstServiceImpl implements SysMenuFirstService {

	@Autowired
	private SysMenuFirstMapper sysMenuFirstMapper;

	@Override
	public ResultMap selectAll() {
		// TODO Auto-generated method stub
		List<SysMenuFirst> data = sysMenuFirstMapper.selectAll();
		return ResultMap.newInstance().success(data);
	}

	@Override
	public ResultMap create(String name, Integer order) {
		// TODO Auto-generated method stub
		String id = Util.UUID();
		Boolean data = sysMenuFirstMapper.create(id,name,order);
		return ResultMap.newInstance().success(data);
	}

	@Override
	public ResultMap update(String id, String name, Integer order) {
		// TODO Auto-generated method stub
		Boolean data = sysMenuFirstMapper.update(id,name,order);
		return ResultMap.newInstance().success(data);
	}

	@Override
	public ResultMap delete(String id) {
		// TODO Auto-generated method stub
		Boolean data = sysMenuFirstMapper.delete(id );
		return ResultMap.newInstance().success(data);
	}

}
