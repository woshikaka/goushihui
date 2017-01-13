package com.sfmy.gsh.web.controler.front;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.service.CarService;
import com.sfmy.gsh.service.ProductService;
import com.sfmy.gsh.utils.CacheUtils;
import com.sfmy.gsh.utils.CookieUtils;
import com.sfmy.gsh.utils.MyArith;
import com.sfmy.gsh.utils.MySecurityUtils;
/**
 * 产品详情页
 */
@Controller
public class ProductDetailController {
	@Resource
	private ProductService productService;
	
	@Resource
	private CarService carService;
	
	@Resource
	private CacheUtils cacheUtils;
	
	@RequestMapping("/product/detail/{id}")
	public String homePage(@PathVariable(value = "id")Integer id,HttpServletRequest request,Model model,HttpServletResponse response) {
		Product product = productService.findProductById(id);
		
		Integer carCnt = carService.countProductCnt(MySecurityUtils.getCurrUserId());
		
		//看了又看的产品
		List<Integer> viewLogIds = CookieUtils.viewLog(request, response,id);
		List<Product> viewLog = productService.findViewLogByIds(viewLogIds);
		
		model.addAttribute("product",product);
		model.addAttribute("economy",MyArith.sub(product.getMarketPrice(),product.getPrice()));
		model.addAttribute("viewLog",viewLog);
		model.addAttribute("carCnt",carCnt);
		return "front/productDetail";
	}
}
