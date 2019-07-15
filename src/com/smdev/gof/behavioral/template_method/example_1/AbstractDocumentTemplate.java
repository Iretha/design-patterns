package com.smdev.gof.behavioral.template_method.example_1;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public abstract class AbstractDocumentTemplate {

    protected static final String BLANK = "___";

    public AbstractDocumentTemplate() {
        System.out.println("\n" + getClass().getSimpleName() +
                ": Please, fill in the blanks for the following template: \n" + getTemplateText());
    }

    public void export(String... inputValues) {
        try {
            prepare(inputValues);

            String templateText = getTemplateText();

            validateInput(templateText, inputValues);

            String fullText = fillBlanks(templateText, inputValues);

            writeOutput(fullText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cannot be modified or skipped by subclasses
     * @param inputValues
     */
    private void prepare(String... inputValues){
        System.out.println("\nExporting with input values: " + Arrays.toString(inputValues));
    }

    /**
     * Must be implemented by each subclass, because it has no default implementation
     * @return the template
     */
    protected abstract String getTemplateText();

    /**
     * Has default implementation, but might be overridden by the subclasses
     *
     * @param templateText
     * @param inputValues
     * @throws Exception
     */
    protected void validateInput(String templateText, String... inputValues) throws Exception {
        if (inputValues == null) {
            throw new Exception("Please, fill in blanks.");
        }

        int blanksCount = StringUtils.countMatches(templateText, BLANK);
        if (blanksCount != inputValues.length) {
            throw new Exception("Please, fill in ALL blanks.");
        }
    }

    /**
     * Could not be overridden by subclasses, because it's final!
     * @param templateText
     * @param inputValues
     * @return
     */
    protected final String fillBlanks(String templateText, String... inputValues) {
        String fulltext = templateText;
        for (String value : inputValues) {
            fulltext = StringUtils.replaceOnce(fulltext, BLANK, value);
        }
        return fulltext;
    }

    /**
     * Has default implementation, but might be overridden by the subclasses
     * @param fullText
     */
    protected void writeOutput(String fullText) {
        System.out.println("Your Document:" + fullText);
    }

}
