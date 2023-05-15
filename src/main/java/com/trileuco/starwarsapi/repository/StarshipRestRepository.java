package com.trileuco.starwarsapi.repository;

import com.trileuco.starwarsapi.exception.ClientException;
import com.trileuco.starwarsapi.model.swapi.StarshipSwapi;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class StarshipRestRepository {

    private final WebClient webClient;

    public StarshipRestRepository(WebClient swapiWebClient) {
        this.webClient = swapiWebClient;
    }

    public StarshipSwapi findStarshipByPath(String path) {
        return this.webClient.get()
                .uri(path)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(ClientException.from(response.statusCode())))
                .bodyToMono(StarshipSwapi.class)
                .block();
    }

}
