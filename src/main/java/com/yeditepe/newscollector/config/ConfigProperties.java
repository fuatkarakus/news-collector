package com.yeditepe.newscollector.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "news")
public class AppConfiguration {


    @V news.rss
    List<String> rss
    news.cssSelectors=div.date:nth-child(1)

}
