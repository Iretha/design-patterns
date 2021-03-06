---
layout: default
title: Strategy/ Policy (GoF)
parent: Behavioral Design Patterns
nav_order: 3080
permalink: /behavioral/strategy
---

# The Strategy Design Pattern 

The Strategy Design Pattern is designed to separate the behavior from it's host class, by encapsulating the algorithm 
in a separate class. This allows you to implement families of algorithms and make them interchangeable.
{: .fs-6 .fw-300 }

--- 

The Strategy Design Pattern is also knows as "Policy" Design Pattern. 
If you have ever used dependency injection (DI), then you also have used Strategy Design Pattern.
It uses "Composition over Inheritance" principle and decouples client from the concrete implementation of the algorithm.

## What problems does it solve? Why to use it?
1). When you want to separate the behavior (the algorithm) from it's host class. 
You can create multiple algorithms, each one encapsulated in a single class and make them interchangeable.

2). Instead of subclassing, because of some differences in the behavior. You'd better implement the behaviors as
different strategies/ polices (separate classes) and inject them in the constructor. Then, you will be able 
to achieve any combination of behaviors if needed.

Purpose:
- To create a family of algorithms
- To encapsulate each algorithm
- To make the algorithms interchangeable within that family
- To "encapsulate what changes"
- To decouple algorithm from the class - if you change the algorithm, you don't have to change the client

## Pros:
- you can change the behavior at anytime, even at runtime (to swap algorithms)
- you can create a family of algorithms and make them interchangeable
- algorithms get reusable (you can pass different objects)
- you can switch to a different behavior without modifying objects
- provides an alternative to subclassing

## Cons:
- increased number of classes
- client should know about different strategies and when/ how to use them

## Comparison:
* One Type that may apply many algorithms of a single family of algorithms (i.e. different sorting algorithms) = Strategy*

* Many Types that can apply same or multiple algorithms = Visitor*

* If you want to add some functionality, not related to the main algorithm i.e. to log when an algorithm is used = Decorator*

## How to recognize it?
If the logic of a behavioral method is executed by a different type and that type is passed as an argument or is 
member of the method class, or even injected (DI).
```java
Chat chat = new Chat(new SendStrategySms("123", "456"));
chat.send(new Message("Hello!"));

chat.changeStrategy(new SendStrategyEmail("JonSnow@gmail.com", "JaneDow@gmail.com"));
chat.send(new Message("Here are the files as I promised"));
```
## Examples from Java API
```
java.util.Comparator#compare(), executed by among others Collections#sort().
javax.servlet.http.HttpServlet, the service() and all doXXX() methods take HttpServletRequest and HttpServletResponse and the implementor has to process them (and not to get hold of them as instance variables!).
javax.servlet.Filter#doFilter()
```
## Scenarios
* When you want to switch to a different behavior easily, without modifying the classes
* If you have many classes with similar behavior, you can extract the "behaviour"/ algorithm in separate class and reuse it by passing the object as an argument
* If you want to "switch" to another algorithm (behavior)
* If you have objects that can "act" differently, depending on something
* When you need different variants of a single algorithm
* When an algorithm uses data, that should not be exposed to the client
* When a class has "many behaviors"

### Example 1
If you want to create an universal chat app that allows you to send messages using different providers, like: 
email, sms, viber, social media and etc. The best way is to implement different strategies 
for sending messages to different users.

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/behavioral/strategy) 

