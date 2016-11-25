package com.sfmy.gsh.web.controler.front;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmy.gsh.constant.AppConstant;
import com.sfmy.gsh.entity.Ad;
import com.sfmy.gsh.entity.ProductType;
import com.sfmy.gsh.utils.CacheUtils;
/**
 * 首页
 * @author hyz
 */
@Controller
public class HomePageController {
	@Resource
	private CacheUtils cacheUtils;
	
	@RequestMapping("/homePage")
	@SuppressWarnings("unchecked")
	public String homePage(HttpServletRequest request,Model model) {
		//轮询广告
		List<Ad> showAds = cacheUtils.get(AppConstant.CACHE_SHOWADS_KEY,List.class);
		
		//导购类目
		List<ProductType> productTypes = cacheUtils.get(AppConstant.CACHE_PRODUCTTYPES_KEY,List.class);
		
		
		
		model.addAttribute("showAds",showAds);
		model.addAttribute("productTypes",productTypes);
		return "front/homePage";
	}
}
