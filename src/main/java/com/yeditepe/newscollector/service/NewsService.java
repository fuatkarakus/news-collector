package com.yeditepe.newscollector.service;

import com.yeditepe.newscollector.domain.News;
import com.yeditepe.newscollector.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getLast10News(){
        return newsRepository.findTop10ByOrderByDateDesc();
    }

}
