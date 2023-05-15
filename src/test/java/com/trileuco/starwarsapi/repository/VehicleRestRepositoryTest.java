package com.trileuco.starwarsapi.repository;

import com.trileuco.starwarsapi.exception.ClientException;
import com.trileuco.starwarsapi.mockserver.MockServer;
import com.trileuco.starwarsapi.model.swapi.VehicleSwapi;
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

public class VehicleRestRepositoryTest {

    private static final String VEHICLE_PATH = "/vehicles/1";
    private static MockServer mockServer;
    private VehicleRestRepository cut;

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
        cut = new VehicleRestRepository(mockServer.getWebClient());
    }

    @Test
    void findVehicleByPathShouldRequestCorrectPathAndMethod() {
        Map<String, String> header = Collections
                .singletonMap(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        String path = mockServer.getMockServerUrl() + VEHICLE_PATH;
        VehicleSwapi vehicle = new VehicleSwapi();

        mockServer.responseWith(HttpStatus.OK, vehicle, header);
        cut.findVehicleByPath(path);

        mockServer.takeRequest()
                .expectMethod(HttpMethod.GET.name())
                .expectPath(VEHICLE_PATH);
    }

    @Test
    void findVehicleByPathShouldRetrieveVehicle() {
        Map<String, String> header = Collections
                .singletonMap(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        String path = mockServer.getMockServerUrl() + VEHICLE_PATH;
        VehicleSwapi vehicle = new VehicleSwapi();
        vehicle.setUrl(path);

        mockServer.responseWith(HttpStatus.OK, vehicle, header);
        VehicleSwapi result = cut.findVehicleByPath(path);
        assertEquals(vehicle, result);
    }

    @Test
    void findVehicleByPathShouldReturnsClientErrorWhenServerRespondsWith4xxError() {
        mockServer.responseWith(HttpStatus.NOT_FOUND);
        String path = mockServer.getMockServerUrl() + VEHICLE_PATH;

        ClientException clientException = assertThrows(ClientException.class, () -> cut.findVehicleByPath(path));
        assertEquals(HttpStatus.NOT_FOUND, clientException.getStatus(), "Unexpected HttpStatus code");
        mockServer.clearRequest();
    }

    @Test
    void findVehicleByPathShouldReturnsServerErrorWhenServerRespondsWith5xxError() {
        mockServer.responseWith(HttpStatus.INTERNAL_SERVER_ERROR);
        String path = mockServer.getMockServerUrl() + VEHICLE_PATH;

        ClientException clientException = assertThrows(ClientException.class, () -> cut.findVehicleByPath(path));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, clientException.getStatus(), "Unexpected HttpStatus code");
        mockServer.clearRequest();
    }
}
