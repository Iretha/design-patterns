package com.smdev.gof.behavioral.visitor.example_1;

public interface VisitableAreaCalc {

    double accept(VisitorAreaCalc visitor);
}
