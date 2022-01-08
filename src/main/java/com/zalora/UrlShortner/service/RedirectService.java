package com.zalora.UrlShortner.service;

import com.zalora.UrlShortner.entity.Redirect;
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

    public Optional<Redirect> createRedirect(RedirectCreationRequest redirectCreationRequest)
    {
        if(redirectRepository.existsByAlias(redirectCreationRequest.getAlias()))
        {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"Alias already reported int the server",new RquestUnknownException());
        }
        System.out.println("Redirect Request " + redirectCreationRequest.toString());
        Redirect redirect = redirectRepository.save(new Redirect(redirectCreationRequest.getAlias(),redirectCreationRequest.getUrl()));
        Redirect publishUrl = redirectRepository.save(redirect);
        System.out.println("Redirect " + publishUrl);
        return Optional.ofNullable(publishUrl);
    }

    public Redirect getRedirect(String alias)
    {
        Redirect redirect = redirectRepository.findByAlias(alias).orElseThrow(()
                -> new NotfoundException(" Alias not Found, Please create a new one"));
        return redirect;

    }
}
