package com.trileuco.starwarsapi.repository;

import com.trileuco.starwarsapi.exception.FilmNotFoundException;
import com.trileuco.starwarsapi.model.swapi.FilmSwapi;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class FilmRestRepository {

    private final WebClient webClient;

    public FilmRestRepository(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://swapi.trileuco.com/api/films")
                .build();
    }

    public FilmSwapi findFilmByPath(String path) {
        return this.webClient.get()
                .uri(path)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    if ( clientResponse.statusCode().isSameCodeAs(HttpStatus.NOT_FOUND) ) {
                        return Mono.error(new FilmNotFoundException("Film not found on path: " + path));
                    }

                    return clientResponse.createException();
                })
                .bodyToMono(FilmSwapi.class)
                .block();
    }


}
