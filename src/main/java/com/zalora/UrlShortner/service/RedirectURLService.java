package com.zalora.UrlShortner.service;

import com.zalora.UrlShortner.entity.URLtbl;
import com.zalora.UrlShortner.exception.NotfoundException;
import com.zalora.UrlShortner.exception.RquestUnknownException;
import com.zalora.UrlShortner.exception.URLError;
import com.zalora.UrlShortner.repository.RedirectRepository;
import com.zalora.UrlShortner.request.RedirectCreationRequest;

import org.apache.commons.validator.routines.UrlValidator;
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
    final int size = 8;

    private RedirectRepository redirectRepository;

    @Autowired
    public RedirectURLService(RedirectRepository redirectRepository) {
        this.redirectRepository = redirectRepository;
    }

    /**
     *
     * @param redirectCreationRequest
     * @return OriginalURL
     * This method will Save the the alias and Ogigonal URL IN H2 and it will return Already loaded in the server
     */

    public Optional<URLtbl> createRedirect(RedirectCreationRequest redirectCreationRequest)
    {
        final UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        if (!urlValidator.isValid(redirectCreationRequest.getUrl())) {
            // Invalid url return HTTP 400 bad request.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"URL ERROR",new URLError("InValid URL"));
        }

        String randomchars= getUniquechar(size);
        if(redirectRepository.existsByAlias(redirectCreationRequest.getAlias()))
        {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"Alias already found the server",new RquestUnknownException());
        }
        logger.info("Redirect Request " + redirectCreationRequest.toString());

        URLtbl redirect = redirectRepository.save(new URLtbl(redirectCreationRequest.getAlias()  + randomchars,redirectCreationRequest.getUrl()
               ));
        URLtbl publishUrl = redirectRepository.save(redirect);
        logger.info("Redirect " + publishUrl);
        return Optional.ofNullable(publishUrl);
    }

    public URLtbl getRedirect(String alias)
    {
        URLtbl redirect = redirectRepository.findByAlias(alias).orElseThrow(()
                ->  new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE , "Alias Name is not Found",new NotfoundException()));
        return redirect;

    }


    /***
     *
     * @return String
     * This Method will be returning the URI In the combination of 'a-z' -> 'A'-'Z' -> '0-9' for long run.
     * Return the randomstring
     */
    private String getUniquechar(int size){
        StringBuilder sb = new StringBuilder();
        sb.append("");
        for(int i=0;i<size;i++) {
            int upperCase = (int) (Math.random() * (91 - 65) + 65);
            int lowerCase = (int) (Math.random() * (123 - 97) + 97);
            int digit = (int) (Math.random() * (58 - 48) + 48);
            int[] ascii = {upperCase, digit, lowerCase};
            sb.append((char)(ascii[(int)(Math.random()*(3))]));
        }
        return sb.toString();
    }
}
