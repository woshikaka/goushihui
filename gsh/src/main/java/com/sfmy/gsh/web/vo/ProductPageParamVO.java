package com.sfmy.gsh.web.vo;

public class ProductPageParamVO {
	private Integer currPageNo=1;
	private String name;
	private String isShangJia;
	private Integer firstTypeId;
	private Integer secTypeId;
	private Integer thirdTypeId;
	public Integer getFirstTypeId() {
		return firstTypeId;
	}

	public void setFirstTypeId(Integer firstTypeId) {
		this.firstTypeId = firstTypeId;
	}

	public Integer getSecTypeId() {
		return secTypeId;
	}

	public void setSecTypeId(Integer secTypeId) {
		this.secTypeId = secTypeId;
	}

	public Integer getThirdTypeId() {
		return thirdTypeId;
	}

	public void setThirdTypeId(Integer thirdTypeId) {
		this.thirdTypeId = thirdTypeId;
	}

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
