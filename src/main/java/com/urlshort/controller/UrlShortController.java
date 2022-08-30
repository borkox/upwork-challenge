package com.urlshort.controller;

import com.urlshort.dto.UrlShortCreateRequestDto;
import com.urlshort.dto.UrlShortResponseDto;
import com.urlshort.service.UrlShortService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is capable of creating a short links based on real long URLs.
 */
@RestController
@RequestMapping("/api/url")
public class UrlShortController {

    @Autowired
    private UrlShortService service;

    @CrossOrigin("*")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UrlShortResponseDto newUrl(
            @Valid @RequestBody UrlShortCreateRequestDto requestDto) {
        return service.shorten(requestDto);
    }


}
