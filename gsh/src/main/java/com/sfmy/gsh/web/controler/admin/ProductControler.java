package com.sfmy.gsh.web.controler.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmy.gsh.entity.Product;

@Controller
@RequestMapping(value="/a/product")
public class ProductControler {
	@RequestMapping(value = "/addUI")
	public String addUI(HttpServletRequest request) {
		return "admin/product/add";
	}
	
	@RequestMapping(value = "/addProduct")
	public String addProduct(Product product,HttpServletRequest request) {
		return "redirect:/a/product/addUI";
	}
}
