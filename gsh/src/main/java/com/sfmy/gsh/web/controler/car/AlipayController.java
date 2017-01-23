package com.sfmy.gsh.web.controler.car;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sfmy.gsh.service.AddressService;
import com.sfmy.gsh.service.CarService;
import com.sfmy.gsh.service.ProductService;
import com.sfmy.gsh.utils.CacheUtils;

/**
 * 购物车模块
 */
@Controller
@RequestMapping(value = "/c")
public class AlipayController {

	@Resource
	private CarService carService;

	@Resource
	private AddressService addressService;

	@Resource
	private ProductService productService;

	@Resource
	private CacheUtils cacheUtils;

	/**
	 * 提交订单
	 */
	@RequestMapping(value="/so",method=RequestMethod.POST)
	public String submitOrder(RedirectAttributes ra) {
		return "";
	}
}
