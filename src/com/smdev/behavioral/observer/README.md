---
layout: default
title: Observer (GoF)
parent: Behavioral Design Patterns
nav_order: 3060
permalink: /behavioral/observer
---

# The Observer Design Pattern 

The Observer Design Pattern is designed to define one-to-many dependency between objects so that when one object changes its state, all its dependents get notified and updated automatically.
{: .fs-6 .fw-300 }

--- 

The Observer Pattern is also known as The Dependents Design Pattern or The Publish-Subscribe Pattern.

## What problems does it solve? 
Defines one-to-many relations between objects and when an objects changes it's state, all dependents get notified.
This way we can easily maintain consistent state of the related objects.

Glossary:
- Subject - defines an interface for attaching (subscribing/ registering)/ detaching (unsubscribing/ deregistering) and notifying observers; contains a list of observers, that should get notified
- Observer (Subscriber) - defines an interface for receiving and processing the received notifications
- Concrete Subject
- Concrete Observer
- Message / State - this is the object that will be sent as a notification. It should be an immutable object.
- Publisher - initiator of the updates/ changes of the subject

## Pros:
- You can avoid tight coupling between related objects, but you still can keep them in a consistent state
- Subjects and observers are independent and don't "know" each other (loose coupling)
- Subjects and observers can be added or removed without further changes
- Subjects and observers can be added or removed at anytime even at runtime
- You can send data to many "receivers"

## Cons:
- The order, in which the observers get notified is not very precise
- May cause memory leaks, because subject holds references to observers

**Java has a in-built implementation of the Observer pattern, but it's done through inheritance (java.util.Observable class and java.util.Observer). 
This is the reason why programmers avoid to use it.**

## How to recognize it?
When you call a behavioral method which invokes a method on an instance of another abstract/interface type.
```java
Publisher publisher = new Publisher();

Topic sports = publisher.findTopic("sports");
sports.subscribe(new Subscriber("Jon Show"));
sports.subscribe(new Subscriber("Peter Dow"));

publisher.publishUpdate(new Message(sports.getLabel(), "New Volleyball Star", "More info..."));
```
## Examples from Java API
```
java.util.Observer/java.util.Observable (rarely used in real world though)
All implementations of java.util.EventListener (practically all over Swing thus)
javax.servlet.http.HttpSessionBindingListener
javax.servlet.http.HttpSessionAttributeListener
javax.faces.event.PhaseListener
```
## Scenarios
* When many entities need to receive notifications, when an object has changed or something happened.
* JMS (Observer + Mediator Pattern)
* MVC frameworks (model is the subject & views are observers)
* Message Brokers (like RabbitMQ etc)
* If you need to notify multiple observers, when something changes

### Example 1
We own an online media (Publisher) that publishes news (Messages) on different topics (Subjects). Users (Subscribers/ Observers) can subscribe to a topic
and when news on the subscribed topics are published, the users receive notifications. 

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/behavioral/observer) 

1). Create a message/ notification that will be send to the subscribers
```java
/**
 * The Notification/ Update (Immutable object)
 */
@ToString
public class Message {

    @Getter
    private String topic;

    @Getter
    private String title;

    @Getter
    private String content;

    public Message(String topic, String title, String content) {
        this.topic = topic;
        this.title = title;
        this.content = content;
    }
}
```
2). Create an abstract observer, that "knows" how to receive messages
```java
/**
 * The Abstract Observer
 */
public interface Observer {

    void receive(Message message);
}
```
3). Create an abstract subject, that "knows" how to subscribe/ unsubscribe subscribers and how to send them messages
```java
/**
 * The Abstract Subject
 */
@ToString
public abstract class Subject {

    private Set<Observer> subscribers = new HashSet<>();

    @Getter
    private String label;

    public Subject(String label) {
        this.label = label;
    }

    void subscribe(Observer observer) {
        this.subscribers.add(observer);
    }

    void unsubscribe(Observer observer) {
        this.subscribers.remove(observer);
    }

    void notifySubscribers(Message message) {
        System.out.println("======= " + this.label + "=====");
        for (Observer observer : this.subscribers) {
            observer.receive(message);
        }

        System.out.println();
    }
}
```
4). Create a concrete observer (subscriber). In our case we will have registered subscribers, that have user names.
```java
/**
 * The Concrete Observer
 */
public class Subscriber implements Observer {

    private String username;

    public Subscriber(String username) {
        this.username = username;
    }

    @Override
    public void receive(Message message) {
        System.out.println("< New message received by " + this.username + " > " + message);
    }
}
```
5). Create a concrete subject, in our case we have topics
```java
/**
 * The Concrete Subject
 */
public class Topic extends Subject {

    public Topic(String label) {
        super(label);
    }
}
```
6). We need a publisher, who publishes (generates) messages on different topics
```java
/**
 * The Initiator of the updates/ notifications
 */
public class Publisher {

    private Map<String, Topic> topics = new HashMap<>();

    public void publishUpdate(Message message) {
        findTopic(message.getTopic()).notifySubscribers(message);
    }

    public Topic findTopic(String topicLabel) {
        if (this.topics.get(topicLabel) == null) {
            this.topics.put(topicLabel, new Topic(topicLabel));
        }
        return this.topics.get(topicLabel);
    }
}
```
7). A demo class
```java
public class _Main {

    public static void main(String[] args) {
        Publisher publisher = new Publisher();

        Subscriber jonShow = new Subscriber("Jon Show");
        Subscriber violet = new Subscriber("Violet Dean");
        Subscriber peter = new Subscriber("Peter Dow");

        Topic sports = publisher.findTopic("sports");
        sports.subscribe(jonShow);

        Topic hiTech = publisher.findTopic("hi-tech");
        hiTech.subscribe(jonShow);
        hiTech.subscribe(violet);
        hiTech.subscribe(peter);

        publisher.publishUpdate(new Message(sports.getLabel(), "New Volleyball Star", "More info..."));
        publisher.publishUpdate(new Message(hiTech.getLabel(), "AI arises", "Super smart AI invented..."));

        hiTech.unsubscribe(peter);
        publisher.publishUpdate(new Message(hiTech.getLabel(), "The smallest chip ever", "Read further..."));
    }
}
```
Output:
```java
======= sports=====
< New message received by Jon Show > Message(topic=sports, title=New Volleyball Star, content=More info...)

======= hi-tech=====
< New message received by Violet Dean > Message(topic=hi-tech, title=AI arises, content=Super smart AI invented...)
< New message received by Peter Dow > Message(topic=hi-tech, title=AI arises, content=Super smart AI invented...)
< New message received by Jon Show > Message(topic=hi-tech, title=AI arises, content=Super smart AI invented...)

======= hi-tech=====
< New message received by Violet Dean > Message(topic=hi-tech, title=The smallest chip ever, content=Read further...)
< New message received by Jon Show > Message(topic=hi-tech, title=The smallest chip ever, content=Read further...)

```
