package com.trileuco.starwarsapi.repository;

import com.trileuco.starwarsapi.exception.ClientException;
import com.trileuco.starwarsapi.model.swapi.VehicleSwapi;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class VehicleRestRepository {

    private final WebClient webClient;

    public VehicleRestRepository(WebClient swapiWebClient) {
        this.webClient = swapiWebClient;
    }

    public VehicleSwapi findVehicleByPath(String path) {
        return this.webClient.get()
                .uri(path)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(ClientException.from(response.statusCode())))
                .bodyToMono(VehicleSwapi.class)
                .block();
    }

}
