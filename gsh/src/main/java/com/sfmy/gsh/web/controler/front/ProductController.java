package com.sfmy.gsh.web.controler.front;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.service.ProductService;
import com.sfmy.gsh.utils.CacheUtils;
/**
 * 产品详情页
 */
@Controller
public class ProductController {
	@Resource
	private ProductService productService;
	
	@Resource
	private CacheUtils cacheUtils;
	
	@RequestMapping("/product/detail/{id}")
	public String homePage(@PathVariable(value = "id")Integer id,HttpServletRequest request,Model model) {
		Product product = productService.findProductById(id);
		model.addAttribute("product",product);
		return "front/productDetail";
	}
}
