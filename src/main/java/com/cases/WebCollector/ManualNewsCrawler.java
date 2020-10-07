package com.cases.WebCollector;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class ManualNewsCrawler extends BreadthCrawler {
	public static void main(String[] args) throws Exception {
		ManualNewsCrawler crawler = new ManualNewsCrawler("crawl", false);
		crawler.getConf().setExecuteInterval(5000);
		crawler.getConf().set("title_prefix", "PREFIX_");
		crawler.getConf().set("content_length_limit", 20);

		crawler.start(4);
	}

	public ManualNewsCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		this.addSeedAndReturn("https://blog.github.com/").type("list");
		for (int pageIndex = 2; pageIndex <= 5; pageIndex++) {
			String seedUrl = String.format("https://blog.github.com/page/%d/", pageIndex);
			this.addSeed(seedUrl, "list");
		}
		setThreads(50);
		getConf().setTopN(100);
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		String url = page.url();

		if (page.matchType("list")) {
			next.add(page.links("h1.lh-condensed>a")).type("content");
		} else if (page.matchType("content")) {
			String title = page.select("h1[class=lh-condensed]").first().text();
			String content = page.select("div.content.markdown-body").text();

			title = getConf().getString("title_prefix") + title;
			content = content.substring(0, getConf().getInteger("content_length_limit"));

			System.out.println("URL:\n" + url);
			System.out.println("title:\n" + title);
			System.out.println("content:\n" + content);
			System.out.println("--------------------------------------------------");
		}

	}
}