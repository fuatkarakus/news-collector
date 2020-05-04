package com.yeditepe.newscollector.service;

import com.yeditepe.newscollector.domain.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CrawlerService {

    private static final Logger log = LoggerFactory.getLogger(CrawlerService.class);

    SpiderFacade spiderFacade;
    NewsService newsService;

    @Autowired
    public CrawlerService(NewsService newsService) {
        this.spiderFacade = new SpiderFacade();
        this.newsService = newsService;
    }

    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void runCrawlers() {
        log.info("Starting crawlers... ");

        Set<News> news = spiderFacade.startCollectingNews();

        log.debug("Total Collected News Size: {}", news.size());
        news.forEach(i -> newsService.insert(i));
    }
}
