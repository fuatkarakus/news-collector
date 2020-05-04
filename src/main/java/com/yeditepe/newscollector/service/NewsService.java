package com.yeditepe.newscollector.service;

import com.yeditepe.newscollector.domain.News;
import com.yeditepe.newscollector.repository.NewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class NewsService {

    private static final Logger log = LoggerFactory.getLogger(NewsService.class);

    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getLast10News(){
        return newsRepository.findTop10ByOrderByDateDesc();
    }

    public void insert(final News news){
        if (newsRepository.findByLink(news.getLink()).isEmpty()) {
            newsRepository.save(news);
        }
    }

    public List<News> saveAll(final List<News> news){
        return newsRepository.saveAll(news);
    }

    public News getByLink(String link) {
        return newsRepository
                .findByLink(link)
                .orElseGet(News::new);
    }

    public List<News> getByTitleLike(String title) {
        return newsRepository.findByTitleLike(title);
    }

    public List<News> getByLinkLike(String title) {
        return newsRepository.findByLinkLike(title);
    }

    public List<News> getByContentLike(String title) {
        return newsRepository.findByContentLike(title);
    }

    public List<News> getByDescriptionLike(String title) {
        return newsRepository.findByDescriptionLike(title);
    }

    public Set<String> getPublishers() {
        return newsRepository.findDistinctPublisher();
    }

    public List<News> getAllByText(String text) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(text);
        return newsRepository.findAllBy(criteria);
    }



}
