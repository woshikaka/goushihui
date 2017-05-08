package com.sfmy.gsh.web.controler.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sfmy.gsh.service.OrderAdminService;
import com.sfmy.gsh.service.ProductService;
import com.sfmy.gsh.utils.CacheUtils;
import com.sfmy.gsh.web.base.JsonResult;
import com.sfmy.gsh.web.controler.BaseSpringController;
import com.sfmy.gsh.web.dto.AdminOrderPageDTO;
import com.sfmy.gsh.web.vo.AdminOrderPageParamVO;

@Controller
@RequestMapping(value = "/a/order")
public class OrderAdminControler extends BaseSpringController{
	@Resource
	private ProductService productService;
	
	@Resource
	private OrderAdminService orderAdminService; 

	@Resource
	private CacheUtils cacheUtils;
	
	@RequestMapping(value = "/listUI")
	public String listUI() { 
		return "admin/order/list";
	}
	
	@RequestMapping(value = "/page")
	@ResponseBody
	public JsonResult<AdminOrderPageDTO> page(@RequestBody AdminOrderPageParamVO requestParam) { 
		AdminOrderPageDTO dto = orderAdminService.pageList(requestParam);
		return success(dto);
	}
	
	@RequestMapping(value = "/sendGoods/{orderId}")
	@ResponseBody
	public JsonResult<Object> sendGoods(@PathVariable Integer orderId) {
		orderAdminService.sendGoods(orderId);
		return success();
	}
}