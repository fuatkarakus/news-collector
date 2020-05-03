package com.yeditepe.newscollector.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feed {

    private String title;
    private String link;
    private String description;
    private String language;
    private String copyright;
    private Date pubDate;

    private List<FeedMessage> entries = new ArrayList<>();
}
