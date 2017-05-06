package com.sfmy.gsh.web.controler.pay;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.sfmy.gsh.constant.OrderStatus;
import com.sfmy.gsh.entity.Address;
import com.sfmy.gsh.entity.Order;
import com.sfmy.gsh.entity.OrderItem;
import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.entity.User;
import com.sfmy.gsh.service.AddressService;
import com.sfmy.gsh.service.OrderService;
import com.sfmy.gsh.service.ProductService;
import com.sfmy.gsh.utils.MyArith;
import com.sfmy.gsh.utils.MySecurityUtils;
import com.sfmy.gsh.utils.MyStringUtils;
import com.sfmy.gsh.web.base.JsonResult;
import com.sfmy.gsh.web.controler.BaseSpringController;
import com.sfmy.gsh.web.dto.OrderInfo;
import com.sfmy.gsh.web.dto.OrderRequestDTO;

/**
 * 提交订单模块
 */
@Controller
@RequestMapping(value = "/p")
public class OrderController extends BaseSpringController {

	@Resource
	private OrderService orderService;

	@Resource
	private AddressService addressService;

	@Resource
	private ProductService productService;

	@RequestMapping(value = "addOrder", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> addOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
		if (Objects.isNull(orderRequestDTO)) {
			return fail("无法产生订单！");
		}

		if (Objects.isNull(orderRequestDTO.getAddressId())) {
			return fail("请先选择收货地址！");
		}

		List<OrderInfo> orderInfos = orderRequestDTO.getOrderInfos();
		if (CollectionUtils.isEmpty(orderInfos)) {
			return fail("请先选择需要购买的商品！");
		}

		Order order = new Order();
		Double toalQuantity = 0d;
		Double totalPrice = 0d;
		List<OrderItem> items = Lists.newArrayList();
		for (OrderInfo orderInfo : orderInfos) {
			if (Objects.isNull(orderInfo.getSubCnt()) || orderInfo.getSubCnt() == 0) {
				return fail("请先选择需要购买的商品！");
			} else {
				toalQuantity = MyArith.add(toalQuantity, orderInfo.getSubCnt());
			}

			if (Objects.isNull(orderInfo.getProductId())) {
				return fail("请先选择需要购买的商品！");
			}

			Product product = productService.findProductById(orderInfo.getProductId());
			if (Objects.isNull(product)) {
				return fail("请先选择需要购买的商品！");
			} else {
				Double price = product.getPrice();
				Double subTotalPrice = MyArith.mul(orderInfo.getSubCnt().doubleValue(), price);
				totalPrice = MyArith.add(subTotalPrice, totalPrice);
			}

			OrderItem item = new OrderItem();
			item.setProduct(product);
			item.setQuantity(orderInfo.getSubCnt());
			item.setTotalPrice(MyArith.mul(orderInfo.getSubCnt().doubleValue(),product.getPrice()));
			item.setUnitPrice(product.getPrice());
			item.setOrder(order);
			items.add(item);
		}

		Address address = addressService.findAddress(MySecurityUtils.getCurrUserId(), orderRequestDTO.getAddressId());
		if (Objects.isNull(address)) {
			fail("收货地址错误！");
		}

		
		order.setContact(address.getContact());
		order.setReceiveAddress(address.getDetailed());
		order.setMobile(address.getMobile());
		order.setOutTradeNo(MyStringUtils.generateOutTradeNo());
		order.setStatus(OrderStatus.WAIT_PAY);
		order.setUser(new User(MySecurityUtils.getCurrUserId()));
		order.setToalQuantity(toalQuantity.intValue());
		order.setTotalPrice(totalPrice);
		order.setItems(items);
		order.setCreateTime(new Date());
		order.setSubject(MyStringUtils.buildSubject(order));
		orderService.addOrder(order);

		return success(order.getOutTradeNo());
	}

}
