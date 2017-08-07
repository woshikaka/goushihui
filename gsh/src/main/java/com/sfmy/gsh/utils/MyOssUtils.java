package com.sfmy.gsh.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;


@Component
public class MyOssUtils /*implements ApplicationContextAware*/ /*ServletContextAware*/{
	
//	private static ServletContext servletContext;
	
//	private static ApplicationContext applicationContext;
	
	public static String upload(String filename,byte[] fileByte) {
		String fileKey = makeFileKey(filename);
		String saveDir = getUploadPath();
		try {
			FileUtils.writeByteArrayToFile(new File(saveDir,fileKey),fileByte);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return fileKey;
	}
	
	// 利用hash算法把文件存在硬盘上
	private static String makeFileKey(String filename) {
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf; // 0--15
		int dir2 = (hashcode & 0xf0) >> 4; // 0-15
		String fileKey = "\\" + dir1 + "\\" + dir2+"\\"+filename;  //  \2\3
		return fileKey;
	}

	
	public static void delete(String fileKey) {
		File file = new File(getUploadPath(),fileKey);
		file.delete();
	}
	
	private static String getUploadPath() {
		String tomcatHome = System.getProperty("catalina.base")+"\\webapps";
		String dir = tomcatHome+"\\"+"upload";
		
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		return dir;
	}
}
