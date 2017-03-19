package com.sfmy.gsh.web.controler.member;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmy.gsh.utils.CacheUtils;

@Controller
@RequestMapping(value="/m")
public class MemberCenterControler {
	@Value("#{configProperties['oss.public.pre']}")
	private String ossPublicPre;
	
	@Resource
	private CacheUtils cacheUtils;
	
	@RequestMapping(value = "/center")
	public String listUI(HttpServletRequest request) {
		return "member/order";
	}
}
