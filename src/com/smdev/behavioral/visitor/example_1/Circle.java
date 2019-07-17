package com.smdev.behavioral.visitor.example_1;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Circle implements VisitableAreaCalc {

    @Getter
    private  double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double accept(VisitorAreaCalc visitor) {
        return visitor.visit(this);
    }
}
