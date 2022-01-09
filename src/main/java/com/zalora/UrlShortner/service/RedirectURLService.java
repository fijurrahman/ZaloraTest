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
public class RedirectURLService {
    Logger logger = LoggerFactory.getLogger(RedirectURLService.class);

    private RedirectRepository redirectRepository;

    @Autowired
    public RedirectURLService(RedirectRepository redirectRepository) {
        this.redirectRepository = redirectRepository;
    }

    public Optional<URLEntity> createRedirect(RedirectCreationRequest redirectCreationRequest)
    {
        String randomchars= getUniquechar();
        if(redirectRepository.existsByAlias(redirectCreationRequest.getAlias()))
        {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"Alias already reported int the server",new RquestUnknownException());
        }
        logger.info("Redirect Request " + redirectCreationRequest.toString());

        URLEntity redirect = redirectRepository.save(new URLEntity(redirectCreationRequest.getAlias()  + randomchars,redirectCreationRequest.getUrl()
               ));
        URLEntity publishUrl = redirectRepository.save(redirect);
        logger.info("Redirect " + publishUrl);
        return Optional.ofNullable(publishUrl);
    }

    public URLEntity getRedirect(String alias)
    {
        URLEntity redirect = redirectRepository.findByAlias(alias).orElseThrow(()
                ->  new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE , "Alias Name is not Found",new NotfoundException()));
        return redirect;

    }
    private String getUniquechar(){
        StringBuilder sb = new StringBuilder();
        sb.append("");
        for(int i=0;i<7;i++) {
            int upperCase = (int) (Math.random() * (91 - 65) + 65); // [65, 90] => 26
            int lowerCase = (int) (Math.random() * (123 - 97) + 97); // [97, 122] => 26
            int digit = (int) (Math.random() * (58 - 48) + 48); // [48, 57] => 10
            int[] ascii = {upperCase, digit, lowerCase};
            sb.append((char)(ascii[(int)(Math.random()*(3))])); // [0,2]
        }
        return sb.toString();
    }
}
