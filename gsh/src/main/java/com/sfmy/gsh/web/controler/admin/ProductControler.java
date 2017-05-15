package com.sfmy.gsh.web.controler.admin;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.entity.ProductSecType;
import com.sfmy.gsh.entity.ProductThirdType;
import com.sfmy.gsh.entity.ProductType;
import com.sfmy.gsh.service.ProductService;
import com.sfmy.gsh.utils.CacheUtils;
import com.sfmy.gsh.utils.MyDateFormatUtils;
import com.sfmy.gsh.utils.MyOssUtils;
import com.sfmy.gsh.utils.MyRegexUtils;
import com.sfmy.gsh.utils.MyStringUtils;
import com.sfmy.gsh.web.base.JsonResult;
import com.sfmy.gsh.web.controler.BaseSpringController;
import com.sfmy.gsh.web.dto.AdminProductDTO;
import com.sfmy.gsh.web.dto.AdminProductPageDTO;
import com.sfmy.gsh.web.vo.AddProductVO;
import com.sfmy.gsh.web.vo.ProductPageParamVO;

@Controller
@RequestMapping(value = "/a/product")
public class ProductControler extends BaseSpringController{
	@Resource
	private ProductService productService;

	@Resource
	private CacheUtils cacheUtils;
	
	@RequestMapping(value = "/listUI")
	public String listUI() { 
		return "admin/product/list";
	}
	@RequestMapping(value = "/modifyUI")
	public String modifyUI() { 
		return "admin/product/modifyModal";
	}
	
	@RequestMapping(value = "/addUI")
	@SuppressWarnings("unchecked")
	public String addUI(HttpServletRequest request) {
		List<ProductType> productTypes = cacheUtils.get("productTypes", List.class);
		request.setAttribute("productTypes", productTypes);
		return "admin/product/add";
	}
	
	@ResponseBody
	@RequestMapping(value = "/page")
	public JsonResult<AdminProductPageDTO> page(@RequestBody ProductPageParamVO pageParamVO) { 
		AdminProductPageDTO result = productService.pageList(pageParamVO);
		return success(result);
	}


