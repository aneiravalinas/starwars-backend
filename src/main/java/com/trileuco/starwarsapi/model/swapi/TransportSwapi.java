package com.trileuco.starwarsapi.model.swapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class TransportSwapi extends BaseSwapi {

    private String name;
    private String model;
    private String manufacturer;
    @JsonProperty(value = "cost_in_credits")
    private String costInCredits;
    private String length;
    @JsonProperty(value = "max_atmosphering_speed")
    private String maxAtmospheringSpeed;
    private String crew;
    private String passengers;
    @JsonProperty(value = "cargo_capacity")
    private String cargoCapacity;
    private String consumables;
    private List<String> pilots;
    private List<String> films;

    public TransportSwapi() {
        this.pilots = new ArrayList<>();
        this.films = new ArrayList<>();
    }

    public TransportSwapi(String name, String model, String manufacturer, String costInCredits, String length,
                          String maxAtmospheringSpeed, String crew, String passengers, String cargoCapacity, String consumables,
                          List<String> pilots, List<String> films,
                          LocalDateTime created, LocalDateTime edited, String url) {
        super(created, edited, url);
        this.name = name;
        this.model = model;
        this.manufacturer = manufacturer;
        this.costInCredits = costInCredits;
        this.length = length;
        this.maxAtmospheringSpeed = maxAtmospheringSpeed;
        this.crew = crew;
        this.passengers = passengers;
        this.cargoCapacity = cargoCapacity;
        this.consumables = consumables;
        this.pilots = pilots;
        this.films = films;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCostInCredits() {
        return costInCredits;
    }

    public void setCostInCredits(String costInCredits) {
        this.costInCredits = costInCredits;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getMaxAtmospheringSpeed() {
        return maxAtmospheringSpeed;
    }

    public void setMaxAtmospheringSpeed(String maxAtmospheringSpeed) {
        this.maxAtmospheringSpeed = maxAtmospheringSpeed;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(String cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public String getConsumables() {
        return consumables;
    }

    public void setConsumables(String consumables) {
        this.consumables = consumables;
    }

    public List<String> getPilots() {
        return pilots;
    }

    public void setPilots(List<String> pilots) {
        this.pilots = pilots;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransportSwapi that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(name, that.name) && Objects.equals(model, that.model) && Objects.equals(manufacturer, that.manufacturer) && Objects.equals(costInCredits, that.costInCredits) && Objects.equals(length, that.length) && Objects.equals(maxAtmospheringSpeed, that.maxAtmospheringSpeed) && Objects.equals(crew, that.crew) && Objects.equals(passengers, that.passengers) && Objects.equals(cargoCapacity, that.cargoCapacity) && Objects.equals(consumables, that.consumables) && Objects.equals(pilots, that.pilots) && Objects.equals(films, that.films);
    }

    @Override
    public String toString() {
        return "TransportSwapi{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", costInCredits='" + costInCredits + '\'' +
                ", length='" + length + '\'' +
                ", maxAtmospheringSpeed='" + maxAtmospheringSpeed + '\'' +
                ", crew='" + crew + '\'' +
                ", passengers='" + passengers + '\'' +
                ", cargoCapacity='" + cargoCapacity + '\'' +
                ", consumables='" + consumables + '\'' +
                ", pilots=" + pilots +
                ", films=" + films +
                "} " + super.toString();
    }
}
