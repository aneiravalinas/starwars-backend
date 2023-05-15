package com.trileuco.starwarsapi.exception;

import org.springframework.http.HttpStatusCode;

public class ClientException extends RuntimeException {

    private final HttpStatusCode status;
    private static final String CLIENT_ERROR = "Client error";
    private static final String SERVER_ERROR = "Server error";

    public ClientException(HttpStatusCode status, String message) {
        super(message);
        this.status = status;
    }

    public static ClientException from(HttpStatusCode status) {
        String message = ( status.is4xxClientError() ) ? CLIENT_ERROR : SERVER_ERROR;
        return new ClientException(status, message);
    }

    public static boolean isClientError(Throwable throwable) {
        return throwable instanceof ClientException
                && ((ClientException) throwable).isClientError();
    }

    public static boolean isServerError(Throwable throwable) {
        return throwable instanceof ClientException
                && ((ClientException) throwable).isServerError();
    }

    public boolean isClientError() {
        return status.is4xxClientError();
    }

    public boolean isServerError() {
        return status.is5xxServerError();
    }

    public HttpStatusCode getStatus() {
        return status;
    }


}
