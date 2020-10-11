package com.cases.SPI;

import java.util.Iterator;
import java.util.ServiceLoader;

import sun.misc.Service;

/**
 * 测试类
 * @author wangjinlong
 * @datetime Oct 11, 2020 11:34:51 AM
 *
 */
public class AMainTest {
	public static void main(String[] args) {
		// resources/META-INF/services新建文件，文件名是接口全限定名，内容是实现类全限定名
		loadClassMethod1();
	}
	
	/**
	 * 加载方式1 ServiceLoader（推荐）
	 */
	public static void loadClassMethod1() {
		ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);

		Iterator<SPIService> iterator = load.iterator();
        while(iterator.hasNext()) {
            SPIService ser = iterator.next();
            ser.execute();
        }
	}
	
	/**
	 * 加载方式2 Service
	 */
	public static void loadClassMethod2() {
		Iterator<SPIService> providers = Service.providers(SPIService.class);
        while(providers.hasNext()) {
            SPIService ser = providers.next();
            ser.execute();
        }
	}
}
