package com.zalora.UrlShortner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Custom exception class is used to show the message in the UI in case of BadRequest
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RquestUnknownException extends RuntimeException{
    public RquestUnknownException(String msg)
    {
        super(msg);
    }
    public RquestUnknownException(String msg,Throwable cs)
    {
        super(msg,cs);
    }

    public RquestUnknownException(){}
}
