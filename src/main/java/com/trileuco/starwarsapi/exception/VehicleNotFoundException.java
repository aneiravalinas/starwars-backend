package com.trileuco.starwarsapi.exception;

public class VehicleNotFoundException extends RuntimeException {

    public VehicleNotFoundException(String message) {
        super(message);
    }
}
