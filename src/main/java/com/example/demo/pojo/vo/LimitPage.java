package com.example.demo.pojo.vo;

import java.io.Serializable;

public class LimitPage implements Serializable {

	private static final long serialVersionUID = -4105973122306207043L;

	private Integer currentPage = 1;
	private Integer maxSize = 0;
	private Integer pageSize = 0;
	private Integer firstPage = 1;
	@SuppressWarnings("unused")
	private Integer lastPage = 1;
	@SuppressWarnings("unused")
	private Integer nextPage = 1;
	@SuppressWarnings("unused")
	private Integer previousPage = 1;

	public LimitPage() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(Integer firstPage) {
		this.firstPage = firstPage;
	}

	public Integer getLastPage() {
		Integer lastPage = getMaxSize() / getPageSize();
		if (lastPage * getPageSize() < getMaxSize()) {
			return lastPage + 1;
		} else {
			return lastPage;
		}
	}

	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}

	public Integer getNextPage() {
		if (getCurrentPage() < getLastPage()) {
			return getCurrentPage() + 1;
		} else {
			return getLastPage();
		}
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getPreviousPage() {
		if (getCurrentPage() > getFirstPage()) {
			return getCurrentPage() - 1;
		} else {
			return getFirstPage();
		}
	}

	public void setPreviousPage(Integer previousPage) {
		this.previousPage = previousPage;
	}

}