package com.yeditepe.newscollector;

import com.yeditepe.newscollector.config.ConfigPropertiesTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = ConfigPropertiesTest.class)
@TestPropertySource(locations="classpath:test.properties")
class NewsCollectorApplicationTests {

    @Test
    void contextLoads() {
    }

}
