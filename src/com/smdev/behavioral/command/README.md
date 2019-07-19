---
layout: default
title: Command (GoF)
parent: Behavioral Design Patterns
nav_order: 3010
permalink: /behavioral/command
---

# The Command Design Pattern 

The Command Design Pattern is designed to encapsulate all the data, needed to perform the action as an object and execute the action.
Sometimes action can be stored in a queue and executed later. 
{: .fs-6 .fw-300 }

---

The Command Design Pattern is also known as Action Pattern/ Transaction Pattern. It uses inheritance to handle requests. 
There is one Abstract Command, that declares an "execute" action and many concrete commands, that "know" the actual receiver.
The action is executed by the receiver itself.

## What problems does it solve? 
It's used to decentralize the business logic. Each request is encapsulated as object and each object (command) has a reference to 
the receiver (the one, whose state the command will change).

In opposite to the chain of responsibility (with many handlers), the command pattern has only one handler.

Glossary:
- Command interface  - the interfaces, that defines the action
- Concrete commands - implement the interface
- Receiver (Handler) - the object, that executes the command
- Invoker/ Client - the object, that gives the command

## Pros:
- Allows encapsulation of the request as an object
- The Invoker and the Receiver are decoupled
- Easy to extend and add new Commands without changing the existing implementation (scalable)
- Improves the readability of the code
- You can implement Undo/ Redo actions (+ Memento to maintain the state)

## Cons:
- Increased number of classes (separate class for each command)

## How to recognize it?
When invoking behavioral methods, located in an abstract/interface type.
The method itself invokes a method in an implementation of a different abstract/interface type, which has been encapsulated by the command.
```java
RemoteControl tvRemoteControl = new RemoteControl(new Device("TV"));

tvRemoteControl.pressButton("Play");
tvRemoteControl.pressButton("Stop");
```
## Examples from Java API
```
All implementations of java.lang.Runnable
All implementations of javax.swing.Action
```
## Scenarios
* When you need to handle menu/ button actions
* When you need to decouple the Invoker and the Receiver and let them "talk" via commands, that only Receiver (the only Handler) knows how to handle them.
* When you want to store requests in a queue and execute them later
* When you want to implement undo/ redo actions

### Example 1 
We want to control remotely our TV. We need a Remote Control (that will act as a Invoker). The TV will be
out receiver, that will actually execute the commands. The remote control has buttons and when someone
presses a button, the concrete command should be performed.

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/behavioral/command) 

1). TV - Our Device class (The Receiver)
```java
@ToString
public class Device {

    private String name;

    public Device(String name) {
        this.name = name;
    }

    public void play() {
        System.out.println(this + " is playing.");
    }

    public void stop() {
        System.out.println(this + " has stopped playing.");
    }
}
```
3). The Command Interface
```java
public interface Command {

    void execute();
}
```
4). A general implementation of the Command
```java
public abstract class AbstractCommand implements Command{

    @Getter
    private Device device;

    public AbstractCommand(Device device) {
        this.device = device;
    }
}
```
5). Concrete commands - Play & Stop
```java
public class CommandPlay extends AbstractCommand {

    public CommandPlay(Device device) {
        super(device);
    }

    @Override
    public void execute() {
        getDevice().play();
    }
}
```
```java
public class CommandStop extends AbstractCommand {

    public CommandStop(Device device) {
        super(device);
    }

    @Override
    public void execute() {
        getDevice().stop();
    }
}
```
6). The Remote Control (The Invoker)
```java
public class RemoteControl {

    private Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    public void pressButton(String button) {
        Command command = null;
        switch (button) {
            case "Play":
                command = new CommandPlay(this.device);
                break;
            case "Stop":
                command = new CommandStop(this.device);
                break;
            default:
                break;
        }

        if (command != null) {
            command.execute();
        }
    }
}
```
7). Demo
```java
public class _Main {

    public static void main(String[] args) {
        RemoteControl tvRemoteControl = new RemoteControl(new Device("TV"));

        tvRemoteControl.pressButton("Play");
        tvRemoteControl.pressButton("Stop");
    }
}
```
Output:
```
Device(name=TV) is playing.
Device(name=TV) has stopped playing.
```