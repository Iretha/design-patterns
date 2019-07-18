package com.smdev.creational.object_pool.example_1;

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
