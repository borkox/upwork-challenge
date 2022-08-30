package com.urlshort;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainAppIntegrationTest {

    @Test
    public void contextLoads() {
    }

    @Test
    public void applicationStarts() {
        UrlShortenerApplication.main(new String[] {});
    }
}
