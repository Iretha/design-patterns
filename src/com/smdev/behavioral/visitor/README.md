---
layout: default
title: Visitor (GoF)
parent: Behavioral Design Patterns
nav_order: 3100
permalink: /behavioral/visitor
---

# The Visitor Design Pattern 

The Visitor Design Pattern is designed to separate the algorithm from the object and to apply it to a group of similar types.
{: .fs-6 .fw-300 }

--- 

## What problems does it solve?
It provides a mechanism to separate the algorithm from the object and to apply it to many different types.

Glossary:
- Visitor - an interfaces or abstract class to define the visitor behavior (the visit methods)
- Concrete Visitor - implements the concrete operations/ algorithms
- Visitable - declares the behavior of the classes that can be "visited", acts as an entry point for visitors
- Concrete Visitable - the concrete classes should implement the behavior when "visited" by the visitor
- Object Structure - a collection or a complex structure, that contains the "elements" that can be visited

You can use it with a collection/ complex structure or without.

## Pros:
- you can define new operation without changing current implementation of the classes
- you can add new operation without changing the code of the class hierarchy
- algorithm implementation will be externalized and more reusable
- implements the open/closed principle of the SOLID design principles

## Cons:
- you should know the return type in advance
- hard to modify the return type later

**One Type that may apply many algorithms = Strategy**
**Many Types that can apply many algorithms = Visitor**

## How to recognize it?
If there are two different abstract/interface types, which have methods and the one actually calls 
the method of the other to execute the desired strategy on it.
```java
// use the complex shape to calculate the total area
ComplexShape c1 = new ComplexShape();
c1.add(new Circle(3));
c1.add(new Circle(6));
c1.add(new Circle(9));
c1.add(new Rectangle(12, 2));
c1.add(new Hexagon(2));
c1.calcTotalArea();
```

## Scenarios
* When you have to perform the same operation on multiple classes, that build a complex structure, but you could
not implement it in the parent class, because not all of the subclasses support it.
In this case, you can extract the operation/ algorithm in external class and reuse it, where applicable.
* When you don't want to add a method to a parent class

## Examples from Java API
```
javax.lang.model.element.AnnotationValue and AnnotationValueVisitor
javax.lang.model.element.Element and ElementVisitor
javax.lang.model.type.TypeMirror and TypeVisitor
java.nio.file.FileVisitor and SimpleFileVisitor
javax.faces.component.visit.VisitContext and VisitCallback
```
## Scenarios
* If you want to apply same operation on all elements of some aggregate structure, 
but elements might be of different types and the algorithm may vary for the concrete types
* If many distinct and unrelated operations need to be performed on objects in an object structure
* To define new operations over the elements of a structure, but you don't want to modify the existing classes

### Example 1
We need an app that can calculate the area of dynamic complex shapes, built from other "simple" shapes. 
Later, we will extend the functionality and some of the shapes will do specific operations like "print".

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/behavioral/visitor) 

1). Create an visitable interface for the Area Calculator, that knows how to "accept" visitors ot this type
```java
public interface VisitablePrint {

    void accept(VisitorPrint visitor);
}
```
2). Create the "simple" shapes we are going to support and implement the interface

2.1).Circle
```java
@ToString
public class Circle implements VisitableAreaCalc {

    @Getter
    private  double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double accept(VisitorAreaCalc visitor) {
        return visitor.visit(this);
    }
}
```
2.2).Hexagon
```java
@ToString
public class Hexagon implements VisitableAreaCalc {

    @Getter
    private int side;

    public Hexagon(int side) {
        this.side = side;
    }

    @Override
    public double accept(VisitorAreaCalc visitor) {
        return visitor.visit(this);
    }
}
```
2.3). Rectangle
```java
@ToString
public class Rectangle implements VisitableAreaCalc {

    @Getter
    private int width;

    @Getter
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double accept(VisitorAreaCalc visitor) {
        return visitor.visit(this);
    }

    public void accept(VisitorPrint visitor) {
        visitor.visit(this);
    }
}
```
3). Add the implementation of the Area Calculator Visitor - this is the implementation of the algorithms, extracted from the classes
```java
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
```
4). Let's extend the functionality and add a visitor for printing the shape
```java
public interface VisitorPrint {

    void visit(Rectangle rectangle);
}
```
5). Add the visitable interface for printing
```java
public interface VisitablePrint {

    void accept(VisitorPrint visitor);
}
```
6). Implement the visitable for a specific shape
```java
@ToString
public class Rectangle implements VisitableAreaCalc, VisitablePrint {

    @Getter
    private int width;

    @Getter
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double accept(VisitorAreaCalc visitor) {
        return visitor.visit(this);
    }

    public void accept(VisitorPrint visitor) {
        visitor.visit(this);
    }
}
```
7). Implement the print mechanism
```java
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
```
8). Create a complex shape
```java
public class ComplexShape {

    private List<VisitableAreaCalc> items = new ArrayList<>();

    public void add(VisitableAreaCalc shape) {
        this.items.add(shape);
    }

    public void calcTotalArea() {
        VisitorAreaCalc areaCalculator = new VisitorAreaCalcImpl();

        double area = 0;
        for (VisitableAreaCalc shape : this.items) {
            area += shape.accept(areaCalculator);
        }

        System.out.println("Total area: " + area);
    }
}
```
9). Create a demo class
```java
public class _Main {

    public static void main(String[] args) {
        // calculate the area of a single shape
        (new Circle(3)).accept(new VisitorAreaCalcImpl());

        // print a single shape
        (new Rectangle(12, 4)).accept(new VisitorPrintImpl());

        // use the complex shape to calculate the total area
        ComplexShape c1 = new ComplexShape();
        c1.add(new Circle(3));
        c1.add(new Circle(6));
        c1.add(new Circle(9));
        c1.add(new Rectangle(12, 2));
        c1.add(new Hexagon(2));
        c1.calcTotalArea();
    }
}
```
Output:
```java
Area of Circle(radius=3.0) is 28.274333882308138
************
************
************
************
Area of Circle(radius=3.0) is 28.274333882308138
Area of Circle(radius=6.0) is 113.09733552923255
Area of Circle(radius=9.0) is 254.46900494077323
Area of Rectangle(width=12, height=2) is 24.0
Area of Hexagon(side=2) is 10.392304845413264
Total area: 430.2329791977272
```

