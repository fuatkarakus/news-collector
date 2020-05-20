package com.yeditepe.newscollector.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class CrawlerService {

    private static final Logger log = LoggerFactory.getLogger(CrawlerService.class);

    NewsService newsService;

    public CrawlerService(NewsService newsService) {
        this.newsService = newsService;
    }

    //@Scheduled(cron = "${cron.expression}")
    @EventListener(ApplicationReadyEvent.class)
    public void runCrawlers() {
        log.info("Starting crawlers... ");
        SpiderFacade spiderFacade = new SpiderFacade(newsService);
        spiderFacade.startCollectingNews();
    }
}
