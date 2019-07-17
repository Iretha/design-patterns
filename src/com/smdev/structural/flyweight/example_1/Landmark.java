package com.smdev.structural.flyweight.example_1;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Landmark {

    @Getter
    private Country country;

    private String landmarkName;

    private String landmarkDescription;

    public Landmark(String countryName, String landmarkName, String landmarkDescription) {
        this.country = CountryFactory.createCountry(countryName);
        this.landmarkName = landmarkName;
        this.landmarkDescription = landmarkDescription;
    }
}
