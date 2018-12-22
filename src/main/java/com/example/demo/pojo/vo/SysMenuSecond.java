package com.example.demo.pojo.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SysMenuSecond implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private Date createTime;
	
	private Date updateTime;
	
	private String name;
	
	private String url;
	
	@JsonIgnore
	private String sysMenuFirstId;
	
	private Integer order;
	
	public SysMenuSecond() {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSysMenuFirstId() {
		return sysMenuFirstId;
	}

	public void setSysMenuFirstId(String sysMenuFirstId) {
		this.sysMenuFirstId = sysMenuFirstId;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
}

