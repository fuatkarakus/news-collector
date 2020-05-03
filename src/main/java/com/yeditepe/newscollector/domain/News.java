package com.yeditepe.newscollector.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Document(collection = "news")
public class News {

    @Id
    String link;

    @TextIndexed
    String publisher;

    @TextIndexed
    String title;

    @TextIndexed
    String content;

    String author;

    @Indexed
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Date date;

}
