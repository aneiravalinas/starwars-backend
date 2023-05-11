package com.trileuco.starwarsapi.repository;

import com.trileuco.starwarsapi.exception.VehicleNotFoundException;
import com.trileuco.starwarsapi.model.swapi.VehicleSwapi;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class VehicleRestRepository {

    private final WebClient webClient;

    public VehicleRestRepository(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://swapi.trileuco.com/api/films")
                .build();
    }

    public VehicleSwapi findVehicleByPath(String path) {
        return this.webClient.get()
                .uri(path)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    if ( clientResponse.statusCode().isSameCodeAs(HttpStatus.NOT_FOUND) ) {
                        return Mono.error(new VehicleNotFoundException("Vehicle not found on path: " + path));
                    }

                    return clientResponse.createException();
                })
                .bodyToMono(VehicleSwapi.class)
                .block();
    }

}
