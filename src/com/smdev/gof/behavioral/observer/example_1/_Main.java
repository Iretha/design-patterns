package com.smdev.gof.behavioral.observer.example_1;

public class _Main {

    public static void main(String[] args) {
        Publisher publisher = new Publisher();

        Subscriber jonShow = new Subscriber("Jon Show");
        Subscriber violet = new Subscriber("Violet Dean");
        Subscriber peter = new Subscriber("Peter Dow");

        Topic sports = publisher.findTopic("sports");
        sports.subscribe(jonShow);

        Topic hiTech = publisher.findTopic("hi-tech");
        hiTech.subscribe(jonShow);
        hiTech.subscribe(violet);
        hiTech.subscribe(peter);

        publisher.publishUpdate(new Message(sports.getLabel(), "New Volleyball Star", "More info..."));
        publisher.publishUpdate(new Message(hiTech.getLabel(), "AI arises", "Super smart AI invented..."));

        hiTech.unsubscribe(peter);
        publisher.publishUpdate(new Message(hiTech.getLabel(), "The smallest chip ever", "Read further..."));
    }
}
