package com.sfmy.gsh.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sfmy.gsh.constant.PayType;
@Entity
@Table(name = "order_pay_info")
public class OrderPayInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="create_time",nullable=false)
	private Date createTime;
	
	/**
	 * 支付宝或微信交易号
	 */
	@Column(name="trade_no",length=64,nullable=false)
	private String tradeNo;
	
	/**
	 * 商户订单号(购食汇订单号)
	 */
	@Column(name="out_trade_no",length=64,nullable=false)
	private String outTradeNo;
	
	/**
	 * 交易类型
	 */
	@Enumerated
	@Column(name="pay_type",nullable=false)
	private PayType payType;
	
	/**
	 * 买家支付宝账号
	 */
	@Column(name="buyer_email")
	private String buyerEmail;

	/**
	 * 交易创建时间
	 */
	@Column(name="gmt_create")
	private Date gmtCreate;
	
	/**
	 * 交易付款时间
	 */
	@Column(name="gmt_payment")
	private Date gmtPayment;
	
	/**
	 * 通知时间
	 */
	@Column(name="notify_time")
	private Date notifyTime;
	
	/**
	 * 交易金额
	 */
	@Column(name="total_fee",columnDefinition="double(10,2)")
	private Double totalFee;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtPayment() {
		return gmtPayment;
	}

	public void setGmtPayment(Date gmtPayment) {
		this.gmtPayment = gmtPayment;
	}

	public Date getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
	
}
