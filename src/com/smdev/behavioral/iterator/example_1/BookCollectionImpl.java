package com.smdev.behavioral.iterator.example_1;

import java.util.ArrayList;
import java.util.List;

public class BookCollectionImpl implements BookCollection {

    private List<Book> elements = new ArrayList<>();

    @Override
    public void add(Book book) {
        this.elements.add(book);
    }

    @Override
    public void remove(Book book) {
        this.elements.remove(book);
    }

    @Override
    public void clear() {
        this.elements.clear();
    }

    @Override
    public BookIterator keywordIterator(String keyword) {
        return new BookIteratorByKeyword(new ArrayList<>(this.elements), keyword);
    }

    @Override
    public BookIterator genreIterator(BookGenre genre) {
        return new BookIteratorByGenre(new ArrayList<>(this.elements), genre);
    }

    private class BookIteratorByKeyword implements BookIterator {

        private List<Book> elements;

        private String keyword;

        private int position = 0;

        public BookIteratorByKeyword(List<Book> elements, String keyword) {
            this.elements = elements;
            this.keyword = keyword.toLowerCase();
        }

        @Override
        public boolean hasNext() {
            while (this.position < this.elements.size()) {
                Book b = this.elements.get(this.position);
                if (b.getTitle().toLowerCase().contains(this.keyword)) {
                    return true;
                }
                this.position++;
            }
            return false;
        }

        @Override
        public Book next() {
            Book book = null;
            if (this.position >= 0 && this.position < this.elements.size()) {
                book = this.elements.get(this.position);
                this.position++;
            }
            return book;
        }
    }

    private class BookIteratorByGenre implements BookIterator {

        private List<Book> elements;

        private BookGenre genre;

        private int position = 0;

        public BookIteratorByGenre(List<Book> elements, BookGenre genre) {
            this.elements = elements;
            this.genre = genre;
        }

        @Override
        public boolean hasNext() {
            while (this.position < this.elements.size()) {
                Book b = this.elements.get(this.position);
                if (this.genre.equals(b.getGenre())) {
                    return true;
                }
                this.position++;
            }
            return false;
        }

        @Override
        public Book next() {
            Book book = null;
            if (this.position >= 0 && this.position < this.elements.size()) {
                book = this.elements.get(this.position);
                this.position++;
            }
            return book;
        }
    }
}
