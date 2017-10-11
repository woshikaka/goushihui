package com.sfmy.gsh.web.dto.front;
public class ProductDetailDTO {
	private Integer id;
	private String name;
	private Double marketPrice;
	private Double price;
	private Double economy;
	private Integer stockCount;
	private Integer sellCount;
	private String htmlDesc;
	private String image;
	private Boolean isShangJia;
	private Integer firstTypeId;
	private String firstTypeName;
	private Integer secTypeId;
	private String secTypeName;
	private Integer thirdTypeId;
	private String thirdTypeName;
	public Integer getFirstTypeId() {
		return firstTypeId;
	}
	public void setFirstTypeId(Integer firstTypeId) {
		this.firstTypeId = firstTypeId;
	}
	public String getFirstTypeName() {
		return firstTypeName;
	}
	public void setFirstTypeName(String firstTypeName) {
		this.firstTypeName = firstTypeName;
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
	public Boolean getIsShangJia() {
		return isShangJia;
	}
	public void setIsShangJia(Boolean isShangJia) {
		this.isShangJia = isShangJia;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getEconomy() {
		return economy;
	}
	public void setEconomy(Double economy) {
		this.economy = economy;
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
	public String getHtmlDesc() {
		return htmlDesc;
	}
	public void setHtmlDesc(String htmlDesc) {
		this.htmlDesc = htmlDesc;
	}
}
