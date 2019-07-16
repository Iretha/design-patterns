---
layout: default
title: Proxy
parent: Structural Design Patterns
nav_order: 2060
permalink: /structural/proxy
---

# The Proxy Design Pattern

GoF Design Patterns -> Structural Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/gof/structural/proxy) 

## What problems does it solve? Why to use it?

Proxies “control and manage access to the object they are protecting“.
They can be chained and keep a reference to the object to which they forward requests.

Types of proxies:
- Remote - local "stub/ representation" of something that "lives" on a remote machine/ place
- Virtual - placeholder for "expensive to create" objects, created on demand
- Protection - controls access to a sensitive object
- Smart - does additional actions like objects locking etc.

Adapters vs Decorators vs Proxies:
- Adapters give different interface and do not add functionality.
- Decorators provide enhanced interface and add functionality.
- Proxies use same interface and add functionality.

## When to use it?
When you need to restrict or manage access to something.

Example usages:
- To check user privileges/ permissions and restrict access
- To add logging
- To cache objects
- To lock/ unlock object
- To route/ forward request, based on some rules

## Pros:
- Enhanced security
- More control over the access to the objects

## Cons:
- Provide additional layer of abstraction

## Examples from Java API
Recognizeable by creational methods which returns an implementation of given abstract/interface type which in turn delegates/uses a different implementation of given abstract/interface type
```
java.lang.reflect.Proxy
java.rmi.*
javax.ejb.EJB (explanation here)
javax.inject.Inject (explanation here)
javax.persistence.PersistenceContext
```
## Examples

### Example 1

You are restricted to search for some keywords in you office.

1). Implement general search interface
```java
public interface Search {

    String searchFor(String... keywords) throws Exception;
}
```

2). Create Real Search, that implements our Search interface
```java
public class RealSearch implements Search {

    @Override
    public String searchFor(String... keywords) {
        return "Result: " + String.join(" ", Arrays.asList(keywords));
    }
}
```

3). Create Search proxy, that also implements our Search interface.
Our search proxy does some checks and if they pass, the request will be forwarded to the real search.

```java
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
```

4). Demo
```java
public class _Main {

    public static void main(String[] args) {
        search("design", "patterns");
        search("big", "data", "explained");
        search("data", "explained");
    }

    private static void search(String... keywords) {
        System.out.println("New search: " + Arrays.toString(keywords));

        try {
            Search search = new SearchProxy();
            String result = search.searchFor(keywords);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("");
    }
}
```
Output:
```
New search: [design, patterns]
Result: design patterns

New search: [big, data, explained]
BIG is a restricted keyword!

New search: [data, explained]
Result: data explained
```

