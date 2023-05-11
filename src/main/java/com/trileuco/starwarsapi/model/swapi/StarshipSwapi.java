package com.trileuco.starwarsapi.model.swapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class StarshipSwapi extends TransportSwapi {
    @JsonProperty(value = "hyperdrive_rating")
    private String hyperdriveRating;
    @JsonProperty(value = "MGLT")
    private String mglt;
    @JsonProperty(value = "starship_class")
    private String starshipClass;

    public StarshipSwapi(String name, String model, String manufacturer, String costInCredits, String length, String maxAtmospheringSpeed, String crew, String passengers, String cargoCapacity, String consumables,
                         List<String> pilots, List<String> films,
                         LocalDateTime created, LocalDateTime edited, String url, String hyperdriveRating, String mglt, String starshipClass) {
        super(name, model, manufacturer, costInCredits, length, maxAtmospheringSpeed, crew, passengers, cargoCapacity, consumables, pilots, films, created, edited, url);
        this.hyperdriveRating = hyperdriveRating;
        this.mglt = mglt;
        this.starshipClass = starshipClass;
    }

    public String getHyperdriveRating() {
        return hyperdriveRating;
    }

    public void setHyperdriveRating(String hyperdriveRating) {
        this.hyperdriveRating = hyperdriveRating;
    }

    public String getMglt() {
        return mglt;
    }

    public void setMglt(String mglt) {
        this.mglt = mglt;
    }

    public String getStarshipClass() {
        return starshipClass;
    }

    public void setStarshipClass(String starshipClass) {
        this.starshipClass = starshipClass;
    }

    @Override
    public String toString() {
        return "StarshipSwapi{" +
                "hyperdriveRating='" + hyperdriveRating + '\'' +
                ", mglt='" + mglt + '\'' +
                ", starshipClass='" + starshipClass + '\'' +
                "} " + super.toString();
    }
}
