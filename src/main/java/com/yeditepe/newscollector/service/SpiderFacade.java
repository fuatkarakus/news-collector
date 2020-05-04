package com.yeditepe.newscollector.service;

import com.yeditepe.newscollector.domain.News;
import com.yeditepe.newscollector.spider.AnadoluAgencySpider;
import com.yeditepe.newscollector.spider.DailySabahSpider;
import com.yeditepe.newscollector.spider.NewsSpider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpiderFacade {

    private final List<NewsSpider> crawlers;

    public SpiderFacade() {
        this.crawlers = List.of(
                new AnadoluAgencySpider(),
                new DailySabahSpider());
    }

    public Set<News> startCollectingNews() {

        Set<News> news = new HashSet<>();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(() -> {
            crawlers.forEach( i ->  news.addAll(i.crawl()));
        });

        return news;
    }

}
