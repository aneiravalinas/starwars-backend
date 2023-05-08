package com.trileuco.starwarsapi.dao;

import com.trileuco.starwarsapi.model.Character;
import com.trileuco.starwarsapi.model.Page;
import com.trileuco.starwarsapi.repository.CharacterRestRepository;
import com.trileuco.starwarsapi.repository.FilmRestRepository;
import com.trileuco.starwarsapi.repository.StarshipRestRepository;
import com.trileuco.starwarsapi.repository.VehicleRestRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CharacterDAORest implements CharacterDAO {

    private final CharacterRestRepository characterRepository;
    private final FilmRestRepository filmRepository;
    private final VehicleRestRepository vehicleRepository;
    private final StarshipRestRepository starshipRepository;

    public CharacterDAORest(CharacterRestRepository characterRepository,
                            FilmRestRepository filmRepository,
                            VehicleRestRepository vehicleRepository,
                            StarshipRestRepository starshipRepository) {
        this.characterRepository = characterRepository;
        this.filmRepository = filmRepository;
        this.vehicleRepository = vehicleRepository;
        this.starshipRepository = starshipRepository;
    }

    @Override
    public Page<Character> findCharacters(String name, Integer numberPage) {

        return null;
    }
}
