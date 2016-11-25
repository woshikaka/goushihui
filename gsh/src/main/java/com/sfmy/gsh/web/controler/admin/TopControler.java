package com.sfmy.gsh.web.controler.admin;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sfmy.gsh.bean.PageBean;
import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.service.ProductService;
import com.sfmy.gsh.utils.CacheUtils;
import com.sfmy.gsh.utils.MyDateFormatUtils;

@Controller
@RequestMapping(value = "/a/top")
public class TopControler {
	@Resource
	private ProductService productService;

	@Resource
	private CacheUtils cacheUtils;
	
	@RequestMapping(value = "/topList/{productTypeId}")
	public String listUI(@PathVariable(value = "productTypeId") String productTypeId,@RequestParam Map<String,Object> requestParam,Model model) { 
		String pageNumber = (String) requestParam.get("pageNumber");
		if(StringUtils.isBlank(pageNumber)){	
			requestParam.put("pageNumber",1);
		}
		requestParam.put("productTypeId",productTypeId);
//		String productTypeId = (String) requestParam.get("productTypeId");
		Page<Product> page = productService.topList(requestParam);
		
		PageBean<Product> pageBean = new PageBean<Product>(page.getContent(),page.getSize(),(int) page.getTotalElements());
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("requestParam",requestParam);
		model.addAttribute("productTypeId",productTypeId);

		return "admin/top/list";
	}
	
	@RequestMapping(value = "/batchTop")
	public String batchTop(@RequestParam("productIds")List<Integer> productIds,Integer productTypeId,Model model,RedirectAttributes ra) { 
		Integer topNum = productService.countTopNum(productTypeId);
		if(topNum!=null && topNum>0){
			if(topNum+productIds.size()>8){
				ra.addFlashAttribute("isDangerShow", true);
				ra.addFlashAttribute("dangerMessage", "top失败！！已经有"+topNum+"个产品已经是top状态，你最多只能再选择"+(8-topNum)+"个产品进行top操作");
				return "redirect:/a/top/topList/"+productTypeId;
			}
		}
		
		productService.batchTop(productIds);
		ra.addFlashAttribute("isSuccessShow",true);
		ra.addFlashAttribute("successMessage",MyDateFormatUtils.getCurrTime()+" top成功^_^");		
		return "redirect:/a/top/topList/"+productTypeId;
	}
	
	@RequestMapping(value = "/batchCancelTop")
	public String batchCancelTop(@RequestParam("productIds")List<Integer> productIds,Integer productTypeId,Model model,RedirectAttributes ra) { 
		productService.batchCancelTop(productIds);
		ra.addFlashAttribute("isSuccessShow",true);
		ra.addFlashAttribute("successMessage",MyDateFormatUtils.getCurrTime()+" 取消top成功^_^");		
		return "redirect:/a/top/topList/"+productTypeId;
	}
}
