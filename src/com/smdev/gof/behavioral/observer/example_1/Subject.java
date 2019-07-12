package com.smdev.gof.behavioral.observer.example_1;

import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

/**
 * The Abstract Subject
 */
@ToString
public abstract class Subject {

    private Set<Observer> subscribers = new HashSet<>();

    @Getter
    private String label;

    public Subject(String label) {
        this.label = label;
    }

    void subscribe(Observer observer) {
        this.subscribers.add(observer);
    }

    void unsubscribe(Observer observer) {
        this.subscribers.remove(observer);
    }

    void notifySubscribers(Message message) {
        System.out.println("======= " + this.label + "=====");
        for (Observer observer : this.subscribers) {
            observer.receive(message);
        }

        System.out.println();
    }
}
