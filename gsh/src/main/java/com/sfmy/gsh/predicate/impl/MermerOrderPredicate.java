package com.sfmy.gsh.predicate.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.sfmy.gsh.constant.OrderStatus;
import com.sfmy.gsh.entity.Order;
import com.sfmy.gsh.entity.User;
import com.sfmy.gsh.web.vo.OrderPageParamVO;

public class MermerOrderPredicate implements Specification<Order>{

	private OrderPageParamVO param;

	public MermerOrderPredicate(OrderPageParamVO param) {
		this.param = param;
	}

	@Override
	public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.notEqual(root.get("status"),OrderStatus.WAIT_PAY));
		if(StringUtils.isNotBlank(param.getUserId()+"")){
			predicates.add(cb.equal(root.get("user"),new User(param.getUserId())));
		}
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}
