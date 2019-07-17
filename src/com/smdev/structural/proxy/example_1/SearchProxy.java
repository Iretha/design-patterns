package com.smdev.structural.proxy.example_1;

import java.util.*;

public class SearchProxy implements Search {

    private static Set<String> bannedSearches = new HashSet<>(Arrays.asList("big", "something-you-should-not-have-access-to"));

    private RealSearch searchService;

    public SearchProxy() {
        this.searchService = new RealSearch();
    }

    @Override
    public String searchFor(String... keywords) throws Exception {
        for (String keyword : keywords) {
            if (bannedSearches.contains(keyword.trim().toLowerCase())) {
                throw new Exception(keyword.toUpperCase() + " is a restricted keyword!");
            }
        }
        return this.searchService.searchFor(keywords);
    }
}
