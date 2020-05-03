package com.yeditepe.newscollector.crawler;

import com.yeditepe.newscollector.domain.Feed;
import com.yeditepe.newscollector.domain.FeedMessage;
import com.yeditepe.newscollector.domain.News;
import com.yeditepe.newscollector.service.JsoupUtil;
import com.yeditepe.newscollector.service.RssReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public class DailySabahCrawler extends NewsCrawler {

    private static final Logger log = LoggerFactory.getLogger(DailySabahCrawler.class);

    public static final String DOMAIN = "https://www.dailysabah.com";
    public static final String URL = "https://www.dailysabah.com/rss";
    public static final String RSS_PREFIX = "rssFeed";
    public static final String CONTENT_QUERY = ".article_body";

    RssReader rssReader;
    JsoupUtil jsoupUtil;

    public DailySabahCrawler(RssReader rssReader, JsoupUtil jsoupUtil) {
        this.rssReader = rssReader;
        this.jsoupUtil = jsoupUtil;
    }

    @Override
    public Set<News> crawl() {

        Set<News> news = new HashSet<>();

        List<String> rssLinks = new ArrayList<>();
        try {
            rssLinks.addAll(jsoupUtil.getRssLinksFromGivenUrl(URL, RSS_PREFIX));

        } catch (IOException e) {
            log.error(" Cannot connect url:  {}", e.getMessage() );
        }

        if (rssLinks.stream().noneMatch(i -> i.contains(DOMAIN))) {

            rssLinks.stream().map(i -> DOMAIN + i).forEach( link -> news.addAll(crawlNews(link)));

        }else {

            rssLinks.forEach( link -> news.addAll(crawlNews(link)));

        }

        return news;
    }

    public List<News> crawlNews ( String rss ) {

        List<News> news = new ArrayList<>();

        Feed feed =  rssReader.read(rss);

        for (FeedMessage feedMessage: feed.getEntries() ) {

            String content =  jsoupUtil
                    .getContent(feedMessage.getLink(), CONTENT_QUERY);

            news.add(News.builder()
                    .publisher(feed.getTitle())
                    .title(feedMessage.getTitle())
                    .author(feedMessage.getAuthor())
                    .link(feedMessage.getLink())
                    .date(feedMessage.getDate())
                    .content(content)
                    .createdDate(new Date())
                    .build());
        }

        return news;
    }


}
