package com.sfmy.gsh.web.controler.front;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sfmy.gsh.constant.AdType;
import com.sfmy.gsh.constant.AppConstant;
import com.sfmy.gsh.entity.Ad;
import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.entity.ProductSecType;
import com.sfmy.gsh.entity.ProductThirdType;
import com.sfmy.gsh.entity.ProductType;
import com.sfmy.gsh.service.AdService;
import com.sfmy.gsh.service.CarService;
import com.sfmy.gsh.service.ProductService;
import com.sfmy.gsh.utils.CacheUtils;
import com.sfmy.gsh.utils.MySecurityUtils;
/**
 * 首页
 */
@Controller
public class HomePageController {
	@Resource
	private AdService adService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private CacheUtils cacheUtils;
	
	@Resource
	private CarService carService;
	
	@RequestMapping("/homePage")
	@SuppressWarnings("unchecked")
	public String homePage(HttpServletRequest request,Model model) {
		//轮询广告
		List<Ad> showAds = cacheUtils.get(AppConstant.CACHE_SHOWADS_KEY,List.class);
		if(CollectionUtils.isEmpty(showAds)){
			showAds = adService.findShowing();
			cacheUtils.put("showAds", showAds);
		}
		Ad barAd = cacheUtils.get(AppConstant.CACHE_BAR_AD_KEY,Ad.class);
		if(barAd == null){
			List<Ad> temp = adService.findAd(AdType.BAR);
			if(CollectionUtils.isNotEmpty(temp)){
				barAd = temp.get(0);
			}
		}
		
		//导购类目
		List<ProductType> productTypes = cacheUtils.get(AppConstant.CACHE_PRODUCTTYPES_KEY,List.class);
		List<ProductSecType> secTypes = cacheUtils.get("secTypes",List.class);
		List<ProductThirdType> thirdTypes = cacheUtils.get("thirdTypes",List.class);
		
		//热卖top5
		List<Product> hotProducts = cacheUtils.get(AppConstant.CACHE_HOT_TOP5_KEY,List.class);
		if(CollectionUtils.isEmpty(hotProducts)){
			hotProducts = productService.hot5();
			cacheUtils.put(AppConstant.CACHE_HOT_TOP5_KEY,hotProducts);
		}
		
		//top8产品
		List<Product> products1 = cacheUtils.get(AppConstant.CACHE_LINGSHI_TOP8_KEY,List.class);
		if (CollectionUtils.isEmpty(products1)) {
			products1 = productService.findTop(1);
			cacheUtils.put(AppConstant.CACHE_LINGSHI_TOP8_KEY,products1);
		}
		
		List<Product> products2 =cacheUtils.get(AppConstant.CACHE_YINLIAO_TOP8_KEY,List.class);
		if (CollectionUtils.isEmpty(products2)) {
			products2 = productService.findTop(2);
			cacheUtils.put(AppConstant.CACHE_YINLIAO_TOP8_KEY,products2);
		}
		
		List<Product> products3 =cacheUtils.get(AppConstant.CACHE_LIANGYOU_TOP8_KEY,List.class);
		if (CollectionUtils.isEmpty(products3)) {
			products3 = productService.findTop(3);
			cacheUtils.put(AppConstant.CACHE_LIANGYOU_TOP8_KEY,products3);
		}
		
		//购车车数字
		Integer carCnt = carService.countProductCnt(MySecurityUtils.getCurrUserId());
		
		model.addAttribute("carCnt",carCnt);
		model.addAttribute("hotProducts",hotProducts);
		model.addAttribute("showAds",showAds);
		model.addAttribute("barAd",barAd);
		model.addAttribute("productTypes",productTypes);
		model.addAttribute("secTypes",secTypes);
		model.addAttribute("thirdTypes",thirdTypes);
		model.addAttribute("products1",products1);
		model.addAttribute("products2",products2);
		model.addAttribute("products3",products3);
		return "front/homePage";
	}
	
	@RequestMapping("/more")
	public String more(Integer productTypeId,Boolean salesHigh2Low,Model model,HttpServletRequest request,RedirectAttributes ra) {
		String para = "";
		if(productTypeId!=null){
			if (productTypeId.intValue() == 1) {
				para = "?productTypeId=1&productTypeName=副食零食";
			} else if(productTypeId.intValue() == 2){
				para = "?productTypeId=2&productTypeName=酒水饮料";
			} else if(productTypeId.intValue() == 3){
				para = "?productTypeId=3&productTypeName=粮油调味";
			}
		}
		
		if(salesHigh2Low!=null && salesHigh2Low){
			para = "?salesHigh2Low=true";
		}
		return "forward:/search"+para;
	}
}
