package com.smdev.behavioral.observer.example_1;

import java.util.HashMap;
import java.util.Map;

/**
 * The Initiator of the updates/ notifications
 */
public class Publisher {

    private Map<String, Topic> topics = new HashMap<>();

    public void publishUpdate(Message message) {
        findTopic(message.getTopic()).notifySubscribers(message);
    }

    public Topic findTopic(String topicLabel) {
        if (this.topics.get(topicLabel) == null) {
            this.topics.put(topicLabel, new Topic(topicLabel));
        }
        return this.topics.get(topicLabel);
    }
}
