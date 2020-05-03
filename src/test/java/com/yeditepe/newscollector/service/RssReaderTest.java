package com.yeditepe.newscollector.service;

import com.yeditepe.newscollector.domain.FeedMessage;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


class RssReaderTest {

    private static final Logger log = LoggerFactory.getLogger(RssReaderTest.class);

    @Test
    void feed() {
        RssReader rssReader = new RssReader();

        log.info(rssReader.readFeed().toString());

    }

    @Test
    void entries() {
        RssReader rssReader = new RssReader();
        List<FeedMessage> list =  rssReader.readEntries(rssReader.readFeed());
        log.info("" + list);

    }
}
