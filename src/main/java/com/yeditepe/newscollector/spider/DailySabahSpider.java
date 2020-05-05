package com.yeditepe.newscollector.spider;

import com.yeditepe.newscollector.domain.Feed;
import com.yeditepe.newscollector.domain.News;
import com.yeditepe.newscollector.service.NewsService;
import com.yeditepe.newscollector.util.JsoupUtil;
import com.yeditepe.newscollector.util.RssReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DailySabahSpider extends NewsSpider {

    private static final Logger log = LoggerFactory.getLogger(DailySabahSpider.class);

    public static final String DOMAIN = "https://www.dailysabah.com";
    public static final String URL = "https://www.dailysabah.com/rss";
    public static final String RSS_PREFIX = "rssFeed";
    public static final String CONTENT_QUERY = ".article_body";

    private NewsService newsService;

    @Autowired
    public DailySabahSpider(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public void crawl() {
        Set<News> news = new HashSet<>();
        List<String> rssLinks = new ArrayList<>();
        try {
            rssLinks.addAll(JsoupUtil.getRssLinksFromGivenUrl(URL, RSS_PREFIX));

        } catch (IOException e) {
            log.error(" Cannot connect url:  {}", e.getMessage() );
        }

        if (rssLinks.stream().noneMatch(i -> i.contains(DOMAIN))) {

            rssLinks.stream().map(i -> DOMAIN + i).forEach( link -> news.addAll(crawlNews(link)));

        }else {
            rssLinks.forEach( link -> news.addAll(crawlNews(link)));
        }

        log.debug("News Size {}", news.size());
    }


    public List<News> crawlNews ( String rss ) {
        Feed feed =  RssReader.read(rss);

        List<News> news = new ArrayList<>(RssReader.fillNewsWithRSS(feed));

        news.forEach(i -> {
            String content =  JsoupUtil
                    .getContent(i.getLink(), CONTENT_QUERY);

            i.setContent(content);
            newsService.insert(i);
        });

        return news;
    }


}
