package com.trileuco.starwarsapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.List;

@Component
public class WebClientCustomizer implements org.springframework.boot.web.reactive.function.client.WebClientCustomizer {

    private static final Logger logger = LoggerFactory.getLogger(WebClientCustomizer.class);

    @Override
    public void customize(WebClient.Builder webClientBuilder) {
        webClientBuilder
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create().followRedirect(true)))
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(logResponse())
                .filter(logRequest());
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            logger.info("Response: {}", clientResponse.statusCode());
            logger.info("--- Http Headers of Response: ---");
            clientResponse.headers().asHttpHeaders()
                    .forEach((name, values) -> values.forEach(value -> logger.info("{}={}", name, value)));
            return Mono.just(clientResponse);
        });
    }

    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            logger.info("--- Http Headers of Request: ---");
            clientRequest.headers().forEach(this::logHeader);
            return next.exchange(clientRequest);
        };
    }

    private void logHeader(String name, List<String> values) {
        values.forEach(value -> logger.info("{}={}", name, value));
    }
}
