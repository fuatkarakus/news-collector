package com.yeditepe.newscollector;

import com.yeditepe.newscollector.config.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class NewsCollectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsCollectorApplication.class, args);
    }

}
