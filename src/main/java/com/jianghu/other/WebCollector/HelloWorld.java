package com.jianghu.other.WebCollector;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * WebCollector 演示案例
 * 
 * @creatTime 2018年11月6日 下午9:50:52
 * @author jinlong
 */
public class HelloWorld extends BreadthCrawler {
	public static void main(String[] args) throws Exception {
		HelloWorld crawler = new HelloWorld("crawl", true);
		//线程数
		crawler.setThreads(50);
		//设置每次迭代爬取的网页数量上限
		crawler.getConf().setTopN(10);
		//开始爬取，迭代次数为depth
		crawler.start(4);
	}

	/**
	 * 
	 * @param crawlPath
	 * @param autoParse
	 *            是否根据设置的正则自动探测新URL
	 */
	public HelloWorld(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		//种子URL
		this.addSeed("http://news.hfut.edu.cn/list-1-1.html");
		//添加URL正则约束
		this.addRegex("http://news.hfut.edu.cn/show-.*html");
		this.addRegex("-.*\\.(jpg|png|gif).*");
		this.addRegex("-.*#.*");
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		String url = page.url();
		if (page.matchUrl("http://news.hfut.edu.cn/show-.*html")) {
			String title = page.select("div[id=Article]>h2").first().text();
			String content = page.select("div#artibody", 0).text();

			System.out.println("URL:\n" + url);
			System.out.println("title:\n" + title);
			System.out.println("content:\n" + content);
			System.out.println("--------------------------------------------------");
		}
	}
}
