package com.yeditepe.newscollector.domain;

import lombok.Data;

import java.util.Date;

@Data
public class FeedMessage {

    private String title;
    private String description;
    private String link;
    private String author;
    private Date date;

}
