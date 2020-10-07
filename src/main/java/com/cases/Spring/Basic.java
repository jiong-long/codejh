package com.cases.Spring;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Spring 基础相关类
 * 
 * @author wangjinlong
 * @createTime 2018年12月2日 下午1:32:00
 */
public class Basic {
	public static void main(String[] args) {

	}

	/**
	 * 获取classpath下的资源文件
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static InputStream readResource(String fileName) throws IOException {
		Resource resource = new ClassPathResource("applicationContext.xml");
		return resource.getInputStream();
	}
}
