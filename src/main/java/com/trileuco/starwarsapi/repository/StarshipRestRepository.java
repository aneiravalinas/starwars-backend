package com.trileuco.starwarsapi.repository;

import com.trileuco.starwarsapi.exception.StarshipNotFoundException;
import com.trileuco.starwarsapi.model.swapi.StarshipSwapi;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class StarshipRestRepository {

    private final WebClient webClient;

    public StarshipRestRepository(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://swapi.trileuco.com/api/films")
                .build();
    }

    public StarshipSwapi findStarshipByPath(String path) {
        return this.webClient.get()
                .uri(path)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    if ( clientResponse.statusCode().isSameCodeAs(HttpStatus.NOT_FOUND) ) {
                        return Mono.error(new StarshipNotFoundException("Starship not found on path: " + path));
                    }

                    return clientResponse.createException();
                })
                .bodyToMono(StarshipSwapi.class)
                .block();
    }

}
