package com.yeditepe.newscollector.spider;

import com.yeditepe.newscollector.domain.News;

import java.util.HashSet;
import java.util.Set;

public abstract class NewsSpider {

    public Set<News> crawl() {
        return new HashSet<>();
    }

}
