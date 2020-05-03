package com.yeditepe.newscollector.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsoupUtil {

    private static final Logger log = LoggerFactory.getLogger(JsoupUtil.class);

    public Elements getElementByQuery(String newsUrl, String cssQuery) {
        Elements select = null;
        try {
            select = Jsoup.connect(newsUrl)
                    .get()
                    .select(cssQuery);
        } catch (IOException e) {
            log.error("Connection error: {}", e.getMessage());
        }
        return select;
    }

    public String getContent(String newsUrl, String cssQuery){
        Elements elements = getElementByQuery(newsUrl, cssQuery);

        Elements p = elements.first().getElementsByTag("p");

        StringBuilder content= new StringBuilder();
        for (Element x: p) {
            // added for -> www.aa.com.tr
            if (!x.getElementsByTag("strong").isEmpty()){
                content.append(x.text()).append(" ");
            }else {
                content.append(x.text());
            }
        }

        return content.toString();
    }

    public List<String> getRssLinksFromGivenUrl(String url, String mustContain) throws IOException {

        List<String> rssLinks = new ArrayList<>();

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");

        log.debug("Links: {}", links.size());
        for (Element link : links) {

            String linkStr =  link.attr("href");

            if(linkStr.contains(mustContain))
                rssLinks.add(linkStr);

        }
        return rssLinks;
    }

}
