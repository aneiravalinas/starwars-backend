package com.trileuco.starwarsapi.service;

import com.trileuco.starwarsapi.dto.CharacterDTO;
import com.trileuco.starwarsapi.model.Page;


public interface CharacterService {

    Page<CharacterDTO> findCharacters(String name, Integer numPage);
}
