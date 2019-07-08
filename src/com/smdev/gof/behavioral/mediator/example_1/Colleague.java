package com.smdev.gof.behavioral.mediator.example_1;

import lombok.Getter;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * The Abstract Colleague
 */
public abstract class Colleague {

    @Getter
    private String label;

    private Mediator mediator;

    private String account;

    @Getter
    private Set<String> files = new TreeSet<>();

    @Getter
    private Date filesModifiedOn;

    public Colleague(String label, Mediator mediator) {
        this.label = label;
        this.mediator = mediator;
    }

    public void connect(String account) {
        this.account = account;
        this.mediator.connect(account, this);
    }

    public void disconnect() {
        this.mediator.disconnect(this.account, this);
    }

    public void synchronize(Set<String> updatedFiles) {
        this.files.clear();
        this.files.addAll(updatedFiles);
        this.filesModifiedOn = new Date();

        System.out.println(this.account + ": " + getLabel() + " got synchronized. Files = " + this.files.size());
    }

    public void addFile(String file) {
        this.files.add(file);
        this.filesModifiedOn = new Date();
        System.out.println(this.account + ": " + getLabel() + " => File added. Files = " + this.files.size());

        this.mediator.synchronize(this.account);
    }
}
