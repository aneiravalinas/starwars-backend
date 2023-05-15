package com.trileuco.starwarsapi.model.swapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CharacterSwapi extends BaseSwapi {
    private String name;
    private String height;
    private String mass;
    @JsonProperty(value = "hair_color")
    private String hairColor;
    @JsonProperty(value = "skin_color")
    private String skinColor;
    @JsonProperty(value = "eye_color")
    private String eyeColor;
    @JsonProperty(value = "birth_year")
    private String birthYear;
    private String gender;
    @JsonProperty(value = "homeworld")
    private String homeWorld;
    private List<String> films;
    private List<String> species;
    private List<String> vehicles;
    private List<String> starships;

    public CharacterSwapi() {
        this.films = new ArrayList<>();
        this.species = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.starships = new ArrayList<>();
    }

    public CharacterSwapi(LocalDateTime created, LocalDateTime edited, String url, String name, String height, String mass, String hairColor, String skinColor, String eyeColor, String birthYear, String gender, String homeWorld,
                          List<String> films, List<String> species, List<String> vehicles, List<String> starships) {
        super(created, edited, url);
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hairColor = hairColor;
        this.skinColor = skinColor;
        this.eyeColor = eyeColor;
        this.birthYear = birthYear;
        this.gender = gender;
        this.homeWorld = homeWorld;
        this.films = films;
        this.species = species;
        this.vehicles = vehicles;
        this.starships = starships;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
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

    public String getHomeWorld() {
        return homeWorld;
    }

    public void setHomeWorld(String homeWorld) {
        this.homeWorld = homeWorld;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public List<String> getStarships() {
        return starships;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CharacterSwapi that = (CharacterSwapi) o;
        return Objects.equals(name, that.name) && Objects.equals(height, that.height) && Objects.equals(mass, that.mass) && Objects.equals(hairColor, that.hairColor) && Objects.equals(skinColor, that.skinColor) && Objects.equals(eyeColor, that.eyeColor) && Objects.equals(birthYear, that.birthYear) && Objects.equals(gender, that.gender) && Objects.equals(homeWorld, that.homeWorld) && Objects.equals(films, that.films) && Objects.equals(species, that.species) && Objects.equals(vehicles, that.vehicles) && Objects.equals(starships, that.starships);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, height, mass, hairColor, skinColor, eyeColor, birthYear, gender, homeWorld, films, species, vehicles, starships);
    }

    @Override
    public String toString() {
        return "CharacterSwapi{" +
                "name='" + name + '\'' +
                ", height='" + height + '\'' +
                ", mass='" + mass + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", skinColor='" + skinColor + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", gender='" + gender + '\'' +
                ", homeWorld='" + homeWorld + '\'' +
                ", films=" + films +
                ", species=" + species +
                ", vehicles=" + vehicles +
                ", starships=" + starships +
                "} " + super.toString();
    }
}

