package com.trileuco.starwarsapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/say-hello")
    public String sayHelloController() {
        return "Hello World!";
    }
}
