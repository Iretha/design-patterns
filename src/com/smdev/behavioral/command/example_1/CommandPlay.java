package com.smdev.behavioral.command.example_1;

public class CommandPlay extends AbstractCommand {

    public CommandPlay(Device device) {
        super(device);
    }

    @Override
    public void execute() {
        getDevice().play();
    }
}
