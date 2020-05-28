package com.yeditepe.newscollector.spider;

import com.yeditepe.newscollector.domain.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class CompositeSpider implements NewsSpider {

    private static final Logger log = LoggerFactory.getLogger(CompositeSpider.class);

    List<Supplier<Set<News>>> components;

    @SafeVarargs
    public CompositeSpider(Supplier<Set<News>>... components) {
        this.components = Arrays.asList(components);
    }

    @Override
    public Set<News> get() {
        Set<News> news = new HashSet<>();
        components.forEach(i -> news.addAll(i.get()));
        log.info("Composite News Size {}", news.size());
        return news;
    }

}
