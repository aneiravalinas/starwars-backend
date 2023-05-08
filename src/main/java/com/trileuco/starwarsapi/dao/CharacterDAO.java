package com.trileuco.starwarsapi.dao;

import com.trileuco.starwarsapi.model.Character;
import com.trileuco.starwarsapi.model.Page;


public interface CharacterDAO {

    Page<Character> findCharacters(String name, Integer numberPage);
}
