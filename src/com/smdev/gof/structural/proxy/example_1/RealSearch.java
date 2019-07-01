package com.smdev.gof.structural.proxy.example_1;

import java.util.Arrays;

public class RealSearch implements Search {

    @Override
    public String searchFor(String... keywords) {
        return "Result: " + String.join(" ", Arrays.asList(keywords));
    }
}
