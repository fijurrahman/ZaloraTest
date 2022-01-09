package com.zalora.UrlShortner.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Custom exception class is used to show the message in the UI in case of NotfoundException
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotfoundException extends RuntimeException{
    public NotfoundException(String msg)
    {
        super(msg);
    }
    public NotfoundException(String msg,Throwable cs)
    {
        super(msg,cs);
    }

    public NotfoundException() {}
}
