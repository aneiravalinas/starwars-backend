package com.trileuco.starwarsapi.unit.repository;

import com.trileuco.starwarsapi.exception.ClientException;
import com.trileuco.starwarsapi.unit.mockserver.MockServer;
import com.trileuco.starwarsapi.model.swapi.FilmSwapi;
import com.trileuco.starwarsapi.repository.FilmRestRepository;
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

public class FilmRestRepositoryTest {

    private static final String FILM_PATH = "/films/1";
    private static MockServer mockServer;
    private FilmRestRepository cut;

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
        cut = new FilmRestRepository(mockServer.getWebClient());
    }

    @Test
    void findFilmByPathShouldRequestCorrectPathAndStatus() {
        Map<String, String> header = Collections
                .singletonMap(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        String path = mockServer.getMockServerUrl() + FILM_PATH;
        FilmSwapi film = new FilmSwapi();

        mockServer.responseWith(HttpStatus.OK, film, header);
        cut.findFilmByPath(path);

        mockServer.takeRequest()
                .expectMethod(HttpMethod.GET.name())
                .expectPath(FILM_PATH);
    }

    @Test
    void findFilmByPathShouldRetrieveFilm() {
        Map<String, String> header = Collections
                .singletonMap(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        String path = mockServer.getMockServerUrl() + FILM_PATH;
        FilmSwapi film = new FilmSwapi();
        film.setUrl(path);

        mockServer.responseWith(HttpStatus.OK, film, header);
        FilmSwapi result = cut.findFilmByPath(path);
        assertEquals(film, result);
    }

    @Test
    void findFilmByPathShouldReturnsClientErrorWhenServerRespondsWith4xxError() {
        mockServer.responseWith(HttpStatus.NOT_FOUND);
        String path = mockServer.getMockServerUrl() + FILM_PATH;

        ClientException clientException = assertThrows(ClientException.class, () -> cut.findFilmByPath(path));
        assertEquals(HttpStatus.NOT_FOUND, clientException.getStatus(), "Unexpected HttpStatus code");
        mockServer.clearRequest();
    }

    @Test
    void findFilmByPathShouldReturnsServerErrorWhenServerRespondsWith5xxError() {
        mockServer.responseWith(HttpStatus.INTERNAL_SERVER_ERROR);
        String path = mockServer.getMockServerUrl() + FILM_PATH;

        ClientException clientException = assertThrows(ClientException.class, () -> cut.findFilmByPath(path));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, clientException.getStatus(), "Unexpected HttpStatus code");
        mockServer.clearRequest();
    }
}
