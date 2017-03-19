package com.sfmy.gsh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.BooleanUtils;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfmy.gsh.constant.AppConstant;
import com.sfmy.gsh.dao.ProductDao;
import com.sfmy.gsh.dao.ProductSecTypeDao;
import com.sfmy.gsh.dao.ProductTypeDao;
import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.entity.ProductSecType;
import com.sfmy.gsh.entity.ProductThirdType;
import com.sfmy.gsh.entity.ProductType;
import com.sfmy.gsh.predicate.impl.ProductPredicate;
import com.sfmy.gsh.predicate.impl.SearchProductPredicate;
import com.sfmy.gsh.predicate.impl.TopPredicate;
import com.sfmy.gsh.web.dto.SearchProductDTO;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService {
	@Resource
	private ProductDao productDao;
	
	@Resource
	private ProductTypeDao productTypeDao;
	
	@Resource
	private ProductSecTypeDao productSecTypeDao;

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

	public List<Product> batchTop(List<Integer> productIds,Integer productTypeId) {
		productDao.setIsTopByIds(true,productIds);
		return productDao.findByIsTopAndFirstType(true,new ProductType(productTypeId));
	}
	
	public List<Product> batchCancelTop(List<Integer> productIds,Integer productTypeId) {
		productDao.setIsTopByIds(false,productIds);
		return productDao.findByIsTopAndFirstType(true,new ProductType(productTypeId));
	}

	public Integer countTopNum(Integer productTypeId) {
		return productDao.countByIsTopAndFirstType(true,new ProductType(productTypeId));
	}
	
	public List<Product> findTop(Integer productTypeId) {
		return productDao.findByIsTopAndFirstType(true,new ProductType(productTypeId));
	}

	public Product findProductById(Integer id) {
		Product product = productDao.findOne(id);
		return product;
	}

	public void updateProduct(Product product) {
		productDao.save(product);
	}
	
	public List<Product> hot5() {
		List<Product> result = new ArrayList<Product>();
		result = productDao.findTop5ByIsTopOrderByUpdateTimeDescSellCountDesc(true);
		return result;
	}

	public List<Product> findProductByIds(List<Integer> ids) {
		return productDao.findProductByIds(ids);
	}

	public List<Product> findViewLogByIds(List<Integer> viewLogIds) {
		List<Product> viewLog = new ArrayList<Product>();
		for (Integer id : viewLogIds) {
			viewLog.add(productDao.findOne(id));
		}
		return viewLog;
	}

	public Page<Product> search(SearchProductDTO dto) {
		Sort sort = null;
		if(BooleanUtils.isTrue(dto.getSalesHigh2Low())){
			sort = new Sort(new Order(Sort.Direction.DESC,"sellCount"));
		}
		if(BooleanUtils.isTrue(dto.getPriceLow2High())){
			sort = new Sort(new Order(Sort.Direction.ASC,"price"));
		}
		
		
		SearchProductPredicate predicate = new SearchProductPredicate(dto);
		PageRequest pageRequest = new PageRequest(dto.getCurrPageNo()-1,AppConstant.PAGE_SIZE,sort);
		Page<Product> page = productDao.findAll(predicate,pageRequest);
		return page;
	}

	public Product findProductByIdForEager(Integer id) {
		Product product = productDao.findById(id);
//		Hibernate.initialize(product.getFirstType());
//		Hibernate.initialize(product.getSecType());
//		Hibernate.initialize(product.getThirdType());
		
		product.setFirstType(new ProductType(product.getFirstType().getId()));
		product.setSecType(new ProductSecType(product.getSecType().getId()));
		product.setThirdType(new ProductThirdType(product.getThirdType().getId()));
		return product;
	}

}
