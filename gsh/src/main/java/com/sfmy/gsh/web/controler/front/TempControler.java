package com.sfmy.gsh.web.controler.front;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TempControler {
	@RequestMapping(value = "/test")
	public String test(HttpServletRequest request) {
		
		//git test
		
		
		
		return "test";
	}
}
