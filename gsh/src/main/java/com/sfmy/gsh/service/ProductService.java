package com.sfmy.gsh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.sfmy.gsh.constant.AppConstant;
import com.sfmy.gsh.dao.ProductDao;
import com.sfmy.gsh.dao.ProductSecTypeDao;
import com.sfmy.gsh.dao.ProductTypeDao;
import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.entity.ProductDesc;
import com.sfmy.gsh.entity.ProductSecType;
import com.sfmy.gsh.entity.ProductThirdType;
import com.sfmy.gsh.entity.ProductType;
import com.sfmy.gsh.predicate.impl.ProductPredicate;
import com.sfmy.gsh.predicate.impl.SearchProductPredicate;
import com.sfmy.gsh.predicate.impl.TopPredicate;
import com.sfmy.gsh.web.dto.AdminProductDTO;
import com.sfmy.gsh.web.dto.AdminProductPageDTO;
import com.sfmy.gsh.web.dto.SearchProductDTO;
import com.sfmy.gsh.web.vo.ProductPageParamVO;

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

	public AdminProductPageDTO pageList(ProductPageParamVO pageParamVO) {
		AdminProductPageDTO dto = new AdminProductPageDTO();
		List<AdminProductDTO> products = Lists.newArrayList();
		
		Page<Product> page = productDao.findAll(new ProductPredicate(pageParamVO), new PageRequest(pageParamVO.getCurrPageNo()-1,AppConstant.PAGE_SIZE));
		List<Product> bos = page.getContent();
		if (CollectionUtils.isNotEmpty(bos)) {
			for (Product bo : bos) {
				AdminProductDTO itemDTO = new AdminProductDTO();
				itemDTO.setId(bo.getId());
				itemDTO.setFirstTypeName(bo.getFirstType().getName());
				itemDTO.setImage("/upload"+bo.getImage());
				itemDTO.setMarketPrice(bo.getMarketPrice());
				itemDTO.setName(bo.getName());
				itemDTO.setPrice(bo.getPrice());
				itemDTO.setSecTypeName(bo.getSecType().getName());
				itemDTO.setSellCount(bo.getSellCount());
				itemDTO.setStockCount(bo.getStockCount());
				itemDTO.setThirdTypeName(bo.getThirdType().getName());
				itemDTO.setIsShangJia(bo.getIsShangJia());
				products.add(itemDTO);
			}
		}
		
		dto.setCurrPageNo(pageParamVO.getCurrPageNo());
		dto.setPageSize(AppConstant.PAGE_SIZE);
		dto.setTotalPages(page.getTotalPages());
		dto.setTotalElements(page.getTotalElements());
		dto.setProducts(products);
		return dto;
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
	
	public void updateProduct(AdminProductDTO dto) {
		Product product = productDao.findOne(dto.getId());

		//商品描述
		String htmlDesc = dto.getDescription();
		if (StringUtils.isNotBlank(dto.getDescription())) {
			if(product.getProductDesc() == null){
				ProductDesc productDesc = new ProductDesc();
				productDesc.setHtmlDesc(htmlDesc);
				product.setProductDesc(productDesc);
			}else{
				ProductDesc productDesc = product.getProductDesc();
				productDesc.setHtmlDesc(htmlDesc);
				product.setProductDesc(productDesc);
			}
		}
		
		product.setFirstType(new ProductType(dto.getFirstTypeId()));
		product.setIsShangJia(dto.getIsShangJia());
		product.setMarketPrice(dto.getMarketPrice());
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setSecType(new ProductSecType(dto.getSecTypeId()));
		product.setSellCount(dto.getSellCount());
		product.setStockCount(dto.getStockCount());
		product.setThirdType(new ProductThirdType(dto.getThirdTypeId()));
		
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

	public AdminProductDTO findProductByIdForEager(Integer id) {
		Product bo = productDao.findById(id);
		
		AdminProductDTO dto = new AdminProductDTO();
		if (Objects.nonNull(bo)) {
			dto.setId(bo.getId());
			
			ProductDesc productDesc = bo.getProductDesc();
			if (Objects.nonNull(productDesc)) {
				dto.setDescription(productDesc.getHtmlDesc());
				dto.setDescId(productDesc.getId());
			}
			
			ProductType firstType = bo.getFirstType();
			dto.setFirstTypeId(firstType.getId());
			dto.setFirstTypeName(firstType.getName());
			
			ProductSecType secType = bo.getSecType();
			dto.setSecTypeId(secType.getId());
			dto.setSecTypeName(secType.getName());
			
			ProductThirdType thirdType = bo.getThirdType();
			dto.setThirdTypeId(thirdType.getId());
			dto.setThirdTypeName(thirdType.getName());
			
			dto.setIsShangJia(bo.getIsShangJia());
			dto.setMarketPrice(bo.getMarketPrice());
			dto.setName(bo.getName());
			dto.setPrice(bo.getPrice());
			
			dto.setSellCount(bo.getSellCount());
			dto.setStockCount(bo.getStockCount());
			
		}
		return dto;
	}

}
