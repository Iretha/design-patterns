package com.smdev.gof.behavioral.template_method.example_1;

public class MiniResumeTemplate extends AbstractDocumentTemplate {

    private static final String RESUME_TEMPLATE = "Hello,\nMy name is ___!\nI have more than ___ years experience in ___.";

    @Override
    protected String getTemplateText() {
        return RESUME_TEMPLATE;
    }

}
