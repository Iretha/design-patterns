---
layout: default
title: Null Object
parent: Behavioral Design Patterns
nav_order: 3100
permalink: /behavioral/null-object
---

# The Null Object Design Pattern 

The Null Object Design Pattern is designed to provide default behavior if object is not found.
{: .fs-6 .fw-300 }

--- 

The Null Object Design Pattern is also known as Stub/ Active Nothing.

## What problems does it solve?
In order to avoid null checks, you can implement a null object, that will do nothing, but it won't
result in NullPointerException. The null object is usually implemented as a Singleton.

Glossary:
- Client - that uses the abstract object
- Abstract Object - declares the abstract behavior of the object
- Real Object - the real object, that implements the abstract behavior
- Null Object - the null object, provides default (no) functionality

**Avoid replacing standard null with a Null Object Pattern. The Null Object Pattern is more appropriate to be used 
when there's a default behavior that could be taken in a case where object isn't found.**

## Pros:
* To handle "null" behavior on a higher level (to abstract handling the null away from client)

## Cons:
* Additional Type

# Scenarios
* To implement default behavior
* To avoid null checks
* To avoid NullPointers

## Examples from Java API
```
"Void" return type (it's still a type)!
```
## Scenarios
* When you have a default behavior, that should execute if object is not found or available.

### Example 1
We have a library, but some book have an unknown author. They still have an author, but we don't know who he is.
Here, we can use the Null Object Pattern, because we may wan't to sort the books by author or so.

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/behavioral/null_object) 

1). Create an abstract class - Author. This is the Abstract Object.
```java
@ToString
public abstract class Author {

    private String name;

    public Author(String name) {
        this.name = name;
    }
}
```
2). Create the Book class. This is the client.
```java
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
```
3). Create the BookAuthor class - This is the RealObject
```java
public class BookAuthor extends Author {

    public BookAuthor(String name) {
        super(name);
    }
}
```
4). Create the NullAuthor class - This is the null object. We will make it Singleton.
```java
public class NullAuthor extends Author {

    private static NullAuthor instance;

    private NullAuthor() {
        super("Unknown author");
    }

    public static Author get() {
        if (instance == null) {
            instance = new NullAuthor();
        }
        return instance;
    }
}
```
5). Create a demo class
```java
public class _Main {

    public static void main(String[] args) {
        System.out.println(new Book("Misery", "Stephen King"));
        System.out.println(new Book("The Arabian Nights"));
    }
}
```
Output:
```java
Book(title=Misery, author=Author(name=Stephen King))
Book(title=The Arabian Nights, author=Author(name=Unknown author))
```