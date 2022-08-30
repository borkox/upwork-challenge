package com.urlshort.controller;

import com.urlshort.service.UrlShortService;
import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller will resolve short links coming from users.
 */
@RestController
@RequestMapping
public class LinkResolveController {

    @Autowired
    private UrlShortService service;

    @CrossOrigin("*")
    @GetMapping("/{id}")
    public ResponseEntity<Void> resolveUrl(@PathVariable String id) {
        Optional<URI> resolve = service.resolve(id);
        if (resolve.isPresent()) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(resolve.get());
            return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


}
