package com.sfmy.gsh.web.controler.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.sfmy.gsh.entity.ProductSecType;
import com.sfmy.gsh.entity.ProductThirdType;
import com.sfmy.gsh.entity.ProductType;
import com.sfmy.gsh.service.ProductService;
import com.sfmy.gsh.utils.CacheUtils;
import com.sfmy.gsh.web.base.JsonResult;
import com.sfmy.gsh.web.controler.BaseSpringController;

@Controller
@RequestMapping(value = "/a/productType")
public class ProductTypeControler extends BaseSpringController{
	@Resource
	private ProductService productService;

	@Resource
	private CacheUtils cacheUtils;
	
	public JsonResult<Object> name() {
		List<ProductType> productTypes = cacheUtils.get("productTypes", List.class);
		
		if(CollectionUtils.isNotEmpty(productTypes)){
			List<ProductSecType> secTypes = Lists.newArrayList();
			for (ProductType productType : productTypes) {
				secTypes.addAll(productType.getProductSecTypes());
			}
			
			List<ProductThirdType> thirdTypes = Lists.newArrayList();
			for (ProductSecType productSecType : secTypes) {
				thirdTypes.addAll(productSecType.getThirdTypes());
			}
		}
		return success();
	}
	
	@ResponseBody
	@RequestMapping(value = "/findSecTypeByFirType",produces="application/json;charset=utf-8")
	public String findSecTypeByFirType(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();

		String firTypeId = request.getParameter("firTypeId");

		List<ProductType> productTypes = cacheUtils.get("productTypes", List.class);
		for (ProductType productType : productTypes) {
			if (StringUtils.equals(productType.getId() + "", firTypeId)) {
				List<ProductSecType> productSecTypes = productType.getProductSecTypes();
				sb.append("<option value=''>二级分类</option>");
				for (ProductSecType productSecType : productSecTypes) {
					sb.append("<option value='" + productSecType.getId() + "'>" + productSecType.getName() + "</option>");
				}
			}
		}
		return sb.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/findThirdTypeBySecType",produces="application/json;charset=utf-8")
	@SuppressWarnings("unchecked")
	public String findThirdTypeBySecType(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();

		String secTypeId = request.getParameter("secTypeId");
		
		List<ProductType> productTypes = cacheUtils.get("productTypes", List.class);
		firFor:for (ProductType productType : productTypes) {
			if(sb.length()>0){
				break firFor;
			}
			List<ProductSecType> productSecTypes = productType.getProductSecTypes();
			for (ProductSecType productSecType : productSecTypes) {
				if(sb.length()>0){
					break firFor;
				}
				if (StringUtils.equals(productSecType.getId() + "", secTypeId)) {
					List<ProductThirdType> thirdTypes = productSecType.getThirdTypes();
					sb.append("<option value=''>三级分类</option>");
					for (ProductThirdType productThirdType : thirdTypes) {
						sb.append("<option value='" + productThirdType.getId() + "'>" + productThirdType.getName() + "</option>");
					}
				}
			}
		}
		return sb.toString();
	}
}
