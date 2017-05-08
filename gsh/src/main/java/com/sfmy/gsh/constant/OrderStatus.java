package com.sfmy.gsh.constant;

public enum OrderStatus {
	/**
	 * 待付款
	 */
	WAIT_PAY("待付款"),
	
	/**
	 * 付款成功等待发货
	 */
	PAY_SUCCESS_WAIT_SEND("付款成功等待发货"),
	
	/**
	 * 付款成功已经发货
	 */
	PAY_SUCCESS_ALREADY_SEND("已经发货"),
	
	/**
	 * 交易结束
	 */
	END("交易结束");
	
	private String chinese;
	private OrderStatus(String chinese) {
		this.chinese = chinese;
	}
	public String getChinese() {
		return chinese;
	}
}
