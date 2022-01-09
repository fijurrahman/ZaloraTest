package com.zalora.UrlShortner.controller;


import com.zalora.UrlShortner.entity.URLtbl;
import com.zalora.UrlShortner.request.RedirectCreationRequest;
import com.zalora.UrlShortner.service.RedirectURLService;

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


/**
 *
 * This Controller class will be used to Redirect the Request from alias string
 */

@RestController
public class RedirectController {
    Logger logger = LoggerFactory.getLogger(RedirectController.class);
    private RedirectURLService redirectService;
    @Autowired
    public RedirectController(RedirectURLService redirectService) {
        this.redirectService = redirectService;
    }

    public RedirectController() {

    }

    @GetMapping("/{alias}")
    public ResponseEntity<?> handleRedirect(@PathVariable String alias) throws URISyntaxException
    {
        URLtbl redirect = redirectService.getRedirect(alias);
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
