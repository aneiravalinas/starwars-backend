package com.trileuco.starwarsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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

    public CharacterDTO() {}

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
