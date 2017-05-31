package com.sfmy.gsh.web.controler.front;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.service.CarService;
import com.sfmy.gsh.service.ProductService;
import com.sfmy.gsh.utils.CacheUtils;
import com.sfmy.gsh.utils.CookieUtils;
import com.sfmy.gsh.utils.MySecurityUtils;
import com.sfmy.gsh.web.base.JsonResult;
import com.sfmy.gsh.web.controler.BaseSpringController;
import com.sfmy.gsh.web.dto.front.ProductDetailDTO;
/**
 * 产品详情页
 */
@Controller
@RequestMapping(value = "/product")
public class ProductDetailController extends BaseSpringController{
	@Resource
	private ProductService productService;
	
	@Resource
	private CarService carService;
	
	@Resource
	private CacheUtils cacheUtils;
	
	@RequestMapping("/detail/{id}")
	public String homePage(@PathVariable(value = "id")Integer id,HttpServletRequest request,Model model,HttpServletResponse response) {
		Integer carCnt = carService.countProductCnt(MySecurityUtils.getCurrUserId());
		
		//看了又看的产品
		List<Integer> viewLogIds = CookieUtils.viewLog(request, response,id);
		List<Product> viewLog = productService.findViewLogByIds(viewLogIds);
		
		model.addAttribute("viewLog",viewLog);
		model.addAttribute("carCnt",carCnt);
		return "front/productDetail";
	}
	
	@ResponseBody
	@RequestMapping("/getDetail/{id}")
	public JsonResult<ProductDetailDTO> getProductDetail(@PathVariable(value = "id")Integer id) {
		ProductDetailDTO dto = productService.findProductDetailDtoById(id);
		return success(dto);
	}
}
