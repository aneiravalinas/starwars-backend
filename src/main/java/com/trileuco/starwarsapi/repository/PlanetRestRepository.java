package com.trileuco.starwarsapi.repository;

import com.trileuco.starwarsapi.exception.PlanetNotFoundException;
import com.trileuco.starwarsapi.model.swapi.PlanetSwapi;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class PlanetRestRepository {

    private final WebClient webClient;

    public PlanetRestRepository(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://swapi.trileuco.com/api/films")
                .build();
    }

    public PlanetSwapi findPlanetByPath(String path) {
        return this.webClient
                .get()
                .uri(path)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    if ( clientResponse.statusCode().isSameCodeAs(HttpStatus.NOT_FOUND) ) {
                        return Mono.error(new PlanetNotFoundException("Planet not found on path: " + path));
                    }

                    return clientResponse.createException();
                })
                .bodyToMono(PlanetSwapi.class)
                .block();
    }
}
