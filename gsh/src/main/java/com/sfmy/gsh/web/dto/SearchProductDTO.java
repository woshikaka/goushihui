package com.sfmy.gsh.web.dto;

public class SearchProductDTO {
	private String keyword;
	private Integer currPageNo=1;
	private Integer productTypeId;
	private String productTypeName;
	private Integer secTypeId;
	private String secTypeName;
	private Integer thirdTypeId;
	private String thirdTypeName;
	private Boolean salesHigh2Low;
	private Boolean priceLow2High;
	private Boolean defaultSort;
	public Integer getThirdTypeId() {
		return thirdTypeId;
	}

	public void setThirdTypeId(Integer thirdTypeId) {
		this.thirdTypeId = thirdTypeId;
	}

	public String getThirdTypeName() {
		return thirdTypeName;
	}

	public void setThirdTypeName(String thirdTypeName) {
		this.thirdTypeName = thirdTypeName;
	}

	public Integer getSecTypeId() {
		return secTypeId;
	}

	public void setSecTypeId(Integer secTypeId) {
		this.secTypeId = secTypeId;
	}

	public String getSecTypeName() {
		return secTypeName;
	}

	public void setSecTypeName(String secTypeName) {
		this.secTypeName = secTypeName;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getCurrPageNo() {
		return currPageNo;
	}

	public void setCurrPageNo(Integer currPageNo) {
		this.currPageNo = currPageNo;
	}

	public Boolean getSalesHigh2Low() {
		return salesHigh2Low;
	}

	public void setSalesHigh2Low(Boolean salesHigh2Low) {
		this.salesHigh2Low = salesHigh2Low;
	}

	public Boolean getPriceLow2High() {
		return priceLow2High;
	}

	public void setPriceLow2High(Boolean priceLow2High) {
		this.priceLow2High = priceLow2High;
	}

	public Boolean getDefaultSort() {
		return defaultSort;
	}

	public void setDefaultSort(Boolean defaultSort) {
		this.defaultSort = defaultSort;
	}
}
