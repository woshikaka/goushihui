package com.sfmy.gsh.web.controler.member;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sfmy.gsh.bean.ShiroUser;
import com.sfmy.gsh.entity.Address;
import com.sfmy.gsh.entity.User;
import com.sfmy.gsh.service.AddressService;

@Controller
@RequestMapping(value="/m")
public class AddressControler {
	@Resource
	private AddressService addressService;
	
	@RequestMapping(value = "/address")
	public String addressList(Model model) {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Integer currUserId = shiroUser.getId();
		List<Address> address = addressService.findAllAddress(currUserId);
		model.addAttribute("address", address); 
		return "member/address";
	}
	
	@RequestMapping(value = "/deleteAddress/{id}")
	public String deleteAddress(@PathVariable Integer id) {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Integer currUserId = shiroUser.getId();
		
		addressService.deleteAddress(new User(currUserId),id);
		return "redirect:/m/address";
	}
	
	@RequestMapping(value = "/setDefaut/{id}")
	public String setDefaut(@PathVariable Integer id,RedirectAttributes ra) {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Integer currUserId = shiroUser.getId();
		
		addressService.setDefaut(new User(currUserId),id);
		ra.addFlashAttribute("isSuccessShow", true);
		ra.addFlashAttribute("successMessage", "设置默认地址成功(*^__^*) ");
		
		return "redirect:/m/address";
	}
	
	@RequestMapping(value = "/addAddress")
	public String addAddress(Address address,HttpServletRequest request,RedirectAttributes ra) {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Integer currUserId = shiroUser.getId();
		
		if(StringUtils.isBlank(address.getContact())){
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "收货人不能为空！");
			return "redirect:/m/address";
		}
		if(StringUtils.length(address.getContact())>255){
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "收货人太长，最多只能有10个字符！");
			return "redirect:/m/address";
		}
		
		if(StringUtils.isBlank(address.getMobile())){
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "手机号不能为空！");
			return "redirect:/m/address";
		}
		if(StringUtils.length(address.getContact())>20){
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "手机号太长，最多只能有20个字符！");
			return "redirect:/m/address";
		}
		
		if(StringUtils.isBlank(address.getDetailed())){
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "收货地址不能为空！");
			return "redirect:/m/address";
		}
		if(StringUtils.length(address.getDetailed())>255){
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "收货地址太长，最多只能有255个字符！");
			return "redirect:/m/address";
		}
		
		Integer countAddress = addressService.countAddress(currUserId);
		if(countAddress!=null && countAddress>=5){
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "收货地址添加失败！收货地址最多只能有5个！");
			return "redirect:/m/address";
		}
		
//		Address address = new Address();
//		address.setDetailed(detailed);
		address.setUser(new User(currUserId));
//		address.setIsDefaut(isDefaut);
		addressService.saveAddress(address);
		
		ra.addFlashAttribute("isSuccessShow", true);
		ra.addFlashAttribute("successMessage", "收货地址添加成功(*^__^*) ");
		return "redirect:/m/address";
	}
}
