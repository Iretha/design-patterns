package com.smdev.gof.behavioral.mediator.example_1;

public interface Mediator {

    boolean connect(String account, Colleague colleague);

    boolean disconnect(String account, Colleague colleague);

    void synchronize(String account);

}
