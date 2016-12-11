package com.sfmy.gsh.web.controler.car;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sfmy.gsh.bean.ShiroUser;
import com.sfmy.gsh.constant.AppConstant;
import com.sfmy.gsh.entity.Address;
import com.sfmy.gsh.entity.CarProduct;
import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.entity.ProductSecType;
import com.sfmy.gsh.entity.ProductThirdType;
import com.sfmy.gsh.entity.ProductType;
import com.sfmy.gsh.service.AddressService;
import com.sfmy.gsh.service.CarService;
import com.sfmy.gsh.service.ProductService;
import com.sfmy.gsh.utils.CacheUtils;
import com.sfmy.gsh.utils.MySecurityUtils;

/**
 * 购物车模块
 */
@Controller
@RequestMapping(value = "/c")
public class CarController {

	@Resource
	private CarService carService;

	@Resource
	private AddressService addressService;

	@Resource
	private ProductService productService;

	@Resource
	private CacheUtils cacheUtils;

	/**
	 * 把商品放入购物
	 */
	@RequestMapping("/putCat")
	public String putCat(Integer productCnt, Integer productId, HttpServletRequest request, RedirectAttributes ra) {
		carService.putCat(MySecurityUtils.getCurrUserId(), productId, productCnt);
		ra.addAttribute("productCnt", productCnt);
		ra.addAttribute("productId", productId);
		return "redirect:putCatResult";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/putCatResult")
	public String putCatResult(Integer productCnt, Integer productId, Model model) {
		Product product = productService.findProductById(productId);
		// 导购类目
		List<ProductType> productTypes = cacheUtils.get(AppConstant.CACHE_PRODUCTTYPES_KEY, List.class);
		List<ProductSecType> secTypes = cacheUtils.get("secTypes", List.class);
		List<ProductThirdType> thirdTypes = cacheUtils.get("thirdTypes", List.class);
		
		// 购车车数字
		Integer carCnt = carService.countProductCnt(MySecurityUtils.getCurrUserId());

		model.addAttribute("carCnt", carCnt);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("secTypes", secTypes);
		model.addAttribute("thirdTypes", thirdTypes);
		model.addAttribute("product", product);
		model.addAttribute("productCnt", productCnt);
		return "car/putCatResult";
	}

	/**
	 * 跳转到购物车
	 */
	@RequestMapping("/carUI")
	public String catUI(HttpServletRequest request) {
		List<CarProduct> catProducts = carService.findCatProduct(MySecurityUtils.getCurrUserId());
		List<Address> address = addressService.findAllAddress(MySecurityUtils.getCurrUserId());
		request.setAttribute("catProducts", catProducts);
		request.setAttribute("address", address);
		return "car/carUI";
	}

	@RequestMapping(value = "/addAddress")
	public String addAddress(String detailed, Boolean isDefaut, HttpServletRequest request, RedirectAttributes ra) {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Integer currUserId = shiroUser.getId();

		if (StringUtils.isBlank(detailed) || StringUtils.length(detailed) > 255) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "收货地址太长，最多只能有255个字符！");
			return "redirect:/c/catUI";
		}

		Integer countAddress = addressService.countAddress(currUserId);
		if (countAddress != null && countAddress >= 5) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "收货地址添加失败！收货地址最多只能有5个！");
			return "redirect:/c/catUI";
		}

		Address address = new Address();
		address.setDetailed(detailed);
		address.setId(currUserId);
		address.setIsDefaut(isDefaut);
		addressService.saveAddress(address);

		ra.addFlashAttribute("isSuccessShow", true);
		ra.addFlashAttribute("successMessage", "收货地址添加成功(*^__^*) ");
		return "redirect:/c/catUI";
	}
}
