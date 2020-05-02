package com.yeditepe.newscollector.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HtmlParser {

    private static final Logger log = LoggerFactory.getLogger(HtmlParser.class);

    public Elements query() {
        Elements select = null;
        try {
            select = Jsoup.connect("https://www.bbc.com/turkce/haberler-dunya-52511235")
                    .get()
                    .select("div.date:nth-child(1)");
        } catch (IOException e) {
            log.error(" {}", e.getMessage());
        }
        return select;
    }



}
