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
import com.sfmy.gsh.web.dto.SearchProductDTO;

public class SearchProductPredicate implements Specification<Product>{

	private SearchProductDTO dto;
	
	public SearchProductPredicate(SearchProductDTO dto) {
		this.dto = dto;
	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(StringUtils.isNotBlank(dto.getKeyword())){
			predicates.add(cb.like(root.get("name"),"%"+dto.getKeyword()+"%"));
			predicates.add(cb.like(root.get("description"),"%"+dto.getKeyword()+"%"));
		}
//		if(StringUtils.isNotBlank((String)requestParam.get("isShangJia"))){
//			predicates.add(cb.equal(root.get("isShangJia"),Boolean.valueOf((String)requestParam.get("isShangJia"))));
//		}
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
   	}

}
