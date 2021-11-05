package com.smdev.task.task0002.parkinglot.domain.parking;

import com.smdev.task.task0002.parkinglot.domain.display.Displayable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor extends BaseParkingObject implements Displayable {

    private ParkingLot parkingLot;

    @Getter
    private List<Entrance> entrances;

    @Getter
    private List<Exit> exits;

    @Getter
    private List<ParkingSpace> spaces;

    private int available;

    public ParkingFloor(ParkingLot parkingLot, int no) {
        super(no);
        this.parkingLot = parkingLot;
        this.entrances = new ArrayList<>();
        this.exits = new ArrayList<>();
        this.spaces = new ArrayList<>();

        parkingLot.addFloor(this);
    }

    public void addEntrance(Entrance entrance) {
        this.entrances.add(entrance);
    }

    public void addExit(Exit exit) {
        this.exits.add(exit);
    }

    public void addParkingSpace(ParkingSpace parkingSpace) {
        this.spaces.add(parkingSpace);

        if (parkingSpace.isAvailable()) {
            this.available++;
        }

        this.parkingLot.updateInfo();
    }

    public int getCapacity() {
        return this.spaces.size();
    }

    public int getAvailable() {
        return this.available;
    }

    @Override
    public String displayInfo() {
        StringBuilder str = new StringBuilder();
        str.append("Floor No: ").append(getNo()).append("\n")
                .append("Total Floor Capacity: ").append(getCapacity()).append("\n")
                .append("Available: ").append(getAvailable()).append("\n");

        str.append("Entrances: ").append(getEntrances().size()).append("\n");
        getEntrances().forEach(f -> str.append("\t").append(f.displayInfo()));

        str.append("Exists: ").append(getExits().size()).append("\n");
        getExits().forEach(f -> str.append("\t").append(f.displayInfo()));

        str.append("Available Spaces: ").append("\n");
        getSpaces().stream().filter(el -> el.isAvailable()).forEach(f -> str.append("\t").append(f.displayInfo()));

        return str.toString();
    }
}
