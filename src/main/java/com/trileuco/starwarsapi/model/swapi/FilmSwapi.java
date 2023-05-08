package com.trileuco.starwarsapi.model.swapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

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
    private String[] characters;
    private String[] planets;
    private String[] starships;
    private String[] vehicles;
    private String[] species;
    private LocalDateTime created;
    private LocalDateTime edited;
    private String url;

    public FilmSwapi(String id, String title, String openingCrawl, String director, String producer, LocalDate releaseDate,
                     String[] characters, String[] planets, String[] starships, String[] vehicles, String[] species,
                     LocalDateTime created, LocalDateTime edited, String url) {
        super(created, edited, url);
        this.id = id;
        this.title = title;
        this.openingCrawl = openingCrawl;
        this.director = director;
        this.producer = producer;
        this.releaseDate = releaseDate;
        this.characters = characters;
        this.planets = planets;
        this.starships = starships;
        this.vehicles = vehicles;
        this.species = species;
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

    public String[] getCharacters() {
        return characters;
    }

    public void setCharacters(String[] characters) {
        this.characters = characters;
    }

    public String[] getPlanets() {
        return planets;
    }

    public void setPlanets(String[] planets) {
        this.planets = planets;
    }

    public String[] getStarships() {
        return starships;
    }

    public void setStarships(String[] starships) {
        this.starships = starships;
    }

    public String[] getVehicles() {
        return vehicles;
    }

    public void setVehicles(String[] vehicles) {
        this.vehicles = vehicles;
    }

    public String[] getSpecies() {
        return species;
    }

    public void setSpecies(String[] species) {
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
                ", characters=" + Arrays.toString(characters) +
                ", planets=" + Arrays.toString(planets) +
                ", starships=" + Arrays.toString(starships) +
                ", vehicles=" + Arrays.toString(vehicles) +
                ", species=" + Arrays.toString(species) +
                ", created=" + created +
                ", edited=" + edited +
                ", url='" + url + '\'' +
                "} " + super.toString();
    }
}
