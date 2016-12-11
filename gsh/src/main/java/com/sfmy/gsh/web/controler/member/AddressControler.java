package com.sfmy.gsh.web.controler.member;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sfmy.gsh.bean.ShiroUser;
import com.sfmy.gsh.entity.Address;
import com.sfmy.gsh.service.AddressService;

@Controller
@RequestMapping(value="/m")
public class AddressControler {
	@Resource
	private AddressService addressService;
	
	@RequestMapping(value = "/addressList")
	public String addressList(Model model) {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Integer currUserId = shiroUser.getId();
		List<Address> address = addressService.findAllAddress(currUserId);
		model.addAttribute("address", address);
		return "member/address";
	}
	
	@RequestMapping(value = "/addAddress")
	public String addAddress(String detailed,Boolean isDefaut,HttpServletRequest request,RedirectAttributes ra) {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Integer currUserId = shiroUser.getId();
		
		if(StringUtils.isBlank(detailed) || StringUtils.length(detailed)>255){
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "收货地址太长，最多只能有255个字符！");
			return "redirect:/m/addressList";
		}
		
		Integer countAddress = addressService.countAddress(currUserId);
		if(countAddress!=null && countAddress>=5){
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "收货地址添加失败！收货地址最多只能有5个！");
			return "redirect:/m/addressList";
		}
		
		Address address = new Address();
		address.setDetailed(detailed);
		address.setId(currUserId);
		address.setIsDefaut(isDefaut);
		addressService.saveAddress(address);
		
		ra.addFlashAttribute("isSuccessShow", true);
		ra.addFlashAttribute("successMessage", "收货地址添加成功(*^__^*) ");
		return "redirect:/m/addressList";
	}
}
