package com.sfmy.gsh.web.controler.pay;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sfmy.gsh.alipay.util.AlipayNotify;
import com.sfmy.gsh.entity.Order;
import com.sfmy.gsh.entity.OrderPayInfo;
import com.sfmy.gsh.service.AddressService;
import com.sfmy.gsh.service.CarService;
import com.sfmy.gsh.service.OrderService;
import com.sfmy.gsh.service.ProductService;
import com.sfmy.gsh.utils.CacheUtils;

/**
 * 提交订单模块
 */
@Controller
@RequestMapping(value = "/p")
public class OrderController {

	@Resource
	private OrderService orderService;

	
	
}
