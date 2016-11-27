package com.sfmy.gsh.web.listener;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.sfmy.gsh.entity.ProductSecType;
import com.sfmy.gsh.entity.ProductThirdType;
import com.sfmy.gsh.entity.ProductType;
import com.sfmy.gsh.service.ProductTypeService;
import com.sfmy.gsh.utils.CacheUtils;
/**
 * 启动监听器
 * @author 黄燕针
 */
@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private CacheUtils cacheUtils;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			List<ProductType> productTypes = productTypeService.findAll();
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
}
