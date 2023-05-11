package com.trileuco.starwarsapi.exception;

public class PageCharacterNotFoundException extends RuntimeException {

    public PageCharacterNotFoundException(String message) {
        super(message);
    }
}
