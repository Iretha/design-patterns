package com.smdev.structural.flyweight.example_1;

import java.util.HashMap;
import java.util.Map;

public final class CountryFactory {

    private static Map<String, Country> cache = new HashMap<>();

    private CountryFactory() {
        // hidden
    }

    public static Country createCountry(String name) {
        if (!cache.containsKey(name)) {
            cache.put(name, new Country(name));
        }
        return cache.get(name);
    }
}
