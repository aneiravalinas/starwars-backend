package com.trileuco.starwarsapi.repository;

import com.trileuco.starwarsapi.exception.ClientException;
import com.trileuco.starwarsapi.mockserver.MockServer;
import com.trileuco.starwarsapi.model.swapi.PlanetSwapi;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PlanetRestRepositoryTest {

    private static final String PLANET_PATH = "/planets/1";
    private static MockServer mockServer;
    private PlanetRestRepository cut;

    @BeforeAll
    static void beforeAll() {
        mockServer = MockServer.create();
    }

    @AfterAll
    static void afterAll() throws IOException {
        mockServer.dispose();
    }

    @BeforeEach
    void setup() {
        cut = new PlanetRestRepository(mockServer.getWebClient());
    }

    @Test
    void findPlanetByPathShouldRequestCorrectPathAndStatus() {
        Map<String, String> header = Collections
                .singletonMap(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        String path = mockServer.getMockServerUrl() + PLANET_PATH;

        mockServer.responseWith(HttpStatus.OK, new PlanetSwapi(), header);
        cut.findPlanetByPath(path);

        mockServer.takeRequest()
                .expectMethod(HttpMethod.GET.name())
                .expectPath(PLANET_PATH);
    }

    @Test
    void findPlanetByPathShouldRetrievePlanet() {
        Map<String, String> header = Collections
                .singletonMap(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        String path = mockServer.getMockServerUrl() + PLANET_PATH;
        PlanetSwapi planet = new PlanetSwapi();
        planet.setUrl(path);

        mockServer.responseWith(HttpStatus.OK, planet, header);
        PlanetSwapi result = cut.findPlanetByPath(path);
        assertEquals(planet, result);
    }

    @Test
    void findPlanetByPathShouldReturnsClientErrorWhenServerRespondsWith4xxError() {
        mockServer.responseWith(HttpStatus.NOT_FOUND);
        String path = mockServer.getMockServerUrl() + PLANET_PATH;

        ClientException clientException = assertThrows(ClientException.class, () -> cut.findPlanetByPath(path));
        assertEquals(HttpStatus.NOT_FOUND, clientException.getStatus(), "Unexpected HttpStatus code");
        mockServer.clearRequest();
    }

    @Test
    void findPlanetByPathShouldReturnsServerErrorWhenServerRespondsWith5xxError() {
        mockServer.responseWith(HttpStatus.INTERNAL_SERVER_ERROR);
        String path = mockServer.getMockServerUrl() + PLANET_PATH;

        ClientException clientException = assertThrows(ClientException.class, () -> cut.findPlanetByPath(path));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, clientException.getStatus(), "Unexpected HttpStatus code");
        mockServer.clearRequest();
    }
}
