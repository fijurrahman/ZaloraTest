package com.zalora.UrlShortner.service;

import com.zalora.UrlShortner.entity.URLEntity;
import com.zalora.UrlShortner.exception.NotfoundException;
import com.zalora.UrlShortner.exception.RquestUnknownException;
import com.zalora.UrlShortner.repository.RedirectRepository;
import com.zalora.UrlShortner.request.RedirectCreationRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class RedirectService {
    Logger logger = LoggerFactory.getLogger(RedirectService.class);

    private RedirectRepository redirectRepository;

    @Autowired
    public RedirectService(RedirectRepository redirectRepository) {
        this.redirectRepository = redirectRepository;
    }

    public Optional<URLEntity> createRedirect(RedirectCreationRequest redirectCreationRequest)
    {
        String randomchars= getRandomChars();
        if(redirectRepository.existsByAlias(redirectCreationRequest.getAlias()))
        {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"Alias already reported int the server",new RquestUnknownException());
        }
        logger.info("Redirect Request " + redirectCreationRequest.toString());
        URLEntity redirect = redirectRepository.save(new URLEntity(redirectCreationRequest.getAlias()  + randomchars,redirectCreationRequest.getUrl()));
        URLEntity publishUrl = redirectRepository.save(redirect);
        logger.info("Redirect " + publishUrl);
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
        for(int i = 0; i<10; i++)
        {
            randomStr += randChars.charAt((int) Math.floor(Math.random() * randChars.length()));
        }
        return randomStr;
    }
}
