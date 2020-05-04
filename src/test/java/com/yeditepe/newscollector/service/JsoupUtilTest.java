package com.yeditepe.newscollector.service;

import com.yeditepe.newscollector.spider.DailySabahSpider;
import com.yeditepe.newscollector.util.JsoupUtil;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class JsoupUtilTest {

    private static final Logger log = LoggerFactory.getLogger(JsoupUtilTest.class);

    @Test
    void getElementByQuery() {

        Elements elements =  JsoupUtil.getElementByQuery("https://www.dailysabah.com/turkey/istanbul/istanbul-trial-pits-church-against-italian-royal-heirs",
                ".article_body");

        String firstP = "St. Anthony of Padua, the largest Roman Catholic church in Istanbul, is nowadays entangled in a legal battle over its ownership. Descendants of Italian royalty seek the property rights for the church in the city’s Beyoğlu district while the church claims they are not entitled to rights due to the status of the church as a religious site. In a hearing Friday, a court in the city rejected the heirs’ demand to block the provisional injunction the church obtained from the court. The Vatican, meanwhile, sought to join the trial as a plaintiff via its embassy in Ankara. “You can’t measure the value of a century-old Catholic Church with municipal laws. This is an insult to the Catholic community and Vatican,” the church’s lawyer Afşin Hatipoğlu said. At the heart of the trial is a Turkish lawyer accused of fraud. Sebahattin Gök, an Istanbul-based realtor, reached out to the heirs of the Italian royal family in Italy, the U.S. and France whose names were registered as the owners of church properties. He managed to convince them to sell the church, two six-story apartment buildings and four buildings used as offices that were registered as church properties. The church countered with a lawsuit in 2016 to clarify the status of the church and block the sale. The lawyers for St. Anthony of Padua say it was a legal practice back in 1937, when the church was registered as property, to register the church’s ownership to Italian royal family members. Royal family’s heirs withdrew their rights in 1971 while the heirs took the matter to the land registration office of Beyoğlu in 2016, seeking the sale of the properties. Speaking to reporters after Friday’s hearing, Hatipoğlu said the court ruled in favor of the church, though the legal process will continue. “This was what we expected. We now have the legal assurance,” he said. Located on the famous İstiklal Avenue in Beyoğlu, the church was built by the Italian community of Istanbul between 1906 and 1912 on the site of a demolished church that was originally built in 1725. It has the largest Catholic congregation in Istanbul and offers masses in Italian, Polish, English and Turkish. Among the Catholic community, it is known as the church where Pope John XXIII preached for a decade during his tenure as the Vatican’s ambassador to Turkey before being elected pontiff in 1958.";

        assertEquals(firstP, elements.first().text());
        
    }

    @Test
    void getContent() {

        String content = JsoupUtil.getContent("https://www.aa.com.tr/en/europe/belgium-virus-cases-near-50-000-as-deaths-drop/1826495",
                ".detay-icerik");
        String str = "BRUSSELS The number of coronavirus cases in Belgium moved close to 50,000 on Saturday, with nearly 8,000 deaths.The Federal Public Health Service recorded 485 new COVID-19 cases over the past 24 hours, raising the total to 49,517.The death toll rose to 7,765 with 82 more fatalities, the first time since March 29 that fewer than 100 patients have died in a day.Following 128 additions on Friday, a total of 3,111 patients are currently in hospital, with the number in intensive care down by 60 to 689.Recoveries since March 15 reached 12,211 after 319 more patients were discharged from hospitals over the past 24 hours.More than 3.35 million cases have been reported in 187 countries and regions since the virus emerged in China last December, with the US and Europe the world’s hardest-hit areas.A significant number of COVID-19 patients – over 1.05 million – have recovered, but the disease has also claimed close to 240,000 lives so far, according to data compiled by the US' Johns Hopkins University.";

        assertEquals(str, content);

    }

    @Test
    void getRssLinksFromGivenUrl() throws IOException {

        String url = "https://www.dailysabah.com/rssFeed/home-page";
        List<String> news;
        List<String> rss = JsoupUtil.getRssLinksFromGivenUrl(DailySabahSpider.URL, "rssFeed");
        if (rss.stream().noneMatch(i -> i.contains(DailySabahSpider.DOMAIN))) {

             news = (rss.stream().map(i -> DailySabahSpider.DOMAIN + i).collect(Collectors.toList()));

        }else {

            news = new ArrayList<>(rss);

        }
        log.info(news.toString());

        assertTrue(news.contains(url));

    }
}
