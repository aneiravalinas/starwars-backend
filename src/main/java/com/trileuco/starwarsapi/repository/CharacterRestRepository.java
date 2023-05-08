package com.trileuco.starwarsapi.repository;

import com.trileuco.starwarsapi.model.Page;
import com.trileuco.starwarsapi.model.swapi.CharacterSwapi;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class CharacterRestRepository {

    private final WebClient webClient;
    private final static ParameterizedTypeReference<Page<CharacterSwapi>> typeReference = new ParameterizedTypeReference<>() {};

    public CharacterRestRepository(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Page<CharacterSwapi>> getCharacters(String name) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/people")
                        .queryParam("search", name)
                        .build())
                // TODO: Check method exchangeToMono for handling status code response
                .retrieve()
                .bodyToMono(typeReference);
    }
}
