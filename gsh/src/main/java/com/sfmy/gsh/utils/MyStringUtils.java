package com.sfmy.gsh.utils;

import java.util.Calendar;
import java.util.UUID;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class MyStringUtils {
	public static boolean isPicExt(String ext){
		if(StringUtils.isBlank(ext)){
			return false;
		}
		
		String[] imgeExt = {"bmp","gif","jpe","jpeg","jpg","png","tiff","ico","dib","jfif","tif",};
		return ArrayUtils.contains(imgeExt,ext.toLowerCase());
	}
	
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-","");
	}
	
	/**
	 * 生成商户订单号
	 */
	public static String generateOutTradeNo() {
		String random = RandomStringUtils.random(3,"abcdefghijklmnopqrstuvwxyz");
		Calendar calendar = Calendar.getInstance();
		String currTimeStr = DateFormatUtils.format(calendar,"yyyyMMddHHmmssSSS");
		return random+currTimeStr;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {
			System.out.println(generateOutTradeNo());
			
		}
	}
}
