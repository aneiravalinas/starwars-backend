package com.trileuco.starwarsapi.unit.repository;

import com.trileuco.starwarsapi.exception.ClientException;
import com.trileuco.starwarsapi.unit.mockserver.MockServer;
import com.trileuco.starwarsapi.model.swapi.StarshipSwapi;
import com.trileuco.starwarsapi.repository.StarshipRestRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StarshipRestRepositoryTest {

    private static final String STARSHIP_PATH = "/starships/1";
    private static MockServer mockServer;
    private StarshipRestRepository cut;

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
        cut = new StarshipRestRepository(mockServer.getWebClient());
    }

    @Test
    void findStarshipByPathShouldRequestCorrectPathAndMethod() {
        Map<String, String> header = Collections
                .singletonMap(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        String path = mockServer.getMockServerUrl() + STARSHIP_PATH;
        StarshipSwapi starship = new StarshipSwapi();

        mockServer.responseWith(HttpStatus.OK, starship, header);
        cut.findStarshipByPath(path);

        mockServer.takeRequest()
                .expectMethod(HttpMethod.GET.name())
                .expectPath(STARSHIP_PATH);
    }

    @Test
    void findStarshipByPathShouldRetrieveStarship() {
        Map<String, String> header = Collections
                .singletonMap(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        String path = mockServer.getMockServerUrl() + STARSHIP_PATH;
        StarshipSwapi starship = new StarshipSwapi();
        starship.setUrl(path);

        mockServer.responseWith(HttpStatus.OK, starship, header);
        StarshipSwapi result = cut.findStarshipByPath(path);
        assertEquals(starship, result);
    }

    @Test
    void findStarshipByPathShouldReturnsClientErrorWhenServerRespondsWith4xxError() {
        mockServer.responseWith(HttpStatus.NOT_FOUND);
        String path = mockServer.getMockServerUrl() + STARSHIP_PATH;

        ClientException clientException = assertThrows(ClientException.class, () -> cut.findStarshipByPath(path));
        assertEquals(HttpStatus.NOT_FOUND, clientException.getStatus(), "Unexpected HttpStatus code");
        mockServer.clearRequest();
    }

    @Test
    void findStarshipByPathShouldReturnsServerErrorWhenServerRespondsWith5xxError() {
        mockServer.responseWith(HttpStatus.INTERNAL_SERVER_ERROR);
        String path = mockServer.getMockServerUrl() + STARSHIP_PATH;

        ClientException clientException = assertThrows(ClientException.class, () -> cut.findStarshipByPath(path));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, clientException.getStatus(), "Unexpected HttpStatus code");
        mockServer.clearRequest();
    }
}
