package com.yeditepe.newscollector.util;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.yeditepe.newscollector.domain.Feed;
import com.yeditepe.newscollector.domain.FeedMessage;
import com.yeditepe.newscollector.domain.News;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RssReader {

    private static final Logger log = LoggerFactory.getLogger(RssReader.class);

    private RssReader() { }

    public static Feed read(String url){
        URL feedSource;
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = new SyndFeedImpl();
        try {
            feedSource = new URL(url);
            feed = input.build(new XmlReader(feedSource));
        } catch (FeedException | IOException e) {
            log.error("FeedException has occurred {}", e.getMessage());
        }

        return convertSyndFeed(feed);
    }

    public static List<News> fillNewsWithRSS(@NotNull Feed feed ) {

        List<News> newsSet = new ArrayList<>();

        for (FeedMessage feedMessage: feed.getEntries()) {

            News news = new News();
            news.setPublisher(feed.getTitle());
            news.setLink(feedMessage.getLink());
            news.setTitle(feedMessage.getTitle());
            news.setDescription(feedMessage.getDescription());
            news.setDate( (feedMessage.getDate() == null) ? feed.getPubDate() : feedMessage.getDate());
            news.setAuthor(feedMessage.getAuthor());
            news.setCreatedDate(new Date());

            newsSet.add(news);

        }
        return newsSet;
    }

    public static Feed convertSyndFeed(@NonNull SyndFeed syndFeed) {

        final Feed feed = new Feed();

        feed.setTitle(syndFeed.getTitle());
        feed.setDescription(syndFeed.getDescription());
        feed.setLink(syndFeed.getLink());
        feed.setPubDate(syndFeed.getPublishedDate());
        feed.setCopyright(syndFeed.getCopyright());
        feed.setLanguage(syndFeed.getLanguage());

        feed.setEntries(readEntries(syndFeed));

        return feed;

    }

    public static List<FeedMessage> readEntries(@NonNull SyndFeed feed){

        List<FeedMessage> feedMessages = new ArrayList<>();

        for (SyndEntry entry : feed.getEntries()) {

            FeedMessage message = new FeedMessage();

            message.setTitle(entry.getTitle());
            message.setDescription(entry.getDescription().getValue());
            message.setAuthor(entry.getAuthor());
            message.setLink(entry.getLink());
            message.setDate(entry.getPublishedDate());
            //message.setCategory(entry.getCategories().get(0).getName());
            feedMessages.add(message);

        }

        return feedMessages;
    }
}
