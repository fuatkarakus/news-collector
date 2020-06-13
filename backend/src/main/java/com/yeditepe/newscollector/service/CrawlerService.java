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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class CrawlerService {

    private static final Logger log = LoggerFactory.getLogger(CrawlerService.class);

    private final NewsService newsService;

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

        List<CompletableFuture<Set<News>>> futures = new ArrayList<>();

        spider.getComponents()
                .forEach( spi -> {
                    futures.add(CompletableFuture.supplyAsync(spi));
                    log.info("Spider Type: {}", spi.getClass().getSimpleName());
                });

        Set<News> result = futures.stream()
                .map(CompletableFuture::join)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(() -> newsService.saveAll(result));

    }

}
