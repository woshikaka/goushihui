package com.sfmy.gsh.web.controler.pay;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sfmy.gsh.alipay.config.AlipayConfig;
import com.sfmy.gsh.alipay.util.AlipayNotify;
import com.sfmy.gsh.alipay.util.AlipaySubmit;
import com.sfmy.gsh.constant.PayType;
import com.sfmy.gsh.entity.Order;
import com.sfmy.gsh.entity.OrderPayInfo;
import com.sfmy.gsh.service.OrderService;
import com.sfmy.gsh.utils.MySecurityUtils;
import com.sfmy.gsh.utils.MyStringUtils;

/**
 * 支付宝支付模块
 */
@Controller
//@RequestMapping(value = "/p")
public class AlipayController {

	@Resource
	private OrderService orderService;

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
			String totalAmount = request.getParameter("total_fee");
			if (!Objects.equals(Double.valueOf(totalAmount),order.getTotalPrice())) {
				return "error";
			}
			
			//3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
			String sellerId = request.getParameter("seller_id");
			if(!StringUtils.equals(AlipayConfig.seller_id,sellerId)){
				return "error";
			}
			
			OrderPayInfo payInfo = new OrderPayInfo(); 
			payInfo.setPayType(PayType.ALIPAY);
			payInfo.setBuyerEmail(request.getParameter("buyer_email"));
			payInfo.setCreateTime(new Date());
			payInfo.setOutTradeNo(outTradeNo);
			payInfo.setPayType(PayType.ALIPAY);
			payInfo.setTotalFee(Double.valueOf(totalAmount));
			payInfo.setTradeNo(request.getParameter("trade_no"));
			
			if(orderService.handlePayInfo(order, payInfo)){
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
			String outTradeNo = request.getParameter("out_trade_no");
			Order order = orderService.findByOutTradeNo(outTradeNo);
			
			//1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号
			if(Objects.isNull(order)){
				return "error";
			}
			
				
			//2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）
			String totalAmount = request.getParameter("total_fee");
			if (!Objects.equals(Double.valueOf(totalAmount),order.getTotalPrice())) {
				return "error";
			}
			
			//3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
			String sellerId = request.getParameter("seller_id");
			if(!StringUtils.equals(AlipayConfig.seller_id,sellerId)){
				return "error";
			}
			
			OrderPayInfo payInfo = new OrderPayInfo(); 
			payInfo.setPayType(PayType.ALIPAY);
			payInfo.setBuyerEmail(request.getParameter("buyer_email"));
			payInfo.setCreateTime(new Date());
			payInfo.setOutTradeNo(outTradeNo);
			payInfo.setPayType(PayType.ALIPAY);
			payInfo.setTotalFee(Double.valueOf(totalAmount));
			payInfo.setTradeNo(request.getParameter("trade_no"));
			
			if(orderService.handlePayInfo(order, payInfo)){
				return "success";
			}
		}
		return "error";
	}
	
	@RequestMapping(value="/p/sar")
	public String submitAliRequest(HttpServletRequest request) {
		String outTradeNo = request.getParameter("outTradeNo");
		if (StringUtils.isBlank(outTradeNo)) {
			request.setAttribute("formHtml","error");
			return "pay/toAliPay";
		}
		
		Order order = orderService.findByOutTradeNoAndUserId(MySecurityUtils.getCurrUserId(),outTradeNo);
		if (Objects.isNull(order)) {
			request.setAttribute("formHtml","error");
			return "pay/toAliPay";
		}

		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service","create_direct_pay_by_user");
		sParaTemp.put("partner",AlipayConfig.partner);
		sParaTemp.put("_input_charset",AlipayConfig.input_charset);
		sParaTemp.put("sign_type",AlipayConfig.sign_type);
		sParaTemp.put("notify_url",AlipayConfig.notify_url);
		sParaTemp.put("return_url",AlipayConfig.return_url);
		sParaTemp.put("payment_type","1");
		sParaTemp.put("seller_id",AlipayConfig.partner);
		
		sParaTemp.put("out_trade_no",order.getOutTradeNo());
		sParaTemp.put("total_fee",order.getTotalPrice()+"");
		sParaTemp.put("subject",MyStringUtils.buildSubject(order));
		String formHtml = AlipaySubmit.buildRequest(sParaTemp,"post","提交");
		
		request.setAttribute("formHtml", formHtml);
		return "pay/toAliPay";
	}
	
	@RequestMapping(value="temp",method=RequestMethod.GET)
	public String temp(HttpServletRequest request) {
		return "pay/paySuccess";
	}
}
