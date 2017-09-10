package com.sfmy.gsh.predicate.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.entity.ProductSecType;
import com.sfmy.gsh.entity.ProductThirdType;
import com.sfmy.gsh.entity.ProductType;
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
//			predicates.add(cb.like(root.get("name"),"%"+dto.getKeyword()+"%"));
//			predicates.add(cb.like(root.get("description"),"%"+dto.getKeyword()+"%"));
//			predicates.add(cb.or(cb.like(root.get("name"),"%"+dto.getKeyword()+"%"),cb.like(root.get("productDesc"),"%"+dto.getKeyword()+"%")));
			predicates.add(cb.or(cb.like(root.get("name"),"%"+dto.getKeyword()+"%")));
		}
		if(Objects.nonNull(dto.getActivityType())){
			if(Objects.equals(dto.getActivityType(), 1)){
				predicates.add(cb.like(root.get("activityType"),"%"+1+"%"));
			}else if(Objects.equals(dto.getActivityType(), 2)){
				predicates.add(cb.like(root.get("activityType"),"%"+2+"%"));
			}else if(Objects.equals(dto.getActivityType(), 3)){
				predicates.add(cb.like(root.get("activityType"),"%"+3+"%"));
			}
		}
		if(dto.getThirdTypeId()!=null){
			predicates.add(cb.equal(root.get("thirdType"),new ProductThirdType(dto.getThirdTypeId())));
		}
		if(dto.getSecTypeId()!=null){
			predicates.add(cb.equal(root.get("secType"),new ProductSecType(dto.getSecTypeId())));
		}
		if(dto.getProductTypeId()!=null){
			predicates.add(cb.equal(root.get("firstType"),new ProductType(dto.getProductTypeId())));
		}
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
   	}

}
