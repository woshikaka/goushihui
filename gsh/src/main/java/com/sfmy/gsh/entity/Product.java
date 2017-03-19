package com.sfmy.gsh.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable{
	private static final long serialVersionUID = 2675455994965218527L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(nullable = false)
	private Double price;

	@Column(length = 100, nullable = false)
	private String image;

	@Column(name = "market_price",nullable=false)
	private Double marketPrice;
	/**
	 * 库存数量
	 */
	@Column(name = "stock_count")
	private Integer stockCount=0;
	/**
	 * 底价(采购进来的价格)
	 **/
	@Column(name = "base_price")
	private Float basePrice;

	/**
	 * 商品描述
	 */
	@Lob
	private String description;

	@Column(name = "create_time")
	private Date createTime = new Date();
	
	@Column(name = "update_time")
	private Date updateTime = new Date();
	/**
	 * 销售量
	 */
	@Column(name = "sell_count",nullable=false)
	private Integer sellCount=0;
	/**
	 * 是否上架
	 */
	@Column(name = "is_shang_jia",nullable=false)
	private Boolean isShangJia;
	
	/**
	 * 是否top
	 */
	@Column(name = "is_top",nullable=false)
	private Boolean isTop=false;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "first_type_id",nullable=false)
	private ProductType firstType;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "sec_type_id",nullable=false)
	private ProductSecType secType;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "third_type_id",nullable=false)
	private ProductThirdType thirdType;

	public Product() {
		super();
	}

	public Product(Integer id) {
		super();
		this.id = id;
	}

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
	public Boolean getIsShangJia() {
		return isShangJia;
	}

	public void setIsShangJia(Boolean isShangJia) {
		this.isShangJia = isShangJia;
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

	public ProductSecType getSecType() {
		return secType;
	}

	public void setSecType(ProductSecType secType) {
		this.secType = secType;
	}

	public ProductType getFirstType() {
		return firstType;
	}

	public void setFirstType(ProductType firstType) {
		this.firstType = firstType;
	}

	public ProductThirdType getThirdType() {
		return thirdType;
	}

	public void setThirdType(ProductThirdType thirdType) {
		this.thirdType = thirdType;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getIsTop() {
		return isTop;
	}

	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
