# The Strategy Design Pattern (Policy Pattern)

GoF Design Patterns -> Behavioral Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/gof/behavioral/strategy) 

## What problems does it solve? Why to use it?
When you want to separate the behavior (the algorithm) from it's host class. 

## When to use it?
- When you have a lot of classes that have similar behaviour. You can extract the behavior as separate class and 
pass the objects.
- When you want to switch to a different behavior easily, without modifying the classes

## Pros:
- you can change the behavior at anytime, even at runtime (to swap algorithms)
- you can create a family of algorithms and pass an object
- algorithms get reusable (you can pass different objects)
- you can switch to a different behavior withou modifying objects

## Cons:
- increased number of classes
- client should know about different strategies and when/ how to use them

## Examples from Java API
Recognizable by behavioral methods in an abstract/interface type which invokes a method in an 
implementation of a different abstract/interface type which has been passed-in as method argument into 
the strategy implementation
```
java.util.Comparator#compare(), executed by among others Collections#sort().
javax.servlet.http.HttpServlet, the service() and all doXXX() methods take HttpServletRequest and HttpServletResponse and the implementor has to process them (and not to get hold of them as instance variables!).
javax.servlet.Filter#doFilter()
```
## Examples
### Example 1 - How to implement it?
If you want to create an universal chat app that allows you to send messages using different providers, like: 
email, sms, viber, social media and etc.

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
public class Chat {

    private SendStrategy strategy;

    public Chat(SendStrategy strategy) {
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
        Chat chat = new Chat(new SendStrategySms("123", "456"));
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
