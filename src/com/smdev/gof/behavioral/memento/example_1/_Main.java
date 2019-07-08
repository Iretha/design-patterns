package com.smdev.gof.behavioral.memento.example_1;

public class _Main {

    public static void main(String[] args) {
        TextFile file1 = new TextFile("Draft1.txt");
        file1.writeContent("This is my initial content");

        TextFileVersionManager.commit(file1, "My initial version");
        TextFileVersionManager.listCommits(file1);

        file1.writeContent("Content Modified 2");
        TextFileVersionManager.commit(file1, "Version 2");
        TextFileVersionManager.listCommits(file1);

        TextFileVersionManager.restore(file1, 0);
        TextFileVersionManager.listCommits(file1);

        System.out.println("Current state of the file: " + file1);
    }
}
