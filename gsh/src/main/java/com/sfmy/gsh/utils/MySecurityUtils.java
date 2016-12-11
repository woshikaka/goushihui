package com.sfmy.gsh.utils;

import org.apache.shiro.SecurityUtils;

import com.sfmy.gsh.bean.ShiroUser;

public class MySecurityUtils {
	public static Integer getCurrUserId() {
		Integer userId = -1;
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if(shiroUser!=null){
			userId = shiroUser.getId();
		}
		return userId;
	}
}
