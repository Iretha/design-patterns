---
layout: default
title: Prototype
parent: Creational Design Patterns
nav_order: 1030
permalink: /creational/prototype
---

# The Prototype Design Pattern

GoF Design Patterns -> Creational Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/gof/creational/prototype) 

## What problems does it solve? Why to use it?
Prototype patterns is required, when object creation is time consuming, and costly operation, 
so we create object with existing object itself. One of the best available way to create object 
from existing objects are clone() method. Clone is the simplest approach to implement prototype 
pattern. However, it is your call to decide how to copy existing object based on your business model.

The existing object is called "prototype".

## When to use it?
The concept is to copy an existing object rather than creating a new instance from scratch, 
something that may include costly operations. The existing object acts as a prototype and 
contains the state of the object. The newly copied object may change same properties only if required. 
This approach saves costly resources and time, especially when the object creation is a heavy process.

## Pros:
- Faster object creation

## Cons:
- Hard to extend, because you should be careful when adding new properties. Sometimes you may need
to extends the clone method implementation as well
- You should decide depending on business requirements to do a shell copy or deep copy
- Each subclass of Prototype must implement the clone() operation which may be difficult, 
when the classes under consideration already exist. Also implementing clone() can be difficult when 
their internals include objects that don’t support copying or have circular references.

## Examples from Java API
Recognizeable by creational methods returning a different instance of itself with the same properties
```
java.lang.Object#clone() (the class has to implement java.lang.Cloneable)
```

## Examples

### Example 1 - How to implement it?

We are going to publish a new edition of a book and we should keep the same images and pages. The only thing we are going to 
update is the editionNo. Because every file of the images is large, we could use Prototype pattern and reuse them, instead of fetching them 
again.
 
To implement the pattern, we need to:
1). Create a Book class, which has "isbn", "author", "editionNo", "illustrationsByPageNo" and "textByPageNo"
```java
public class Book {

    private String isbn;

    private String author;

    private int editionNo;

    private Map<Integer, List<Image>> illustrationsByPageNo = new HashMap<>();

    private Map<Integer, List<String>> textByPageNo = new HashMap<>();
    
}
```
2). The Book class should implement the Cloneable interface

```java
public class Book implements Cloneable {
   
}
```
3). We should override the clone() method, so that every time we clone a book, we should increment the edition number
```java
public class Book implements Cloneable {
       @Override
       protected Book clone() throws CloneNotSupportedException {
           Book nextEdition = new Book(this.isbn, this.author, this.editionNo + 1, this.illustrationsByPageNo, this.textByPageNo);
           return nextEdition;
       }
}
```
4). Whole example
```java
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
```
