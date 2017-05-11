package com.sfmy.gsh.web.vo;

public class ProductPageParamVO {
	private Integer currPageNo=1;
	private String name;
	private String isShangJia;
	public String getIsShangJia() {
		return isShangJia;
	}

	public void setIsShangJia(String isShangJia) {
		this.isShangJia = isShangJia;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Integer getCurrPageNo() {
		return currPageNo;
	}

	public void setCurrPageNo(Integer currPageNo) {
		this.currPageNo = currPageNo;
	}
}
