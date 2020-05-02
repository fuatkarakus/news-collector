package com.yeditepe.newscollector.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;


class RssReaderTest {

    private static final Logger log = LoggerFactory.getLogger(RssReaderTest.class);

    @Test
    void feed() {
        RssReader rssReader = new RssReader();

        log.info(rssReader.feed().toString());

    }
}
