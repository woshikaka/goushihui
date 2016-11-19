package com.sfmy.gsh.utils;

import java.io.StringWriter;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtils {
	static ObjectMapper mapper = new ObjectMapper();
	/**
	 * 集合转json
	 */
	public static String list2Json(List<?> list){
		try {
			String result = mapper.writeValueAsString(list);
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 对象转json
	 */
	public static String object2Json(Object obj){
		try {
			String result = mapper.writeValueAsString(obj);
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * json转对象
	 */
	public static <T> T json2Object(String json,Class<T> clazz){
		try {
			return mapper.readValue(json,clazz);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
	
	public static String obj2Json(Object obj){
		try {
			StringWriter sw = new StringWriter();
			mapper.writer().writeValue(sw, obj);
			return sw.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
	
	
}
