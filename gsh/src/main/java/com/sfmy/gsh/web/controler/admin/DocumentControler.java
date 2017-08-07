package com.sfmy.gsh.web.controler.admin;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.sfmy.gsh.entity.TextPlan;
import com.sfmy.gsh.service.AdService;
import com.sfmy.gsh.service.TextPlanService;
import com.sfmy.gsh.utils.CacheUtils;
import com.sfmy.gsh.utils.MyOssUtils;
import com.sfmy.gsh.web.base.JsonResult;
import com.sfmy.gsh.web.controler.BaseSpringController;

@Controller
@RequestMapping(value = "/a/document")
public class DocumentControler extends BaseSpringController {
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

	@RequestMapping(value = "/wholesaleRuleUI")
	public String groupRuleUI() {
		return "admin/document/wholesaleRule";
	}

	@RequestMapping(value = "/aboutMeUI")
	public String aboutMe() {
		return "admin/document/aboutMe";
	}

	@RequestMapping(value = "/modifyLogoUI")
	public String modifyLogoUI() {
		return "admin/document/modifyLogo";
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public JsonResult<String> save(@RequestBody Map<String, String> requestParam) {
		String type = requestParam.get("type");
		String ruleContent = requestParam.get("ruleContent");

		textPlanService.save(Integer.valueOf(type), ruleContent);

		return success();
	}

	//headers = "'Content-Type': 'multipart/form-data'"
	@RequestMapping(value = "/upload",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> upload(HttpServletRequest request, MultipartFile file) throws IOException {
		String realPath = WebUtils.getRealPath(request.getServletContext(),"/resources/images");
		System.out.println(realPath);
		FileUtils.writeByteArrayToFile(new File(realPath,"logo.png"),file.getBytes());
		return success();
	}
}
