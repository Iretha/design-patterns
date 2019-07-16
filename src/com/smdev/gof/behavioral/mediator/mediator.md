---
layout: default
title: Mediator
parent: Behavioral Design Patterns
nav_order: 3040
permalink: /behavioral/mediator
---

# The Mediator Design Pattern 

GoF Design Patterns -> Behavioral Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/gof/behavioral/mediator) 

## What problems does it solve? Why to use it?
To eliminate direct connections between separate objects or components.
The Mediator Pattern provides a centralized communication by encapsulating the communication between them. 
Later the interaction can be changed without changing the components.

Glossary:
- Colleagues - the objects that interact together
- Contract of communication - the interface or abstract implementation of the mediator
- Concrete Mediator - the concrete implementation of the mediator

## When to use it?
To avoid tight coupling - if many objects have to interact together, you can put a mediator between them.

## Pros:
- Reduces the coupling between colleagues
- Easy to modify the way the components interact without changing the components
- Reusable components
- Easy to add new colleagues

## Cons:
- The mediator can get very complex

## Examples from Java API
Recognizable by behavioral methods taking an instance of different abstract/interface type 
(usually using the command pattern) which delegates/uses the given instance

```
java.util.Timer (all scheduleXXX() methods)
java.util.concurrent.Executor#execute()
java.util.concurrent.ExecutorService (the invokeXXX() and submit() methods)
java.util.concurrent.ScheduledExecutorService (all scheduleXXX() methods)
java.lang.reflect.Method#invoke()
```
## Examples
- Traffic Cops
- Air Control Tower
- Post Office
- Group Chat App

### Example 1 - How to implement it?
We are going to create a demo of "Google Drive"- like app. You can connect to a cloud account
and synchronize a folder between multiple devices.

1). Create an abstract mediator
```java
public interface Mediator {

    void connect(String account, Colleague colleague);

    void disconnect(String account, Colleague colleague);

    void synchronize(String account);
}
```
2). Create an abstract colleague
```java
/**
 * The Abstract Colleague
 */
public abstract class Colleague {

    @Getter
    private String label;

    private Mediator mediator;

    private String account;

    @Getter
    private Set<String> files = new TreeSet<>();

    @Getter
    private Date filesModifiedOn;

    public Colleague(String label, Mediator mediator) {
        this.label = label;
        this.mediator = mediator;
    }

    public void connect(String account) {
        this.account = account;
        this.mediator.connect(account, this);
    }

    public void disconnect() {
        this.mediator.disconnect(this.account, this);
    }

    public void synchronize(Set<String> updatedFiles) {
        this.files.clear();
        this.files.addAll(updatedFiles);
        this.filesModifiedOn = new Date();

        System.out.println(this.account + ": " + getLabel() + " got synchronized. Files = " + this.files.size());
    }

    public void addFile(String file) {
        this.files.add(file);
        this.filesModifiedOn = new Date();
        System.out.println(this.account + ": " + getLabel() + " => File added. Files = " + this.files.size());

        this.mediator.synchronize(this.account);
    }
}
```
3). Create multiple concrete colleagues

3.1). Android Client
```java
public class ClientAndroid extends Colleague {

    public ClientAndroid(String label, Mediator mediator) {
        super(label, mediator);
    }
}
```
3.2). MacOs Client
```java
public class ClientMacOs extends Colleague {

    public ClientMacOs(String label, Mediator mediator) {
        super(label, mediator);
    }
}
```
3.3). Windows Client
```java
public class ClientWindows extends Colleague {

    public ClientWindows(String label, Mediator mediator) {
        super(label, mediator);
    }
}
```
4). Create the concrete mediator
```java
public class MediatorCloud implements Mediator {

    private Map<String, List<Colleague>> connectedDevices = new HashMap<>();

    @Override
    public void connect(String account, Colleague device) {
        List<Colleague> devices = this.connectedDevices.get(account);
        if (devices == null) {
            devices = new ArrayList<>();
            this.connectedDevices.put(account, devices);
        }

        if (!devices.contains(device)) {
            devices.add(device);
            System.out.println(device.getLabel() + " connected to " + account);
        }

        synchronize(account);
    }

    @Override
    public void disconnect(String account, Colleague device) {
        List<Colleague> devices = this.connectedDevices.get(account);
        if (devices != null && devices.contains(device)) {
            devices.remove(device);
            System.out.println(device.getLabel()+ " disconnected from " + account);
        }
    }

    @Override
    public void synchronize(String account) {
        List<Colleague> devices = this.connectedDevices.get(account);
        if (devices == null || devices.size() < 2) {
            return;
        }

        devices.sort(Comparator.comparing(Colleague::getFilesModifiedOn, Comparator.nullsFirst(Comparator.naturalOrder())));

        Colleague lastUpdated = devices.get(devices.size() - 1);
        for (Colleague d : devices) {
            if (!d.equals(lastUpdated)) {
                d.synchronize(lastUpdated.getFiles());
            }
        }
    }
}
```
5). Demo class
```java
public class _Main {

    public static void main(String[] args) {

        String cloudAccount = "smdev.cloud";

        Mediator mediator = new MediatorCloud();

        // add 2 files => connect to the cloud
        Colleague homePc = new ClientWindows("homePc", mediator);
        homePc.addFile("WorkFlow_Example.txt");
        homePc.addFile("DraftLetter.doc");
        homePc.connect(cloudAccount);
        System.out.println();

        // connect to cloud => got synced =>  add 3 more files
        Colleague notebook1 = new ClientMacOs("notebook1", mediator);
        notebook1.connect(cloudAccount);
        notebook1.addFile("CV.pdf");
        notebook1.addFile("Motivation1.doc");
        notebook1.addFile("Motivation2.doc");
        System.out.println();

        // connect to cloud => got synced =>  add 1 more file
        Colleague workPc = new ClientWindows("workPc", mediator);
        workPc.connect(cloudAccount);
        workPc.addFile("Homework.doc");

        // connect to clod => got synced
        Colleague phone = new ClientAndroid("mobile phone", mediator);
        phone.connect(cloudAccount);
    }
}
```
Output:
```
null: homePc => File added. Files = 1
null: homePc => File added. Files = 2
homePc connected to smdev.cloud

notebook1 connected to smdev.cloud
smdev.cloud: notebook1 got synchronized. Files = 2
smdev.cloud: notebook1 => File added. Files = 3
smdev.cloud: homePc got synchronized. Files = 3
smdev.cloud: notebook1 => File added. Files = 4
smdev.cloud: homePc got synchronized. Files = 4
smdev.cloud: notebook1 => File added. Files = 5
smdev.cloud: homePc got synchronized. Files = 5

workPc connected to smdev.cloud
smdev.cloud: workPc got synchronized. Files = 5
smdev.cloud: homePc got synchronized. Files = 5
smdev.cloud: workPc => File added. Files = 6
smdev.cloud: homePc got synchronized. Files = 6
smdev.cloud: notebook1 got synchronized. Files = 6
mobile phone connected to smdev.cloud
smdev.cloud: mobile phone got synchronized. Files = 6
smdev.cloud: homePc got synchronized. Files = 6
smdev.cloud: notebook1 got synchronized. Files = 6
```