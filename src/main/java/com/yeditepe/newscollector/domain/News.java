package com.yeditepe.newscollector.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "news")
@Builder
public class News extends RepresentationModel<News> {

    @Id
    Long id;

    String link;

    // newspaper
    @Indexed
    String publisher;

    @TextIndexed
    String title;

    @TextIndexed
    String content;

    // writer if any
    String author;

    // published date if any
    @Indexed
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Date date;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Date createdDate;

}
