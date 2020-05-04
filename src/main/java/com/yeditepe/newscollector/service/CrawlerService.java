package com.yeditepe.newscollector.service;

import com.yeditepe.newscollector.domain.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void runCrawlers() throws IOException {
        log.info("Starting crawlers... ");

        Set<News> news = spiderFacade.startCollectingNews();

        log.debug("Total Collected News Size: {}", news.size());
        news.forEach(i -> newsService.save(i));
    }
}
