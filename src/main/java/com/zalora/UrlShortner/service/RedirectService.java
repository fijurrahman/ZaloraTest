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

import java.util.Optional;

@Service
public class RedirectService {
    private RedirectRepository redirectRepository;

    @Autowired
    public RedirectService(RedirectRepository redirectRepository) {
        this.redirectRepository = redirectRepository;
    }

    public Optional<URLEntity> createRedirect(RedirectCreationRequest redirectCreationRequest)
    {
        if(redirectRepository.existsByAlias(redirectCreationRequest.getAlias()))
        {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"Alias already reported int the server",new RquestUnknownException());
        }
        System.out.println("Redirect Request " + redirectCreationRequest.toString());
        URLEntity redirect = redirectRepository.save(new URLEntity(redirectCreationRequest.getAlias(),redirectCreationRequest.getUrl()));
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
}
