package com.yeditepe.newscollector.domain;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
public class FeedMessage {

    private String title;
    private String description;
    private String link;
    private String author;
    private Date date;
    private String category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedMessage message = (FeedMessage) o;
        return link.equals(message.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }

}
