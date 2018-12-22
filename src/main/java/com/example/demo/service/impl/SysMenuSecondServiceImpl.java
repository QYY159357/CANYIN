package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.mapper.SysMenuSecondMapper;
import com.example.demo.service.SysMenuSecondService;
import com.example.demo.util.Util;

@Service
public class SysMenuSecondServiceImpl implements SysMenuSecondService {
	
	@Autowired
	private SysMenuSecondMapper sysMenuSecondMapper;

	@Override
	public ResultMap selectAll() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public ResultMap create(String name, String url, String sysMenuFirstId, Integer order) {
		// TODO Auto-generated method stub
		String id = Util.UUID();
		Boolean data = sysMenuSecondMapper.create(id,name,url,sysMenuFirstId,order);
		return ResultMap.newInstance().success(data);
	}

	@Override
	public ResultMap update(String id, String name, String url, String sysMenuFirstId, Integer order) {
		// TODO Auto-generated method stub
		Boolean data = sysMenuSecondMapper.update(id,name,url,sysMenuFirstId,order);
		return ResultMap.newInstance().success(data);
	}

	@Override
	public ResultMap delete(String id) {
		// TODO Auto-generated method stub
		Boolean data = sysMenuSecondMapper.delete(id );
		return ResultMap.newInstance().success(data);
	}


}
