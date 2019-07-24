---
layout: default
title: Proxy  (GoF)
parent: Structural Design Patterns
nav_order: 2060
permalink: /structural/proxy
---

# The Proxy Design Pattern

The Proxy Pattern is designed to provide a level of indirection to object members and may add additional logic (i.e. 
to control access or to provide a wrapper implementation for better performance).
{: .fs-6 .fw-300 }

---

The Proxy Pattern is also known as Surrogate.
Acts as a surrogate to another objects, so that you can control the access to it.

## What problems does it solve?
Proxies “control and manage access to the object, they are protecting“.
They can be chained and keep a reference to the object, to which they forward requests.

Types of proxies:
- Remote - local "stub/ representation" of something that "lives" on a remote machine/ place
- Virtual - creates "expensive" objects on demand
- Protection - controls access to the original object
- smart reference - does additional actions like objects locking etc.

## Pros:
- Enhanced security
- More control over the access to the objects
- Act as an entry point or point of access

## Cons:
- Provide additional layer of abstraction

Adapters vs Decorators vs Proxies:
- Adapters give different interface.
- Proxies use same interface and may add functionality.
- Decorators provide enhanced interface and add functionality (they have other purpose as a whole).

## How to recognize it?
When you cannot access directly the object "behind" the proxy, but the proxy provides the same interface as its subject.

## Examples from Java API
```
java.lang.reflect.Proxy
java.rmi.*
javax.ejb.EJB (explanation here)
javax.inject.Inject 
javax.persistence.PersistenceContext
```
## Scenarios
- To check user privileges/ permissions and restrict access
- To add logging
- To cache objects
- To lock/ unlock objects
- To route/ forward request, based on some rules

### Example 1
You should restrict search for some keywords in your office.

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/structural/proxy) 

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

