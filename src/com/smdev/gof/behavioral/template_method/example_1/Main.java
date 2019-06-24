package com.smdev.gof.behavioral.template_method.example_1;

import com.smdev.gof.behavioral.template_method.example_1.domain.ServiceA;

public class Main {

    public static void main(String[] args) {
        ServiceA serviceA = new ServiceA();
        serviceA.persist();
    }
}
