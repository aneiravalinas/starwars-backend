package com.trileuco.starwarsapi.service;

import com.trileuco.starwarsapi.dto.CharacterDTO;
import com.trileuco.starwarsapi.dto.FilmDTO;
import com.trileuco.starwarsapi.exception.character.CharacterNotFoundException;
import com.trileuco.starwarsapi.model.Page;
import com.trileuco.starwarsapi.model.swapi.*;
import com.trileuco.starwarsapi.repository.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class CharacterRestService implements CharacterService {

    private final CharacterRestRepository characterRepository;
    private final FilmRestRepository filmRepository;
    private final VehicleRestRepository vehicleRepository;
    private final StarshipRestRepository starshipRepository;
    private final PlanetRestRepository planetRepository;

    public CharacterRestService(CharacterRestRepository characterRepository,
                                FilmRestRepository filmRepository,
                                VehicleRestRepository vehicleRepository,
                                StarshipRestRepository starshipRepository,
                                PlanetRestRepository planetRepository) {
        this.characterRepository = characterRepository;
        this.filmRepository = filmRepository;
        this.vehicleRepository = vehicleRepository;
        this.starshipRepository = starshipRepository;
        this.planetRepository = planetRepository;
    }

    @Override
    public Page<CharacterDTO> findCharacters(String name, Integer numPage) {
        PageSwapi<CharacterSwapi> pageSwapi = this.characterRepository.findCharacters(name, numPage);

        Integer count = pageSwapi.getCount();
        Integer next = null;
        Integer previous = null;

        if ( count == 0 ) {
            throw new CharacterNotFoundException("No characters found by name: " + name);
        }

        if ( pageSwapi.getNext() != null ) {
            int nextLength = pageSwapi.getNext().length();
            next = Integer.parseInt(pageSwapi.getNext().substring(nextLength - 1));
        }

        if ( pageSwapi.getPrevious() != null ) {
            int previousLength = pageSwapi.getPrevious().length();
            previous = Integer.parseInt(pageSwapi.getPrevious().substring(previousLength - 1));
        }

        List<CharacterDTO> characterDTOS = pageSwapi.getResults()
                .parallelStream()
                .map(this::parseCharacter)
                .collect(Collectors.toList());

        return new Page<>(count, next, previous, characterDTOS);

    }


    private CharacterDTO parseCharacter(CharacterSwapi characterSwapi) {

        List<FilmDTO> filmDTOS = characterSwapi.getFilms()
                .parallelStream()
                .map(this.filmRepository::findFilmByPath)
                .map(filmSwapi -> new FilmDTO(filmSwapi.getTitle(), filmSwapi.getReleaseDate()))
                .toList();

        List<VehicleSwapi> vehicleList = characterSwapi.getVehicles()
                .parallelStream()
                .map(this.vehicleRepository::findVehicleByPath)
                .toList();

        List<StarshipSwapi> starshipList = characterSwapi.getStarships()
                .parallelStream()
                .map(this.starshipRepository::findStarshipByPath)
                .toList();

        TransportSwapi fastestTransport = Stream.concat(vehicleList.stream(), starshipList.stream())
                .max(Comparator.comparingInt(transport -> Integer.parseInt(transport.getMaxAtmospheringSpeed())))
                .orElse(null);

        String fastestTransportName = (fastestTransport != null) ? fastestTransport.getName() : null;

        String planetName = null;

        if ( characterSwapi.getHomeWorld() != null ) {
            planetName = this.planetRepository.findPlanetByPath(characterSwapi.getHomeWorld()).getName();
        }

        return new CharacterDTO(characterSwapi.getName(), characterSwapi.getBirthYear(), characterSwapi.getGender(), planetName, fastestTransportName, filmDTOS);
    }

}
