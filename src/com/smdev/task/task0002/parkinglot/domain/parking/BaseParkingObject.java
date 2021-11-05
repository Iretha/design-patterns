package com.smdev.task.task0002.parkinglot.domain.parking;

import lombok.Getter;

public class BaseParkingObject {

    @Getter
    private int no;

    public BaseParkingObject(int no) {
        this.no = no;
    }
}
