# Singleton

GoF Design Patterns -> Creational Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/gof/creational/singleton) 

## What problems does it solve? Why to use it?
- Restricts the instantiation of a class to one "single" instance. This is useful when exactly 
one object is needed to coordinate actions across the system.

## When to use it?
- We we need to be sure that there is only one instance of an object in the whole application and 
provide global access/ entry point to it.
- Singletons may often be modeled as a server within the application that accepts requests to send, 
store, or retrieve data and configure the resource state.

## Pros:
- Singleton encapsulates a unique resource and makes it readily available throughout the application. 
- Singletons act as a control, ensuring orderly access to the shared resource.

## Cons:
- When not used properly, provides unnecessary limitations
- Sometimes Singletons are used to access global data (not correct!) and you give access of the clients to all
data exposed in that Singleton
- Singletons hinder unit testing - sometimes is impossible to test without writing a fully-functional class dedicated to the Singleton.
- Singletons create hidden dependencies

## Implementation
An implementation of the singleton pattern must:
- ensure that only one instance of the singleton class ever exists; 
- provide global access to that instance

Typically, this is done by:
- declaring all constructors of the class to be private; 
- providing a static method that returns a reference to the instance

The instance is usually stored as a private static variable; the instance is created when the 
variable is initialized, at some point before the static method is first called. The following is a sample
implementation written in Java.

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

## Examples from Java API
Recognizeable by creational methods returning the same instance (usually of itself) every time
```
java.lang.Runtime#getRuntime()
java.awt.Desktop#getDesktop()
java.lang.System#getSecurityManager()
```

## Examples

### Example 1 - How to implement it?
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

