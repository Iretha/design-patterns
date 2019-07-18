---
layout: default
title: Object Pool
parent: Creational Design Patterns
nav_order: 1060
permalink: /creational/object-pool
---

# The Object Pool

Object Pools are also known as Resource Pools. They are designed to reduce/ optimize the resource cost 
(time/ memory/ cpu etc) by reusing the instances of stateless objects, that are otherwise expensive to create.
{: .fs-6 .fw-300 }

---

## What problems does it solve? 
Reduces the instantiation cost and does a performance boost. 
This is achieved by reusing the instances of stateless objects, that will be kept in a pool in the memory.

You should consider using an Object Pool when:

1) Creating a new instance is an expensive operation

2) New instances are created often

3) The number of the instances, that are used at the same time is low (even limited)

Glossary:
- Reusable - the resource we are going to reuse instead of creating new instance every time
- Client - will use an instance of Reusable
- ReusablePool - manages the reusable objects

## Pros:
- To boost the performance
- To reuse a costly resource (fewer objects are created)
- To control (limit) GC by limiting the number of instances and keeping them "in use"

## Cons:
- You should take care of the pool and the number of the allocated objects
- All allocated objects will be kept in the memory (even if they are not needed)
- It can be a potential bottleneck of your system
- Violates GC best practices, that objects should not be referenced if they are no longer used (objects should live short)

**Don't use the Object Pool Design Pattern, if you are not 100% sure, that there is no other way to achieve your goal!**

## How to recognize it?
When you call a creational method and you always receive one of few instances.

## How can be improved?
- You should use a Singleton for the Pool. 
- You can implement a "growable" object pool and/ or a pool with a limited number of instances
- You must ensure that the number of objects won't "grow" forever

## Scenarios

* When you have to control the number of instances

* When you have to create often network connections

* When you have to create often database/ datasource connections

* When you have to connect often to some external resource

* When you have to create often new threads

* In games, to avoid (to try to control) GC in order to improve user experience (by keeping the number of instances low and "in use"). 

### Example 1

We are going to implement a thread-safe connection pool with limited number of connections in use (5 max).

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/creational/object-pool)

1). Create a generic connection interfaces. This is the reusable resource.
```java
public interface Connection {

    void release();
}
```
2). Create an example implementation and simulate long creation time (2 seconds in our case)
```java
import lombok.ToString;
import java.util.UUID;

@ToString
public class ConnectionImpl implements Connection {

    private String id;

    ConnectionImpl() {
        this.id = UUID.randomUUID().toString();
        initialize();
    }

    private void initialize() {
        long start = System.currentTimeMillis();

        try {
            Thread.sleep(2000L); // we will simulate that instantiation takes 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("New connection with id=" + this.id + " created. It took " + (System.currentTimeMillis() - start) + "ms.");
    }

    public void release() {
        ConnectionPool.getPool().releaseConnection(this);
    }
}
```
3). Create the connection pool
```java
import lombok.AccessLevel;
import lombok.Getter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {

    private static final int MAX_PARALLEL_CONNECTIONS = 5;

    private static volatile ConnectionPool instance;

    @Getter(AccessLevel.PRIVATE)
    private BlockingQueue<Connection> connectionsInUse = new LinkedBlockingDeque(MAX_PARALLEL_CONNECTIONS);

    @Getter(AccessLevel.PRIVATE)
    private BlockingQueue<Connection> freeConnections = new LinkedBlockingDeque(MAX_PARALLEL_CONNECTIONS);


    public static ConnectionPool getPool() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    private ConnectionPool() {
        //
    }

    public synchronized Connection getConnection() throws Exception {
        int capacity = getConnectionsInUse().remainingCapacity();
        if (capacity < 1) {
            throw new Exception("No available connections! Please, try again later!");
        }

        Connection connection = null;
        if (getFreeConnections().size() > 0) {
            connection = getFreeConnections().take();
        }

        if (connection == null) {
            connection = new ConnectionImpl();
        }

        if (!getConnectionsInUse().contains(connection)) {
            getConnectionsInUse().put(connection);
        }

        System.out.println("Getting " + connection + "\n");
        return connection;
    }

    synchronized void releaseConnection(Connection connection) {
        getConnectionsInUse().remove(connection);

        if (!getFreeConnections().contains(connection)) {
            getFreeConnections().add(connection);
        }

        System.out.println("Connection released and ready to be reused " + connection);
    }
}
```
4). And a demo class
```java
public class _Main {

    public static void main(String[] args) {
        try {
            Connection conn1 = ConnectionPool.getPool().getConnection(); // new instance created
            Connection conn2 = ConnectionPool.getPool().getConnection(); // new instance created
            Connection conn3 = ConnectionPool.getPool().getConnection(); // new instance created

            conn1.release(); // instance released for reuse

            Connection conn4 = ConnectionPool.getPool().getConnection(); // receives conn1 instance

            conn2.release(); // instance released for reuse
            conn3.release(); // instance released for reuse

            Connection conn5 = ConnectionPool.getPool().getConnection(); // receives conn2 instance
            Connection conn6 = ConnectionPool.getPool().getConnection(); // receives conn3 instance

            Connection conn7 = ConnectionPool.getPool().getConnection(); // new instance created
            Connection conn8 = ConnectionPool.getPool().getConnection(); // new instance created
            Connection conn9 = ConnectionPool.getPool().getConnection(); // throws Exception (max number of connections is exceeded)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
Output:
```
New connection with id=27ae7ee3-7171-4be0-853b-09de034824cc created. It took 2002ms.
Getting ConnectionImpl(id=27ae7ee3-7171-4be0-853b-09de034824cc)

New connection with id=cb7b87cc-6dee-4c78-9ed0-86b81109d3b0 created. It took 2003ms.
Getting ConnectionImpl(id=cb7b87cc-6dee-4c78-9ed0-86b81109d3b0)

New connection with id=1a79fcdf-bd5e-4849-93fe-c1d8f6dcc2f8 created. It took 2005ms.
Getting ConnectionImpl(id=1a79fcdf-bd5e-4849-93fe-c1d8f6dcc2f8)

Connection released and ready to be reused ConnectionImpl(id=27ae7ee3-7171-4be0-853b-09de034824cc)
Getting ConnectionImpl(id=27ae7ee3-7171-4be0-853b-09de034824cc)

Connection released and ready to be reused ConnectionImpl(id=cb7b87cc-6dee-4c78-9ed0-86b81109d3b0)
Connection released and ready to be reused ConnectionImpl(id=1a79fcdf-bd5e-4849-93fe-c1d8f6dcc2f8)
Getting ConnectionImpl(id=cb7b87cc-6dee-4c78-9ed0-86b81109d3b0)

Getting ConnectionImpl(id=1a79fcdf-bd5e-4849-93fe-c1d8f6dcc2f8)

New connection with id=52de5399-29ce-4ab7-bd21-e0098c64ab18 created. It took 2003ms.
Getting ConnectionImpl(id=52de5399-29ce-4ab7-bd21-e0098c64ab18)

New connection with id=072c0273-9a7a-4a3f-b85c-ccb0480164f4 created. It took 2003ms.
Getting ConnectionImpl(id=072c0273-9a7a-4a3f-b85c-ccb0480164f4)

java.lang.Exception: No available connections! Please, try again later!
	at com.smdev.creational.object_pool.example_1.ConnectionPool.getConnection(ConnectionPool.java:39)
	at com.smdev.creational.object_pool.example_1._Main.main(_Main.java:23)
```