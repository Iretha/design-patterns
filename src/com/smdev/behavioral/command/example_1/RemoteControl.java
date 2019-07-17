package com.smdev.behavioral.command.example_1;

public class RemoteControl {

    private Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    public void pressButton(String button) {
        Command command = null;
        switch (button) {
            case "Play":
                command = new CommandPlay(this.device);
                break;
            case "Stop":
                command = new CommandStop(this.device);
                break;
            default:
                break;
        }

        if (command != null) {
            command.execute();
        }
    }
}
