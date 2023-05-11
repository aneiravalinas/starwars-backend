package com.trileuco.starwarsapi.exception;

public class FilmNotFoundException extends RuntimeException {

    public FilmNotFoundException(String message) {
        super(message);
    }
}
