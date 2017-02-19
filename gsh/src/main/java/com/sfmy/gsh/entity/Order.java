package com.sfmy.gsh.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sfmy.gsh.constant.OrderStatus;

@Entity
@Table(name = "`order`")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="order")
	private List<OrderItem> items;
	
	@Column(name="create_time",nullable=false)
	private Date createTime=new Date();
	
	@OneToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	/**
	 * 支付信息
	 */
	@OneToOne
	@JoinColumn(name="pay_info_id",nullable=false)
	private OrderPayInfo payInfo;
	
	/**
	 * 收货地址
	 */
	@OneToOne
	@JoinColumn(name="address_id",nullable=false)
	private Address address;
	
	/**
	 * 订单状态
	 */
	@Enumerated
	@Column(nullable=false)
	private OrderStatus status;
	
	/**
	 * 合计总价
	 */
	@Column(name="total_price",nullable=false,columnDefinition="double(10,2)")
	private Double totalPrice;
	
	/**
	 * 总数
	 */
	@Column(name="toal_quantity",nullable=false)
	private Integer toalQuantity;
	
	/**
	 * 商户订单号
	 */
	@Column(name="out_trade_no",length=64,nullable=false)
	private String outTradeNo;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getToalQuantity() {
		return toalQuantity;
	}

	public void setToalQuantity(Integer toalQuantity) {
		this.toalQuantity = toalQuantity;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public OrderPayInfo getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(OrderPayInfo payInfo) {
		this.payInfo = payInfo;
	}
}