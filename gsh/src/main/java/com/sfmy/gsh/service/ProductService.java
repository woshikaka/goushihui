package com.sfmy.gsh.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfmy.gsh.constant.AppConstant;
import com.sfmy.gsh.dao.ProductDao;
import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.entity.ProductType;
import com.sfmy.gsh.predicate.impl.ProductPredicate;
import com.sfmy.gsh.predicate.impl.TopPredicate;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService {
	@Resource
	private ProductDao productDao;

	public void addProduct(Product product) {
		productDao.save(product);
	}

	public Page<Product> pageList(Map<String,Object> requestParam) {
		Integer pageNumber = (Integer) requestParam.get("pageNumber");
		Page<Product> page = productDao.findAll(new ProductPredicate(requestParam), new PageRequest(pageNumber-1,AppConstant.PAGE_SIZE));
		return page;
	}

	public void batchShangJia(List<Integer> productIds) {
		for (Integer id : productIds) {
			productDao.setIsShangJiaById(true,id);
		}
	}

	public void batchXiaJia(List<Integer> productIds) {
		for (Integer id : productIds) {
			productDao.setIsShangJiaById(false,id);
		}		
	}

	public Page<Product> topList(Map<String, Object> requestParam) {
		Integer pageNumber = (Integer) requestParam.get("pageNumber");
		Sort sort = new Sort(new Order[]{new Order(Sort.Direction.DESC,"isTop"),new Order(Sort.Direction.DESC,"updateTime")});
		Page<Product> page = productDao.findAll(new TopPredicate(requestParam), new PageRequest(pageNumber-1,AppConstant.PAGE_SIZE,sort));
		return page;
	}

	public void batchTop(List<Integer> productIds) {
		productDao.setIsTopByIds(true,productIds);
	}
	
	public void batchCancelTop(List<Integer> productIds) {
		productDao.setIsTopByIds(false,productIds);
	}

	public Integer countTopNum(Integer productTypeId) {
		return productDao.countByIsTopAndFirstType(true,new ProductType(productTypeId));
	}

}
