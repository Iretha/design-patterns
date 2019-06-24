package com.smdev.gof.behavioral.template_method.example_1.domain;

import com.smdev.gof.behavioral.template_method.example_1.model.EntityA;

public class ServiceA extends AbstractService<EntityA> {

    @Override
    protected EntityA createEntityInstance() {
        return new EntityA();
    }

    @Override
    protected boolean isEntityValid() {
        System.out.println("Validating " + getEntity().getClass().getSimpleName());
        return true;
    }
}
