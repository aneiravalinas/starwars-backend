package com.trileuco.starwarsapi.model.swapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilmSwapi extends BaseSwapi {

    @JsonProperty(value = "episode_id")
    private String id;
    private String title;
    @JsonProperty(value = "opening_crawl")
    private String openingCrawl;
    private String director;
    private String producer;
    @JsonProperty(value = "release_date")
    private LocalDate releaseDate;
    private List<String> characters;
    private List<String> planets;
    private List<String> starships;
    private List<String> vehicles;
    private List<String> species;

    public FilmSwapi(LocalDateTime created, LocalDateTime edited, String url) {
        super(created, edited, url);
        this.characters = new ArrayList<>();
        this.planets = new ArrayList<>();
        this.starships = new ArrayList<>();
        this.species = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public void setOpeningCrawl(String openingCrawl) {
        this.openingCrawl = openingCrawl;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public List<String> getPlanets() {
        return planets;
    }

    public void setPlanets(List<String> planets) {
        this.planets = planets;
    }

    public List<String> getStarships() {
        return starships;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return "FilmSwapi{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", openingCrawl='" + openingCrawl + '\'' +
                ", director='" + director + '\'' +
                ", producer='" + producer + '\'' +
                ", releaseDate=" + releaseDate +
                ", characters=" + characters +
                ", planets=" + planets +
                ", starships=" + starships +
                ", vehicles=" + vehicles +
                ", species=" + species +
                "} " + super.toString();
    }
}
