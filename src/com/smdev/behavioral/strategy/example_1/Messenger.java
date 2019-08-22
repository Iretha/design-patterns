package com.smdev.behavioral.strategy.example_1;

public class Messenger {

    private SendStrategy strategy;

    public Messenger(SendStrategy strategy) {
        changeStrategy(strategy);
    }

    public void changeStrategy(SendStrategy strategy){
        System.out.println("\n === Strategy changed to: " + strategy);

        this.strategy = strategy;
    }

    public void send(Message message){
        this.strategy.send(message);
    }
}
