package com.yeditepe.newscollector.repository;

import com.yeditepe.newscollector.domain.News;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends MongoRepository<News, String> {

    List<News> findTop10ByOrderByDateDesc();

    Optional<News> findByLink(String link);

    List<News> findByTitleLike(String link);

    List<News> findByLinkLike(String link);

    List<News> findByContentLike(String link);

    List<News> findAllBy(TextCriteria criteria);

    List<News> findByDescriptionLike(String description);

}
