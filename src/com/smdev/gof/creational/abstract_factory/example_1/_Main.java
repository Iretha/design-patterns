package com.smdev.gof.creational.abstract_factory.example_1;

public class _Main {

    public static void main(String[] args) {
        initApp("Android");
        initApp("Swift");
    }

    private static void initApp(String type){
        UIFactory factory = FactoryMaker.getFactory(type);

        InputText input = factory.createInput();
        input.setValue("Hello, " + type);

        Button button = factory.createButton();
        button.click();

        input.submit();
    }
}
