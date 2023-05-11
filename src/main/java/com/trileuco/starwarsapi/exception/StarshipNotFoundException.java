package com.trileuco.starwarsapi.exception;

public class StarshipNotFoundException extends RuntimeException {

    public StarshipNotFoundException(String message) {
        super(message);
    }
}
