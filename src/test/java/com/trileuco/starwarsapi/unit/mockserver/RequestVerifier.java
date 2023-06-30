package com.trileuco.starwarsapi.unit.mockserver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import okhttp3.mockwebserver.RecordedRequest;

public class RequestVerifier {

    private final RecordedRequest request;

    public RequestVerifier(RecordedRequest request) {
        assertThat("Request is null", request, is(notNullValue()));
        this.request = request;
    }

    public RequestVerifier expectPath(String path) {
        assertThat(request.getPath(), is(path));
        return this;
    }

    public RequestVerifier expectMethod(String method) {
        assertThat(request.getMethod(), is(method));
        return this;
    }

}
