package com.sfmy.gsh.web.controler.front;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmy.gsh.constant.AppConstant;
import com.sfmy.gsh.entity.ProductSecType;
import com.sfmy.gsh.entity.ProductThirdType;
import com.sfmy.gsh.entity.ProductType;
import com.sfmy.gsh.service.CarService;
import com.sfmy.gsh.service.ProductService;
import com.sfmy.gsh.utils.CacheUtils;
import com.sfmy.gsh.utils.MySecurityUtils;
/**
 * 产品详情页
 */
@Controller
public class SearchController {
	@Resource
	private ProductService productService;
	
	@Resource
	private CarService carService;
	
	@Resource
	private CacheUtils cacheUtils;
	
	@RequestMapping("/search")
	public String homePage(HttpServletRequest request,Model model,HttpServletResponse response) {
		// 导购类目
		List<ProductType> productTypes = cacheUtils.get(AppConstant.CACHE_PRODUCTTYPES_KEY, List.class);
		List<ProductSecType> secTypes = cacheUtils.get("secTypes", List.class);
		List<ProductThirdType> thirdTypes = cacheUtils.get("thirdTypes", List.class);
		
		// 购车车数字
		Integer carCnt = carService.countProductCnt(MySecurityUtils.getCurrUserId());
		
		model.addAttribute("carCnt",carCnt);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("secTypes", secTypes);
		model.addAttribute("thirdTypes", thirdTypes);
		
		return "front/searchList";
	}
}
