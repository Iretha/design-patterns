package com.smdev.creational.prototype.example_1;

public class _Main {

    public static void main(String[] args) {
        Book bookCurrentEdition = new Book("9784150115647");
        System.out.println("Current Edition: " + bookCurrentEdition);

        try {
            Book nextEdition = bookCurrentEdition.clone();
            System.out.println("Next Edition: " + nextEdition);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
