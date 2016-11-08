package com.sfmy.gsh.utils;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aliyun.oss.OSSClient;
@Component
public class OssUtils {
	static String  endpoint = "http://oss-cn-hangzhou.aliyuncs.com";

	private static String accessKeyId = "LTAI7dafNiLiE8rp";

	private static String accessKeySecret = "MhhiXpcZpTW3Kq1reev7VgzE3YrtQg";

	private static OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

	private static String bucketName;
	
	@Value("${bucket.name}")
	public void setBucketName(String bucketName) {
		OssUtils.bucketName = bucketName;
	}

	public static void upload(String key,InputStream input) {
		//String content = "Hello OSS test";
		// new ByteArrayInputStream(content.getBytes()
		ossClient.putObject(bucketName,key,input);
//		ossClient.shutdown();
	}
	public static void delete(String key) {
		ossClient.deleteObject(bucketName,key);
	}
}
