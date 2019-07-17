package com.smdev.behavioral.mediator.example_1;

public interface Mediator {

    void connect(String account, Colleague colleague);

    void disconnect(String account, Colleague colleague);

    void synchronize(String account);

}
