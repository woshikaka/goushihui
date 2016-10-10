package com.sfmy.gsh.web.controler.front;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sfmy.gsh.entity.User;

@Controller
public class LoginController {

	@RequestMapping("/loginUI")
	public String loginUI(HttpServletRequest request) {
		return "security/loginUI";
	}
	
	/**
	 * 登出
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "security/loginUI";
	}

	
	/**
	 * 登录
	 */
	@RequestMapping("/login")
	public String login(User user,RedirectAttributes ra) {
		String username = user.getName();
		String password = user.getPassword();
		if(StringUtils.isBlank(username)){
			ra.addFlashAttribute("isDangerShow",true);
			ra.addFlashAttribute("dangerMessage","用户名为空！");
			return "redirect:loginUI";
		}else if(StringUtils.isBlank(password)){
			ra.addFlashAttribute("isDangerShow",true);
			ra.addFlashAttribute("dangerMessage","密码为空！");
			return "redirect:loginUI";
		}
		
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
		try {
			currentUser.login(token);
		} catch (UnknownAccountException e) {
			ra.addFlashAttribute("isDangerShow",true);
			ra.addFlashAttribute("dangerMessage","用户名不存在！请先注册！");
			return "redirect:loginUI";
		} catch (IncorrectCredentialsException e ) {
			ra.addFlashAttribute("isDangerShow",true);
			ra.addFlashAttribute("dangerMessage","密码错误！");
			return "redirect:loginUI";
		} catch (AuthenticationException e) {
			ra.addFlashAttribute("isDangerShow",true);
			ra.addFlashAttribute("dangerMessage","登录失败！请联系管理员！");
			return "redirect:loginUI";
		}
		
		return "redirect:/quickSearch";
	}
}
