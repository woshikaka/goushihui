package com.sfmy.gsh.predicate.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.sfmy.gsh.constant.OrderStatus;
import com.sfmy.gsh.entity.Order;
import com.sfmy.gsh.web.vo.AdminOrderPageParamVO;

public class OrderPredicate implements Specification<Order>{

	private AdminOrderPageParamVO requestParam;
	
	public OrderPredicate(AdminOrderPageParamVO requestParam) {
		super();
		this.requestParam = requestParam;
	}

	@Override
	public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(StringUtils.isNotBlank(requestParam.getOutTradeNo())){
			predicates.add(cb.like(root.get("outTradeNo"),"%"+requestParam.getOutTradeNo()+"%"));
		}
		String status = requestParam.getStatus();
		if(StringUtils.isNotBlank(status)){
			OrderStatus orderStatus = EnumUtils.getEnum(OrderStatus.class, status);
			predicates.add(cb.equal(root.get("status"),orderStatus));
		}
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
   	}

}
