package com.trileuco.starwarsapi.unit.service;

import com.trileuco.starwarsapi.dto.CharacterDTO;
import com.trileuco.starwarsapi.dto.FilmDTO;
import com.trileuco.starwarsapi.exception.character.CharacterNotFoundException;
import com.trileuco.starwarsapi.model.Page;
import com.trileuco.starwarsapi.model.swapi.*;
import com.trileuco.starwarsapi.repository.*;
import com.trileuco.starwarsapi.service.CharacterRestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CharacterRestServiceTest {

    @Mock
    private CharacterRestRepository characterRestRepository;
    @Mock
    private FilmRestRepository filmRestRepository;
    @Mock
    private VehicleRestRepository vehicleRestRepository;
    @Mock
    private StarshipRestRepository starshipRestRepository;
    @Mock
    private PlanetRestRepository planetRestRepository;
    private CharacterRestService cut;

    @BeforeEach
    void setUp() {
        cut = new CharacterRestService(
                characterRestRepository,
                filmRestRepository,
                vehicleRestRepository,
                starshipRestRepository,
                planetRestRepository
        );
    }

    @Test
    void findCharactersThrowsCharacterNotFoundWhenPageCountIsZero() {
        PageSwapi<CharacterSwapi> page = new PageSwapi<>(
                0,
                null,
                null,
                new ArrayList<>()
        );

        String name = "Test";
        int numPage = 1;
        given(characterRestRepository.findCharacters(name, numPage))
                .willReturn(page);

        assertThatThrownBy(() -> cut.findCharacters(name, numPage))
                .isInstanceOf(CharacterNotFoundException.class)
                .hasMessageContaining(String.format("No characters found by name: %s", name));
    }

    @Test
    void findCharactersReturnCharacterPage() {
        CharacterSwapi characterSwapi = getDefaultCharacterSwapi();
        PageSwapi<CharacterSwapi> page = new PageSwapi<>(
                1,
                "https://uri.com/3",
                "https://uri.com/2",
                List.of(characterSwapi)
        );

        given(characterRestRepository.findCharacters(anyString(), anyInt()))
                .willReturn(page);

        given(filmRestRepository.findFilmByPath(anyString()))
                .willReturn(getDefaultFilmSwapi());

        given(planetRestRepository.findPlanetByPath(anyString()))
                .willReturn(getDefaultPlanetSwapi());

        given(vehicleRestRepository.findVehicleByPath(anyString()))
                .willReturn(getDefaultVehicleSwapi());

        given(starshipRestRepository.findStarshipByPath(anyString()))
                .willReturn(getDefaultStarshipSwapi());

        Page<CharacterDTO> expectedPage = new Page<>(
                1,
                3,
                2,
                List.of(getDefaultCharacterDTO())
        );

        Page<CharacterDTO> resultPage = cut.findCharacters(anyString(), anyInt());

        assertThat(resultPage).isEqualTo(expectedPage);
    }

    @Test
    void findCharactersDoesntCallStarshipRepositoryWhenHomeWorldIsNull() {
        CharacterSwapi characterSwapi = getDefaultCharacterSwapi();
        characterSwapi.setHomeWorld(null);
        PageSwapi<CharacterSwapi> page = new PageSwapi<>(
                1,
                "https://uri.com/3",
                "https://uri.com/2",
                List.of(characterSwapi)
        );

        given(characterRestRepository.findCharacters(anyString(), anyInt()))
                .willReturn(page);

        given(filmRestRepository.findFilmByPath(anyString()))
                .willReturn(getDefaultFilmSwapi());

        given(vehicleRestRepository.findVehicleByPath(anyString()))
                .willReturn(getDefaultVehicleSwapi());

        given(starshipRestRepository.findStarshipByPath(anyString()))
                .willReturn(getDefaultStarshipSwapi());

        Page<CharacterDTO> resultPage = cut.findCharacters(anyString(), anyInt());

        assertThat(resultPage.getResults().get(0).getPlanetName()).isNull();
        verify(planetRestRepository, never()).findPlanetByPath(anyString());
    }

    @Test
    void findCharactersDoesntCallStarshipAndVehicleRepositoryWhenTheyAreEmptyAndDoesntSetFastestTransportName() {
        CharacterSwapi characterSwapi = getDefaultCharacterSwapi();
        characterSwapi.setStarships(Collections.emptyList());
        characterSwapi.setVehicles(Collections.emptyList());
        PageSwapi<CharacterSwapi> page = new PageSwapi<>(
                1,
                "https://uri.com/3",
                "https://uri.com/2",
                List.of(characterSwapi)
        );

        given(characterRestRepository.findCharacters(anyString(), anyInt()))
                .willReturn(page);

        given(filmRestRepository.findFilmByPath(anyString()))
                .willReturn(getDefaultFilmSwapi());

        given(planetRestRepository.findPlanetByPath(anyString()))
                .willReturn(getDefaultPlanetSwapi());

        Page<CharacterDTO> resultPage = cut.findCharacters(anyString(), anyInt());

        assertThat(resultPage.getResults().get(0).getFastestVehicle()).isNull();
        verify(starshipRestRepository, never()).findStarshipByPath(anyString());
        verify(vehicleRestRepository, never()).findVehicleByPath(anyString());
    }

    @Test
    void findCharactersDoesntCallFilmRepositoryWhenFilmListIsEmpty() {
        CharacterSwapi characterSwapi = getDefaultCharacterSwapi();
        characterSwapi.setFilms(Collections.emptyList());
        PageSwapi<CharacterSwapi> page = new PageSwapi<>(
                1,
                "https://uri.com/3",
                "https://uri.com/2",
                List.of(characterSwapi)
        );

        given(characterRestRepository.findCharacters(anyString(), anyInt()))
                .willReturn(page);

        given(planetRestRepository.findPlanetByPath(anyString()))
                .willReturn(getDefaultPlanetSwapi());

        given(vehicleRestRepository.findVehicleByPath(anyString()))
                .willReturn(getDefaultVehicleSwapi());

        given(starshipRestRepository.findStarshipByPath(anyString()))
                .willReturn(getDefaultStarshipSwapi());

        Page<CharacterDTO> resultPage = cut.findCharacters(anyString(), anyInt());

        assertThat(resultPage.getResults().get(0).getFilms()).isEmpty();
        verify(filmRestRepository, never()).findFilmByPath(anyString());
    }

    @Test
    void findCharactersPageNextAndPreviousIsNullWhenTheyAreNullInPageSwapi() {
        CharacterSwapi characterSwapi = getDefaultCharacterSwapi();
        PageSwapi<CharacterSwapi> page = new PageSwapi<>(
                1,
                null,
                null,
                List.of(characterSwapi)
        );

        given(characterRestRepository.findCharacters(anyString(), anyInt()))
                .willReturn(page);

        given(filmRestRepository.findFilmByPath(anyString()))
                .willReturn(getDefaultFilmSwapi());

        given(planetRestRepository.findPlanetByPath(anyString()))
                .willReturn(getDefaultPlanetSwapi());

        given(vehicleRestRepository.findVehicleByPath(anyString()))
                .willReturn(getDefaultVehicleSwapi());

        given(starshipRestRepository.findStarshipByPath(anyString()))
                .willReturn(getDefaultStarshipSwapi());

        Page<CharacterDTO> resultPage = cut.findCharacters(anyString(), anyInt());

        assertThat(resultPage.getNext()).isNull();
        assertThat(resultPage.getPrevious()).isNull();
    }

    private CharacterSwapi getDefaultCharacterSwapi() {
        CharacterSwapi characterSwapi = new CharacterSwapi();
        characterSwapi.setName("name");
        characterSwapi.setBirthYear("2BBY");
        characterSwapi.setGender("M");
        characterSwapi.setHomeWorld("TestWorld");
        characterSwapi.setFilms(List.of("film"));
        characterSwapi.setVehicles(List.of("vehicle"));
        characterSwapi.setStarships(List.of("starhip"));
        return characterSwapi;
    }

    private CharacterDTO getDefaultCharacterDTO() {
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setName("name");
        characterDTO.setBirthYear("2BBY");
        characterDTO.setGender("M");
        characterDTO.setPlanetName(getDefaultPlanetSwapi().getName());
        characterDTO.setFastestVehicle(getDefaultStarshipSwapi().getName());
        characterDTO.setFilms(List.of(getDefaultFilmDTO()));
        return characterDTO;
    }

    private FilmSwapi getDefaultFilmSwapi() {
        FilmSwapi filmSwapi = new FilmSwapi();
        filmSwapi.setTitle("Title Film");
        filmSwapi.setReleaseDate(LocalDate.of(1992, 12, 25));
        return filmSwapi;
    }

    private FilmDTO getDefaultFilmDTO() {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setName("Title Film");
        filmDTO.setReleaseDate(LocalDate.of(1992, 12, 25));
        return filmDTO;
    }

    private PlanetSwapi getDefaultPlanetSwapi() {
        PlanetSwapi planetSwapi = new PlanetSwapi();
        planetSwapi.setName("HomeWorld");
        return planetSwapi;
    }

    private VehicleSwapi getDefaultVehicleSwapi() {
        VehicleSwapi vehicleSwapi = new VehicleSwapi();
        vehicleSwapi.setName("Vehicle");
        vehicleSwapi.setMaxAtmospheringSpeed("100");
        return vehicleSwapi;
    }

    private StarshipSwapi getDefaultStarshipSwapi() {
        StarshipSwapi starshipSwapi = new StarshipSwapi();
        starshipSwapi.setName("Starship");
        starshipSwapi.setMaxAtmospheringSpeed("200");
        return starshipSwapi;
    }
}
