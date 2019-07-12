package com.smdev.gof.behavioral.strategy.example_1;

public class Chat {

    private SendStrategy strategy;

    public Chat(SendStrategy strategy) {
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
