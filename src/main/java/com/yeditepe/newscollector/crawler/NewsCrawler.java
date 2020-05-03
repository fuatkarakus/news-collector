package com.yeditepe.newscollector.crawler;

import com.yeditepe.newscollector.domain.News;

import java.util.Set;

public abstract class NewsCrawler {

    abstract Set<News> crawl();


}
