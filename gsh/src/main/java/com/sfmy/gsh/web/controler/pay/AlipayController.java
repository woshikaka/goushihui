package com.sfmy.gsh.web.controler.pay;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sfmy.gsh.alipay.config.AlipayConfig;
import com.sfmy.gsh.alipay.util.AlipayNotify;
import com.sfmy.gsh.alipay.util.AlipaySubmit;
import com.sfmy.gsh.entity.Order;
import com.sfmy.gsh.entity.OrderPayInfo;
import com.sfmy.gsh.service.AddressService;
import com.sfmy.gsh.service.CarService;
import com.sfmy.gsh.service.OrderService;
import com.sfmy.gsh.service.ProductService;
import com.sfmy.gsh.utils.CacheUtils;
import com.sfmy.gsh.utils.MyStringUtils;
import com.sfmy.gsh.web.dto.OrderRequestDTO;

/**
 * 支付宝支付模块
 */
@Controller
//@RequestMapping(value = "/p")
public class AlipayController {

	@Resource
	private OrderService orderService;
	
//	@Resource
//	private AlipayConfig alipayConfig;

	/**
	 * 支付宝异步通知
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="yb",method=RequestMethod.POST)
	@ResponseBody
	public String notifyUrl(HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String,String> params = new HashMap<String,String>();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String name = (String) parameterNames.nextElement();
			String value = request.getParameter(name);
			params.put(name,value);
		}
		
		if(AlipayNotify.verify(params)){
			String outTradeNo = request.getParameter("out_trade_no");
			Order order = orderService.findByOutTradeNo(outTradeNo);
			
			//1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号
			if(Objects.isNull(order)){
				return "error";
			}
			
				
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
		}
		return "error";
	}
	
	/**
	 * 支付宝同步通知
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="tb",method=RequestMethod.GET)
	@ResponseBody
	public String returnUrl(HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String,String> params = new HashMap<String,String>();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String name = (String) parameterNames.nextElement();
			String value = request.getParameter(name);
			params.put(name,new String(value.getBytes(StandardCharsets.ISO_8859_1),"utf-8"));
		}		
		
		
		if(AlipayNotify.verify(params)){
			//TODO
			
			
			return "success";
		}
		return "error";
	}
	
//	@ResponseBody
	@RequestMapping(value="/p/sar")
	public String submitAliRequest(@RequestBody OrderRequestDTO submitAliRequestDTO,HttpServletRequest request) {
		
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service","create_direct_pay_by_user");
		sParaTemp.put("partner",AlipayConfig.partner);
		sParaTemp.put("_input_charset",AlipayConfig.input_charset);
		sParaTemp.put("sign_type",AlipayConfig.sign_type);
		sParaTemp.put("notify_url",AlipayConfig.notify_url);
		sParaTemp.put("return_url",AlipayConfig.return_url);
		sParaTemp.put("out_trade_no",MyStringUtils.generateOutTradeNo());
//		sParaTemp.put("subject","123456789");
		sParaTemp.put("subject","手套");
		sParaTemp.put("payment_type","1");
		sParaTemp.put("total_fee","0.01");
		sParaTemp.put("seller_id",AlipayConfig.partner);
		
		String formHtml = AlipaySubmit.buildRequest(sParaTemp,"post","提交");
		
		request.setAttribute("formHtml", formHtml);
		return "pay/toAliPay";
//		return formHtml;
	}
	
	@RequestMapping(value="temp",method=RequestMethod.GET)
	public String temp(HttpServletRequest request) {
		return "pay/paySuccess";
	}
}
