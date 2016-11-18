package com.sfmy.gsh.web.controler.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sfmy.gsh.bean.PageBean;
import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.entity.ProductSecType;
import com.sfmy.gsh.entity.ProductThirdType;
import com.sfmy.gsh.entity.ProductType;
import com.sfmy.gsh.service.ProductService;
import com.sfmy.gsh.utils.CacheUtils;
import com.sfmy.gsh.utils.MyDateFormatUtils;
import com.sfmy.gsh.utils.MyRegexUtils;
import com.sfmy.gsh.utils.MyStringUtils;
import com.sfmy.gsh.utils.OssUtils;
import com.sfmy.gsh.web.vo.AddProductVO;

@Controller
@RequestMapping(value = "/a/product")
public class ProductControler {
	@Resource
	private ProductService productService;

	@Resource
	private CacheUtils cacheUtils;

	@RequestMapping(value = "/listUI")
	public String listUI(@RequestParam Map<String,Object> requestParam,HttpServletRequest request,Model model) { 
		String pageNumber = (String) requestParam.get("pageNumber");
		if(StringUtils.isBlank(pageNumber)){	
			requestParam.put("pageNumber",1);
		}
		Page<Product> page = productService.pageList(requestParam);
		
		PageBean<Product> pageBean = new PageBean<Product>(page.getContent(),page.getSize(),(int) page.getTotalElements());
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("requestParam",requestParam);

		return "admin/product/list";
		
//		int currentIndex = page.getNumber() + 1;
//	    int beginIndex = Math.max(1, currentIndex - 5);i
//	    int endIndex = Math.min(beginIndex + 10, page.getTotalPages());
//		model.addAttribute("pageBean", page);
//	    model.addAttribute("beginIndex", beginIndex);
//	    model.addAttribute("endIndex", endIndex);
//	    model.addAttribute("currentIndex", currentIndex);
	}

	@RequestMapping(value = "/addUI")
	@SuppressWarnings("unchecked")
	public String addUI(HttpServletRequest request) {
		List<ProductType> productTypes = cacheUtils.get("productTypes", List.class);
		request.setAttribute("productTypes", productTypes);
		return "admin/product/add";
	}

	@ResponseBody
	@RequestMapping(value = "/findSecTypeByFirType",produces="application/json;charset=utf-8")
	@SuppressWarnings("unchecked")
	public String findSecTypeByFirType(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();

		String firTypeId = request.getParameter("firTypeId");

		List<ProductType> productTypes = cacheUtils.get("productTypes", List.class);
		for (ProductType productType : productTypes) {
			if (StringUtils.equals(productType.getId() + "", firTypeId)) {
				List<ProductSecType> productSecTypes = productType.getProductSecTypes();
				sb.append("<option value=''>二级分类</option>");
				for (ProductSecType productSecType : productSecTypes) {
					// <option value="${bean.id}">${bean.name}</option>
					sb.append("<option value='" + productSecType.getId() + "'>" + productSecType.getName() + "</option>");
				}
			}
		}

		return sb.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/findThirdTypeBySecType",produces="application/json;charset=utf-8")
	@SuppressWarnings("unchecked")
	public String findThirdTypeBySecType(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();

		String secTypeId = request.getParameter("secTypeId");
		
		List<ProductType> productTypes = cacheUtils.get("productTypes", List.class);
		firFor:for (ProductType productType : productTypes) {
			if(sb.length()>0){
				break firFor;
			}
			List<ProductSecType> productSecTypes = productType.getProductSecTypes();
			for (ProductSecType productSecType : productSecTypes) {
				if(sb.length()>0){
					break firFor;
				}
				if (StringUtils.equals(productSecType.getId() + "", secTypeId)) {
					List<ProductThirdType> thirdTypes = productSecType.getThirdTypes();
					sb.append("<option value=''>三级分类</option>");
					for (ProductThirdType productThirdType : thirdTypes) {
						sb.append("<option value='" + productThirdType.getId() + "'>" + productThirdType.getName() + "</option>");
					}
				}
			}
		}
		return sb.toString();
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
		String ossKey = MyStringUtils.getUUID() + "." + ext;
		OssUtils.upload(ossKey, file.getInputStream());

		Product product = new Product();
		product.setName(productVO.getName());
		product.setImage(ossKey);
		product.setMarketPrice(marketPrice);
		product.setPrice(price);
		product.setIsShangJia(productVO.getIsShangJia());
		product.setFirstType(new ProductType(productVO.getFirstTypeId()));
		product.setSecType(new ProductSecType(productVO.getSecTypeId()));
		product.setThirdType(new ProductThirdType(productVO.getThirdTypeId()));
		product.setDescription(productVO.getDescription());
		
		productService.addProduct(product);
		ra.addFlashAttribute("isSuccessShow", true);
		ra.addFlashAttribute("successMessage", MyDateFormatUtils.getCurrTime() + " 产品添加成功^_^");
		return "redirect:/a/product/addUI";
	}
	
	@RequestMapping(value = "/batchShangJia")
	public String batchShangJia(@RequestParam("productIds")List<Integer> productIds,HttpServletRequest request,RedirectAttributes ra) {
		productService.batchShangJia(productIds);
		ra.addFlashAttribute("isSuccessShow", true);
		ra.addFlashAttribute("successMessage", MyDateFormatUtils.getCurrTime() + " 产品批量上架成功^_^");
		return "redirect:/a/product/listUI";
	}
	
	@RequestMapping(value = "/batchXiaJia")
	public String batchXiaJia(@RequestParam("productIds")List<Integer> productIds,HttpServletRequest request,RedirectAttributes ra) {
		productService.batchXiaJia(productIds);
		ra.addFlashAttribute("isSuccessShow", true);
		ra.addFlashAttribute("successMessage", MyDateFormatUtils.getCurrTime() + " 产品批量下架成功^_^");
		return "redirect:/a/product/listUI";
	}

}
