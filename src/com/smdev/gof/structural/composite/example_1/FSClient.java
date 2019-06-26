package com.smdev.gof.structural.composite.example_1;

public class FSClient {

    public static void ls(Entry entry){
        entry.ls(false);
    }

    public static void lsDeep(Entry entry){
        entry.ls(true);
    }
}
