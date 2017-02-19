package com.sfmy.gsh.utils;

import java.util.Calendar;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateFormatUtils;

public class MyDateFormatUtils {
	static{
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+08"));//东八区
	}
	private final static String DATE_FORMAT = "yyyy-MM-dd";
	private final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
    public static String getCurrDate() {  
    	Calendar calendar = Calendar.getInstance();
        return DateFormatUtils.format(calendar, DATE_FORMAT);  
    } 

	public static String getCurrTime() {
		Calendar calendar = Calendar.getInstance();
		return DateFormatUtils.format(calendar, TIME_FORMAT);
	}
	
	
}
