package com.yeditepe.newscollector.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "news")
@Data
public class ConfigProperties {

    String rss;
    String cssSelectors;

}
