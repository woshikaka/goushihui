package com.sfmy.gsh.web.controler.member;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sfmy.gsh.service.OrderService;
import com.sfmy.gsh.utils.MySecurityUtils;
import com.sfmy.gsh.web.dto.MemberOrderPageDTO;
import com.sfmy.gsh.web.vo.OrderPageParamVO;
/**
 * 订单中心
 * @author hyz
 */
@Controller
@RequestMapping(value="/m")
public class OrderControler {
	@Resource
	private OrderService orderService;
	
	@ResponseBody
	@RequestMapping(value = "/orderPage")
	public Map<String,Object> addressList(@RequestBody OrderPageParamVO param) {
		MemberOrderPageDTO pageResult = orderService.listPage(MySecurityUtils.getCurrUserId(),param);
		
		Map<String,Object> response = new HashMap<String, Object>();
		response.put("code",1);
		response.put("data",pageResult);
		return response;
	}
}
