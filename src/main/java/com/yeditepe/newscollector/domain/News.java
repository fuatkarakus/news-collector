package com.yeditepe.newscollector.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Document(collection = "news")
public class News {

    @Id
    @NotNull
    String link;

    @Indexed
    String publisher;

    @TextIndexed
    @NotNull
    String title;

    @TextIndexed
    String description;

    @TextIndexed
    String content;

    @Indexed
    String author;

    @Indexed
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Date date;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    Date createdDate;

}
