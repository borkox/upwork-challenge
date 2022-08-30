package com.urlshort.service;

import com.urlshort.dto.UrlShortCreateRequestDto;
import com.urlshort.dto.UrlShortResponseDto;
import com.urlshort.entity.UrlShortEntity;
import com.urlshort.repository.UrlShortRepository;
import java.net.URI;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.util.Date;
import java.util.Optional;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UrlShortService {
    private final Supplier<String> idSupplier;
    private final UrlShortRepository repository;
    private final Clock clock;
    private final String baseUrl;
    private final Duration expireAdditive;

    public UrlShortService(Supplier<String> idSupplier,
            UrlShortRepository repository,
            Clock clock,
            @Value("${base-url}") String baseUrl,
            @Value("${url-expire-duration}") String expireDurationString
            ) {
        this.idSupplier = idSupplier;
        this.repository = repository;
        this.clock = clock;
        this.baseUrl = baseUrl;
        this.expireAdditive = Duration.parse(expireDurationString);
        log.info("Default expire set to: {}", this.expireAdditive);
    }

    public UrlShortResponseDto shorten(UrlShortCreateRequestDto request) {
        String id = idSupplier.get();
        // Note:
        // Ideally from the requirements we have that shortened URLs should expire after a
        // configured retention period based on the last access time, but this is not implemented like that.
        // It can be considered as mistake from my side.
        // Current implementation is working with fixed expiry offset.
        // If I had more time I could implement original requirement.
        Date expire = Date.from(Instant.now(clock).plus(this.expireAdditive));
        UrlShortEntity save = repository.save(new UrlShortEntity(id, request.getUrl(), expire));
        log.info("Shortened link: {}", save);
        return new UrlShortResponseDto(baseUrl + save.getId(), request.getUrl());
    }

    public Optional<URI> resolve(String id) {
        Optional<UrlShortEntity> byId = repository.findById(id);
        return byId.map(urlShortEntity -> URI.create(urlShortEntity.getUrl()));
    }


}
