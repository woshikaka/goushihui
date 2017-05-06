package com.sfmy.gsh.web.dto;

import java.util.List;

public class AdminOrderPageDTO {
	private Integer currPageNo;
	private Integer totalPages;
	private List<AdminOrderDTO> orders;
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
	public List<AdminOrderDTO> getOrders() {
		return orders;
	}
	public void setOrders(List<AdminOrderDTO> orders) {
		this.orders = orders;
	}
}
