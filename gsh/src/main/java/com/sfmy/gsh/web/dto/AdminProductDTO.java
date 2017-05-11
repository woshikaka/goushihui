package com.sfmy.gsh.web.dto;

public class AdminProductDTO {
	private Integer id;
	private String name;
	private String image;
	private String firstTypeName;
	private String secTypeName;
	private String thirdTypeName;
	private Double price;
	private Double marketPrice;
	private Integer stockCount;
	private Integer sellCount;
	private Boolean isShangJia;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getIsShangJia() {
		return isShangJia;
	}
	public void setIsShangJia(Boolean isShangJia) {
		this.isShangJia = isShangJia;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getFirstTypeName() {
		return firstTypeName;
	}
	public void setFirstTypeName(String firstTypeName) {
		this.firstTypeName = firstTypeName;
	}
	public String getSecTypeName() {
		return secTypeName;
	}
	public void setSecTypeName(String secTypeName) {
		this.secTypeName = secTypeName;
	}
	public String getThirdTypeName() {
		return thirdTypeName;
	}
	public void setThirdTypeName(String thirdTypeName) {
		this.thirdTypeName = thirdTypeName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public Integer getStockCount() {
		return stockCount;
	}
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
	public Integer getSellCount() {
		return sellCount;
	}
	public void setSellCount(Integer sellCount) {
		this.sellCount = sellCount;
	}
}
