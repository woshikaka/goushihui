package com.sfmy.gsh.web.dto;

import java.util.List;

public class OrderRequestDTO {
	private Integer addressId;
	private List<OrderInfo> orderInfos;
	public List<OrderInfo> getOrderInfos() {
		return orderInfos;
	}
	public void setOrderInfos(List<OrderInfo> orderInfos) {
		this.orderInfos = orderInfos;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
}
