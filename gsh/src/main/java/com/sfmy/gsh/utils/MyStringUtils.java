package com.sfmy.gsh.utils;

import java.util.UUID;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

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
}
