package com.urlshort.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.urlshort.TestConfig;
import com.urlshort.entity.UrlShortEntity;
import com.urlshort.repository.UrlShortRepository;
import java.time.Clock;
import java.util.Date;
import java.util.Iterator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(TestConfig.class)
@ActiveProfiles("test")
@Slf4j
public class HousekeepingServiceTest {

    @Autowired
    private UrlShortRepository repository;

    @Autowired
    private HousekeepingService housekeepingService;

    @Autowired
    private Clock clock;

    @Test
    public void testCleaning() {
        repository.deleteAll();
        repository.save(new UrlShortEntity("1", "http://", new Date(-1)));
        assertHaveRecords(true);

        housekeepingService.cleanExpired();
        assertHaveRecords(false);
    }

    private void assertHaveRecords(boolean flag) {
        Iterable<UrlShortEntity> all = repository.findAll();
        Iterator<UrlShortEntity> iterator = all.iterator();
        assertThat(iterator.hasNext()).isEqualTo(flag);
    }

}
