package com.zalora.UrlShortner.request;

import javax.validation.constraints.NotNull;


/**
 * RedirectCreationRequest class is used to create a redirect request
 * String alias
 * String url
 * */

public class RedirectCreationRequest {

    @NotNull
    private String alias;
    @NotNull
    private String url;


    public RedirectCreationRequest(final String alias, final String url) {
        this.alias = alias;
        this.url = url;
    }


    public String getAlias() {
        return alias;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "RedirectCreationRequest{" +
                "alias='" + alias + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
