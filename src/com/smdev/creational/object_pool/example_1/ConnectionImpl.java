package com.smdev.creational.object_pool.example_1;

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
