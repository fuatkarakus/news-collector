package com.yeditepe.newscollector.service;

import com.yeditepe.newscollector.spider.DailySabahSpider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class NewsSpiderTest {

    private DailySabahSpider dailySabahSpider;
    NewsService newsService;

    @BeforeEach
    void setup() {
        newsService = mock(NewsService.class);
        dailySabahSpider = new DailySabahSpider();
    }

    @Test
    void test() {
       assertNotNull(dailySabahSpider);
    }
}
