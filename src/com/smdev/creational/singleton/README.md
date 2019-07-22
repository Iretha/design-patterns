---
layout: default
title: Singleton (GoF)
parent: Creational Design Patterns
nav_order: 1040
permalink: /creational/singleton
---

# The Singleton Design Pattern (Cloneable)

Designed to limit the number of instances to 1 and to control the access to that instance 
(in serial way via the provided entry/ access point).
{: .fs-6 .fw-300 }

---

## What problems does it solve?
Restricts the instantiation of a class to one "single" instance. This is useful when exactly 
one object is needed to coordinate actions across the system. 

**Best thread-safe implementation is via enums.**

## When to use it?
- To ensure that there is only one instance of an object in the whole application and you want to provide global access/ entry point to it.

## Pros:
- To encapsulate a unique (shared) resource and make it available throughout the whole application
- To control or to ensure serial access to a shared resource

## Cons:
- When not used properly, provides unnecessary limitations
- Often Singletons are used to access global/ shared data and you give access of the clients to all data exposed in that Singleton
- Singletons hinder unit testing - sometimes is impossible to test without writing a fully-functional class dedicated to the Singleton
- Singletons may create hidden dependencies

## Implementation
An implementation of the singleton pattern must:
- ensure that only one instance of the singleton class ever exists; 
- provide global access to that instance;
- make instance volatile (Volatile is used to indicate that a variable's value will be modified by different threads)

Typically, this is done by:
- declaring all constructors of the class to be private; 
- providing a static method that returns a reference to the instance;

The instance is usually stored as a private static variable;
- Eager Initialization:
```java
public final class Singleton {

    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
```
- Lazy Initialization:
```java
public final class Singleton {

    private static volatile Singleton instance = null;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized(Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

The easiest way is to do a thread-safe Singleton is via Enum. The drawback is that the initialization is eager. 
See [Example 1](#example-1) - uses classic implementation with double checked locking
See [Example 2](#example-2) - uses enumeration, which is also thread-safe

To make a Singleton serializable you need to implement the Serializable interface. The problem is that 
when we want to deserialize it, it will create another instance of the class. This can be solved by:
* when using enum, everything is ok
* when using classic implementation we need to override readResolve() as follows
```java
protected Object readResolve() {
    return getInstance();
}
```
See [Example 3](#example-3) - how to serialize a singleton

## How to recognize it?
When you call a creational method and it returns the same instance (itself) every time.
```
see the examples above

```
## Examples from Java API
```
java.lang.Runtime#getRuntime()
java.awt.Desktop#getDesktop()
java.lang.System#getSecurityManager()
```

## Scenarios

* Singletons are often modeled as a server that accepts requests to: send, store or retrieve data.

* If you need to control the state of something in a serial way

## Example 1

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/creational/singleton) 

Let's say we have a single mobile device and we want to start some apps on it. 
In this case we could use our Device as a Singleton object, because we have only one device.
We can run on that device multiple mobile apps, but only one of them could be on focus at a time.
To implement this example, we need to:
1). Create class MobileApp
```java
public class MobileApp {

    @Getter
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    public void runInBackground(){
        System.out.println(this.appName + " is running in background!");
    }

    public void moveToFocus(){
        System.out.println(this.appName + " is on focus!");
    }

    public void moveToBackground(){
        System.out.println(this.appName + " is moved to background!");
    }

    public void stop(){
        System.out.println(this.appName + " is stopped!");
    }
}

```
2). Create class MobileDevice, that will have only one static instance with a list of running apps and an app on focus
```java
public class MobileDevice {
    /**
     * Volatile is used to indicate that a variable's value will be modified by different threads
     */
    private static volatile MobileDevice instance = null;

    private Map<String, MobileApp> runningApps = new HashMap<>();

    private MobileApp appOnFocus;
    
    }
```
3). We need to ensure that no one could instantiate our MobileDevice, so we make the constructor private
```java
public class MobileDevice {
    private MobileDevice() {
        // hidden constructor
    }
}
```
4). We may want to ensure that no one could extend our class, so we may want to make it final
```java
public final class MobileDevice {

}
```
5). Now we should provide a single entry point, that will give access to the instance and it's data
```java
public final class MobileDevice {
    public static MobileDevice getInstance() {
        if (instance == null) {
            synchronized (MobileDevice.class) {
                if (instance == null) {
                    instance = new MobileDevice();
                }
            }
        }
        return instance;
    }
}
```
6). We also may want to provide some business logic so that we could always have only one app on focus, and when an app looses focus,
it will be sent to background or even paused in order to save battery
```java
public final class MobileDevice {
    public synchronized void runApp(String appName, boolean moveToFocus) {
            MobileApp app = this.runningApps.get(appName);
            if (app == null) {
                app = new MobileApp(appName);
                this.runningApps.put(appName, app);
                app.runInBackground();
            }
    
            if (moveToFocus) {
                focusApp(appName);
            }
        }
    
        public synchronized void focusApp(String appName) {
            MobileApp app = this.runningApps.get(appName);
            if (app == null) {
                return;
            }
    
            if (this.appOnFocus != null) {
                this.appOnFocus.moveToBackground();
            }
            this.appOnFocus = app;
            this.appOnFocus.moveToFocus();
        }
    
        public synchronized void stopApp(String appName) {
            MobileApp app = this.runningApps.get(appName);
            if (app == null) {
                return;
            }
    
            if (this.appOnFocus != null && this.appOnFocus.equals(app)) {
                this.appOnFocus = null;
            }
    
            this.runningApps.remove(appName);
            app.stop();
        }
}
```
7). This example illustrates how we can control the state of the apps, by providing a single entry point to the device and 
it's functions.

```java
public class MobileDevice {

    private static volatile MobileDevice instance = null;

    private Map<String, MobileApp> runningApps = new HashMap<>();

    private MobileApp appOnFocus;


    private MobileDevice() {
    }

    public static MobileDevice getInstance() {
        if (instance == null) {
            synchronized (MobileDevice.class) {
                if (instance == null) {
                    instance = new MobileDevice();
                }
            }
        }
        return instance;
    }

    public synchronized void runApp(String appName, boolean moveToFocus) {
        MobileApp app = this.runningApps.get(appName);
        if (app == null) {
            app = new MobileApp(appName);
            this.runningApps.put(appName, app);
            app.runInBackground();
        }

        if (moveToFocus) {
            focusApp(appName);
        }
    }

    public synchronized void focusApp(String appName) {
        MobileApp app = this.runningApps.get(appName);
        if (app == null) {
            return;
        }

        if (this.appOnFocus != null) {
            this.appOnFocus.moveToBackground();
        }
        this.appOnFocus = app;
        this.appOnFocus.moveToFocus();
    }

    public synchronized void stopApp(String appName) {
        MobileApp app = this.runningApps.get(appName);
        if (app == null) {
            return;
        }

        if (this.appOnFocus != null && this.appOnFocus.equals(app)) {
            this.appOnFocus = null;
        }

        this.runningApps.remove(appName);
        app.stop();
    }
}
```

## Example 2

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/creational/singleton) 

We can create Singleton via enum. The drawback is that it will be eager.
1). Create singleton class
```java
@ToString
public enum MySingleton {
    INSTANCE;

    public void doSomething() {
        System.out.println(this + ": Doing something");
    }
}
```
2). Create demo class
```java
public class _Main {
    public static void main(String[] args) {
        MySingleton.INSTANCE.doSomething();
        MySingleton.INSTANCE.doSomething();
    }
}
```
Output:
```
MySingleton.INSTANCE: Doing something
MySingleton.INSTANCE: Doing something
```

## Example 3

How to serialize Singletons?

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/creational/singleton) 

1). Create a enum singleton
```java
@ToString
public enum SerializedEnumSingleton implements Serializable {
    INSTANCE;

    public void doSomething() {
        System.out.println(this + ": Doing something");
    }
}
```
2). Create a classic singleton
```java
import java.io.Serializable;

public class SerializedClassicSingleton implements Serializable {

    /**
     * Volatile is used to indicate that a variable's value will be modified by different threads
     */
    private static volatile SerializedClassicSingleton instance = null;


    private SerializedClassicSingleton() {
    }

    public static SerializedClassicSingleton getInstance() {
        if (instance == null) {
            synchronized (SerializedClassicSingleton.class) {
                if (instance == null) {
                    instance = new SerializedClassicSingleton();
                }
            }
        }
        return instance;
    }

//    protected Object readResolve() {
//        return getInstance();
//    }
}
```
3). Create a demo class
```java
public class _Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Enum Singleton:");
        serializeAndDeserialize(SerializedEnumSingleton.INSTANCE);

        System.out.println();

        System.out.println("Classic Singleton:");
        serializeAndDeserialize(SerializedClassicSingleton.getInstance());
    }

    private static void serializeAndDeserialize(Serializable serializableOne) throws Exception {
        // serialize it
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(
                "filename.ser"));
        out.writeObject(serializableOne);
        out.close();

        //deserialize from file to object
        ObjectInput in = new ObjectInputStream(new FileInputStream(
                "filename.ser"));
        Serializable serializableOneTwo = (Serializable) in.readObject();
        in.close();

        System.out.println("singleton hashCode=" + serializableOne.hashCode());
        System.out.println("deserialized singleton hashCode=" + serializableOneTwo.hashCode());
    }
}
```
Output:
```java
Enum Singleton:
singleton hashCode=1531333864
deserialized singleton hashCode=1531333864

Classic Singleton:
singleton hashCode=1937348256
deserialized singleton hashCode=750044075
```

As you can see, the enum implementation is OK. To make it work for the classic implementation as well, 
we need to override readResolve() as follows:
```java
    protected Object readResolve() {
        return getInstance();
    }
``` 

Run again and now the output is:
```
Enum Singleton:
singleton hashCode=1531333864
deserialized singleton hashCode=1531333864

Classic Singleton:
singleton hashCode=1937348256
deserialized singleton hashCode=1937348256
```