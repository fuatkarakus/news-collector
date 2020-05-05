package com.yeditepe.newscollector.service;

import com.yeditepe.newscollector.domain.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CrawlerService {

    private static final Logger log = LoggerFactory.getLogger(CrawlerService.class);

    SpiderFacade spiderFacade;

    @Autowired
    public CrawlerService(SpiderFacade spiderFacade) {
        this.spiderFacade = spiderFacade;
    }

    //@Scheduled(cron = "${cron.expression}")
    @EventListener(ApplicationReadyEvent.class)
    public void runCrawlers() {
        log.info("Starting crawlers... ");
        spiderFacade.startCollectingNews();
    }
}
