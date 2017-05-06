package com.sfmy.gsh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfmy.gsh.constant.AppConstant;
import com.sfmy.gsh.constant.OrderStatus;
import com.sfmy.gsh.dao.OrderDao;
import com.sfmy.gsh.dao.OrderPayInfoDao;
import com.sfmy.gsh.entity.Order;
import com.sfmy.gsh.entity.OrderItem;
import com.sfmy.gsh.entity.OrderPayInfo;
import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.entity.User;
import com.sfmy.gsh.predicate.impl.MermerOrderPredicate;
import com.sfmy.gsh.predicate.impl.OrderPredicate;
import com.sfmy.gsh.predicate.impl.ProductPredicate;
import com.sfmy.gsh.web.dto.MemberOrderDTO;
import com.sfmy.gsh.web.dto.MemberOrderPageDTO;
import com.sfmy.gsh.web.vo.OrderPageParamVO;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService {
	@Resource
	private OrderDao orderDao;
	
	@Resource
	private OrderPayInfoDao orderPayInfoDao;
	
	public Order findByOutTradeNo(String outTradeNo) {
		return orderDao.findByOutTradeNo(outTradeNo);
	}
	
	public Order addOrder(Order order) {
		return orderDao.save(order);
	}
	
	public boolean handlePayInfo(Order order,OrderPayInfo payInfo) {
		if (order.getPayInfo() == null) {
			orderPayInfoDao.save(payInfo);
			
			order.setPayInfo(payInfo);
			order.setStatus(OrderStatus.PAY_SUCCESS_WAIT_SEND);
			orderDao.save(order);
		}else{
			order.setStatus(OrderStatus.PAY_SUCCESS_WAIT_SEND);
			orderDao.save(order);
		}
		
		return true;
	}

	public MemberOrderPageDTO listPage(Integer currUserId, OrderPageParamVO param) {
		MemberOrderPageDTO pageDTO = new MemberOrderPageDTO();
		List<MemberOrderDTO> items = new ArrayList<MemberOrderDTO>();
		pageDTO.setItems(items);
		
		param.setUserId(currUserId);
		MermerOrderPredicate predicate = new MermerOrderPredicate(param);
		
		Sort sort = new Sort(new org.springframework.data.domain.Sort.Order(Sort.Direction.DESC,"createTime"));
		PageRequest pageRequest = new PageRequest(param.getCurrPageNo()-1,5,sort);
		Page<Order> page = orderDao.findAll(predicate,pageRequest);
		
		//bo转dto
		if (Objects.nonNull(page)) {
			pageDTO.setTotalPages(page.getTotalPages());
			pageDTO.setCurrPageNo(page.getNumber()+1);
			
			List<Order> orderBos = page.getContent();
			if (CollectionUtils.isNotEmpty(orderBos)) {
				for (Order order : orderBos) {
					MemberOrderDTO dto = new MemberOrderDTO();
					dto.setToalQuantity(order.getToalQuantity());
					dto.setTotalPrice(order.getTotalPrice());
					dto.setOutTradeNo(order.getOutTradeNo());
					dto.setStatus(order.getStatus().getChinese());
					setNamesAndQuantitys(dto,order);
					items.add(dto);
				}
			}
		}
		
		return pageDTO;
	}
	
	private void setNamesAndQuantitys(MemberOrderDTO dto,Order bo){
		List<String> names = new ArrayList<String>();
		List<Integer> quantitys = new ArrayList<Integer>();
		
		List<OrderItem> items = bo.getItems();
		if (CollectionUtils.isNotEmpty(items)) {
			for (OrderItem orderItem : items) {
				names.add(orderItem.getProduct().getName());
				quantitys.add(orderItem.getQuantity());
			}
		}
		
		dto.setNames(names);
		dto.setQuantitys(quantitys);
	}

	public Order findByOutTradeNoAndUserId(Integer userId, String outTradeNo) {
		Order order = orderDao.findByOutTradeNoAndUser(outTradeNo,new User(userId));
		return order;
	}
}
