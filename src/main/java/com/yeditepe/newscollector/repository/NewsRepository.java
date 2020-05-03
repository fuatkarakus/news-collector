package com.yeditepe.newscollector.repository;

import com.yeditepe.newscollector.domain.News;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends MongoRepository<News, String> {

    List<News> findTop10ByOrderByDateDesc();

}
