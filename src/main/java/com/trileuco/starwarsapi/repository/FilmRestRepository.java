package com.trileuco.starwarsapi.repository;

import com.trileuco.starwarsapi.exception.ClientException;
import com.trileuco.starwarsapi.model.swapi.FilmSwapi;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class FilmRestRepository {

    private final WebClient webClient;

    public FilmRestRepository(WebClient swapiWebClient) {
        this.webClient = swapiWebClient;
    }

    public FilmSwapi findFilmByPath(String path) {
        return this.webClient.get()
                .uri(path)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(ClientException.from(response.statusCode())))
                .bodyToMono(FilmSwapi.class)
                .block();
    }


}
