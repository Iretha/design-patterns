package com.smdev.gof.behavioral.template_method.example_1.domain;

import com.smdev.gof.behavioral.template_method.example_1.model.AbstractEntity;

public abstract class AbstractService<E extends AbstractEntity> {

    private E entity;

    public AbstractService() {
        System.out.println("New instance of " + getClass().getSimpleName() + " created!");
        this.entity = createEntityInstance();
    }

    public void persist() {
        if (isEntityValid()) {
            System.out.println(getClass().getSimpleName() + " persists " + getEntity().getClass().getSimpleName());
        }
    }

    protected abstract E createEntityInstance();

    protected abstract boolean isEntityValid();

    protected E getEntity() {
        return this.entity;
    }
}
