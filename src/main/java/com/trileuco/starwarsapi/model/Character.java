package com.trileuco.starwarsapi.model;

import java.util.HashSet;
import java.util.Set;

public class Character {
    private String id;
    private String name;
    private String birthYear;
    private String homeWorld;
    private Set<Film> films;
    private Set<Vehicle> vehicles;
    private Set<Starship> starships;

    public Character() {
        this.films = new HashSet<>();
        this.vehicles = new HashSet<>();
        this.starships = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getHomeWorld() {
        return homeWorld;
    }

    public void setHomeWorld(String homeWorld) {
        this.homeWorld = homeWorld;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Set<Starship> getStarships() {
        return starships;
    }

    public void setStarships(Set<Starship> starships) {
        this.starships = starships;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", homeWorld='" + homeWorld + '\'' +
                ", films=" + films +
                ", vehicles=" + vehicles +
                ", starships=" + starships +
                '}';
    }
}
