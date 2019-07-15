package com.smdev.gof.behavioral.visitor.example_1;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Rectangle implements VisitableAreaCalc, VisitablePrint {

    @Getter
    private int width;

    @Getter
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double accept(VisitorAreaCalc visitor) {
        return visitor.visit(this);
    }

    public void accept(VisitorPrint visitor) {
        visitor.visit(this);
    }
}
