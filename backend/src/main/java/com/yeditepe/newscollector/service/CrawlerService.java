package com.yeditepe.newscollector.service;

import com.yeditepe.newscollector.domain.News;
import com.yeditepe.newscollector.spider.AnadoluAgencySpider;
import com.yeditepe.newscollector.spider.CompositeSpider;
import com.yeditepe.newscollector.spider.DailySabahSpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

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

        CompositeSpider spider = new CompositeSpider(
                new AnadoluAgencySpider(),
                new DailySabahSpider()
        );

        CompletableFuture<Set<News>> all = CompletableFuture.supplyAsync(() -> {
            log.info("Task execution started.");
            return spider.get();
        });

        CompletableFuture<Void> allOf = CompletableFuture.allOf(all);
        allOf.whenComplete((aVoid, throwable) -> {
           log.info("Completed allOf");
        });
        allOf.join();

    }
}
