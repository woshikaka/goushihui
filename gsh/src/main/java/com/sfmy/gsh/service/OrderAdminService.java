package com.sfmy.gsh.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.sfmy.gsh.constant.AppConstant;
import com.sfmy.gsh.constant.OrderStatus;
import com.sfmy.gsh.dao.OrderDao;
import com.sfmy.gsh.dao.OrderPayInfoDao;
import com.sfmy.gsh.entity.Order;
import com.sfmy.gsh.predicate.impl.OrderPredicate;
import com.sfmy.gsh.web.dto.AdminOrderDTO;
import com.sfmy.gsh.web.dto.AdminOrderPageDTO;
import com.sfmy.gsh.web.vo.AdminOrderPageParamVO;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderAdminService {
	@Resource
	private OrderDao orderDao;
	
	@Resource
	private OrderPayInfoDao orderPayInfoDao;
	
	public AdminOrderPageDTO pageList(AdminOrderPageParamVO requestParam) {
		Page<Order> page = orderDao.findAll(new OrderPredicate(requestParam), new PageRequest(requestParam.getCurrPageNo()-1,AppConstant.PAGE_SIZE));
		
		AdminOrderPageDTO dto = page2Dto(page);
		
		return dto;
	}

	private AdminOrderPageDTO page2Dto(Page<Order> page) {
		AdminOrderPageDTO dto = new AdminOrderPageDTO();
		dto.setCurrPageNo(page.getNumber()+1);
		dto.setTotalPages(page.getTotalPages());
		
		List<AdminOrderDTO> orders = Lists.newArrayList();
		List<Order> bos = page.getContent();
		if (CollectionUtils.isNotEmpty(bos)) {
			for (Order order : bos) {
				AdminOrderDTO orderDTO = new AdminOrderDTO();
				orderDTO.setId(order.getId());
				orderDTO.setContact(order.getContact());
				orderDTO.setMobile(order.getMobile());
				orderDTO.setOutTradeNo(order.getOutTradeNo());
				orderDTO.setReceiveAddress(order.getReceiveAddress());
				orderDTO.setStatus(order.getStatus().getChinese());
				orderDTO.setStatusFlag(order.getStatus().name());
				orderDTO.setSubject(order.getSubject());
				orderDTO.setToalQuantity(order.getToalQuantity());
				orderDTO.setTotalPrice(order.getTotalPrice());
				orderDTO.setUserName(order.getUser().getName());
				orders.add(orderDTO);
			}
		}
		dto.setOrders(orders);
		return dto;
	}

	public void sendGoods(Integer orderId) {
		orderDao.updateStatus(OrderStatus.PAY_SUCCESS_ALREADY_SEND,orderId);
	}
}
