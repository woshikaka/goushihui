package com.sfmy.gsh.predicate.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.entity.ProductType;

public class TopPredicate implements Specification<Product>{

	private Map<String,Object> requestParam;
	
	public TopPredicate(Map<String, Object> requestParam) {
		this.requestParam = requestParam;
	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(StringUtils.isNotBlank((String)requestParam.get("name"))){
			predicates.add(cb.like(root.get("name"),"%"+requestParam.get("name")+"%"));
		}
		if(StringUtils.isNotBlank((String)requestParam.get("productTypeId"))){
			
			predicates.add(cb.equal(root.get("firstType"),new ProductType(Integer.valueOf(requestParam.get("productTypeId").toString()))));
		}
		predicates.add(cb.equal(root.get("isShangJia"),true));
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
   	}

}
