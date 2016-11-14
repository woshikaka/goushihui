package com.sfmy.gsh.service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfmy.gsh.dao.ProductDao;
import com.sfmy.gsh.entity.Product;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService {
	@Resource
	private ProductDao productDao;

	public void addProduct(Product product) {
		productDao.save(product);
	}

	public Page<Product> pageList(Integer pageNumber) {
		Page<Product> page = productDao.findAll(new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return null;
			}

		}, new PageRequest(pageNumber-1,50));
		return page;
	}

}
