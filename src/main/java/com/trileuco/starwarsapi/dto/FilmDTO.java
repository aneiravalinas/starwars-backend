package com.trileuco.starwarsapi.dto;

import java.time.LocalDate;

public class FilmDTO {

    private String name;
    private LocalDate releaseDate;

    public FilmDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "FilmDTO{" +
                "name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
