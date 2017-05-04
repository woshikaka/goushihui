package com.sfmy.gsh.web.vo;

public class OrderPageParamVO {
	private Integer currPageNo=1;

	private Integer userId;
	
	public Integer getCurrPageNo() {
		return currPageNo;
	}

	public void setCurrPageNo(Integer currPageNo) {
		this.currPageNo = currPageNo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
