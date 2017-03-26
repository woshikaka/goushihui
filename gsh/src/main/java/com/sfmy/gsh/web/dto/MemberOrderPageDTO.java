package com.sfmy.gsh.web.dto;

import java.util.List;

public class MemberOrderPageDTO {
	private Integer currPageNo;
	private Integer totalPages;
	private List<MemberOrderDTO> items;
	public Integer getCurrPageNo() {
		return currPageNo;
	}
	public void setCurrPageNo(Integer currPageNo) {
		this.currPageNo = currPageNo;
	}
	public List<MemberOrderDTO> getItems() {
		return items;
	}
	public void setItems(List<MemberOrderDTO> items) {
		this.items = items;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
}
