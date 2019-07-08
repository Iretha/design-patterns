package com.smdev.gof.behavioral.mediator.example_1;

import java.util.*;

public class MediatorCloud implements Mediator {

    private Map<String, List<Colleague>> connectedDevices = new HashMap<>();

    @Override
    public void connect(String account, Colleague device) {
        List<Colleague> devices = this.connectedDevices.get(account);
        if (devices == null) {
            devices = new ArrayList<>();
            this.connectedDevices.put(account, devices);
        }

        if (!devices.contains(device)) {
            devices.add(device);
            System.out.println(device.getLabel() + " connected to " + account);
        }

        synchronize(account);
    }

    @Override
    public void disconnect(String account, Colleague device) {
        List<Colleague> devices = this.connectedDevices.get(account);
        if (devices != null && devices.contains(device)) {
            devices.remove(device);
            System.out.println(device.getLabel()+ " disconnected from " + account);
        }
    }

    @Override
    public void synchronize(String account) {
        List<Colleague> devices = this.connectedDevices.get(account);
        if (devices == null || devices.size() < 2) {
            return;
        }

        devices.sort(Comparator.comparing(Colleague::getFilesModifiedOn, Comparator.nullsFirst(Comparator.naturalOrder())));

        Colleague lastUpdated = devices.get(devices.size() - 1);
        for (Colleague d : devices) {
            if (!d.equals(lastUpdated)) {
                d.synchronize(lastUpdated.getFiles());
            }
        }
    }
}
