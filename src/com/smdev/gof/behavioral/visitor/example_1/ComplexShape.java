package com.smdev.gof.behavioral.visitor.example_1;

import java.util.ArrayList;
import java.util.List;

public class ComplexShape {

    private List<VisitableAreaCalc> items = new ArrayList<>();

    public void add(VisitableAreaCalc shape) {
        this.items.add(shape);
    }

    public void calcTotalArea() {
        VisitorAreaCalc areaCalculator = new VisitorAreaCalcImpl();

        double area = 0;
        for (VisitableAreaCalc shape : this.items) {
            area += shape.accept(areaCalculator);
        }

        System.out.println("Total area: " + area);
    }
}
