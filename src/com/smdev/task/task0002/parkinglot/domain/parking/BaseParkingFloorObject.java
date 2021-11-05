package com.smdev.task.task0002.parkinglot.domain.parking;

import com.smdev.task.task0002.parkinglot.domain.display.Displayable;
import lombok.Getter;
import lombok.ToString;

@ToString
public abstract class BaseParkingFloorObject extends BaseParkingObject implements Displayable {

    @Getter
    private ParkingFloor floor;

    public BaseParkingFloorObject(int no, ParkingFloor floor) {
        super(no);
        this.floor = floor;
    }

    @Override
    public String displayInfo() {
        return this.toString();
    }
}
