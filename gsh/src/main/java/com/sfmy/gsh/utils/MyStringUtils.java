package com.sfmy.gsh.utils;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.sfmy.gsh.entity.Order;
import com.sfmy.gsh.entity.OrderItem;

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
	
	public static String buildSubject(Order order) {
		if (Objects.isNull(order)) {
			return null;
		}

		StringBuilder subject = new StringBuilder();
		List<OrderItem> items = order.getItems();
		if (CollectionUtils.isNotEmpty(items)) {
			if (CollectionUtils.size(items) == 1) {
				subject.append(items.get(0).getProduct().getName());
			}else{
				subject.append(items.get(0).getProduct().getName()).append("等多件商品");
			}
		}
		
		return subject.toString();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {
			System.out.println(generateOutTradeNo());
			
		}
	}
}
