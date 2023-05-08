package com.trileuco.starwarsapi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class StarshipRestRepository {

    private final WebClient webClient;

    public StarshipRestRepository(WebClient webClient) {
        this.webClient = webClient;
    }
}
