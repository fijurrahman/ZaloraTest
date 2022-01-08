package com.zalora.UrlShortner.service;

import com.zalora.UrlShortner.entity.URLEntity;
import com.zalora.UrlShortner.exception.NotfoundException;
import com.zalora.UrlShortner.exception.RquestUnknownException;
import com.zalora.UrlShortner.repository.RedirectRepository;
import com.zalora.UrlShortner.request.RedirectCreationRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RedirectService {
    private RedirectRepository redirectRepository;
    private Map<String, URLEntity> shortenURLMap = new HashMap<>();

    @Autowired
    public RedirectService(RedirectRepository redirectRepository) {
        this.redirectRepository = redirectRepository;
    }

    /*@RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<Object> getShortenUrl(@RequestBody URLEntity shortenurl) throws MalformedURLException
    {
        String randomChar = getRandomChars();
        setSortUrl(randomChar,shortenurl);
        return  new ResponseEntity<Object>(shortenurl, HttpStatus.OK);
    }
    public void setSortUrl(String randomChar, URLEntity shorturl) throws MalformedURLException{
        shorturl.setUrl("http://localhost:8080/"+randomChar);
        shortenURLMap.put(randomChar,shorturl);
    }*/

    public Optional<URLEntity> createRedirect(RedirectCreationRequest redirectCreationRequest)
    {
        if(redirectRepository.existsByAlias(redirectCreationRequest.getAlias()))
        {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"Alias already reported in the server",new RquestUnknownException());
        }
        String randomChar = getRandomChars();
        System.out.println("Redirect Request " + redirectCreationRequest.toString());
        URLEntity redirect = redirectRepository.save(new URLEntity(redirectCreationRequest.getAlias()+randomChar,redirectCreationRequest.getUrl()));
        URLEntity publishUrl = redirectRepository.save(redirect);
        System.out.println("Redirect " + publishUrl);
        return Optional.ofNullable(publishUrl);
    }

    public URLEntity getRedirect(String alias)
    {
        URLEntity redirect = redirectRepository.findByAlias(alias).orElseThrow(()
                -> new NotfoundException(" Alias not Found, Please create a new one"));
        return redirect;

    }

    private String getRandomChars(){
        String randomStr = "";
        String randChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for(int i = 0; i<5; i++)
        {
            randomStr += randChars.charAt((int) Math.floor(Math.random() * randChars.length()));
        }
        return randomStr;
    }
}
