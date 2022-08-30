package com.urlshort.service;

import com.urlshort.entity.UrlShortEntity;
import com.urlshort.repository.UrlShortRepository;
import java.net.URI;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * This service is responsible to clean already expired links from database. The cleaning is performed regularly with
 * '@Scheduled' annotation support from Spring. Deletion of expired links are made on portions given with constant
 * 'MAX_CLEAN_AT_A_TIME' and default value of 30.
 */
@AllArgsConstructor
@Service
@Slf4j
public class HousekeepingService {

    public static final int MAX_CLEAN_AT_A_TIME = 30;
    private final UrlShortRepository repository;
    private final Clock clock;

    @Scheduled(fixedRateString = "${housekeeping.interval}", initialDelayString = "${housekeeping.initial}")
    public void cleanExpired() {
        Instant now = Instant.now(clock);
        Date expiredBefore = Date.from(now);
        List<UrlShortEntity> expire = repository.findByExpire(expiredBefore, PageRequest.of(0, MAX_CLEAN_AT_A_TIME));
        repository.deleteAll(expire);
        log.info("Cleaned {} urls expired before: {}", expire.size(), now);
    }

}
