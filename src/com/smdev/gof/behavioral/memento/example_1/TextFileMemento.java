package com.smdev.gof.behavioral.memento.example_1;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

/**
 * The Memento Object - should be immutable
 */
@ToString
public class TextFileMemento {

    private int serialNo;

    @Getter
    private String id;

    @Getter
    private String name;

    @Getter
    private String content;

    private String commitMessage;
    private Date commitTime;

    public TextFileMemento(int serialNo, String id, String name, String content, String commitMessage) {
        this.serialNo = serialNo;

        this.id = id;
        this.name = name;
        this.content = content;

        this.commitMessage = commitMessage;
        this.commitTime = new Date();
    }
}
