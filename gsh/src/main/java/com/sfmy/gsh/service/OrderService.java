package com.sfmy.gsh.service;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfmy.gsh.dao.OrderDao;
import com.sfmy.gsh.entity.Order;
import com.sfmy.gsh.entity.OrderPayInfo;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService {
	@Resource
	private OrderDao orderDao;
	
	public Order findByOutTradeNo(String outTradeNo) {
		return orderDao.findByOutTradeNo(outTradeNo);
	}
	
	public boolean callBackWithHandleOrder(Order order,OrderPayInfo payInfo) {
		boolean isHandled = false;
		
		if(order == null){
			return isHandled;
		}
		if(payInfo == null){
			return isHandled;
		}
		if(StringUtils.isBlank(payInfo.getTradeNo())){
			return isHandled;
		}
		if (StringUtils.isBlank(payInfo.getOutTradeNo())) {
			return isHandled;
		}
		if (payInfo.getPayType()==null) {
			return isHandled;
		}
		
		//过滤重复的通知结果数据
		if (order.getPayInfo() != null) {
			return true;
		}
		
		order.setPayInfo(payInfo);
		orderDao.save(order);
		return true;
	}
}
