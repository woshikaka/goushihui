package com.sfmy.gsh.web.controler.front;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmy.gsh.entity.TextPlan;
import com.sfmy.gsh.service.TextPlanService;

@Controller
public class AboutMeControler {
	
	@Autowired
	private TextPlanService textPlanService;
	
	@RequestMapping(value = "/aboutMe")
	public String aboutMe(HttpServletRequest request) {
		String text = "暂无说明！";
		TextPlan textPlan = textPlanService.findByType(2);
		if (Objects.nonNull(textPlan)) {
			text = textPlan.getContent();
		}
		request.setAttribute("text",text);
		return "front/aboutMe";
	}
	
	@RequestMapping(value = "/wholesaleRule")
	public String wholesaleRule(HttpServletRequest request) {
		String text = "暂无说明！";
		TextPlan textPlan = textPlanService.findByType(1);
		if (Objects.nonNull(textPlan)) {
			text = textPlan.getContent();
		}
		request.setAttribute("text",text);
		return "front/wholesaleRule";
	}
}
