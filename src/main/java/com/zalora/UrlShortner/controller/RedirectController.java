package com.zalora.UrlShortner.controller;


import com.zalora.UrlShortner.entity.URLEntity;
import com.zalora.UrlShortner.request.RedirectCreationRequest;
import com.zalora.UrlShortner.service.RedirectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

@RestController
public class RedirectController {
    Logger logger = LoggerFactory.getLogger(RedirectController.class);
    private RedirectService redirectService;
    @Autowired
    public RedirectController(RedirectService redirectService) {
        this.redirectService = redirectService;
    }

    @GetMapping("/{alias}")
    public ResponseEntity<?> handleRedirect(@PathVariable String alias) throws URISyntaxException
    {
        URLEntity redirect = redirectService.getRedirect(alias);
        logger.info("Redirecting URI " + redirect);
        URI uri = new URI(redirect.getUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, MOVED_PERMANENTLY);
    }

    @PostMapping("/")
    public ResponseEntity<?> createRedirect(@Valid @RequestBody RedirectCreationRequest redirectCreationRequest){
        return ResponseEntity.ok(redirectService.createRedirect(redirectCreationRequest));
    }

}
