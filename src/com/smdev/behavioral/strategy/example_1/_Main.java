package com.smdev.behavioral.strategy.example_1;

public class _Main {

    public static void main(String[] args) {
        Chat chat = new Chat(new SendStrategySms("123", "456"));
        chat.send(new Message("Hello!"));
        chat.send(new Message("Can you talk?"));
        chat.send(new Message("Let's talk in FB!"));

        chat.changeStrategy(new SendStrategyFacebook("Jon Snow", "Jane Dow"));
        chat.send(new Message("Done."));
        chat.send(new Message("Do you have the files? I'll send them to you via email."));

        chat.changeStrategy(new SendStrategyEmail("JonSnow@gmail.com", "JaneDow@gmail.com"));
        chat.send(new Message("Here are the files as I promised"));

        chat.changeStrategy(new SendStrategyFacebook("Jon Snow", "Jane Dow"));
        chat.send(new Message("Files sent, check your email"));
    }
}
