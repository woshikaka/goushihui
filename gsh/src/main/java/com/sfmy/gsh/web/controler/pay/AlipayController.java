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
 * 支付宝支付模块
 */
@Controller
@RequestMapping(value = "/p")
public class AlipayController {

	@Resource
	private OrderService orderService;

	/**
	 * 支付宝异步通知
	 */
	@RequestMapping(value="yb",method=RequestMethod.POST)
	@ResponseBody
	public String notifyUrl(HttpServletRequest request) {
		Map params = request.getParameterMap();
		if(AlipayNotify.verify(params)){
			String outTradeNo = request.getParameter("out_trade_no");
			Order order = orderService.findByOutTradeNo(outTradeNo);
			
			if(order!=null){//1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号
				
				//2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）
				String totalAmount = request.getParameter("total_amount");
				if(!StringUtils.equals(totalAmount,order.getTotalPrice()+"")){
					return "error";
				}
				
				//3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
				
				
				
				//4、验证app_id是否为该商户本身。
				
				//上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
				
				
				OrderPayInfo payInfo = new OrderPayInfo(); 
				if(orderService.callBackWithHandleOrder(order, payInfo)){
					return "success";
				}
			}else{
				return "error";
			}
		}
		return "error";
	}
	
	
	/**
	 * 支付宝同步通知
	 */
	@RequestMapping(value="tb",method=RequestMethod.GET)
	@ResponseBody
	public String returnUrl(HttpServletRequest request) {
		Map params = request.getParameterMap();
		if(AlipayNotify.verify(params)){
			//TODO
			
			
			return "success";
		}
		return "error";
	}
	
	@RequestMapping(value="temp",method=RequestMethod.GET)
	public String temp(HttpServletRequest request) {
		return "pay/paySuccess";
	}
}
