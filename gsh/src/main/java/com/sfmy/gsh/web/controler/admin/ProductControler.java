package com.sfmy.gsh.web.controler.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.transform.SimpleTypeUnmarshallers.StringUnmarshaller;
import com.sfmy.gsh.entity.Product;

@Controller
@RequestMapping(value="/a/product")
public class ProductControler {
	@RequestMapping(value = "/listUI")
	public String listUI(HttpServletRequest request) {
		return "admin/product/list";
	}
	
	@RequestMapping(value = "/addUI")
	public String addUI(HttpServletRequest request,Product product,RedirectAttributes ra,Boolean isForm) {
		if (isForm!=null && isForm) {
			String name = product.getName();
			if(StringUtils.isBlank(name)){
				request.setAttribute("isDangerShow",true);
				request.setAttribute("dangerMessage","产品名称不能为空！");
				request.setAttribute("product",product);
				return "admin/product/add";
			}
			if(name.length()>100){
				request.setAttribute("isDangerShow",true);
				request.setAttribute("dangerMessage","产品名称长度不能超过100个字符");
				request.setAttribute("product",product);
				return "admin/product/add";
			}
			
			
			return "redirect:/a/product/addUI";
		} else {
			return "admin/product/add";
		}
	}
	
	@Deprecated
	@RequestMapping(value = "/addProduct")
	public String addProduct(Product product,HttpServletRequest request,RedirectAttributes ra) {
		String name = product.getName();
		if(StringUtils.isBlank(name)){
			ra.addFlashAttribute("isDangerShow",true);
			ra.addFlashAttribute("dangerMessage","产品名称不能为空！");
//			ra.addAttribute("product",product);
//			return "redirect:/a/product/addUI";
			request.setAttribute("product",product);
//			return "forward:/a/product/addUI";
			return null;
		}
		
		return "redirect:/a/product/addUI";
	}
	
	
}
