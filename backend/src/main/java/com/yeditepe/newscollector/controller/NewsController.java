package com.yeditepe.newscollector.controller;

import com.yeditepe.newscollector.domain.News;
import com.yeditepe.newscollector.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/news")
@CrossOrigin
public class NewsController {

    private final NewsService newsService;

    @Autowired
    NewsController (NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public List<News> getAllNews()  {
        return newsService.getAllNews();
    }

    @GetMapping(value = "/latest")
    public List<News> getLast10News()  {
        return newsService.getLast10News();
    }

    @GetMapping(value = "/{key}")
    public List<News> getAllByText(@PathVariable String key)  {
        return newsService.getAllByText(key);
    }

    @GetMapping(value = "/link/{link}")
    public List<News> getLinkLike(@PathVariable String key)  {
        return newsService.getByLinkLike(key);
    }


}
