package com.sfmy.gsh.web.controler.member;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sfmy.gsh.service.UserService;
import com.sfmy.gsh.utils.MySecurityUtils;

@Controller
@RequestMapping(value="/m")
public class PasswordControler {
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/password")
	public String passwordUI(Model model) {
		return "member/password";
	}
	
	@RequestMapping(value = "/mp")
	public String modifyPassword(String oldPassword,String newPassword,String configPassword, Model model,RedirectAttributes ra) {
		
		if(!userService.inputPasswordEqualsSourcePassword(MySecurityUtils.getCurrUserId(), oldPassword)){
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "旧密码错误！");
			return "redirect:/m/password";
		}
		
		if(StringUtils.isBlank(newPassword) || StringUtils.isBlank(configPassword)){
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "新密码不能为空！");
			return "redirect:/m/password";	
		}
		
		if(!StringUtils.equals(newPassword, configPassword)){
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "2个新密码不一致！");
			return "redirect:/m/password";		
		}
		
		userService.modifyPassword(MySecurityUtils.getCurrUserId(), newPassword);
		ra.addFlashAttribute("isSuccessShow", true);
		ra.addFlashAttribute("successMessage", "密码修改成功(*^__^*) ");
		
		return "redirect:/m/password";
	}
}
