package com.sfmy.gsh.web.controler.front;

import javax.annotation.Resource;
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
import com.sfmy.gsh.service.UserService;
import com.sfmy.gsh.utils.RandomValidateCode;

@Controller
public class LoginController {

	@Resource
	private UserService userService;
	
	/**
	 * 跳转到登录UI
	 */
	@RequestMapping("/loginUI")
	public String loginUI(HttpServletRequest request) {
		return "front/loginUI";
	}
	
	/**
	 * 跳转到注册UI
	 */
	@RequestMapping("/registerUI")
	public String registerUI(HttpServletRequest request) {
		return "front/registerUI";
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
	 * 注册
	 */
	@RequestMapping("/register")
	public String register(User user,HttpServletRequest request,RedirectAttributes ra) {
		String yzm = request.getParameter("yzm");
		if(StringUtils.isBlank(yzm)){
			ra.addFlashAttribute("isDangerShow",true);
			ra.addFlashAttribute("dangerMessage","验证码为空！");
			return "redirect:registerUI";
		}
		
		String sessionYzm = (String) request.getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY);
		if(!StringUtils.equalsIgnoreCase(yzm, sessionYzm)){
			ra.addFlashAttribute("isDangerShow",true);
			ra.addFlashAttribute("dangerMessage","验证码输入错误！");
			return "redirect:registerUI";
		}
		String username = user.getName();
		String password = user.getPassword();
		String againPassword = request.getParameter("againPassword");
		if(StringUtils.isBlank(username)){
			ra.addFlashAttribute("isDangerShow",true);
			ra.addFlashAttribute("dangerMessage","用户名为空！请输入用户名！");
			return "redirect:registerUI";
		}
		if(StringUtils.isBlank(password)){
			ra.addFlashAttribute("isDangerShow",true);
			ra.addFlashAttribute("dangerMessage","密码为空！请输入密码！");
			return "redirect:registerUI";
		}
		if(!StringUtils.equals(password, againPassword)){
			ra.addFlashAttribute("isDangerShow",true);
			ra.addFlashAttribute("dangerMessage","2次密码输入不一致！");
			return "redirect:registerUI";
		}
		User tempUser = userService.findUserByName(username);
		if(tempUser!=null){
			ra.addFlashAttribute("isDangerShow",true);
			ra.addFlashAttribute("dangerMessage",username+"该用户名已经被别人注册了，请使用其他用户名进行注册！");
			return "redirect:registerUI";
		}
		userService.saveUser(user);
		return "/front/gshIndex";
	}

	
	/**
	 * 登录
	 */
	@RequestMapping("/login")
	public String login(User user,RedirectAttributes ra,HttpServletRequest request) {
		String username = user.getName();
		String password = user.getPassword();
		if(StringUtils.isBlank(username)){
			ra.addFlashAttribute("isDangerShow",true);
			ra.addFlashAttribute("dangerMessage","用户名为空！请输入用户名！");
			return "redirect:loginUI";
		}
		if(StringUtils.isBlank(password)){
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
		
		return "/front/gshIndex";
	}
}
