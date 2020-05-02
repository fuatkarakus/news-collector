package com.yeditepe.newscollector.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class HtmlParserTest {

    private static final Logger log = LoggerFactory.getLogger(HtmlParserTest.class);

    @Test
    void query() {
        HtmlParser parser = new HtmlParser();

        log.info(parser.query().text());
        
    }
}
