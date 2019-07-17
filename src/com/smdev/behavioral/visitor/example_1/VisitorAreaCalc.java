package com.smdev.behavioral.visitor.example_1;

public interface VisitorAreaCalc {

    double visit(Rectangle shape);

    double visit(Circle shape);

    double visit(Hexagon shape);
}
