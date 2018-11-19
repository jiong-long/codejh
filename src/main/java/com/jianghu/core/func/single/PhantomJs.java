package com.jianghu.core.func.single;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 利用phantomjs工具实现网页截图
 * 
 * @creatTime 2018年11月18日 下午10:39:26
 * @author jinlong
 */
public class PhantomJs {

	public static void main(String[] args) throws IOException {
		String BLANK = "  ";
		Process process = Runtime.getRuntime().exec("E:/phantomjs/bin/phantomjs.exe" + BLANK //你的phantomjs.exe路径
				+ "E:/eclipse/codejh/src/main/webapp/js/single/phantomjs.js" + BLANK //就是上文中那段javascript脚本的存放路径
				+ "http://localhost:8080/jiong/page/echarts/echarts.html" + BLANK //你的目标url地址
				+ "C:/Users/jinlong/Desktop/111.png");//你的图片输出路径

		InputStream inputStream = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		System.out.println(reader.readLine());

		if (reader != null) {
			reader.close();
		}
		if (process != null) {
			process.destroy();
			process = null;

			System.out.println("渲染成功...");
		}
	}
}
