package com.smdev.structural.composite.example_1;

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
