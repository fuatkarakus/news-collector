package com.yeditepe.newscollector.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.yeditepe.newscollector.config.ConfigProperties;
import com.yeditepe.newscollector.domain.Feed;
import com.yeditepe.newscollector.domain.FeedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class RssReader {

    private static final Logger log = LoggerFactory.getLogger(RssReader.class);

    private ConfigProperties config;

    @Autowired
    public RssReader(ConfigProperties config) {
        this.config = config;
    }

    public SyndFeed readFeed(){
        URL feedSource;
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = null;
        try {
            feedSource = new URL(config.getRss());
            feed = input.build(new XmlReader(feedSource));
        } catch (FeedException | IOException e) {
            log.error("FeedException has occurred {}", e.getMessage());
        }

        return feed;
    }

    public Feed convertSyndFeed(SyndFeed syndFeed) {

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

    public List<FeedMessage> readEntries(SyndFeed feed){

        List<FeedMessage> feedMessages = new ArrayList<>();

        for (SyndEntry entry : feed.getEntries()) {

            FeedMessage message = new FeedMessage();

            message.setTitle(entry.getTitle());
            message.setDescription(entry.getDescription().getValue());
            message.setAuthor(entry.getAuthor());
            message.setLink(entry.getLink());
            message.setDate(entry.getPublishedDate());

            feedMessages.add(message);
        }

        return feedMessages;
    }
}
