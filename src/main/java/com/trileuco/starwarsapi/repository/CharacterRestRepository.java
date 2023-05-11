package com.trileuco.starwarsapi.repository;

import com.trileuco.starwarsapi.exception.CharacterNotFoundException;
import com.trileuco.starwarsapi.exception.PageCharacterNotFoundException;
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
    public CharacterRestRepository(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://swapi.trileuco.com/api/people")
                .build();
    }

    public PageSwapi<CharacterSwapi> findCharacters(String name, int numPage) {
        PageSwapi<CharacterSwapi> page = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("page", numPage)
                            .queryParamIfPresent("search", Optional.ofNullable(name)).build())
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                        if ( clientResponse.statusCode().isSameCodeAs(HttpStatus.NOT_FOUND) ) {
                            return Mono.error(new PageCharacterNotFoundException("Character page " + numPage + " is empty"));
                        }

                        return clientResponse.createException();
                    })
                    .bodyToMono(typeReference)
                    .block();

        if ( page != null && page.getCount() == 0 ) {
            throw new CharacterNotFoundException("Not character found by name: " + name);
        }

        return page;
    }
}

