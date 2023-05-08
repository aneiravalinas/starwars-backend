package com.trileuco.starwarsapi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class FilmRestRepository {

    private final WebClient webClient;

    public FilmRestRepository(WebClient webClient) {
        this.webClient = webClient;
    }
}
