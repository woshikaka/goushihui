package com.sfmy.gsh.web.controler.admin;

import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sfmy.gsh.entity.TextPlan;
import com.sfmy.gsh.service.AdService;
import com.sfmy.gsh.service.TextPlanService;
import com.sfmy.gsh.utils.CacheUtils;
import com.sfmy.gsh.web.base.JsonResult;
import com.sfmy.gsh.web.controler.BaseSpringController;

@Controller
@RequestMapping(value = "/a/document")
public class DocumentControler  extends BaseSpringController{
	@Resource
	private AdService adService;

	@Value("#{configProperties['oss.public.pre']}")
	private String ossPublicPre;

	@Resource
	private CacheUtils cacheUtils;
	
	@Autowired
	private TextPlanService textPlanService;

	@ResponseBody
	@RequestMapping(value = "/getTextPlan/{typeId}")
	public JsonResult<String> getTextPlan(@PathVariable Integer typeId) {
		String text = "暂无说明！";
		TextPlan textPlan = textPlanService.findByType(typeId);
		if (Objects.nonNull(textPlan)) {
			text = textPlan.getContent();
		}
		return success(text);
	}
}
