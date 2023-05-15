package com.trileuco.starwarsapi.repository;

import com.trileuco.starwarsapi.exception.ClientException;
import com.trileuco.starwarsapi.model.swapi.PageSwapi;
import com.trileuco.starwarsapi.model.swapi.CharacterSwapi;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class CharacterRestRepository {

    private final WebClient webClient;
    private final static ParameterizedTypeReference<PageSwapi<CharacterSwapi>> typeReference = new ParameterizedTypeReference<>() {};
    public CharacterRestRepository(WebClient swapiWebClient) {
        this.webClient = swapiWebClient;
    }

    public PageSwapi<CharacterSwapi> findCharacters(String name, int numPage) {
        return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .pathSegment("people")
                            .queryParam("page", numPage)
                            .queryParamIfPresent("search", Optional.ofNullable(name)).build())
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, response -> {
                        if ( response.statusCode().isSameCodeAs(HttpStatus.NOT_FOUND) ) {
                            return Mono.error(new ClientException(HttpStatus.NOT_FOUND, String.format("Character page number %s doesn't exist", numPage)));
                        }

                        return Mono.error(ClientException.from(response.statusCode()));
                    })
                    .bodyToMono(typeReference)
                    .block();
    }

}

