package com.sfmy.gsh.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sfmy.gsh.constant.ProductFirstType;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 100, nullable = false)
	private String name;
	private Double price;
	@Column(length = 100)
	private String image;
	@Column(name = "market_price")
	private Double marketPrice;
	/**
	 * 库存数量
	 */
	@Column(name = "stock_count")
	private Integer stockCount;
	/**
	 * 底价(采购进来的价格)
	 **/
	@Column(name = "base_price")
	private Float basePrice;
	private String description;
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 销售量
	 */
	@Column(name = "sell_count")
	private Integer sellCount;
	/**
	 * 是否可见
	 */
	@Column(name = "is_visible")
	private Boolean isVisible;
	@Column(name = "first_type")
	@Enumerated(EnumType.STRING)
	private ProductFirstType firstType;
	@OneToOne
	@JoinColumn(name = "sec_type_id")
	private ProductSecType secType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Float basePrice) {
		this.basePrice = basePrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSellCount() {
		return sellCount;
	}

	public void setSellCount(Integer sellCount) {
		this.sellCount = sellCount;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ProductFirstType getFirstType() {
		return firstType;
	}

	public void setFirstType(ProductFirstType firstType) {
		this.firstType = firstType;
	}

	public ProductSecType getSecType() {
		return secType;
	}

	public void setSecType(ProductSecType secType) {
		this.secType = secType;
	}
}
