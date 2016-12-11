package com.sfmy.gsh.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class CookieUtils {
	public static List<Integer> viewLog(HttpServletRequest request,HttpServletResponse response,Integer productId) {
		List<Integer> viewLogIds = new ArrayList<Integer>(5);
		
		Cookie[] cookies = request.getCookies();
		if(ArrayUtils.isNotEmpty(cookies)){
			boolean isHasHistory = false;
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				if(StringUtils.equals("viewLogIds", name)){
					isHasHistory = true;
					
					String[] ids = StringUtils.split(cookie.getValue(),",");
					
					while(ArrayUtils.contains(ids, productId+"")){
						ids = ArrayUtils.removeElement(ids,productId+"");
					}
					ids = ArrayUtils.add(ids,0,productId+"");
					while(ids.length>5){
						ids = ArrayUtils.remove(ids,5);
					}
					
					cookie.setValue(StringUtils.join(ids,","));
					response.addCookie(cookie);
					
					for (String id : ids) {
						viewLogIds.add(Integer.valueOf(id));
					}
					break;
				}
			}
			
			if(!isHasHistory){
				Cookie cookie = new Cookie("viewLogIds",productId+"");
				response.addCookie(cookie);
				
				viewLogIds.add(productId);
			}
		}
		
		return viewLogIds;
	}
}