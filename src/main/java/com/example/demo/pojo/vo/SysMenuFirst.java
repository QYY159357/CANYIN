package com.example.demo.pojo.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SysMenuFirst implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private Date createTime;

	private Date updateTime;

	private String name;

	private Integer order;
	
	private List<SysMenuSecond> sysMenuSecondList = new ArrayList<SysMenuSecond>();

	public SysMenuFirst() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public List<SysMenuSecond> getSysMenuSecondList() {
		return sysMenuSecondList;
	}

	public void setSysMenuSecondList(List<SysMenuSecond> sysMenuSecondList) {
		this.sysMenuSecondList = sysMenuSecondList;
	}

}
