package com.yeditepe.newscollector.crawler;

import com.yeditepe.newscollector.domain.Feed;
import com.yeditepe.newscollector.domain.FeedMessage;
import com.yeditepe.newscollector.domain.News;
import com.yeditepe.newscollector.service.JsoupUtil;
import com.yeditepe.newscollector.service.RssReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class AnadoluAgencyCrawler extends NewsCrawler {

    private static final Logger log = LoggerFactory.getLogger(AnadoluAgencyCrawler.class);

    public static final String URL = "https://www.aa.com.tr/en/rss/default?cat=europe";
    public static final String CONTENT_QUERY = ".article_body";
    public static final String AUTHOR_QUERY = "div.row:nth-child(6) > span:nth-child(1)";

    RssReader rssReader;
    JsoupUtil jsoupUtil;

    public AnadoluAgencyCrawler(RssReader rssReader, JsoupUtil jsoupUtil) {
        this.rssReader = rssReader;
        this.jsoupUtil = jsoupUtil;
    }

    @Override
    public Set<News> crawl() {

        Set<News> news = new HashSet<>();

        Feed feed = rssReader.read(URL);

        for (FeedMessage feedMessage: feed.getEntries()) {

            String content =  jsoupUtil
                    .getContent(feedMessage.getLink(), CONTENT_QUERY);

            news.add(News.builder()
                    .publisher(feed.getTitle())
                    .title(feedMessage.getTitle())
                    .author(jsoupUtil.getElementByQuery(feedMessage.getLink() ,AUTHOR_QUERY).text())
                    .link(feedMessage.getLink())
                    .date(feedMessage.getDate())
                    .content(content)
                    .createdDate(new Date())
                    .build());

        }


        return null;
    }


}
