---
layout: default
title: Template Method
parent: Behavioral Design Patterns
nav_order: 3090
permalink: /behavioral/template-method
---


# The Template Method Design Pattern

GoF Design Patterns -> Behavioral Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/gof/behavioral/template_method) 

## What problems does it solve? Why to use it?
It is used to define a skeleton of an algorithm in the superclass ("method stub"), but allows the subclasses to override 
or implement specific parts. The common part will be implemented by the parent to avoid code duplication.


Glossary:
- Abstract Class - the superclass = the host of the algorithm
- Concrete Class - the subclasses = that can modify parts of the algorithm

## When to use it?
To create a "template" or a "stub" of the algorithm = to define it's skeleton.

In you have many classes with almost identical algorithms, you can consider using the template method. In this case, you can implement 
the differences in the concrete classes and leave the skeleton in their parent class. 
It's not very appropriate for complex algorithms or algorithms that may change, because it may get very hard to maintain.

## Pros:
- Avoid code replication

## Cons:
- Later, if you need to change the algorithm only for specific subclasses,  you might be limited by the skeleton
- Hard to follow and maintain, if the algorithm has many steps
- Not appropriate for algorithms that may evolve in different directions for the subclasses
- Often can lead to violation of the Liskov Substitution Principle

## Examples from Java API
Recognizable by behavioral methods which already have a "default" behaviour defined by an abstract type
```
All non-abstract methods of java.io.InputStream, java.io.OutputStream, java.io.Reader and java.io.Writer.
All non-abstract methods of java.util.AbstractList, java.util.AbstractSet and java.util.AbstractMap.
javax.servlet.http.HttpServlet, all the doXXX() methods by default sends a HTTP 405 "Method Not Allowed" error to the response. 
You're free to implement none or any of them.
```
## Examples

### Example 1 - How to implement it?
Let's say we want to create an app that generates different documents. Each document has specific content with blanks in it. 
Everyone, who wants to export a specific document, should first fill in the blanks.
The algorithm that exports the document will have the following steps:
- Validating the input values
- Filling in the blanks with the given values
- Exporting the text without blanks

Let's implement it.
1). Create the abstract class. The skeleton of the algorithm is in the "export" method. 
Some of the steps are "locked" for modification, other are optional and depends on the subclass.
Abstract methods have to be implemented by all subclasses.

```java
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
     * Cannot be modified or skipped by subclasses ("locked for modification")
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
     * Could not be overridden by subclasses, because it's final!  ("locked for modification")
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
```
2). Create the concrete classes

2.1). Mini Resume - the only thing we want to change here is the text itself
```java
public class MiniResumeTemplate extends AbstractDocumentTemplate {

    private static final String RESUME_TEMPLATE = "Hello,\nMy name is ___!\nI have more than ___ years experience in ___.";

    @Override
    protected String getTemplateText() {
        return RESUME_TEMPLATE;
    }

}
```

2.2). Short Story - we have specific text and we want to change the way the output is written (we may store it in DB, return it as a string, write it to a file etc..)
```java
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
```
3). Demo class
```java
public class _Main {

    public static void main(String[] args) {
        MiniResumeTemplate miniResume = new MiniResumeTemplate();
        miniResume.export("Jon Snow", "8", "acting");
        miniResume.export("Jane Dow", "5", "content writing");

        System.out.println("\n----------------------------");

        ShortStoryTemplate shortStory = new ShortStoryTemplate();
        shortStory.export("white", "horse", "standing", "tree");
    }
}
```
Output:
```
MiniResumeTemplate: Please, fill in the blanks for the following template: 
Hello,
My name is ___!
I have more than ___ years experience in ___.

Exporting with input values: [Jon Snow, 8, acting]
Your Document:Hello,
My name is Jon Snow!
I have more than 8 years experience in acting.

Exporting with input values: [Jane Dow, 5, content writing]
Your Document:Hello,
My name is Jane Dow!
I have more than 5 years experience in content writing.

----------------------------

ShortStoryTemplate: Please, fill in the blanks for the following template: 
Once upon a time, there was a ___ ___ ___ next to a ___. Suddenly...

Exporting with input values: [white, horse, standing, tree]
Your Short Story:Once upon a time, there was a white horse standing next to a tree. Suddenly...
```