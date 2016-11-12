package com.sfmy.gsh.service;

import javax.annotation.Resource;

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
	
}
