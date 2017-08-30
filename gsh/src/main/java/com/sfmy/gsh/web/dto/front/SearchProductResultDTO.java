package com.sfmy.gsh.web.dto.front;

public class SearchProductResultDTO {
	private Integer id;
	private String name;
	private String image;
	private Integer sellCount;
	private Double marketPrice;
	private Double price;
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
	public Integer getSellCount() {
		return sellCount;
	}
	public void setSellCount(Integer sellCount) {
		this.sellCount = sellCount;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