	@RequestMapping(value = "/addProduct")
	public String addProduct(HttpServletRequest request, AddProductVO productVO, @RequestParam("file") MultipartFile file, RedirectAttributes ra) throws IOException {
		// 产品名称
		String name = productVO.getName();
		if (StringUtils.isBlank(name)) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "产品名称不能为空！");
			ra.addFlashAttribute("productVO", productVO);
			return "redirect:/a/product/addUI";
		}
		if (name.length() > 100) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "产品名称长度不能超过100个字符");
			ra.addFlashAttribute("productVO", productVO);
			return "redirect:/a/product/addUI";
		}
		
		//验证产品类型
		if(productVO.getFirstTypeId()==null||productVO.getSecTypeId()==null||productVO.getThirdTypeId()==null){
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "产品类型为空！");
			ra.addFlashAttribute("productVO", productVO);
			return "redirect:/a/product/addUI";
		}

		// 价格
		String priceStr = productVO.getPriceStr();
		if (StringUtils.isBlank(priceStr)) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "价格不能为空！");
			ra.addFlashAttribute("productVO", productVO);
			return "redirect:/a/product/addUI";
		}
		if (!MyRegexUtils.isDouble(priceStr) && !MyRegexUtils.isPositiveInteger(priceStr)) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "你输入的价格不合法！请重新输入价格");
			ra.addFlashAttribute("productVO", productVO);
			return "redirect:/a/product/addUI";
		}
		String decimalPoint = StringUtils.substringAfter(priceStr, ".");
		if (StringUtils.length(decimalPoint) > 2) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "你输入的价格小数位太多！小数点最多只能为2位");
			ra.addFlashAttribute("productVO", productVO);
			return "redirect:/a/product/addUI";
		}
		Double price = Double.valueOf(priceStr);
		if (price == null || price < 0 || price > 10000) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "你输入的价格不合法！价格只能在0~1万之间");
			ra.addFlashAttribute("productVO", productVO);
			return "redirect:/a/product/addUI";
		}

		// 超市价格
		String marketPriceStr = productVO.getMarketPriceStr();
		if (StringUtils.isBlank(marketPriceStr)) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "超市价格不能为空！");
			ra.addFlashAttribute("productVO", productVO);
			return "redirect:/a/product/addUI";
		}
		if (!MyRegexUtils.isDouble(marketPriceStr) && !MyRegexUtils.isPositiveInteger(marketPriceStr)) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "你输入的超市价格不合法！请重新输入价格");
			ra.addFlashAttribute("productVO", productVO);
			return "redirect:/a/product/addUI";
		}
		String marketPriceDecimalPoint = StringUtils.substringAfter(marketPriceStr, ".");
		if (StringUtils.length(marketPriceDecimalPoint) > 2) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "你输入的超市价格小数位太多！小数点最多只能为2位");
			ra.addFlashAttribute("productVO", productVO);
			return "redirect:/a/product/addUI";
		}
		Double marketPrice = Double.valueOf(marketPriceStr);
		if (marketPrice == null || marketPrice < 0 || marketPrice > 10000) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "你输入的超市价格不合法！超市价格只能在0~1万之间");
			ra.addFlashAttribute("productVO", productVO);
			return "redirect:/a/product/addUI";
		}
		
		// 库存
		String stockCountStr = productVO.getStockCount();
		if (StringUtils.isBlank(stockCountStr)) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "库存不能为空！");
			ra.addFlashAttribute("productVO", productVO);
			return "redirect:/a/product/addUI";
		}
		if (!MyRegexUtils.isPositiveInteger(stockCountStr)) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "你输入的库存不合法！请重新输入价格");
			ra.addFlashAttribute("productVO", productVO);
			return "redirect:/a/product/addUI";
		}
		Integer stockCount = Integer.valueOf(stockCountStr);
		if (stockCount == null || stockCount < 0 || stockCount > 10000) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "你输入的库存不合法！库存只能在0~1万之间");
			ra.addFlashAttribute("productVO", productVO);
			return "redirect:/a/product/addUI";
		}

		// 验证产品图片
		if (file == null) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "产品图片为空！请先选择图片。");
			ra.addFlashAttribute("productVO", productVO);
			return "redirect:/a/product/addUI";
		}
		String originalFilename = file.getOriginalFilename();
		String ext = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
		if (!MyStringUtils.isPicExt(ext)) {
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "你选择的不是图片！请重新选择");
			ra.addFlashAttribute("productVO", productVO);
			return "redirect:/a/product/addUI";
		}
		String fileKey = MyOssUtils.upload(MyStringUtils.getUUID() + "." + ext,file.getBytes());

		Product product = new Product();
		product.setName(productVO.getName());
		product.setImage(fileKey);
		product.setMarketPrice(marketPrice);
		product.setPrice(price);
		product.setStockCount(stockCount);
		product.setIsShangJia(productVO.getIsShangJia());
		product.setFirstType(new ProductType(productVO.getFirstTypeId()));
		product.setSecType(new ProductSecType(productVO.getSecTypeId()));
		product.setThirdType(new ProductThirdType(productVO.getThirdTypeId()));
//		product.setDescription(productVO.getDescription()); TODO
		
		productService.addProduct(product);
		ra.addFlashAttribute("isSuccessShow", true);
		ra.addFlashAttribute("successMessage", MyDateFormatUtils.getCurrTime() + " 产品添加成功^_^");
		return "redirect:/a/product/addUI";
	}
	
	@ResponseBody
	@RequestMapping(value = "/batchShangJia")
	public JsonResult<Object> batchShangJia(@RequestParam("productIds")List<Integer> productIds) {
		productService.batchShangJia(productIds);
		return success();
	}
	
	@ResponseBody
	@RequestMapping(value = "/batchXiaJia")
	public JsonResult<Object> batchXiaJia(@RequestParam("productIds")List<Integer> productIds) {
		productService.batchXiaJia(productIds);
		return success();
	}
	
	@ResponseBody
	@RequestMapping(value = "/getProductInfoById/{id}")
	public JsonResult<AdminProductDTO> getProductInfoById(@PathVariable Integer id) {
		AdminProductDTO result = productService.findProductByIdForEager(id);
		return success(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateProduct")
	public JsonResult<Object> updateProduct(@RequestBody AdminProductDTO dto) throws IOException {
		productService.updateProduct(dto);
		return success();
	}
}
