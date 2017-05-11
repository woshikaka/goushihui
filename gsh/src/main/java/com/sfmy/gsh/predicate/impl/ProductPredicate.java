package com.sfmy.gsh.predicate.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.web.vo.ProductPageParamVO;

public class ProductPredicate implements Specification<Product>{

	private ProductPageParamVO pageParamVO;
	
	public ProductPredicate(ProductPageParamVO pageParamVO) {
		super();
		this.pageParamVO = pageParamVO;
	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		String name = pageParamVO.getName();
		if(StringUtils.isNotBlank(name)){
			predicates.add(cb.like(root.get("name"),"%"+name+"%"));
		}
		
		String isShangJia = pageParamVO.getIsShangJia();
		if(StringUtils.isNotBlank(isShangJia)){
			predicates.add(cb.equal(root.get("isShangJia"),Boolean.valueOf(isShangJia)));
		}
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
   	}

}
