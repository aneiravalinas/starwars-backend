package com.trileuco.starwarsapi.dto;

import java.util.Set;

public class CharacterDTO {

    private String name;
    private String birthYear;
    private String gender;
    private String planetName;
    private String fastestVehicle;
    private Set<FilmDTO> films;

    public CharacterDTO() {}

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

    public Set<FilmDTO> getFilms() {
        return films;
    }

    public void setFilms(Set<FilmDTO> films) {
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
