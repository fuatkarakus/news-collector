package com.yeditepe.newscollector.domain;

import java.util.ArrayList;
import java.util.List;

public class Feed {

    final String title;
    final String link;
    final String description;
    final String language;
    final String copyright;
    final String pubDate;

    final List<FeedMessage> entries = new ArrayList<>();

    public Feed(String title, String link, String description, String language,
                String copyright, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.language = language;
        this.copyright = copyright;
        this.pubDate = pubDate;
    }
}
