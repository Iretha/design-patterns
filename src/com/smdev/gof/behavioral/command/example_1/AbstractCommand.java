package com.smdev.gof.behavioral.command.example_1;

import lombok.Getter;

public abstract class AbstractCommand implements Command{

    @Getter
    private Device device;

    public AbstractCommand(Device device) {
        this.device = device;
    }
}
