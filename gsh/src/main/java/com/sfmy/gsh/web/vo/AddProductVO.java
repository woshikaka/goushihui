package com.sfmy.gsh.web.vo;

import java.util.List;

public class AddProductVO {
	private String priceStr;
	private String marketPriceStr;
	private String name;
	private Integer firstTypeId;
	private Integer secTypeId;
	private Integer thirdTypeId;
	private String stockCount;
	private Boolean isShangJia;
	private String description;
	private List<String> activityType;
	public List<String> getActivityType() {
		return activityType;
	}
	public void setActivityType(List<String> activityType) {
		this.activityType = activityType;
	}
	public String getStockCount() {
		return stockCount;
	}
	public void setStockCount(String stockCount) {
		this.stockCount = stockCount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPriceStr() {
		return priceStr;
	}
	public void setPriceStr(String priceStr) {
		this.priceStr = priceStr;
	}
	public String getMarketPriceStr() {
		return marketPriceStr;
	}
	public void setMarketPriceStr(String marketPriceStr) {
		this.marketPriceStr = marketPriceStr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public Boolean getIsShangJia() {
		return isShangJia;
	}
	public void setIsShangJia(Boolean isShangJia) {
		this.isShangJia = isShangJia;
	}
}
