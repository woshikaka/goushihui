package com.sfmy.gsh.web.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员中心的订单信息
 * @author hyz
 */
public class MemberOrderDTO {
	/**
	 * 订单号
	 */
	private String outTradeNo;
	/**
	 * 商品名称
	 */
	private List<String> names = new ArrayList<String>();
	/**
	 * 商品个数
	 */
	private List<Integer> quantitys = new ArrayList<Integer>();
	/**
	 * 总数
	 */
	private Integer toalQuantity;
	/**
	 * 总价
	 */
	private Double totalPrice;
	/**
	 * 订单状态
	 */
	private String status;
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}
	public List<Integer> getQuantitys() {
		return quantitys;
	}
	public void setQuantitys(List<Integer> quantitys) {
		this.quantitys = quantitys;
	}
	public Integer getToalQuantity() {
		return toalQuantity;
	}
	public void setToalQuantity(Integer toalQuantity) {
		this.toalQuantity = toalQuantity;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
