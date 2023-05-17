package com.trileuco.starwarsapi.controller;

import com.trileuco.starwarsapi.dto.CharacterDTO;
import com.trileuco.starwarsapi.model.Page;
import com.trileuco.starwarsapi.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/person-info")
public class CharacterController {


    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public Page<CharacterDTO> findCharacters(@RequestParam Optional<String> name, @RequestParam(name = "num_page", defaultValue = "1") int numPage ) {
        return characterService.findCharacters(name.orElse(null), numPage);
    }
}
