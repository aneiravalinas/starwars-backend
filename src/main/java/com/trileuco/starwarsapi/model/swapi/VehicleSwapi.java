package com.trileuco.starwarsapi.model.swapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class VehicleSwapi extends TransportSwapi{

    @JsonProperty("vehicle_class")
    private String vehicleClass;

    public VehicleSwapi(String name, String model, String manufacturer, String costInCredits, String length, String maxAtmospheringSpeed, String crew, String passengers, String cargoCapacity, String consumables,
                        List<String> pilots, List<String> films, LocalDateTime created, LocalDateTime edited, String url, String vehicleClass) {
        super(name, model, manufacturer, costInCredits, length, maxAtmospheringSpeed, crew, passengers, cargoCapacity, consumables, pilots, films, created, edited, url);
        this.vehicleClass = vehicleClass;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    @Override
    public String toString() {
        return "VehicleSwapi{" +
                "vehicleClass='" + vehicleClass + '\'' +
                "} " + super.toString();
    }
}
