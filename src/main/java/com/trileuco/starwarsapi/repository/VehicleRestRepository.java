package com.trileuco.starwarsapi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class VehicleRestRepository {

    private final WebClient webClient;

    public VehicleRestRepository(WebClient webClient) {
        this.webClient = webClient;
    }
}
