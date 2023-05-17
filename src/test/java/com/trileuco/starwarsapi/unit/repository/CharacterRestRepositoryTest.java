package com.trileuco.starwarsapi.unit.repository;

import com.trileuco.starwarsapi.exception.ClientException;
import com.trileuco.starwarsapi.unit.mockserver.MockServer;
import com.trileuco.starwarsapi.model.swapi.CharacterSwapi;
import com.trileuco.starwarsapi.model.swapi.PageSwapi;
import com.trileuco.starwarsapi.repository.CharacterRestRepository;
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
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CharacterRestRepositoryTest {

    private static final String CHARACTERS_PATH = "/people?page={num_page}";
    private static MockServer mockServer;
    private CharacterRestRepository cut;

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
        cut = new CharacterRestRepository(mockServer.getWebClient());
    }

    @Test
    void findCharacterPageByNullNameShouldRequestCorrectPath() {
        Map<String, String> header = Collections
                .singletonMap(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        PageSwapi<CharacterSwapi> page = new PageSwapi<>();

        int numPage = 1;

        String expectedPath = CHARACTERS_PATH.replace("{num_page}", Integer.toString(numPage));

        mockServer.responseWith(HttpStatus.OK, page, header);
        cut.findCharacters(null, numPage);

        mockServer.takeRequest()
                .expectMethod(HttpMethod.GET.name())
                .expectPath(expectedPath);

    }

    @Test
    void findCharacterPageByNotNullNameShouldRequestCorrectPath() {
        Map<String, String> header = Collections
                .singletonMap(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        PageSwapi<CharacterSwapi> page = new PageSwapi<>();

        int numPage = 1;
        String name = "Luke";

        String expectedPath = CHARACTERS_PATH.replace("{num_page}", Integer.toString(numPage)) +
                "&search=" + name;

        mockServer.responseWith(HttpStatus.OK, page, header);
        cut.findCharacters(name, numPage);
        mockServer.takeRequest()
                .expectMethod(HttpMethod.GET.name())
                .expectPath(expectedPath);
    }

    @Test
    void findCharacterPageShouldRetrievePage() {
        Map<String, String> header = Collections
                .singletonMap(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        CharacterSwapi character = new CharacterSwapi();
        character.setUrl("id");
        PageSwapi<CharacterSwapi> page = new PageSwapi<>();
        page.setCount(1);
        page.setResults(List.of(character));

        int numPage = 1;

        mockServer.responseWith(HttpStatus.OK, page, header);
        PageSwapi<CharacterSwapi> result = cut.findCharacters(null, numPage);
        assertEquals(page, result);
    }

    @Test
    void findCharacterPageShouldReturnsPageNotFoundWhenServerRespondsWith404Error() {
        mockServer.responseWith(HttpStatus.NOT_FOUND);
        int numPage = 1;

        ClientException clientException = assertThrows(ClientException.class, () -> cut.findCharacters(null, numPage));
        assertEquals(HttpStatus.NOT_FOUND, clientException.getStatus(), "Unexpected HttpStatus code");

        String expectedResponseMessage = String.format("Character page number %s doesn't exist", numPage);
        assertEquals(expectedResponseMessage, clientException.getMessage(), "404 message response is not as expected");
        mockServer.clearRequest();
    }

    @Test
    void findCharacterPageShouldReturnsClientErrorWhenServerRespondsWith4xxError() {
        mockServer.responseWith(HttpStatus.BAD_REQUEST);
        int numPage = 1;

        ClientException clientException = assertThrows(ClientException.class, () -> cut.findCharacters(null, numPage));
        assertEquals(HttpStatus.BAD_REQUEST, clientException.getStatus(), "Unexpected HttpStatus code");

        mockServer.clearRequest();
    }

    @Test
    void findCharacterPageShouldReturnsServerErrorWhenServerRespondsWith5xxError() {
        mockServer.responseWith(HttpStatus.INTERNAL_SERVER_ERROR);
        int numPage = 1;

        ClientException clientException = assertThrows(ClientException.class, () -> cut.findCharacters(null, numPage));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, clientException.getStatus(), "Unexpected HttpStatus code");

        mockServer.clearRequest();
    }
}
