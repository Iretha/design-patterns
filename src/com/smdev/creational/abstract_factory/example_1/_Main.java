package com.smdev.creational.abstract_factory.example_1;

public class _Main {

    public static void main(String[] args) {
        initApp(Platform.ANDROID);
        initApp(Platform.SWIFT);
    }

    private static void initApp(Platform platform) {
        UIFactory factory = platform.getFactory();

        InputText input = factory.createInput();
        input.setValue("Hello, " + platform.name());

        Button button = factory.createButton();
        button.click();

        input.submit();
    }
}
