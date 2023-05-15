package com.trileuco.starwarsapi.mockserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MockServer {

    private final ObjectWriter objectWriter;
    private final WebClient webClient;
    private final MockWebServer server;

    private MockServer() {
        this.objectWriter = new ObjectMapper().registerModule(new JavaTimeModule()).writer();
        this.server = new MockWebServer();
        this.webClient = WebClient
                .builder()
                .baseUrl(getMockServerUrl())
                .build();
    }

    public static MockServer create() {
        return new MockServer();
    }

    public void dispose() throws IOException {
        server.shutdown();
    }

    public WebClient getWebClient() {
        return webClient;
    }

    public String getMockServerUrl() {
        return server.url("").toString();
    }

    public MockServer responseWith(HttpStatus status) {
        server.enqueue(new MockResponse().setResponseCode(status.value()));
        return this;
    }

    public <T> MockServer responseWith(HttpStatus status, T responseBody, Map<String, String> headers) {
        MockResponse response = new MockResponse()
                .setResponseCode(status.value())
                .setBody(toJson(responseBody));
        headers.forEach(response::addHeader);

        server.enqueue(response);
        return this;
    }

    private <T> String toJson(T value) {
        try {
            return objectWriter.writeValueAsString(value);
        } catch ( JsonProcessingException ex ) {
            throw new RuntimeException(ex);
        }
    }

    public RequestVerifier takeRequest() {
        try {
            RecordedRequest request = server.takeRequest(1000, TimeUnit.MILLISECONDS);
            return new RequestVerifier(request);
        } catch (InterruptedException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void clearRequest() {
        try {
            server.takeRequest(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
            throw new IllegalStateException(ex);
        }
    }

}
