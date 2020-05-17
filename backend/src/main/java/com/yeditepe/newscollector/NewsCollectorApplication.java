package com.yeditepe.newscollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NewsCollectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsCollectorApplication.class, args);
    }

}
