package com.smdev.behavioral.iterator.example_1;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Book {
    @Getter
    private String title;
    @Getter
    private BookGenre genre;

    public Book(String title, BookGenre genre) {
        this.title = title;
        this.genre = genre;
    }
}
