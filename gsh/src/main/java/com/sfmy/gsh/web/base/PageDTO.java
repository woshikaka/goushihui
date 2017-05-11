package com.sfmy.gsh.web.base;

public class PageDTO {
	private Integer currPageNo;
	private Integer totalPages;
	private Integer pageSize;
	private Long totalElements;
	public Integer getCurrPageNo() {
		return currPageNo;
	}
	public void setCurrPageNo(Integer currPageNo) {
		this.currPageNo = currPageNo;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
}
