package com.trileuco.starwarsapi.exception;

public class CharacterNotFoundException extends RuntimeException {

    public CharacterNotFoundException(String message) {
        super(message);
    }
}
