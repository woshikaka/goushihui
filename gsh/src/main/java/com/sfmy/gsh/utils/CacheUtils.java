package com.sfmy.gsh.utils;

import javax.annotation.Resource;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;
@Component
public class CacheUtils {
	@Resource
	public EhCacheCacheManager cacheManager;
	
	public void put(String key,Object value){
		cacheManager.getCache("gshCache").put(key, value);
	}
	
	public <T> T get(String key,Class<T> clazz) {
		return cacheManager.getCache("gshCache").get(key,clazz);
	}
}
