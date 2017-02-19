package com.sfmy.gsh.constant;

public enum OrderStatus {
	/**
	 * 待付款
	 */
	WAIT_PAY,
	
	/**
	 * 付款成功等待发货
	 */
	PAY_SUCCESS_WAIT_SEND,
	
	/**
	 * 付款成功已经发货
	 */
	PAY_SUCCESS_ALREADY_SEND,
	
	/**
	 * 交易结束
	 */
	END
}
