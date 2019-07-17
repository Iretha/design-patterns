package com.smdev.structural.proxy.example_1;

import java.util.Arrays;

public class _Main {

    public static void main(String[] args) {
        search("design", "patterns");
        search("big", "data", "explained");
        search("data", "explained");
    }

    private static void search(String... keywords) {
        System.out.println("New search: " + Arrays.toString(keywords));

        try {
            Search search = new SearchProxy();
            String result = search.searchFor(keywords);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("");
    }
}
