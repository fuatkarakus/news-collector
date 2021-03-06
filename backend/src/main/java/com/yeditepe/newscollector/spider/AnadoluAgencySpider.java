package com.yeditepe.newscollector.spider;

import com.yeditepe.newscollector.domain.Feed;
import com.yeditepe.newscollector.domain.News;
import com.yeditepe.newscollector.util.JsoupUtil;
import com.yeditepe.newscollector.util.RssReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class AnadoluAgencySpider implements NewsSpider {

    private static final Logger log = LoggerFactory.getLogger(AnadoluAgencySpider.class);

    public static final String URL = "https://www.aa.com.tr/en/rss/default?cat=europe";
    public static final String CONTENT_QUERY = ".detay-icerik";
    public static final String AUTHOR_QUERY = "div.row:nth-child(6) > span:nth-child(1)";

    public Set<News> crawl() {

        Feed feed = RssReader.read(URL);

        Set<News> news = new HashSet<>(RssReader.fillNewsWithRSS(feed));

        news.forEach(i -> {
            try {
                String content =  JsoupUtil
                        .getContent(i.getLink(), CONTENT_QUERY);
                i.setContent(content);
            } catch (Exception e) {
                i.setContent( " " );
            }

            i.setAuthor(JsoupUtil.getElementByQuery(i.getLink() ,AUTHOR_QUERY).text());

        });
        log.debug("News Size {}", news.size());

        return news;
    }

    @Override
    public Set<News> get() {
        return crawl();
    }
}
