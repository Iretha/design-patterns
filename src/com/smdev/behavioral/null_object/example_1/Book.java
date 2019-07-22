package com.smdev.behavioral.null_object.example_1;

import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@ToString
public class Book {

    private String title;

    private Author author;

    public Book(String title) {
        this(title, null);
    }

    public Book(String title, String authorName) {
        this.title = title;

        if (StringUtils.isNotEmpty(authorName)) {
            this.author = new BookAuthor(authorName);
        } else {
            this.author = NullAuthor.get();
        }
    }
}
