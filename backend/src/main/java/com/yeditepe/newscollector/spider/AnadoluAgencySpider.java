package com.yeditepe.newscollector.spider;

import com.yeditepe.newscollector.domain.Feed;
import com.yeditepe.newscollector.domain.News;
import com.yeditepe.newscollector.service.NewsService;
import com.yeditepe.newscollector.util.JsoupUtil;
import com.yeditepe.newscollector.util.RssReader;

import java.util.HashSet;
import java.util.Set;

public class AnadoluAgencySpider extends NewsSpider {

    public static final String URL = "https://www.aa.com.tr/en/rss/default?cat=europe";
    public static final String CONTENT_QUERY = ".detay-icerik";
    public static final String AUTHOR_QUERY = "div.row:nth-child(6) > span:nth-child(1)";

    private final NewsService newsService;

    public AnadoluAgencySpider(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public void crawl() {

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

            newsService.insert(i);
        });
        log().debug("News Size {}", news.size());
    }


}
