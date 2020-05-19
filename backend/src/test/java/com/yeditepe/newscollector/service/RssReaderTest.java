package com.yeditepe.newscollector.service;

import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.yeditepe.newscollector.domain.FeedMessage;
import com.yeditepe.newscollector.util.RssReader;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class RssReaderTest {

    private static final Logger log = LoggerFactory.getLogger(RssReaderTest.class);


    @Test
    void feed() {
        URL feedSource;
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = null;
        try {
            feedSource = new URL("https://www.dailysabah.com/rssFeed/10/1");
            feed = input.build(new XmlReader(feedSource));
        } catch (FeedException | IOException e) {
            log.error("FeedException has occurred {}", e.getMessage());
        }

        log.info(feed.toString());

    }

    @Test
    void entries() {

        List<FeedMessage> list =  RssReader.read("https://www.dailysabah.com/rssFeed/10/1").getEntries();
        log.info("" + list);

    }

    @Test
    void shouldReadDateFromRssEntry() {
        URL feedSource;
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = null;
        try {
            feedSource = new URL("https://www.dailysabah.com/rssFeed/10/1");
            feed = input.build(new XmlReader(feedSource));
        } catch (FeedException | IOException e) {
            log.error("FeedException has occurred {}", e.getMessage());
        }

        List<String> stringsList = new ArrayList<>(SyndEntryImpl.CONVENIENCE_PROPERTIES);

        log.info(stringsList.toString());
        assertNotNull(feed.getPublishedDate());

    }
}
