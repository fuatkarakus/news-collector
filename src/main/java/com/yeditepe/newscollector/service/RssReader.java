package com.yeditepe.newscollector.service;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class RssReader {

    private static final Logger log = LoggerFactory.getLogger(RssReader.class);

    public SyndFeed feed(){
        URL feedSource = null;
        try {
            feedSource = new URL("http://feeds.bbci.co.uk/turkce/rss.xml");
        } catch (MalformedURLException e) {
            log.error("malformed URL has occurred {}", e.getMessage());
        }
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = null;
        try {
            assert feedSource != null;
            feed = input.build(new XmlReader(feedSource));
        } catch (FeedException | IOException e) {
            log.error("malformed URL has occurred {}", e.getMessage());
        }
        return feed;
    }
}
