package com.smdev.behavioral.iterator.example_1;

public interface BookCollection {

    void add(Book book);

    void remove(Book book);

    void clear();

    BookIterator keywordIterator(String keyword);

    BookIterator genreIterator(BookGenre genre);
}
