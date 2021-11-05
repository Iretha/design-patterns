package com.smdev.task.task0002.parkinglot.domain.parking;

public class Entrance extends BaseParkingFloorObject {

    public Entrance(int no, ParkingFloor floor) {
        super(no, floor);

        floor.addEntrance(this);
    }
}
