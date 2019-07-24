---
layout: default
title: Composite (GoF)
parent: Structural Design Patterns
nav_order: 2020
permalink: /structural/composite
---

# The Composite Design Pattern

The Composite Pattern is designed to build a class hierarchy from primitive and composite objects, that may represent complex structures, but have similar behavior.
{: .fs-6 .fw-300 }

---

## What problems does it solve? 
The pattern is used to implement a tree-like, hierarchical structure of elements, that have same behavior. The elements can be
primitive (leafs) or composite objects. Composite objects have children, which might be leafs or composite objects.

Glossary:
- Composition - the abstraction of the composites and the leafs, their common functionality
- Leaf - primitive objects in the composition - they implement the composition, but doesn't have children
- Composite - composite objects in the composition - they implement the child behavior, bu also have children of type Composition
- Client - The client operates with the compositions in a general manner. In fact the client doesn't even know if it's a leaf or composite.

## Pros:
- Client knows only about the general behavior off all components and treats them the same way.
- to decouple client
- to achieve good level of abstraction

## Cons:
- It is hard to restrict the components of the composite

## How to recognize it?
When you have behavioral methods, that take an instance of same abstract/interface type into 
and create or process a tree structure.
```java
Folder homeDir = new Folder("Home");

Folder documentsDir = new Folder("Personal Documents");
homeDir.addChild(documentsDir);

documentsDir.addChild(new File("CV.doc"));
```
## Examples from Java API
```
java.awt.Container#add(Component) (practically all over Swing thus)
javax.faces.component.UIComponent#getChildren() (practically all over JSF UI thus)
```
## Scenarios

* Use it for a tree-like structure, when all elements have some general behavior

* Like a FileSystem, where you have tree of folders and files and you can move them, delete the etc (general behavior).

### Example 1

We have hierarchical folder structure, that may contain other folders in files.

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/structural/composite) 

1). Entry Interface (our Composition)
```java
public interface Entry {

    String getName();

    void setName(String name);

    void ls(boolean recursive);
}
```
2). File Class (our Leaf or Primitive object)
```java
public class File implements Entry {

    @Getter
    @Setter
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void ls(boolean recursive) {
        System.out.println(getName());
    }

}
```
3). Folder Class (our Composite object, that extends the primitive behavior, but also has children)
```java
public class Folder extends File {

    private List<Entry> children = new ArrayList<>();

    public Folder(String name) {
        super(name);
    }

    public void addChild(Entry child) {
        this.children.add(child);
        child.setName(getName() + "\\" + child.getName());
    }

    @Override
    public void ls(boolean recursive) {
        System.out.println(getName());
        for (Entry c : this.children) {
            if(recursive){
                c.ls(true);
            }else{
                System.out.println(c.getName());
            }
        }
    }
}
```
4). FileSystemClient class (our client)
```java
public class FSClient {

    public static void ls(Entry entry){
        entry.ls(false);
    }

    public static void lsDeep(Entry entry){
        entry.ls(true);
    }
}
```
5. Main class
```java
public class _Main {

    public static void main(String[] args) {

        Folder homeDir = new Folder("Home");

        Folder documentsDir = new Folder("Personal Documents");
        homeDir.addChild(documentsDir);

        documentsDir.addChild(new File("CV.doc"));
        documentsDir.addChild(new File("Diplom.pdf"));
        documentsDir.addChild(new File("ID_Photo.png"));

        Folder examsDir = new Folder("Exams");
        documentsDir.addChild(examsDir);

        Folder examAEDir = new Folder("AE");
        examsDir.addChild(examAEDir);
        examAEDir.addChild(new File("AE_Book.pdf"));
        examAEDir.addChild(new File("AE_Sample_Tests.pdf"));

        Folder examDEDir = new Folder("DE");
        examsDir.addChild(examDEDir);
        examDEDir.addChild(new File("DE_Book.pdf"));
        examDEDir.addChild(new File("DE_Sample_Tests.pdf"));


        FSClient.lsDeep(homeDir);
    }
}
```
Output:
```
Home
Home\Personal Documents
Home\Personal Documents\CV.doc
Home\Personal Documents\Diplom.pdf
Home\Personal Documents\ID_Photo.png
Home\Personal Documents\Exams
Home\Personal Documents\Exams\AE
Home\Personal Documents\Exams\AE\AE_Book.pdf
Home\Personal Documents\Exams\AE\AE_Sample_Tests.pdf
Home\Personal Documents\Exams\DE
Home\Personal Documents\Exams\DE\DE_Book.pdf
Home\Personal Documents\Exams\DE\DE_Sample_Tests.pdf
```