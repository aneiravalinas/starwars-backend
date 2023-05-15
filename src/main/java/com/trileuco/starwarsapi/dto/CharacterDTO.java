package com.trileuco.starwarsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CharacterDTO {

    private String name;
    @JsonProperty(value = "birth_year")
    private String birthYear;
    private String gender;
    @JsonProperty(value = "planet_name")
    private String planetName;
    @JsonProperty(value = "fastest_vehicle_driven")
    private String fastestVehicle;
    private List<FilmDTO> films;

    public CharacterDTO() {
        this.films = new ArrayList<>();
    }

    public CharacterDTO(String name, String birthYear, String gender, String planetName, String fastestVehicle, List<FilmDTO> films) {
        this.name = name;
        this.birthYear = birthYear;
        this.gender = gender;
        this.planetName = planetName;
        this.fastestVehicle = fastestVehicle;
        this.films = films;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public String getFastestVehicle() {
        return fastestVehicle;
    }

    public void setFastestVehicle(String fastestVehicle) {
        this.fastestVehicle = fastestVehicle;
    }

    public List<FilmDTO> getFilms() {
        return films;
    }

    public void setFilms(List<FilmDTO> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterDTO that = (CharacterDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(birthYear, that.birthYear) && Objects.equals(gender, that.gender) && Objects.equals(planetName, that.planetName) && Objects.equals(fastestVehicle, that.fastestVehicle) && Objects.equals(films, that.films);
    }

    @Override
    public String toString() {
        return "CharacterDTO{" +
                "name='" + name + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", gender='" + gender + '\'' +
                ", planetName='" + planetName + '\'' +
                ", fastestVehicle='" + fastestVehicle + '\'' +
                ", films=" + films +
                '}';
    }
}
