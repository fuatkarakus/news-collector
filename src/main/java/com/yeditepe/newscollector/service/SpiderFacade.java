package com.yeditepe.newscollector.service;

import com.yeditepe.newscollector.spider.AnadoluAgencySpider;
import com.yeditepe.newscollector.spider.DailySabahSpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SpiderFacade {

    private static final Logger log = LoggerFactory.getLogger(SpiderFacade.class);

    AnadoluAgencySpider anadoluAgencySpider;
    DailySabahSpider dailySabahSpider;

    public SpiderFacade(AnadoluAgencySpider anadoluAgencySpider, DailySabahSpider dailySabahSpider) {
        this.anadoluAgencySpider = anadoluAgencySpider;
        this.dailySabahSpider = dailySabahSpider;
    }

    public void startCollectingNews() {
        log.debug("executorService initialize...");
        //  dailySabahSpider.crawl();
        anadoluAgencySpider.crawl();
    }

}
