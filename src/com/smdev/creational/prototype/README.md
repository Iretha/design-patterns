---
layout: default
title: Prototype/ Cloneable (GoF)
parent: Creational Design Patterns
nav_order: 1030
permalink: /creational/prototype
---

# The Prototype Design Pattern (Cloneable)

Designed to create objects faster, when creating a new object is an expensive operation.
{: .fs-6 .fw-300 }

---

## What problems does it solve?
Prototype patterns is required, when object creation is time consuming and costly operation.
To solve this problem, we create new objects from existing (in memory) objects.
To do this, we use the clone() method. This is the easies approach to implement the prototype 
pattern. However, you should decide how to create a copy.

The existing object, you use, is called "prototype".

## When to use it?
The concept is to copy an existing object rather than creating a new instance from scratch. 
The existing object acts as a prototype and contains the state of the object. 
The newly copied object may change same properties if required. 
This approach saves resources and time, especially when the object creation is a heavy process.

## Pros:
- Faster object creation

## Cons:
- Hard to extend, because you should be careful when adding new properties. Sometimes you may need
to extend the clone method implementation as well
- You should decide depending on business requirements what copy you need (shell/ deep/ smth else)
- Each subclass of Prototype must implement the clone() operation, which might be difficult
- Implementing clone() can be difficult when not all members support the clone operation
- Sometimes you can achieve circular references

## How to recognize it?
When a creational method returns a different instance of itself with almost the same values of the properties.
```java
public class Book implements Cloneable {

    @Override
    protected Book clone() throws CloneNotSupportedException {
        Book nextEdition = new Book(this.isbn, this.author, this.editionNo + 1, this.illustrationsByPageNo, this.textByPageNo);
        return nextEdition;
    }
}
```
## Examples from Java API
In Java, you can implement the Cloneable interface to achieve the prototype pattern.
```
java.lang.Object#clone() (the class has to implement java.lang.Cloneable)
```

## Examples

* When you need to do expensive calls or operations to create ан object and you already have the data in the memory

* When you need to copy an object or part of its fields

### Example 1

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/creational/prototype) 

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
