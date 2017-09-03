package com.sfmy.gsh.web.controler.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.sfmy.gsh.bean.ShiroUser;
import com.sfmy.gsh.constant.AppConstant;
import com.sfmy.gsh.entity.Address;
import com.sfmy.gsh.entity.CarProduct;
import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.entity.ProductSecType;
import com.sfmy.gsh.entity.ProductThirdType;
import com.sfmy.gsh.entity.ProductType;
import com.sfmy.gsh.entity.User;
import com.sfmy.gsh.service.AddressService;
import com.sfmy.gsh.service.CarService;
import com.sfmy.gsh.service.ProductService;
import com.sfmy.gsh.utils.CacheUtils;
import com.sfmy.gsh.utils.MyArith;
import com.sfmy.gsh.utils.MySecurityUtils;
import com.sfmy.gsh.web.base.JsonResult;
import com.sfmy.gsh.web.controler.BaseSpringController;
import com.sfmy.gsh.web.vo.CarProductVO;

/**
 * 购物车模块
 */
@Controller
@RequestMapping(value = "/c")
public class CarController extends BaseSpringController{

	@Resource
	private CarService carService;

	@Resource
	private AddressService addressService;

	@Resource
	private ProductService productService;

	@Resource
	private CacheUtils cacheUtils;

	/**
	 * 临时保存在购物车中选中的商品
	 */
	@RequestMapping("/tempSaveSelected")
	@ResponseBody
	public String tempSaveSelected(@RequestBody List<CarProductVO> carProductVOs,HttpServletRequest request) {
		WebUtils.setSessionAttribute(request,AppConstant.CAR_SELECTED+MySecurityUtils.getCurrUserId(),carProductVOs);
		return "true";
	}
	
	@RequestMapping("/getTempSaveSelected")
	@ResponseBody
	public Map<String,Object> getTempSaveSelected(HttpServletRequest request) {
		List<CarProductVO> carProductVOs = (List<CarProductVO>) WebUtils.getSessionAttribute(request, AppConstant.CAR_SELECTED+MySecurityUtils.getCurrUserId());
		
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("code",1);
		result.put("data", carProductVOs);
		return result;
	}
	
	/**
	 * 结算UI
	 */
	@RequestMapping(value="/jieSuanUI")
	public String jieSuanUI(HttpServletRequest request) {
		return "car/jieSuanUI";
	}
	
	/**
	 * 把商品放入购物车
	 */
	@RequestMapping("/putCat")
	public String putCat(Integer productId, HttpServletRequest request, RedirectAttributes ra) {
		carService.putCat(MySecurityUtils.getCurrUserId(), productId, 1);
		ra.addAttribute("productCnt", 1);
		ra.addAttribute("productId", productId);
		return "redirect:putCatResult";
	}
	
	/**
	 * 删除购物车中的商品
	 */
	@RequestMapping("/deleteCarProduct/{carProductId}")
	public String deleteCarProduct(@PathVariable(value = "carProductId") Integer carProductId, HttpServletRequest request, RedirectAttributes ra) {
		Integer userId = MySecurityUtils.getCurrUserId();
		carService.deleteCarProduct(new User(userId),carProductId);
		return "redirect:/c/carUI";
	}

	/**
	 * 把商品放入购物车后成功页
	 */
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
	public String catUI(HttpServletRequest request,Model model) {
		Integer carCnt = carService.countProductCnt(MySecurityUtils.getCurrUserId());
		model.addAttribute("carCnt", carCnt);
		return "car/carUI";
	}
	
	/**
	 * 获取购物车商品信息
	 */
	@RequestMapping("/carProduct")
	@ResponseBody
	public Map<String,Object> carProduct() {
		List<CarProduct> carProducts = carService.findCatProduct(MySecurityUtils.getCurrUserId());
		
		List<CarProductVO> vos = new ArrayList<CarProductVO>();
		if (CollectionUtils.isNotEmpty(carProducts)) {
			for (CarProduct carProduct : carProducts) {
				CarProductVO vo = new CarProductVO();
				vo.setId(carProduct.getId());
				vo.setProductId(carProduct.getProduct().getId());
				vo.setImage("/upload"+carProduct.getProduct().getImage());
				vo.setName(carProduct.getProduct().getName());
				vo.setPrice(carProduct.getProduct().getPrice());
				vo.setSubCnt(carProduct.getCount());
				vo.setSubtotal(MyArith.mul(carProduct.getCount().doubleValue(),carProduct.getProduct().getPrice()));
				vos.add(vo);
			}
		}
		
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("code",1);
		result.put("data", vos);
		return result;
	}
	
	/**
	 * 购物车界面中添加地址
	 */
	@RequestMapping(value = "/addAddress")
	@Deprecated
	public String addAddress(String detailed, Boolean isDefaut, HttpServletRequest request, RedirectAttributes ra) {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Integer currUserId = shiroUser.getId();

		if (StringUtils.isBlank(detailed) || StringUtils.length(detailed) > 255) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "收货地址太长，最多只能有255个字符！");
			return "redirect:/c/carUI";
		}

		Integer countAddress = addressService.countAddress(currUserId);
		if (countAddress != null && countAddress >= 5) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "收货地址添加失败！收货地址最多只能有5个！");
			return "redirect:/c/carUI";
		}

		Address address = new Address();
		address.setDetailed(detailed);
		address.setId(currUserId);
		address.setIsDefaut(isDefaut);
		addressService.saveAddress(address);

		ra.addFlashAttribute("isSuccessShow", true);
		ra.addFlashAttribute("successMessage", "收货地址添加成功(*^__^*) ");
		return "redirect:/c/carUI";
	}
}
