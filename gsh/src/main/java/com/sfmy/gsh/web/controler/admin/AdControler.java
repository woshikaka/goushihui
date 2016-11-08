package com.sfmy.gsh.web.controler.admin;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sfmy.gsh.entity.Ad;
import com.sfmy.gsh.service.AdService;
import com.sfmy.gsh.utils.CacheUtils;
import com.sfmy.gsh.utils.MyDateFormatUtils;
import com.sfmy.gsh.utils.MyStringUtils;
import com.sfmy.gsh.utils.OssUtils;

@Controller
@RequestMapping(value="/a/ad")
public class AdControler {
	@Resource
	private AdService adService;
	
	@Value("#{configProperties['oss.public.pre']}")
	private String ossPublicPre;
	
	@Resource
	private CacheUtils cacheUtils;
	
	@RequestMapping(value = "/adListUI")
	public String listUI(HttpServletRequest request) {
		List<Ad> ads = adService.findAll();
		request.setAttribute("ads",ads);
		request.setAttribute("ossPublicPre",ossPublicPre);
		return "admin/ad/list";
	}
	
	@RequestMapping(value = "/addUI")
	public String addUI(HttpServletRequest request) {
		return "admin/ad/add";
	}
	
	@RequestMapping(value = "/add")
	public String addUI(@RequestParam("file")MultipartFile file,HttpServletRequest request,Ad ad,RedirectAttributes ra,Boolean isForm) throws IOException {
		if (isForm!=null && isForm) {
			String href = ad.getHref();
			if(StringUtils.isBlank(href)){
//				request.setAttribute("isDangerShow",true);
//				request.setAttribute("dangerMessage","广告超链接为空！请输入广告超链接。");
//				request.setAttribute("ad",ad);
//				return "admin/ad/add";
				ra.addFlashAttribute("isDangerShow",true);
				ra.addFlashAttribute("dangerMessage","广告超链接为空！请输入广告超链接。");
				ra.addFlashAttribute("ad",ad);
				return "redirect:/a/ad/addUI";
				
			}
			if(href.length()>255){
//				request.setAttribute("isDangerShow",true);
//				request.setAttribute("dangerMessage","广告超链接长度不能超过255个字符");
//				request.setAttribute("ad",ad);
//				return "admin/ad/add";
				ra.addFlashAttribute("isDangerShow",true);
				ra.addFlashAttribute("dangerMessage","广告超链接长度不能超过255个字符");
				ra.addFlashAttribute("ad",ad);
				return "redirect:/a/ad/addUI";
			}
			
			if(file==null){
//				request.setAttribute("isDangerShow",true);
//				request.setAttribute("dangerMessage","图片为空！请先选择图片。");
//				request.setAttribute("ad",ad);
				ra.addFlashAttribute("isDangerShow",true);
				ra.addFlashAttribute("dangerMessage","图片为空！请先选择图片。");
				ra.addFlashAttribute("ad",ad);
				return "redirect:/a/ad/addUI";
			}
			String originalFilename = file.getOriginalFilename();
			String ext = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
			if(!MyStringUtils.isPicExt(ext)){
//				request.setAttribute("isDangerShow",true);
//				request.setAttribute("dangerMessage","你选择的不是图片！");
//				request.setAttribute("ad",ad);
				ra.addFlashAttribute("isDangerShow",true);
				ra.addFlashAttribute("dangerMessage","你选择的不是图片！");
				ra.addFlashAttribute("ad",ad);
				return "redirect:/a/ad/addUI";
			}
			String ossKey = MyStringUtils.getUUID()+"."+ext;
			OssUtils.upload(ossKey,file.getInputStream());
			ad.setOssKey(ossKey);
			
			adService.saveAd(ad);
			ra.addFlashAttribute("isSuccessShow",true);
			ra.addFlashAttribute("successMessage",MyDateFormatUtils.getCurrTime()+"广告添加成功^_^");
			return "redirect:/a/ad/addUI";
		} else {
			return "admin/ad/add";
		}
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam("adIds")List<Integer> ads,HttpServletRequest request,RedirectAttributes ra) {
		adService.delete(ads);
//		if(isDeleteSuccess){
//			adService.findByIds(adIds);
//		}
		ra.addFlashAttribute("isSuccessShow",true);
		ra.addFlashAttribute("successMessage",MyDateFormatUtils.getCurrTime()+"广告删除成功^_^");
		return "redirect:/a/ad/adListUI";
	}
	
	/**
	 * 切换当前正在显示的广告
	 */
	@RequestMapping(value = "/show")
	public String show(@RequestParam("adIds")List<Integer> ads,HttpServletRequest request,RedirectAttributes ra) {
		List<Ad> showAds = adService.show(ads);
		cacheUtils.put("showAds", showAds);
		ra.addFlashAttribute("isSuccessShow",true);
		ra.addFlashAttribute("successMessage",MyDateFormatUtils.getCurrTime()+" 广告切换显示成功^_^");
		return "redirect:/a/ad/adListUI";
	}
}
