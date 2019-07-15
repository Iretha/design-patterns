package com.smdev.gof.behavioral.template_method.example_1;

public class ShortStoryTemplate extends AbstractDocumentTemplate {

    private static final String TEMPLATE = "Once upon a time, there was a ___ ___ ___ next to a ___. Suddenly...";

    @Override
    protected String getTemplateText() {
        return TEMPLATE;
    }

    @Override
    protected void writeOutput(String fullText) {
        System.out.println("Your Short Story:" + fullText);
    }
}
