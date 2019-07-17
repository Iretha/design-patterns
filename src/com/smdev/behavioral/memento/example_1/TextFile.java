package com.smdev.behavioral.memento.example_1;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

/**
 * The Originator
 */
@ToString
public class TextFile {

    @Getter
    private String id;

    @Getter
    private String name;

    @Getter
    private String content;

    public TextFile(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public TextFileMemento createSnapShot(int serialNo, String commitMessage){
        return new TextFileMemento(serialNo, this.id, this.name, this.content, commitMessage);
    }

    public void writeContent(String updatedContent) {
        this.content = updatedContent;
    }
}
