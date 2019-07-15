package com.smdev.gof.behavioral.visitor.example_1;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Hexagon implements VisitableAreaCalc {

    @Getter
    private int side;

    public Hexagon(int side) {
        this.side = side;
    }

    @Override
    public double accept(VisitorAreaCalc visitor) {
        return visitor.visit(this);
    }
}
