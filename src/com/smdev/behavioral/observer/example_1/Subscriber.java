package com.smdev.behavioral.observer.example_1;

/**
 * The Concrete Observer
 */
public class Subscriber implements Observer {

    private String username;

    public Subscriber(String username) {
        this.username = username;
    }

    @Override
    public void receive(Message message) {
        System.out.println("< New message received by " + this.username + " > " + message);
    }
}
