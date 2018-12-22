package com.example.demo.service;

import com.example.demo.config.bean.entity.ResultMap;

public interface SysMenuSecondService {

	public ResultMap selectAll();

	public ResultMap create(String name, String url, String sysMenuFirstId, Integer order);

	public ResultMap update(String id, String name, String url, String sysMenuFirstId, Integer order);

	public ResultMap delete(String id);

}
