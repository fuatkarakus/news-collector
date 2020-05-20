package com.yeditepe.newscollector.service;

import com.yeditepe.newscollector.spider.AnadoluAgencySpider;
import com.yeditepe.newscollector.spider.DailySabahSpider;
import com.yeditepe.newscollector.spider.NewsSpider;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

@Getter
public class SpiderFacade {

    private static final Logger log = LoggerFactory.getLogger(SpiderFacade.class);

    private final List<NewsSpider> spiderList;

    public SpiderFacade(NewsService newsService) {
        spiderList = Arrays.asList(
                new AnadoluAgencySpider(newsService),
                new DailySabahSpider(newsService)
        );
    }

    public void startCollectingNews() {
        log.debug("executorService initialize...");
        spiderList.forEach(NewsSpider::crawl);
    }

}
