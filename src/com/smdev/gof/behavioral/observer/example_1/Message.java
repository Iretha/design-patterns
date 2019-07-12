package com.smdev.gof.behavioral.observer.example_1;

import lombok.Getter;
import lombok.ToString;

/**
 * The Notification/ Update (Immutable object)
 */
@ToString
public class Message {

    @Getter
    private String topic;

    @Getter
    private String title;

    @Getter
    private String content;

    public Message(String topic, String title, String content) {
        this.topic = topic;
        this.title = title;
        this.content = content;
    }
}