![](https://github.com/Iretha/design-patterns/blob/master/src/com/smdev/behavioral/strategy/example_1/_diagram.png)

1). Universal message object that will be sent to via different providers
```java
@ToString
public class Message {

    @Getter
    private String text;

    @Getter
    private Date createdOn;

    public Message(String text) {
        this.text = text;
        this.createdOn = new Date();
    }
}
```
2). Abstract strategy for sending messages - defines the general behavior
```java
public interface SendStrategy {

    void send(Message message);
}
```
3). Concrete implementations of the general strategy
3.1). Sending messages via Email
```java
@ToString
public class SendStrategyEmail implements SendStrategy {

    private String from;

    private String to;

    public SendStrategyEmail(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void send(Message message) {
        System.out.println("Sending Email: " + this + "; message=" + message);
    }
}
```
3.2). Sending messages via Facebook
```java
@ToString
public class SendStrategyFacebook implements SendStrategy {

    private String from;

    private String to;

    public SendStrategyFacebook(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void send(Message message) {
        System.out.println("Sending FB Message: " + this + "; message=" + message);
    }
}
```
3.3). Sending messages via SMS
```java
@ToString
public class SendStrategySms implements SendStrategy {

    private String fromPhoneNo;

    private String toPhoneNo;

    public SendStrategySms(String fromPhoneNo, String toPhoneNo) {
        this.fromPhoneNo = fromPhoneNo;
        this.toPhoneNo = toPhoneNo;
    }

    @Override
    public void send(Message message) {
        System.out.println("Sending SMS: " + this + "; message=" + message);
    }
}
```
4). A chat app
```java
public class Messenger {

    private SendStrategy strategy;

    public Messenger(SendStrategy strategy) {
        changeStrategy(strategy);
    }

    public void changeStrategy(SendStrategy strategy){
        System.out.println("\n === Strategy changed to: " + strategy);

        this.strategy = strategy;
    }

    public void send(Message message){
        this.strategy.send(message);
    }
}
```
5). Demo
```java
public class _Main {

    public static void main(String[] args) {
        Messenger chat = new Messenger(new SendStrategySms("123", "456"));
        chat.send(new Message("Hello!"));
        chat.send(new Message("Can you talk?"));
        chat.send(new Message("Let's talk in FB!"));

        chat.changeStrategy(new SendStrategyFacebook("Jon Snow", "Jane Dow"));
        chat.send(new Message("Done."));
        chat.send(new Message("Do you have the files? I'll send them to you via email."));

        chat.changeStrategy(new SendStrategyEmail("JonSnow@gmail.com", "JaneDow@gmail.com"));
        chat.send(new Message("Here are the files as I promised"));

        chat.changeStrategy(new SendStrategyFacebook("Jon Snow", "Jane Dow"));
        chat.send(new Message("Files sent, check your email"));
    }
}
```
Output:
```
 === Strategy changed to: SendStrategySms(fromPhoneNo=123, toPhoneNo=456)
Sending SMS: SendStrategySms(fromPhoneNo=123, toPhoneNo=456); message=Message(text=Hello!, createdOn=Fri Jul 12 11:45:27 EEST 2019)
Sending SMS: SendStrategySms(fromPhoneNo=123, toPhoneNo=456); message=Message(text=Can you talk?, createdOn=Fri Jul 12 11:45:27 EEST 2019)
Sending SMS: SendStrategySms(fromPhoneNo=123, toPhoneNo=456); message=Message(text=Let's talk in FB!, createdOn=Fri Jul 12 11:45:27 EEST 2019)

 === Strategy changed to: SendStrategyFacebook(from=Jon Snow, to=Jane Dow)
Sending FB Message: SendStrategyFacebook(from=Jon Snow, to=Jane Dow); message=Message(text=Done., createdOn=Fri Jul 12 11:45:27 EEST 2019)
Sending FB Message: SendStrategyFacebook(from=Jon Snow, to=Jane Dow); message=Message(text=Do you have the files? I'll send them to you via email., createdOn=Fri Jul 12 11:45:27 EEST 2019)

 === Strategy changed to: SendStrategyEmail(from=JonSnow@gmail.com, to=JaneDow@gmail.com)
Sending Email: SendStrategyEmail(from=JonSnow@gmail.com, to=JaneDow@gmail.com); message=Message(text=Here are the files as I promised, createdOn=Fri Jul 12 11:45:27 EEST 2019)

 === Strategy changed to: SendStrategyFacebook(from=Jon Snow, to=Jane Dow)
Sending FB Message: SendStrategyFacebook(from=Jon Snow, to=Jane Dow); message=Message(text=Files sent, check your email, createdOn=Fri Jul 12 11:45:27 EEST 2019)
```