---
layout: default
title: Iterator
parent: Behavioral Design Patterns
nav_order: 3030
permalink: /behavioral/iterator
---


# The Iterator Design Pattern 

GoF Design Patterns -> Behavioral Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/gof/behavioral/iterator) 

## What problems does it solve? Why to use it?
You can travers/ access elements of aggregate objects (like custom collections) sequentially without exposing their internal structure.
The actual traversing algorithm will be implemented in the aggregate objects.

Glossary:
- Aggregate - aggregate object or collection, which elements we want to iterate
- Concrete Aggregate - specific aggregate object
- Iterator - the generic interface that allows us to iterate over the elements of the aggregate
- Concrete Iterator - specific iterator for the specific aggregate object

## When to use it?
To traverse elements of aggregate objects (like collections etc) sequentially. The order will be based on your business logic.
You can even implement multiple iterators

## Pros:
- Hides the implementation of traversing
- Decouples collection classes and algorithms
- More flexible than internal iterators (i.e. you can implement an iterator that traverses elements backward or based on 
another specification or to traverse 2 collections at the same time)

## Cons:
- The iterator may not be aware if elements change during the iteration
- It can be an overkill if you can actually use the internal iterators instead of creating you own external iterator

## Examples from Java API
Recognizable by behavioral methods sequentially returning instances of a different type from a queue
```
All implementations of java.util.Iterator (thus among others also java.util.Scanner!).
All implementations of java.util.Enumeration
```
## Examples

### Example 1 - How to implement it?
Let's say we have a collection of books. Each book has a title and genre. We may want to iterate our collection by 
some keyword in the title or by genre. This can be done by implementing two different iterators. Let's do it.

1). Create the Book class (this is the type of the elements of the aggregate type) and the Book Genre enum
```java
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
```
```java
public enum BookGenre {
    MYSTERY,
    FANTASY,
    POETRY
}
```
2). We need a generic iterator that will iterate through the collection elements
```java
public interface BookIterator {

    boolean hasNext();

    Book next();
}
```
3). We need an interface for the book collection (out aggregate object)
```java
public interface BookCollection {

    void add(Book book);

    void remove(Book book);

    void clear();

    BookIterator keywordIterator(String keyword);

    BookIterator genreIterator(BookGenre genre);
}
```
4). We need an implementation of the collection. This is the place, where we are going to implement 
the both iterators. We will place them as private inner classes, in order not to expose the traversal.

```java
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
```
5). A Demo class
```java
public class _Main {

    public static void main(String[] args) {
        BookCollection collection = new BookCollectionImpl();
        collection.add(new Book("The Ruin of Kings /A Chorus of Dragons, #1/ by Jenn Lyons", BookGenre.FANTASY));
        collection.add(new Book("Black Leopard, Red Wolf (The Dark Star Trilogy)", BookGenre.FANTASY));
        collection.add(new Book("The Bird King by G. Willow Wilson - Goodreads", BookGenre.FANTASY));
        collection.add(new Book("The Strength In Our Scars, by Bianca Sparacino", BookGenre.POETRY));
        collection.add(new Book("End of Watch /Bill Hodges Trilogy, #3/ by Stephen King", BookGenre.MYSTERY));
        collection.add(new Book("The Bird King by G. Willow Wilson - Goodreads", BookGenre.MYSTERY));

        System.out.println("========== Keyword Iterator - \"King\" =========");
        BookIterator keywordIt = collection.keywordIterator("King");
        traverseCollection(keywordIt);

        System.out.println("========== Genre Iterator - \"Mystery\" =========");
        BookIterator mysteryIt = collection.genreIterator(BookGenre.MYSTERY);
        traverseCollection(mysteryIt);

        System.out.println("========== Genre Iterator - \"Fantasy\" =========");
        BookIterator fantasyIt = collection.genreIterator(BookGenre.FANTASY);
        traverseCollection(fantasyIt);
    }

    private static void traverseCollection(BookIterator iterator) {
        Book book;
        while (iterator.hasNext()) {
            book = iterator.next();
            if (book != null) {
                System.out.println(book);
            }
        }
        System.out.println();
    }
}
```
Output:
```
========== Keyword Iterator - "King" =========
Book(title=The Ruin of Kings /A Chorus of Dragons, #1/ by Jenn Lyons, genre=FANTASY)
Book(title=The Bird King by G. Willow Wilson - Goodreads, genre=FANTASY)
Book(title=End of Watch /Bill Hodges Trilogy, #3/ by Stephen King, genre=MYSTERY)
Book(title=The Bird King by G. Willow Wilson - Goodreads, genre=MYSTERY)

========== Genre Iterator - "Mystery" =========
Book(title=End of Watch /Bill Hodges Trilogy, #3/ by Stephen King, genre=MYSTERY)
Book(title=The Bird King by G. Willow Wilson - Goodreads, genre=MYSTERY)

========== Genre Iterator - "Fantasy" =========
Book(title=The Ruin of Kings /A Chorus of Dragons, #1/ by Jenn Lyons, genre=FANTASY)
Book(title=Black Leopard, Red Wolf (The Dark Star Trilogy), genre=FANTASY)
Book(title=The Bird King by G. Willow Wilson - Goodreads, genre=FANTASY)
```