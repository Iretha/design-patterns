package com.smdev.gof.behavioral.visitor.example_1;

public class _Main {

    public static void main(String[] args) {
        // calculate the area of a single shape
        (new Circle(3)).accept(new VisitorAreaCalcImpl());

        // print a single shape
        (new Rectangle(12, 4)).accept(new VisitorPrintImpl());

        // use the complex shape to calculate the total area
        ComplexShape c1 = new ComplexShape();
        c1.add(new Circle(3));
        c1.add(new Circle(6));
        c1.add(new Circle(9));
        c1.add(new Rectangle(12, 2));
        c1.add(new Hexagon(2));
        c1.calcTotalArea();
    }
}
