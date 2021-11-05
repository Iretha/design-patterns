package com.smdev.task.task0002.parkinglot.domain.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingSpace extends BaseParkingFloorObject {

    private ParkingSpaceSize size;

    private List<ParkingSpaceFeature> features;

    private ParkingSpaceStatus status = ParkingSpaceStatus.AVAILABLE;

    public ParkingSpace(int no, ParkingFloor floor, ParkingSpaceSize size, List<ParkingSpaceFeature> features) {
        super(no, floor);
        this.size = size;
        this.features = features != null ? features : new ArrayList<>();

        floor.addParkingSpace(this);
    }

    public ParkingSpace(int no, ParkingFloor floor, ParkingSpaceSize size) {
        this(no, floor, size, null);
    }

    public boolean isAvailable() {
        return ParkingSpaceStatus.AVAILABLE.equals(status);
    }
}
