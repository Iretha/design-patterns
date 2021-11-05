package com.smdev.task.task0002.parkinglot.domain.parking;

public class Exit extends BaseParkingFloorObject {

    public Exit(int no, ParkingFloor floor) {
        super(no, floor);

        floor.addExit(this);
    }
}
