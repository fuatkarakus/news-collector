package com.yeditepe.newscollector.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feed feed = (Feed) o;
        return pubDate.equals(feed.pubDate) &&
                entries.equals(feed.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pubDate, entries);
    }
}
