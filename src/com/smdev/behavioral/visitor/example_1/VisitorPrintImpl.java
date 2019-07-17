package com.smdev.behavioral.visitor.example_1;

public class VisitorPrintImpl implements VisitorPrint {
    @Override
    public void visit(Rectangle rectangle) {
        for (int h = 0; h < rectangle.getHeight(); h++) {
            for (int w = 0; w < rectangle.getWidth(); w++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
