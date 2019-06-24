package com.smdev.gof.behavioral.template_method.example_1.domain;

import com.smdev.gof.behavioral.template_method.example_1.model.EntityB;

public class ServiceB extends AbstractService<EntityB> {

    @Override
    protected EntityB createEntityInstance() {
        return new EntityB();
    }

    @Override
    protected boolean isEntityValid() {
        System.out.println("Validating " + getEntity().getClass().getSimpleName());
        return true;
    }
}
