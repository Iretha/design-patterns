package com.smdev.gof.behavioral.visitor.example_1;

public class VisitorAreaCalcImpl implements VisitorAreaCalc {

    @Override
    public double visit(Rectangle shape) {
        double area = shape.getWidth() * shape.getHeight();
        System.out.println("Area of " + shape + " is " + area);
        return area;
    }

    @Override
    public double visit(Circle shape) {
        double area = Math.PI * Math.pow(shape.getRadius(), 2);
        System.out.println("Area of " + shape + " is " + area);
        return area;
    }

    @Override
    public double visit(Hexagon shape) {
        double area = (3 * Math.sqrt(3) * Math.pow(shape.getSide(), 2)) / 2;
        System.out.println("Area of " + shape + " is " + area);
        return area;
    }
}
