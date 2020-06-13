package com.yeditepe.newscollector.spider;

import com.yeditepe.newscollector.domain.News;

import java.util.Set;
import java.util.function.Supplier;

public interface NewsSpider extends Supplier<Set<News>> {
}
