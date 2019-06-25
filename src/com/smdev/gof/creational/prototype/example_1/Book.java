package com.smdev.gof.creational.prototype.example_1;

import lombok.ToString;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
public class Book implements Cloneable {

    private String isbn;

    private String author;

    private int editionNo;

    private Map<Integer, List<Image>> illustrationsByPageNo = new HashMap<>();

    private Map<Integer, List<String>> textByPageNo = new HashMap<>();

    public Book(String isbn) {
        this.isbn = isbn;
        loadFromDbByIsbn();
    }

    private void loadFromDbByIsbn(){
        this.author = "Jon Snow";
        this.editionNo = (int) Math.random();

        loadIllustrationsFromDbByIsbn();
        loadPagesFromDbByIsbn();
    }

    /**
     * Fetching images from DB is very heavy operation, especially when files are large
     */
    private void loadIllustrationsFromDbByIsbn(){
        // do some expensive operation to fetch all images
    }

    /**
     * Fetching pages from DB
     */
    private void loadPagesFromDbByIsbn(){
        // do some expensive operation to fetch all pages
    }

    public Book(String isbn, String author, int edition, Map<Integer, List<Image>> illustrationsByPageNo, Map<Integer, List<String>> textByPageNo) {
        this.isbn = isbn;
        this.author = author;
        this.editionNo = edition;
        this.illustrationsByPageNo = illustrationsByPageNo;
        this.textByPageNo = textByPageNo;
    }

    @Override
    protected Book clone() throws CloneNotSupportedException {
        Book nextEdition = new Book(this.isbn, this.author, this.editionNo + 1, this.illustrationsByPageNo, this.textByPageNo);
        return nextEdition;
    }
}
