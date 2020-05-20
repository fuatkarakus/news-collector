package com.yeditepe.newscollector.spider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NewsSpider {

    public void crawl() {
    }

    protected Logger log() { return LoggerFactory.getLogger(getClass()); }
}
