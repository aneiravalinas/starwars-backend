package com.trileuco.starwarsapi.exception.character;

public class CharacterNotFoundException extends RuntimeException {

    public CharacterNotFoundException(String message) {
        super(message);
    }
}
