package com.sfmy.gsh.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfmy.gsh.dao.ProductTypeDao;
import com.sfmy.gsh.entity.ProductSecType;
import com.sfmy.gsh.entity.ProductThirdType;
import com.sfmy.gsh.entity.ProductType;
import com.sfmy.gsh.utils.CacheUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductTypeService {
	@Resource
	private ProductTypeDao productTypeDao;

	@Resource
	private CacheUtils cacheUtils;
	
	public void initProductType() {
		List<ProductType> productTypes = productTypeDao.findAllByOrderByIndexAsc();
		if(CollectionUtils.isNotEmpty(productTypes)){
			List<ProductSecType> secTypes = new ArrayList<ProductSecType>();
			for (ProductType productType : productTypes) {
				secTypes.addAll(productType.getProductSecTypes());
			}
			cacheUtils.put("secTypes",secTypes);
			
			List<ProductThirdType> thirdTypes = new ArrayList<ProductThirdType>();
			for (ProductSecType productSecType : secTypes) {
				thirdTypes.addAll(productSecType.getThirdTypes());
			}
			cacheUtils.put("thirdTypes",thirdTypes);
		}
		
		cacheUtils.put("productTypes", productTypes);
	}
}
