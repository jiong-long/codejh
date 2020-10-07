package com.cases.WebCollector;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class AutoNewsCrawler extends BreadthCrawler {
	public static void main(String[] args) throws Exception {
		AutoNewsCrawler crawler = new AutoNewsCrawler("crawl", true);
		crawler.start(4);
	}

	public AutoNewsCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		this.addSeed("https://blog.github.com/");
		for (int pageIndex = 2; pageIndex <= 5; pageIndex++) {
			String seedUrl = String.format("https://blog.github.com/page/%d/", pageIndex);
			this.addSeed(seedUrl);
		}
		this.addRegex("https://blog.github.com/[0-9]{4}-[0-9]{2}-[0-9]{2}-[^/]+/");
		setThreads(50);
		getConf().setTopN(10);
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		String url = page.url();
		if (page.matchUrl("https://blog.github.com/[0-9]{4}-[0-9]{2}-[0-9]{2}[^/]+/")) {
			String title = page.select("h1[class=lh-condensed]").first().text();
			String content = page.select("div.content.markdown-body").text();

			System.out.println("URL:\n" + url);
			System.out.println("title:\n" + title);
			System.out.println("content:\n" + content);
			System.out.println("--------------------------------------------------");
		}
	}
}