package com.sfmy.gsh.web.controler.common;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sfmy.gsh.entity.ProductType;
import com.sfmy.gsh.service.ProductService;
import com.sfmy.gsh.utils.CacheUtils;
import com.sfmy.gsh.web.base.JsonResult;
import com.sfmy.gsh.web.controler.BaseSpringController;

@Controller
@RequestMapping(value = "/common")
public class CommonControler extends BaseSpringController{
	@Resource
	private ProductService productService;

	@Resource
	private CacheUtils cacheUtils;
	
	@ResponseBody
	@RequestMapping("/getAllType")
	public JsonResult<List<ProductType>> getAllType() {
		List<ProductType> productTypes = cacheUtils.get("productTypes", List.class);
		return success(productTypes);
	}
}
