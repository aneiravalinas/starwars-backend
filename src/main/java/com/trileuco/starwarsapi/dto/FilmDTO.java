package com.trileuco.starwarsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;

public class FilmDTO {

    private String name;
    @JsonProperty(value = "release_date")
    private LocalDate releaseDate;

    public FilmDTO() {}

    public FilmDTO(String name, LocalDate releaseDate) {
        this.name = name;
        this.releaseDate = releaseDate;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmDTO filmDTO = (FilmDTO) o;
        return Objects.equals(name, filmDTO.name) && Objects.equals(releaseDate, filmDTO.releaseDate);
    }

    @Override
    public String toString() {
        return "FilmDTO{" +
                "name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
