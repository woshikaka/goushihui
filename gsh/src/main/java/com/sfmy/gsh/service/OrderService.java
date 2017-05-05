package com.sfmy.gsh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfmy.gsh.dao.OrderDao;
import com.sfmy.gsh.entity.Order;
import com.sfmy.gsh.entity.OrderItem;
import com.sfmy.gsh.entity.OrderPayInfo;
import com.sfmy.gsh.predicate.impl.MermerOrderPredicate;
import com.sfmy.gsh.web.dto.MemberOrderDTO;
import com.sfmy.gsh.web.dto.MemberOrderPageDTO;
import com.sfmy.gsh.web.vo.OrderPageParamVO;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService {
	@Resource
	private OrderDao orderDao;
	
	public Order findByOutTradeNo(String outTradeNo) {
		return orderDao.findByOutTradeNo(outTradeNo);
	}
	
	public Order addOrder(Order order) {
		return orderDao.save(order);
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
}
