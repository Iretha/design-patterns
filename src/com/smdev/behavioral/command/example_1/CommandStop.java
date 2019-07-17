package com.smdev.behavioral.command.example_1;

public class CommandStop extends AbstractCommand {

    public CommandStop(Device device) {
        super(device);
    }

    @Override
    public void execute() {
        getDevice().stop();
    }
}
