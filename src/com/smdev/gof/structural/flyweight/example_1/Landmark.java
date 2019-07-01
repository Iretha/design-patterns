package com.smdev.gof.structural.flyweight.example_1;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Landmark {

    @Getter
    private Country country;

    private String name;

    private String description;

    public Landmark(String countryName, String name, String description) {
        this.country = CountryFactory.createCountry(countryName);
        this.name = name;
        this.description = description;
    }
}
