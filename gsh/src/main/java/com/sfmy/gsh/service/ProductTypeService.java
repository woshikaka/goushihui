package com.sfmy.gsh.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfmy.gsh.dao.ProductTypeDao;
import com.sfmy.gsh.entity.ProductType;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductTypeService {
	@Resource
	private ProductTypeDao productTypeDao;

	public List<ProductType> findAll() {
		return productTypeDao.findAll();
	}
}
