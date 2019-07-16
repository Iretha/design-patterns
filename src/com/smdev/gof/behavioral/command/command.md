---
layout: default
title: Command
parent: Behavioral Design Patterns
nav_order: 3010
permalink: /behavioral/command
---

# The Command Design Pattern 

GoF Design Patterns -> Behavioral Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/gof/behavioral/command) 

## What problems does it solve? Why to use it?
It's used to decentralize the business logic. 
Each command has a reference to the receiver, the one, whose state the command will change.
There is only one handler (as opposed to the chain of responsibility pattern with many handlers)

Glossary:
- Command interface  - the interfaces that defines the action
- Concrete commands - implement the interface
- Receiver - the object that executes the command
- Invoker/ Client - the object that gives the command

## When to use it?
When you need to decouple the Invoker and the Receiver and let them "talk" via commands, that only Receiver (The only Handler) 
knows how to handle them.

## Pros:
- Allows encapsulation of the request as an object
- The Invoker and the Receiver are decoupled
- Easy to extend and add new Commands without changing the existing implementation (scalable)
- Improves the readability of the code
- You can implement Undo/ Redo actions (+ Memento to maintain the state)

## Cons:
- Increased number of classes (separate class for each command)

## Examples from Java API
Recognizable by behavioral methods in an abstract/interface type which invokes a method in 
an implementation of a different abstract/interface type which has been encapsulated by the command 
implementation during its creation
```
All implementations of java.lang.Runnable
All implementations of javax.swing.Action
```
## Examples
- Handling menu/ button actions

### Example 1 - How to implement it?
We want to control remotely our TV. We need a Remote Control (that will act as a Invoker). The TV will be
out receiver, that will actually execute the commands. The remote control has buttons and when someone
presses a button, the concrete command should be performed.

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