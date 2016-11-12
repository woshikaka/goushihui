package com.sfmy.gsh.utils;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class MyRegexUtils {
	/**
	 * 是否是整数(包括全体正整数、全体负整数和零)
	 */
	public static boolean isInteger(String str) {
		if(StringUtils.isBlank(str)){
			return false;
		}
//		return str.matches("[1-9]{1}[0-9]*|-[1-9]{1}[0-9]*|0");
		if(str.matches("[1-9]{1}[0-9]*")){
			return true;
		}
		if(str.matches("-[1-9]{1}[0-9]*")){
			return true;
		}
		if(str.matches("0")){
			return true;
		}
		return false;
	}
	
	/**
	 * 是否是正整数
	 */
	public static boolean isPositiveInteger(String str) {
		if(StringUtils.isBlank(str)){
			return false;
		}
		return str.matches("[1-9]{1}[0-9]*");
	}
	
	/**
	 * 是否是负整数
	 */
	public static boolean isMinusInteger(String str) {
		if(StringUtils.isBlank(str)){
			return false;
		}
		return str.matches("-[1-9]{1}[0-9]*");
	}
	
	/**
	 * 只能由数字或字母混合组成
	 */
	public static boolean isMixForLetterOrInteger(String str) {
		if(StringUtils.isBlank(str)){
			return false;
		}
		return str.matches("[0-9a-zA-Z]+");
	}
	
	/**
	 * 是否是纯数字
	 */
	public static boolean isNumeric(String str) {
		if(StringUtils.isBlank(str)){
			return false;
		}
		return str.matches("[0-9]+");
	}
	
	/**
	 * 是否是邮箱
	 */
	public static boolean isEmail(String str) {
		if(StringUtils.isBlank(str)){
			return false;
		}
		return str.matches("\\w+@\\w+(\\.\\w+)+");
	}
	
	public static boolean isDouble(String value) {
		Pattern pattern = Pattern.compile("^[-\\+]?\\d+\\.\\d+$");
		return pattern.matcher(value).matches();
	}
}
